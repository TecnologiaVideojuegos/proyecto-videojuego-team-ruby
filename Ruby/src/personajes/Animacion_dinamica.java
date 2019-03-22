package personajes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Animacion_dinamica extends Animacion {

    // TODO: fijar valores para todos los sprites
    private final int ANCHO = 32;   // Ancho de cada frame del sprite
    private final int ALTO = 32;    // Alto de cada frame del sprite

    private Animation animacion_w, animacion_a, animacion_s, animacion_d;
    private int size = 3, lado_animacion = 0;

    private SpriteSheet sprite;

    public Animacion_dinamica(String dir) throws SlickException {
        super();

        sprite = new SpriteSheet(dir, ANCHO, ALTO);

        animacion_w = new Animation();
        animacion_a = new Animation();
        animacion_s = new Animation();
        animacion_d = new Animation();
        animacion_s.addFrame(sprite.getSprite(0, 0), 200);
        animacion_s.addFrame(sprite.getSprite(2, 0), 200);
        animacion_s.addFrame(sprite.getSprite(1, 0), 200);
        animacion_a.addFrame(sprite.getSprite(0, 1), 200);
        animacion_a.addFrame(sprite.getSprite(2, 1), 200);
        animacion_a.addFrame(sprite.getSprite(1, 1), 200);
        animacion_d.addFrame(sprite.getSprite(0, 2), 200);
        animacion_d.addFrame(sprite.getSprite(2, 2), 200);
        animacion_d.addFrame(sprite.getSprite(1, 2), 200);
        animacion_w.addFrame(sprite.getSprite(0, 3), 200);
        animacion_w.addFrame(sprite.getSprite(2, 3), 200);
        animacion_w.addFrame(sprite.getSprite(1, 3), 200);

    }

    public void animaPersonaje(GameContainer gc) {
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            lado_animacion = 1;
            if (animacion_a.getFrame() == 2) {
                animacion_a.setCurrentFrame(0);
            }
        } else if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            animacion_d.start();
            lado_animacion = 3;
            if (animacion_d.getFrame() == 2) {
                animacion_d.setCurrentFrame(0);
            }
        } else if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            animacion_w.start();
            lado_animacion = 0;
            if (animacion_w.getFrame() == 2) {
                animacion_w.setCurrentFrame(0);
            }
        } else if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            animacion_s.start();
            lado_animacion = 2;
            if (animacion_s.getFrame() == 2) {
                animacion_s.setCurrentFrame(0);
            }
        }
        if (!(gc.getInput().isKeyDown(Input.KEY_W)
                || gc.getInput().isKeyDown(Input.KEY_A)
                || gc.getInput().isKeyDown(Input.KEY_S)
                || gc.getInput().isKeyDown(Input.KEY_D)
                || gc.getInput().isKeyDown(Input.KEY_UP)
                || gc.getInput().isKeyDown(Input.KEY_DOWN)
                || gc.getInput().isKeyDown(Input.KEY_LEFT)
                || gc.getInput().isKeyDown(Input.KEY_RIGHT))) {
            animacion_w.stop();
            animacion_w.setCurrentFrame(2);
            animacion_a.stop();
            animacion_a.setCurrentFrame(2);
            animacion_s.stop();
            animacion_s.setCurrentFrame(2);
            animacion_d.stop();
            animacion_d.setCurrentFrame(2);
        } else {
            animacion_w.start();
            animacion_a.start();
            animacion_s.start();
            animacion_d.start();
        }
    }

    @Override
    public void renderAnimacion(GameContainer gc, float pos_x, float pos_y) {
        animaPersonaje(gc);
        //Jugador
        switch (lado_animacion) {
            case 0:
                animacion_w.draw(gc.getWidth() / 2 - ANCHO, gc.getHeight() / 2 - ALTO, ANCHO * size, ALTO * size);
                break;
            case 1:
                animacion_a.draw(gc.getWidth() / 2 - ANCHO, gc.getHeight() / 2 - ALTO, ANCHO * size, ALTO * size);
                break;
            case 2:
                animacion_s.draw(gc.getWidth() / 2 - ANCHO, gc.getHeight() / 2 - ALTO, ANCHO * size, ALTO * size);
                break;
            case 3:
                animacion_d.draw(gc.getWidth() / 2 - ANCHO, gc.getHeight() / 2 - ALTO, ANCHO * size, ALTO * size);
                break;
            default:
                break;
        }
    }
}
