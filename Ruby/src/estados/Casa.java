package estados;

import elementos.Huerto;
import elementos.Mapa;
import objetos.plantas.Planta_agua;
import objetos.plantas.Planta_fuego;
import objetos.semillas.Semilla;
import objetos.semillas.Semilla_agua;
import objetos.semillas.Semilla_fuego;
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
import personajes.Gato;
import personajes.Jugador;
import personajes.Npc;
import personajes.Personaje;
import services.Colision_Service;
import services.Comercio_Service;
import services.Dialog_Service;
import services.InputCapture_Service;
import services.Inventario_Service;
import services.Plantar_Service;

public class Casa extends BasicGameState {

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

    private float mov[] = new float[2]; //Movimiento entre cada update

    private float gcWidth, gcHeight;

    //ACCIONES
    private boolean hablando = false;
    private boolean inventario = false;
    private boolean plantando = false;
    private boolean comerciando = false;

    private Plantar_Service plantar;

    private Semilla semilla = null;
    private Huerto huerto;

    private Npc npc = null;

    private int n_dialogo = 0;

    private Gato gato;
    private boolean huir = false;

    //Combate
    private Personaje combatiente = null;

    //Musica
    private Music music;

    private int tutorial = -1;
    private boolean dia_nuevo;
    private boolean gato_ido;
    private boolean abuela_gato;

    public Casa(Jugador ruby, boolean ver_hitbox, Huerto huerto, boolean dia_nuevo, boolean gato_ido, boolean abuela_gato) {
        this.ruby = ruby;
        this.ver_hitbox = ver_hitbox;
        this.huerto = huerto;
        this.dia_nuevo = dia_nuevo;
        if (dia_nuevo) {
            this.gato_ido = false;
        }
        if (ruby.getNivel() == 1) {
            tutorial = 0;
        } else {
            tutorial = -1;
        }
        if (dia_nuevo) {
            abuela_gato = false;
        } else {
            this.abuela_gato = abuela_gato;
        }
        if (gato_ido) {
            map.setGato(null);
            gato = null;
        }
    }

    /**
     * Inicializa
     */
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        this.gcWidth = gc.getWidth();
        this.gcHeight = gc.getHeight();

        gc.setMusicVolume(1 / 10.0f);

        map = new Mapa("./resources/maps/Granja_test.tmx", ruby.getNivel(), huerto);
        huerto = map.getHuerto();
        map.agregarSpawn("SpawnEste");
        gato = map.getGato();

