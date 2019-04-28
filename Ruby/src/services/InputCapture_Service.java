package services;

import elementos.Hitbox;
import elementos.Mapa;
import java.util.ArrayList;
import objetos.Planta;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import personajes.Npc;
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
    
    public static void clickHuerto(GameContainer gc, Mapa map, Circle cursor_hitbox, Personaje personajeReferencia) throws SlickException{
        boolean hay = false;
        if (gc.getInput().isMouseButtonDown(0)) {
            Hitbox hitbox = actualizarMouse(map.getHuerto(), cursor_hitbox, personajeReferencia);
            if (hitbox != null) {
                for(Planta planta : map.getPlantas()){
                    if(planta.getPos_x() == hitbox.getRectangulo().getCenterX() && planta.getPos_y() == hitbox.getRectangulo().getCenterY()){
                        hay = true;
                        break;
                    }
                }
                if(!hay){
                    map.anadirPlanta_fuego(hitbox.getRectangulo().getMinX(), hitbox.getRectangulo().getMinY());
                }
            }
        }
    }
    
    public static Npc clickNpc(GameContainer gc, Mapa map, Circle cursor_hitbox, Personaje personajeReferencia){
        Hitbox x;
        int i = 0;
        if (gc.getInput().isMouseButtonDown(0)) {
            x = actualizarMouse(map.getHitboxNpc(), cursor_hitbox, personajeReferencia);
            if(x != null){
                return map.getNpcs().get(i);
            }
            i++;
        }
        return null;
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
