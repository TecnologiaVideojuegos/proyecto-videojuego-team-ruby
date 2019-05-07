package estados;

import elementos.Hitbox;
import elementos.Mapa;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import personajes.*;
import services.*;

public class Prueba extends BasicGameState {

    private Mapa map;
    private float x, y;

    //PERSONAJE
    private Jugador ruby;

    //RATÓN
    private String coordenadas = "";
    private Image cursor;
    private Circle cursor_hitbox;

    //HITBOX
    private boolean ver_hitbox;

    private StateBasedGame game;
    private GameContainer gc;

    private float mov[] = new float[2]; //Movimiento entre cada update

    private float gcWidth, gcHeight;

    private boolean hablando = false;
    private boolean inventario = false;
    private boolean plantando = false;
    private boolean click = false;

    private Plantar_Service plantar;

    //Combate
    private Personaje combatiente = null;

    public Prueba(Jugador ruby, boolean ver_hitbox) {
        this.ruby = ruby;
        this.ver_hitbox = ver_hitbox;
    }

    /**
     * Inicializa
     */
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        this.gc = gc;
        this.gcWidth = gc.getWidth();
        this.gcHeight = gc.getHeight();
        map = new Mapa("./resources/maps/demo_map.tmx");
        map.agregarSpawn("SpawnSur");
        map.agregarSpawn("SpawnEste");

        //Posicionar a Ruby en un spawn inicial
        float posSapawnRuby[] = map.getPosicionSpawn("SpawnRuby");
        x = -(posSapawnRuby[0]) + (gc.getWidth() / 2);
        y = -(posSapawnRuby[1]) + (gc.getHeight() / 2) + 32;
        map.actualizarElementos(x, y);

        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);
    }

    /**
     * Dibuja
     */
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics grphcs) throws SlickException {

        map.renderMap(gc, x, y, grphcs, ver_hitbox);
        grphcs.drawString(coordenadas, 35, 35);

        //Jugador
        ruby.renderPersonaje(gc, mov[0], mov[1]);
        if (ver_hitbox) {
            grphcs.drawRect(ruby.getHitbox().getRectangulo().getX(), ruby.getHitbox().getRectangulo().getY(), ruby.getHitbox().getRectangulo().getWidth(), ruby.getHitbox().getRectangulo().getHeight());
        }

        if (hablando) {
            Dialog_Service.mostrarBocadillo(gc, grphcs, "Pipo recibe una patada", "Hostia.. Perro", "Pasan los meses....", "Pipo muere", true, false);
        }

        if (inventario) {
            Inventario_Service.mostrarInventario(grphcs, ruby);
        }

        if (plantando) {
            if (plantar.elegirSemilla(grphcs, gc.getInput().getMouseX(), gc.getInput().getMouseY(), ruby.getInventario(), click) != null) {
                plantando = false;
            }
        }

    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
        click = gc.getInput().isMousePressed(0);
        if (ruby.getVida() > 0) {
            if ((!hablando) && (!inventario) && (!plantando)) {
                cursor_hitbox.setX(gc.getInput().getMouseX() - (cursor_hitbox.getHeight() / 2));
                cursor_hitbox.setY(gc.getInput().getMouseY() - (cursor_hitbox.getWidth() / 2));

                if ((combatiente = Colision_Service.colisionCombate(ruby, map, gc)) != null) {
                    Combate combate = (Combate) game.getState(2);
                    combate.setEstadoAnterior(getID());
                    combate.setCombatiente(combatiente);
                    game.enterState(2); //COMBATE
                    game.getCurrentState().leave(gc, game);
                }

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

                //Detección de click sobre huerto
                InputCapture_Service.clickHuerto(gc, map, cursor_hitbox, ruby);
                if (gc.getInput().isMouseButtonDown(0)) {
                    for (Hitbox hitbox_terreno : map.getHuerto()) {
                        if (hitbox_terreno.getRectangulo().intersects(cursor_hitbox)) {
                            plantando = true;
                            plantar = new Plantar_Service(gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
                        }
                    }
                }

                //Detección de click sobre npcs
                if (InputCapture_Service.clickNpc(gc, map, cursor_hitbox, ruby) != null) {
                    hablando = true;
                }

                //MOVIMENTO DEL RATÓN
                coordenadas = "(" + gc.getInput().getMouseX() + "," + gc.getInput().getMouseY() + ")";

                //Comprobacion de salto de escenario
                Prueba_Mazmorra p = (Prueba_Mazmorra) game.getState(4);
                switch (Colision_Service.saltoMapa(ruby, map)) {
                    case "SpawnSur":
                        p.posicinarEnSpawnARuby("SpawnNorte", 0, -100);
                        game.enterState(4);
                        break;
                    case "SpawnEste":
                        p.posicinarEnSpawnARuby("SpawnEste", 100, 0);
                        game.enterState(4);
                        break;
                    default:
                }
            }
            if (hablando && click) {
                hablando = false;
            }
        } else {  //Ruby a muerto en combate
            ((Casa) game.getState(1)).init(gc, game);
            ruby.setVida(100);
            ruby.setDinero(ruby.getDinero() - 50);
            game.enterState(1);
        }
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_F11) {
            game.enterState(5); //DEMO
        }

        if (key == Input.KEY_ESCAPE) {
            ((Menu) game.getState(0)).setEstadoAnterior(getID());
            game.enterState(0); //MENU
        }

        if (!hablando && !plantando) {
            if (key == Input.KEY_E) {
                if (!inventario) {
                    inventario = true;
                } else {
                    inventario = false;
                }
            }
        }
    }

    public void posicinarEnSpawnARuby(String spawn, int mov_x, int mov_y) throws SlickException {
        float posSapawnRuby[] = map.getPosicionSpawn(spawn);
        x = +(-(posSapawnRuby[0]) + (gcWidth / 2 + mov_x));
        y = +(-(posSapawnRuby[1]) + (gcHeight / 2) + mov_y);
        map = new Mapa("./resources/maps/demo_map.tmx");
        map.agregarSpawn("SpawnSur");
        map.agregarSpawn("SpawnEste");
        map.actualizarElementos(x, y);
    }

    public void combateGanado(boolean ganado) throws SlickException {
        if (ganado && combatiente != null) {
            if (map.getEnemigos().contains(combatiente)) {
                map.getEnemigos().remove(combatiente);
            } else {
                map.getBosses().remove(combatiente);
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
