package personajes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Animacion_dinamica extends Animacion {

    // TODO: fijar valores para todos los sprites
    private final int ANCHO = 64;   // Ancho de cada frame del sprite
    private final int ALTO = 65;    // Alto de cada frame del sprite
    
    private Animation animacion;
    private SpriteSheet sprite;
    
    public Animacion_dinamica(String dir) throws SlickException {
        super();
        
        sprite = new SpriteSheet(dir, ANCHO, ALTO);
        animacion = new Animation();

        //Recorte del sprite
        for (int j = 0; j < 4; j++) {   // TODO: fijar, parametizar o automatizar filas/columnas
            for (int i = 0; i < 9; i++) {
                animacion.addFrame(sprite.getSprite(i, j), 50);
            }
        }
    }
    
    public void animaPersonaje(GameContainer gc) {
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            if ((8 < animacion.getFrame()) && (animacion.getFrame() < 17)) {
                animacion.start();
            } else {
                animacion.setCurrentFrame(9);
            }
        } else if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            if ((26 < animacion.getFrame()) && (animacion.getFrame() < 35)) {
                animacion.start();
            } else {
                animacion.setCurrentFrame(27);
            }
        } else if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            if (animacion.getFrame() < 8) {
                animacion.start();
            } else {
                animacion.setCurrentFrame(0);
            }
        } else if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            if ((17 < animacion.getFrame()) && (animacion.getFrame() < 26)) {
                animacion.start();
            } else {
                animacion.setCurrentFrame(18);
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
            animacion.stop();
        } else {
            animacion.start();
        }
    }
    
    @Override
    public void renderAnimacion(GameContainer gc, float pos_x, float pos_y) {
        animaPersonaje(gc);
        animacion.draw(gc.getWidth() / 2 - ANCHO, gc.getHeight() / 2 - ALTO, ANCHO * 2, ALTO * 2);
    }
}
