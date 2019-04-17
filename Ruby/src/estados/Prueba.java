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
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import personajes.*;
import services.*;

public class Prueba extends BasicGameState {

    private Mapa map;
    private float x, y;

    //PERSONAJE
    private Jugador ruby;

    //RATÓN
    private String coordenadas = "", click = "";
    private Image cursor;
    private Circle cursor_hitbox;

    //HITBOX
    private boolean ver_hitbox;

    private StateBasedGame game;

    private float mov[] = new float[2]; //Movimiento entre cada update

    private float gcWidth, gcHeight;

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
        this.gcWidth = gc.getWidth();
        this.gcHeight = gc.getHeight();
        cursor = new Image("./resources/sprites/cursor.png");
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
        grphcs.drawString(click, gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
        grphcs.drawString(coordenadas, 35, 35);

        //Jugador
        ruby.renderPersonaje(gc, mov[0], mov[1]);
        if (ver_hitbox) {
            grphcs.drawRect(ruby.getHitbox().getRectangulo().getX(), ruby.getHitbox().getRectangulo().getY(), ruby.getHitbox().getRectangulo().getWidth(), ruby.getHitbox().getRectangulo().getHeight());
        }

        //Cursor
        grphcs.drawImage(cursor, gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());

    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
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

        //Detección de click sobre huerto
        click = InputCapture_Service.clickHuerto(gc, map, cursor_hitbox, ruby);

        //MOVIMENTO DEL RATÓN
        coordenadas = "(" + gc.getInput().getMouseX() + "," + gc.getInput().getMouseY() + ")";

        //Comprobacion de salto de escenario
        Prueba_Mazmorra p = (Prueba_Mazmorra) game.getState(2);
        switch (Colision_Service.saltoMapa(ruby, map)) {
            case "SpawnSur":
                p.posicinarEnSpawnARuby("SpawnNorte", 0, -100);
                game.enterState(2);
                break;
            case "SpawnEste":
                p.posicinarEnSpawnARuby("SpawnEste", 100, 0);
                game.enterState(2);
                break;
            default:
        }
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_F11) {
            game.enterState(1); //DEMO
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
}
