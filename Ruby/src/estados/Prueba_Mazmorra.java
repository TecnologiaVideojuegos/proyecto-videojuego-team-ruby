package estados;

import elementos.Hitbox;
import elementos.Mapa;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import personajes.Jugador;
import services.Colision_Service;
import services.InputCapture_Service;

public class Prueba_Mazmorra extends BasicGameState {

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

    public Prueba_Mazmorra(Jugador ruby, boolean ver_hitbox) {
        this.ruby = ruby;
        this.ver_hitbox = ver_hitbox;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        this.gcWidth = gc.getWidth();
        this.gcHeight = gc.getHeight();
        cursor = new Image("./resources/sprites/cursor.png");
        map = new Mapa("./resources/maps/demo_map2.tmx");
        map.agregarSpawn("SpawnNorte");
        map.agregarSpawn("SpawnEste");

        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);
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

        //Cursor
        grphcs.drawImage(cursor, gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
    }

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
        float movColision[] = Colision_Service.colisionMuros(ruby, map, gc, i, mov[0], mov[1], 0);
        x += movColision[0];
        y += movColision[1];

        //Movimiento enemigos
        map.movimientoEnemigos(i, gc, ruby.getHitbox());

        //Detección de click sobre huerto
        click = InputCapture_Service.clickHuerto(gc, map, cursor_hitbox, ruby);

        //MOVIMENTO DEL RATÓN
        coordenadas = "(" + gc.getInput().getMouseX() + "," + gc.getInput().getMouseY() + ")";

        //Comprobacion de salto de escenario
        Prueba p = (Prueba) game.getState(0);
        switch (Colision_Service.saltoMapa(ruby, map)) {
            case "SpawnNorte":
                p.posicinarEnSpawnARuby("SpawnSur", 0, 100);
                game.enterState(0);
                break;
            case "SpawnEste":
                p.posicinarEnSpawnARuby("SpawnEste", 100, 0);
                game.enterState(0);
                break;
            default:
        }
    }

    @Override
    public int getID() {
        return 2;
    }

    public void posicinarEnSpawnARuby(String spawn, int mov_x, int mov_y) throws SlickException {
        float posSapawnRuby[] = map.getPosicionSpawn(spawn);
        x = +(-(posSapawnRuby[0]) + (gcWidth / 2 + mov_x));
        y = +(-(posSapawnRuby[1]) + (gcHeight / 2) + mov_y);
        map = new Mapa("./resources/maps/demo_map2.tmx");
        map.agregarSpawn("SpawnNorte");
        map.agregarSpawn("SpawnEste");
        map.actualizarElementos(x, y);
    }
}
