package estados;

import elementos.Huerto;
import elementos.Mapa;
import objetos.plantas.Planta_fuego;
import objetos.plantas.Planta_rayo;
import objetos.semillas.Semilla_fuego;
import objetos.semillas.Semilla_rayo;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import static org.newdawn.slick.Input.KEY_SPACE;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import personajes.Enemigo;
import personajes.Jugador;
import personajes.Npc;
import personajes.Personaje;
import services.Colision_Service;
import services.Dialog_Service;
import services.InputCapture_Service;
import services.Inventario_Service;

public class Mazmorra extends BasicGameState {

    private Mapa map;
    private float x, y;

    //PERSONAJE
    private Jugador ruby;
    private int size_Ruby = 3, ancho_Ruby = 32, largo_Ruby = 32;

    //RATÓN
    private String coordenadas = "", click = "";
    private Image cursor;
    private Circle cursor_hitbox;

    //HITBOX
    private boolean ver_hitbox;

    private StateBasedGame game;

    private float mov[] = new float[2]; //Movimiento entre cada update

    private float gcWidth, gcHeight;

    //Combate
    private Personaje combatiente = null;
    private boolean finMazmorra = false;

    //Musica
    private Music music;

    private boolean hablando = false;
    private boolean inventario = false;
    private boolean hablado = false;
    
    private boolean dia_nuevo;
    
    private Npc npc;
    private boolean hablando_npc=false;

    private int n_dialogo = 0;
    
    private boolean boss_vencido=false;