        //Posicionar a Ruby en un spawn inicial
        float posSapawnRuby[] = map.getPosicionSpawn("SpawnRuby");
        x = -(posSapawnRuby[0]) + (gc.getWidth() / 2);
        y = -(posSapawnRuby[1]) + (gc.getHeight() / 2) + 32;
        map.actualizarElementos(x, y);

        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);

        //Musica
        music = new Music("./resources/music/Ambiente bosque.ogg");
        music.loop();
        
        if(ruby.getVida()>0){
            dia_nuevo=true;
        }
        if(dia_nuevo){
            gato_ido=false;
            huir=false;
            abuela_gato=false;
        }
        System.out.println("tutorial:"+ tutorial +", dia_nuevo:"+ dia_nuevo +", gato_ido:"+ gato_ido +", abuela_gato:"+ abuela_gato +", huir:"+huir);
        dia_nuevo = false;
        
        ruby.setVida(100);
    }

    /**
     * Dibuja
     */
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics grphcs) throws SlickException {

        map.renderMap(gc, x, y, grphcs, ver_hitbox);
        //grphcs.drawString(coordenadas, 35, 35);

        //Jugador
        ruby.renderPersonaje(gc, mov[0], mov[1]);
        if (ver_hitbox) {
            grphcs.drawRect(ruby.getHitbox().getRectangulo().getX(), ruby.getHitbox().getRectangulo().getY(), ruby.getHitbox().getRectangulo().getWidth(), ruby.getHitbox().getRectangulo().getHeight());
        }

        if (hablando) {
            if ((npc.getDialogos().get(n_dialogo).getFrases().get(0).equals("Muy bien abuela") || npc.getDialogos().get(n_dialogo).getFrases().get(0).equals("Buenos días Ruby")) && gato_ido) {
                n_dialogo = n_dialogo + 2;
            }
            if (!huir) {
                Dialog_Service.mostrarBocadillo(gc, grphcs, npc.getDialogos().get(n_dialogo), npc.getImagen());
            }
        }

        if (inventario) {
            Inventario_Service.mostrarInventario(grphcs, ruby);
        }

        if (plantando) {
            semilla = plantar.elegirSemilla(grphcs, gc.getInput().getMouseX(), gc.getInput().getMouseY(), ruby.getInventario(), gc.getInput().isMousePressed(0));
            if (semilla != null) {
                plantar.plantar(gc, map, plantar.getPos_x(), plantar.getPos_y(), ruby, semilla);
                semilla = null;
                plantando = false;
            }
        }

        if (comerciando) {
            Comercio_Service.comerciar(grphcs, ruby, gc.getInput().getMouseX(), gc.getInput().getMouseY(), gc.getInput().isMouseButtonDown(0));
        }

    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
        if (huir && !dia_nuevo) {
            gato.huir();
            if (gato.getHitbox().getRectangulo().getCenterX() > 1500) {
                huir = false;
                gato = null;
                map.setGato(null);
                gato_ido = true;
                System.out.println("gato_ido:"+gato_ido);
            }
        }
        if (hablando && (gc.getInput().isMouseButtonDown(0) || gc.getInput().isKeyPressed(KEY_SPACE))) {
            if (npc.getDialogos().get(n_dialogo).isCont_hablando()) {
                if (npc.getDialogos().get(n_dialogo + 1).getFrases().get(0).equals("Comercio_Service")) {
                    comerciando = true;
                    n_dialogo = 0;
                    hablando = false;
                    npc = null;
                } else if (npc.getDialogos().get(n_dialogo + 1).getFrases().get(0).equals("gato.huir()")) {
                    if (!gato_ido) {
                        huir = true;
                    } else {
                        n_dialogo = n_dialogo + 2;
                    }
                } else if (npc.getDialogos().get(n_dialogo).getFrases().get(0).equals("Toma estas semillas y")) {
                    ruby.getInventario().anadirObjeto(new Semilla_fuego(), 3);
                    ruby.getInventario().anadirObjeto(new Semilla_agua(), 15);
                    n_dialogo++;
                } else if (npc.getDialogos().get(n_dialogo).getFrases().get(0).equals("Toma estas plantas Ruby,")) {
                    ruby.getInventario().anadirObjeto(new Planta_fuego(), 3);
                    ruby.getInventario().anadirObjeto(new Planta_agua(), 15);
                    n_dialogo++;
                } else {
                    n_dialogo++;
                }
            } else {
                hablando = false;
                n_dialogo = 0;
                npc = null;
            }
        }
        if ((!hablando) && (!inventario) && (!plantando) && (!comerciando)) {
            cursor_hitbox.setX(gc.getInput().getMouseX() - (cursor_hitbox.getHeight() / 2));
            cursor_hitbox.setY(gc.getInput().getMouseY() - (cursor_hitbox.getWidth() / 2));

            //Captura movimiento Ruby
            if (!huir) {
                mov = InputCapture_Service.capturaMovimiento(gc, i);
                x += mov[0];    // Agregamos movimiento sobre el ejeX
                y += mov[1];    // Agregamos movimiento sobre el ejeY
            }

            //Actualización de elementos del mapa
            map.actualizarElementos(mov[0], mov[1]);

            //Colision con muros
            float movColision[] = Colision_Service.colisionMuros(ruby, map, gc, i, mov[0], mov[1]);
            x += movColision[0];
            y += movColision[1];

            //Detección de click sobre huerto
            plantando = InputCapture_Service.clickHuerto2(gc, map, i, i, ruby, cursor_hitbox, plantar);
            plantar = InputCapture_Service.clickHuerto1(gc, map, i, i, ruby, cursor_hitbox, plantar);

            //Detección de click sobre npcs
            if (InputCapture_Service.clickNpc(gc, map, cursor_hitbox, ruby) != null && !comerciando && !hablando) {
                hablando = true;
                npc = InputCapture_Service.clickNpc(gc, map, cursor_hitbox, ruby);
                if (npc.getNombre().equals("Abuela")) {
                    if (!abuela_gato && ruby.getNivel() != 1) {
                        n_dialogo = 2;
                    } else if (abuela_gato && ruby.getNivel() != 1) {
                        n_dialogo = 0;
                    } else if (ruby.getNivel() == 1 && tutorial == 0) {
                        n_dialogo = 5;
                        tutorial = 1;
                    } else if (ruby.getNivel() == 1 && tutorial == 1 && !map.getHuerto().isVacio()) {
                        n_dialogo = 12;
                        tutorial = -1;
                    } else if (ruby.getNivel() == 1 && tutorial == -1) {
                        n_dialogo = 0;
                    } else {
                        hablando = false;
                    }
                }
                if (npc.getNombre().equals("Gato")) {
                    n_dialogo = 0;
                }
            }

            //MOVIMENTO DEL RATÓN
            //coordenadas = "(" + gc.getInput().getMouseX() + "," + gc.getInput().getMouseY() + ")";
            //Comprobacion de salto de escenario
            Mazmorra p = (Mazmorra) game.getState(2);
            if (gato_ido && tutorial==-1) {
                switch (Colision_Service.saltoMapa(ruby, map)) {
                    case "SpawnSur":

                        break;
                    case "SpawnEste":
                        p.posicinarEnSpawnARuby("SpawnOeste", -50, 0);
                        huerto = map.getHuerto();
                        music.stop();
                        game.enterState(2, new FadeOutTransition(), new FadeInTransition());
                        break;
                    default:
                }
            }
        }
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void keyPressed(int key, char c) {

        if (!hablando && !plantando && !inventario && !comerciando) {
            if (key == Input.KEY_ESCAPE) {
                ((Menu) game.getState(0)).setEstadoAnterior(getID());
                huerto = map.getHuerto();
                game.enterState(0); //MENU
            } else if (key == Input.KEY_F12) {
                ver_hitbox = !ver_hitbox;
            } else if (key == Input.KEY_F11) {
                game.enterState(4); //SANDBOX
            }
        }

        if ((hablando || plantando || inventario || comerciando) && key == Input.KEY_ESCAPE) {
            hablando = false;
            plantando = false;
            inventario = false;
            comerciando = false;
            npc = null;
            semilla = null;
            n_dialogo = 0;
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
        map = new Mapa("./resources/maps/Granja_test.tmx", ruby.getNivel(), huerto);
        map.agregarSpawn("SpawnEste");
        map.actualizarElementos(x, y);
        music.loop();
        if (gato_ido) {
            map.setGato(null);
            gato = null;
        }
    }

}
