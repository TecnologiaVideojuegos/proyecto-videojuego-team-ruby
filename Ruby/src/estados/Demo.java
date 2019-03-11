package estados;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Demo extends BasicGameState {

    private TiledMap map; //Mapa de la demo
    private double x, y;
    private Animation esqueleto; //Frames de la animación del personaje
    private int x_homer = 500, y_homer = 500, size_esqueleto = 2, ancho_esqueleto = 64, largo_esqueleto = 65; //Propieadades del personaje

    public Demo() {

    }

    /**
     * Inicializa
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new TiledMap("./resources/maps/demo_map.tmx");
        SpriteSheet sprite_esqueleto = new SpriteSheet("./resources/sprites/sprite_esqueleto.png", ancho_esqueleto, largo_esqueleto);
        esqueleto = new Animation();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 9; i++) {
                esqueleto.addFrame(sprite_esqueleto.getSprite(i, j), 50);
            }
        }
    }

    /**
     * Dibuja
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        map.render((int) x, (int) y, 0, 0, gc.getWidth(), gc.getHeight());

        //Jugador
        esqueleto.draw(x_homer, y_homer, largo_esqueleto * size_esqueleto, largo_esqueleto * size_esqueleto);
    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        //Movimiento del jugador
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de update
            if (esqueleto.getFrame() < 8) {
                esqueleto.start();
            } else {
                esqueleto.setCurrentFrame(0);
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de update
            if ((17 < esqueleto.getFrame()) && (esqueleto.getFrame() < 26)) {
                esqueleto.start();
            } else {
                esqueleto.setCurrentFrame(18);
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de update
            if ((8 < esqueleto.getFrame()) && (esqueleto.getFrame() < 17)) {
                esqueleto.start();
            } else {
                esqueleto.setCurrentFrame(9);
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de update
            if ((26 < esqueleto.getFrame()) && (esqueleto.getFrame() < 35)) {
                esqueleto.start();
            } else {
                esqueleto.setCurrentFrame(27);
            }
        }
        if (!(gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_D))) {
            esqueleto.stop();
        } else {
            esqueleto.start();
        }
    }

    @Override
    public int getID() {
        return 0;
    }

}