    public Mazmorra(Jugador ruby, boolean ver_hitbox, boolean dia_nuevo) {
        this.ruby = ruby;
        this.ver_hitbox = ver_hitbox;
        this.dia_nuevo = dia_nuevo;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        gc.setMusicVolume(1 / 10.0f);
        this.gcWidth = gc.getWidth();
        this.gcHeight = gc.getHeight();
        map = new Mapa("./resources/maps/Dungeon" + (int) (Math.random() * 3 + 1) + ".tmx", ruby.getNivel(), new Huerto());
        map.agregarSpawn("SpawnOeste");

        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);
        //Musica
        music = new Music("./resources/music/Mazmorra.ogg");
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics grphcs) throws SlickException {
        map.renderMap(gc, x, y, grphcs, ver_hitbox);
        grphcs.drawString(click, gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
        grphcs.drawString(coordenadas, 35, 35);

        //Jugador
        ruby.renderPersonaje(gc, mov[0], mov[1]);
        if (ver_hitbox) {
            grphcs.drawRect(ruby.getHitbox().getRectangulo().getX(), ruby.getHitbox().getRectangulo().getY(), ruby.getHitbox().getRectangulo().getWidth(), ruby.getHitbox().getRectangulo().getHeight());
        }

        if (hablando) {
            Dialog_Service.mostrarBocadillo(gc, grphcs, combatiente.getDialogos().get(n_dialogo), combatiente.getImagen());
        }
        
        if(hablando_npc) {
            Dialog_Service.mostrarBocadillo(gc, grphcs, npc.getDialogos().get(n_dialogo), npc.getImagen());
        }

        if (inventario) {
            Inventario_Service.mostrarInventario(grphcs, ruby);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
        if (ruby.getVida() > 0) {
            if (!hablando && !inventario && !hablando_npc) {
                if (finMazmorra) {
                    dia_nuevo = true;
                    ((Casa) game.getState(1)).init(gc, game);
                    ruby.setVida(100);
                    ruby.setNivel(ruby.getNivel() + 1);
                    music.stop();
                    game.enterState(1, new FadeOutTransition(), new FadeInTransition());
                } else {
                    cursor_hitbox.setX(gc.getInput().getMouseX() - (cursor_hitbox.getHeight() / 2));
                    cursor_hitbox.setY(gc.getInput().getMouseY() - (cursor_hitbox.getWidth() / 2));

                    //Captura movimiento Ruby
                    mov = InputCapture_Service.capturaMovimiento(gc, i);
                    x += mov[0];    // Agregamos movimiento sobre el ejeX
                    y += mov[1];    // Agregamos movimiento sobre el ejeY

                    //Actualización de elementos del mapa
                    map.actualizarElementos(mov[0], mov[1]);

                    //Colision con muros
                    float movColision[] = Colision_Service.colisionMuros(ruby, map, gc, i, mov[0], mov[1]);
                    x += movColision[0];
                    y += movColision[1];

                    //Movimiento enemigos
                    map.movimientoEnemigos(i, gc, ruby.getHitbox());

                    //MOVIMENTO DEL RATÓN
                    //coordenadas = "(" + gc.getInput().getMouseX() + "," + gc.getInput().getMouseY() + ")";
                    //Comprobacion de salto de escenario
                    Prueba p = (Prueba) game.getState(4);
                    switch (Colision_Service.saltoMapa(ruby, map)) {
                        case "SpawnOeste":
                            ((Casa) game.getState(1)).posicinarEnSpawnARuby("SpawnEste", 100, 0);
                            ruby.setVida(100);
                            music.stop();
                            game.enterState(1, new FadeOutTransition(), new FadeInTransition());
                            break;
                        case "SpawnEste":

                            break;
                        default:
                    }
                    
                    if (InputCapture_Service.clickNpc(gc, map, cursor_hitbox, ruby)!=null && boss_vencido){
                        npc = InputCapture_Service.clickNpc(gc, map, cursor_hitbox, ruby);
                        hablando_npc=true;
                    }
                    
                    if ((combatiente = Colision_Service.colisionCombate(ruby, map, gc)) != null) {
                        if (combatiente.getNombre().equals("Boss") && !hablado) {
                            hablando = true;
                        } else {
                            Combate combate = (Combate) game.getState(3);
                            combate.setEstadoAnterior(getID());
                            combate.setCombatiente(combatiente);
                            game.enterState(3, new FadeOutTransition(), new FadeInTransition()); //COMBATE
                            game.getCurrentState().leave(gc, game);
                        }
                    }
                }
            } else if (hablando) {
                if (combatiente.getDialogos().get(n_dialogo).isCont_hablando()) {
                    if (gc.getInput().isMousePressed(0)) {
                        n_dialogo++;
                    }
                } else {
                    if (gc.getInput().isMousePressed(0)) {
                        hablando = false;
                        hablado = true;
                    }
                }
            } else if(hablando_npc && gc.getInput().isKeyPressed(KEY_SPACE)) {
                if (npc.getDialogos().get(n_dialogo).isCont_hablando()) {
                    n_dialogo++;
                }else{
                    finMazmorra = true;
                    hablando_npc=false;
                }
            }
        } else {  //Ruby a muerto en combate
            ((Casa) game.getState(1)).init(gc, game);
            ruby.setDinero(ruby.getDinero() - 50);
            music.stop();
            game.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            ((Menu) game.getState(0)).setEstadoAnterior(getID());
            game.enterState(0); //MENU
        } else if (key == Input.KEY_F12) {
            ver_hitbox = !ver_hitbox;   //VER HITBOX
        }
        if (!hablando && key == Input.KEY_ESCAPE) {
            inventario = !inventario;
        }
        if (hablando) {
            if (combatiente.getDialogos().get(n_dialogo).isCont_hablando()) {
                if(key == Input.KEY_SPACE){
                    n_dialogo++;
                }
            } else {
                if(key == Input.KEY_SPACE){
                    hablando = false;
                    hablado = true;
                }
            }
        }
    }

    public void posicinarEnSpawnARuby(String spawn, int mov_x, int mov_y) throws SlickException {
        map = new Mapa("./resources/maps/Dungeon" + (int) (Math.random() * 3 + 1) + ".tmx", ruby.getNivel(), new Huerto());
        map.agregarSpawn("SpawnOeste");
        float posSapawnRuby[] = map.getPosicionSpawn(spawn);
        x = +(-(posSapawnRuby[0]) + (gcWidth / 2 + mov_x));
        y = +(-(posSapawnRuby[1]) + (gcHeight / 2) + mov_y);
        map.actualizarElementos(x, y);
        music.loop();
        finMazmorra = false;
    }

    public void combateGanado(boolean ganado) throws SlickException {
        if (ganado) {
            if (map.getEnemigos().contains(combatiente)) {
                map.getEnemigos().remove(combatiente);
                switch (ruby.getNivel()) {
                    case 1:
                        ruby.getInventario().anadirObjeto(new Semilla_fuego(), 1);
                        break;
                    case 2:
                        ruby.getInventario().anadirObjeto(new Semilla_rayo(), 1);
                        break;
                    case 3:
                        ruby.getInventario().anadirObjeto(new Semilla_rayo(), 2);
                        break;
                }
            } else {
                map.getBosses().remove(combatiente);
                switch (ruby.getNivel()) {
                    case 1:
                        ruby.getInventario().anadirObjeto(new Planta_fuego(), 3);
                        break;
                    case 2:
                        ruby.getInventario().anadirObjeto(new Planta_rayo(), 3);
                        break;
                    case 3:
                        ruby.getInventario().anadirObjeto(new Planta_rayo(), 5);
                        break;
                }

                boss_vencido = true;
                n_dialogo=1;
            }
            ruby.setDinero(ruby.getDinero() + combatiente.getDinero());

        }
        combatiente = null;
    }

    public void huidoDeCombate() {
        for (Enemigo e : map.getEnemigos()) {
            e.pararMovimiento();
            e.setCombate(true);
        }

        if (combatiente.getClass().getName().equals("personajes.Boss")) {
            int vectorX = (int) (ruby.getHitbox().getRectangulo().getCenterX() - combatiente.getHitbox().getRectangulo().getCenterX());
            int vectorY = (int) (ruby.getHitbox().getRectangulo().getCenterY() - combatiente.getHitbox().getRectangulo().getCenterY());

            map.actualizarElementos(vectorX * (-1) * 2, vectorY * (-1) * 2);
            x += vectorX * (-1) * 2;    // Agregamos movimiento sobre el ejeX
            y += vectorY * (-1) * 2;    // Agregamos movimiento sobre el ejeY
        }
        combatiente = null;
    }
}
