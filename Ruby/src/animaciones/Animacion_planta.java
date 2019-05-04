package animaciones;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Animacion_planta extends Thread{
    private final int ALTO = 32, ANCHO = 32;
    private Animation animacion;
    private SpriteSheet sprite;
    
    public Animacion_planta(String string) throws SlickException{
        sprite = new SpriteSheet(string, ALTO, ANCHO);
        
        animacion = new Animation();
        animacion.addFrame(sprite.getSprite(1, 0), 10000);
        animacion.addFrame(sprite.getSprite(0, 0), 1);
        this.start();
    }
    
    public int getFrame(){
        return animacion.getFrame();
    }
    
    public void renderAnimacion(float pos_x, float pos_y){
        animacion.draw(pos_x, pos_y);
    }
    
    @Override
    public void run(){
        animacion.start();
        animacion.stopAt(1);
    }
}
