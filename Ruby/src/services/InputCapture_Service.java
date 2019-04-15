package services;

import elementos.Hitbox;
import elementos.Mapa;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import personajes.Personaje;

public class InputCapture_Service {
    
    public static float[] capturaMovimiento(GameContainer gc, int i) {
        float mov[] = new float[2];
        float x = 0, y = 0;
        
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de update
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de update
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de update
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de update
        }
        
        mov[0] = x;
        mov[1] = y;
        
        return mov;
    }
    
    public static String clickHuerto(GameContainer gc, Mapa map, Circle cursor_hitbox, Personaje personajeReferencia){
        String click;
        if (gc.getInput().isMouseButtonDown(0)) {
            Hitbox x = actualizarMouse(map.getHuerto(), cursor_hitbox, personajeReferencia);
            if (x != null) {
                click = "click";
            } else {
                click = "";
            }
        }else{
            click = "";
        }
        
        return click;
    }
    
    public static Hitbox actualizarMouse(ArrayList<Hitbox> hitbox, Circle cursor_hitbox, Personaje personajeReferencia){
        int distancia = 100;
        for (Hitbox re : hitbox) {
                if (cursor_hitbox.intersects(re.getRectangulo()) && (distancia > (Math.sqrt((Math.pow(re.getRectangulo().getX() - personajeReferencia.getHitbox().getRectangulo().getX(), 2)) + (Math.pow(re.getRectangulo().getY() - personajeReferencia.getHitbox().getRectangulo().getY(), 2)))))) {
                    return re;
                }
        }
        return null;
    }
}
