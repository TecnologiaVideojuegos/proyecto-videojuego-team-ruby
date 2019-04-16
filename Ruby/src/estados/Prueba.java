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
    private int size_Ruby = 3, ancho_Ruby = 32, largo_Ruby = 32;

    //RATÓN
    private String coordenadas = "", click = "";
    private Image cursor;
    private Circle cursor_hitbox;

    //HITBOX
    private boolean ver_hitbox = true;

    private StateBasedGame game;
    
    private float mov[] = new float[2]; //Movimiento entre cada update

    public Prueba() {
    }

    /**
     * Inicializa
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        cursor = new Image("./resources/sprites/cursor.png");
        map = new Mapa("./resources/maps/demo_map.tmx");
        ruby = new Jugador(new Hitbox(gc.getWidth() / 2 - (ancho_Ruby - 30) - 5, (gc.getHeight() / 2 - (largo_Ruby - 25)) + 45, 40, 32));
        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);
    }

    /**
     * Dibuja
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

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
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
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
        map.movimientoEnemigos(i, gc);
        
        //Detección de click sobre huerto
        click = InputCapture_Service.clickHuerto(gc, map, cursor_hitbox, ruby);

        //MOVIMENTO DEL RATÓN
        coordenadas = "(" + gc.getInput().getMouseX() + "," + gc.getInput().getMouseY() + ")";

    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_F11) {
            game.enterState(0); //DEMO
        }
    }
}
