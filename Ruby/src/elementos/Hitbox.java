package elementos;

import org.newdawn.slick.geom.Rectangle;

public class Hitbox{
    private Rectangle hitbox;
    
    //constructor por defecto
    public Hitbox(){
        hitbox = new Rectangle(0, 0, 0, 0);
    }
    
    /**
     * Constuctor con parametros
     * @param pos_x  -> cordenada x en pantalla
     * @param pos_y  -> cordenada y en pantalla
     * @param width  -> ancho de la hitbox
     * @param height -> altura de la hitbox
     */
    public Hitbox(float pos_x, float pos_y, float width, float height){
        hitbox = new Rectangle(pos_x, pos_y, width, height);
    }
    
    
    /** 
     * Actualizacion de posicion de la hitbox
     * @param pos_x -> cordenada x en pantalla
     * @param pos_y  -> cordenada y en pantalla
     */
    public void updatePos(float pos_x, float pos_y){
        hitbox.setX(hitbox.getX() + pos_x);
        hitbox.setY(hitbox.getY() + pos_y);
    }
}
