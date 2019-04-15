package services;

import elementos.Hitbox;
import elementos.Mapa;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import personajes.*;

public class Colision_Service {
    
    
    public static float[] colisionMuros(Personaje personaje, Mapa map, GameContainer gc, int i){
        float mov[] = new float[2];
        float x = 0, y = 0;
        for (Hitbox hitbox : map.getBlocks()) {
            while (personaje.getHitbox().getRectangulo().intersects(hitbox.getRectangulo())) {
                mov = colision(map.getBlocks(), i, gc, map, personaje.getHitbox().getRectangulo());
                x += mov[0];
                y += mov[1];
            }
        }
        mov[0] = x;
        mov[1] = y;

        return mov;
    }
    
    private static float[] colision(ArrayList<Hitbox> hitboxes, int i, GameContainer gc, Mapa map, Rectangle personaje) {
        float mov[] = new float[2];
        float x = 0, y = 0;
        
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, -(i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y += i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, (i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(-(i / 3.f), 0);
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x += i / 3.f;  //i=tiempo de update
            map.actualizarElementos((i / 3.f), 0);
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, (i / 3.f));
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    y -= i / 3.f;  //i=tiempo de update
                    map.actualizarElementos(0, -(i / 3.f));
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, -(i / 3.f));
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    y += i / 3.f;  //i=tiempo de update
                    map.actualizarElementos(0, (i / 3.f));
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de update
            map.actualizarElementos((i / 3.f), 0);
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    x -= i / 3.f;  //i=tiempo de update
                    map.actualizarElementos(-(i / 3.f), 0);
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(-(i / 3.f), 0);
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    x += i / 3.f;  //i=tiempo de update
                    map.actualizarElementos((i / 3.f), 0);
                }
            }
        }
        mov[0] = x;
        mov[1] = y;

        return mov;
    }
    
}
