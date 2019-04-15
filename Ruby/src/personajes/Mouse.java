/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajes;

import elementos.Hitbox;
import java.util.ArrayList;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author javir
 */
public class Mouse {
    private int distancia = 100;
    private Personaje ruby;
    
    public Mouse(Personaje ruby){
        this.ruby = ruby;
    }
    
    //Devuelve un rectangulo que indica la hitbox que ha pulsado, si no se ha pulsado ninguno se devulve null
    public Hitbox actualizarMouse(ArrayList<Hitbox> hitbox, Circle cursor_hitbox){
        for (Hitbox re : hitbox) {
                if (cursor_hitbox.intersects(re.getRectangulo()) && (distancia > (Math.sqrt((Math.pow(re.getRectangulo().getX() - ruby.getHitbox().getRectangulo().getX(), 2)) + (Math.pow(re.getRectangulo().getY() - ruby.getHitbox().getRectangulo().getY(), 2)))))) {
                    return re;
                }
        }
        return null;
    }
}
