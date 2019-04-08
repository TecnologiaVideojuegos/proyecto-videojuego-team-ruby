package estados;

import elementos.Hitbox;
import elementos.Mapa;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import personajes.*;
import core.*;

public class Prueba extends BasicGameState {

    private Mapa map;
    private float x, y;

    //PERSONAJE
    private Jugador ruby;
    private int x_homer = 500, y_homer = 500, size_esqueleto = 2, ancho_esqueleto = 64, largo_esqueleto = 65;

    //RATÓN
    private String coordenadas = "", click = "";
    private Image cursor;

    //HITBOX
    private boolean ver_hitbox = true;

    private StateBasedGame game;

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
        ruby = new Jugador(new Hitbox(gc.getWidth() / 2 - (ancho_esqueleto - 30), (gc.getHeight() / 2 - (largo_esqueleto - 25)) + 60, (ancho_esqueleto - 30) * size_esqueleto, ((largo_esqueleto - 15) * size_esqueleto) - 60));
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
        ruby.renderPersonaje(gc);

        //Cursor
        grphcs.drawImage(cursor, gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());

    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        float mov[] = InputCapture.capturaMovimiento(gc, i);
        x += mov[0];
        y += mov[1];
        map.actualizarElementos(mov[0], mov[1]);

        //MOVIMENTO DEL RATÓN
        coordenadas = "(" + gc.getInput().getMouseX() + "," + gc.getInput().getMouseY() + ")";
        if (gc.getInput().isMouseButtonDown(0)) {
            click = "click";
        } else {
            click = "";
        }
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
