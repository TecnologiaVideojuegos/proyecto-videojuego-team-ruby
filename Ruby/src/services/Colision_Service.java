package services;

import elementos.Hitbox;
import elementos.Mapa;
import elementos.Spawn;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import personajes.*;

public class Colision_Service {

    public static float[] colisionMuros(Personaje personaje, Mapa map, GameContainer gc, int i, float movEjeX, float movEjeY) {
        float mov[] = new float[2];
        float x = 0, y = 0;
        for (Hitbox hitbox : map.getBlocks()) {
            while (personaje.getHitbox().getRectangulo().intersects(hitbox.getRectangulo())) {
                mov = colision(map.getBlocks(), i, gc, map, personaje.getHitbox().getRectangulo(), movEjeX, movEjeY);
                x += mov[0];
                y += mov[1];
            }
        }
        mov[0] = x;
        mov[1] = y;

        return mov;
    }
    
    public static float[] colisionMuros(Personaje personaje, Mapa map, GameContainer gc, int i, float movEjeX, float movEjeY, float reductorVelocidad) {
        float mov[] = new float[2];
        float x = 0, y = 0;
        for (Hitbox hitbox : map.getBlocks()) {
            while (personaje.getHitbox().getRectangulo().intersects(hitbox.getRectangulo())) {
                mov = colision(map.getBlocks(), i, gc, map, personaje.getHitbox(), movEjeX, movEjeY, reductorVelocidad);
                x += mov[0];
                y += mov[1];
            }
        }
        mov[0] = x;
        mov[1] = y;

        return mov;
    }

    private static float[] colision(ArrayList<Hitbox> hitboxes, int i, GameContainer gc, Mapa map, Rectangle personaje, float movEjeX, float movEjeY) {
        float mov[] = new float[2];
        float x = 0, y = 0;

        if (movEjeY>0) {
            y -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, -(i / 3.f));
        }
        if (movEjeY<0) {
            y += i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, (i / 3.f));
        }
        if (movEjeX>0) {
            x -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(-(i / 3.f), 0);
        }
        if (movEjeX<0) {
            x += i / 3.f;  //i=tiempo de update
            map.actualizarElementos((i / 3.f), 0);
        }

        if (movEjeY>0) {
            y += i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, (i / 3.f));
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    y -= i / 3.f;  //i=tiempo de update
                    map.actualizarElementos(0, -(i / 3.f));
                }
            }
        }
        if (movEjeY<0) {
            y -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, -(i / 3.f));
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    y += i / 3.f;  //i=tiempo de update
                    map.actualizarElementos(0, (i / 3.f));
                }
            }
        }
        if (movEjeX>0) {
            x += i / 3.f;  //i=tiempo de update
            map.actualizarElementos((i / 3.f), 0);
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    x -= i / 3.f;  //i=tiempo de update
                    map.actualizarElementos(-(i / 3.f), 0);
                }
            }
        }
        if (movEjeX<0) {
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
    
    private static float[] colision(ArrayList<Hitbox> hitboxes, int i, GameContainer gc, Mapa map, Hitbox npc, float movEjeX, float movEjeY, float reductorVelocidad) {
        float mov[] = new float[2];
        float x = 0, y = 0;
        
        if (movEjeY>0) {
            y -= i / reductorVelocidad;  //i=tiempo de update
            npc.updatePos(0, -i / reductorVelocidad);
        }
        if (movEjeY<0) {
            y += i / reductorVelocidad;  //i=tiempo de update
            npc.updatePos(0, i / reductorVelocidad);
        }
        if (movEjeX>0) {
            x -= i / reductorVelocidad;  //i=tiempo de update
            npc.updatePos(-i / reductorVelocidad, 0);
        }
        if (movEjeX<0) {
            x += i / reductorVelocidad;  //i=tiempo de update
            npc.updatePos(i / reductorVelocidad, 0);
        }
        
        if (movEjeY>0) {
            y += i / reductorVelocidad;  //i=tiempo de update
            npc.updatePos(0, i / reductorVelocidad);
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(npc.getRectangulo())) {
                    y -= i / reductorVelocidad;  //i=tiempo de update
                    npc.updatePos(0, -i / reductorVelocidad);
                }
            }
        }
        if (movEjeY<0) {
            y -= i / reductorVelocidad;  //i=tiempo de update
            npc.updatePos(0, -i / reductorVelocidad);
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(npc.getRectangulo())) {
                    y += i / reductorVelocidad;  //i=tiempo de update
                    npc.updatePos(0, i / reductorVelocidad);
                }
            }
        }
        if (movEjeX>0) {
            x += i / reductorVelocidad;  //i=tiempo de update
            npc.updatePos(i / reductorVelocidad, 0);
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(npc.getRectangulo())) {
                    x -= i / reductorVelocidad;  //i=tiempo de update
                    npc.updatePos(-i / reductorVelocidad, 0);
                }
            }
        }
        if (movEjeX<0) {
            x -= i / reductorVelocidad;  //i=tiempo de update
            npc.updatePos(-i / reductorVelocidad, 0);
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(npc.getRectangulo())) {
                    x += i / reductorVelocidad;  //i=tiempo de update
                    npc.updatePos(i / reductorVelocidad, 0);
                }
            }
        }
        
        mov[0] = x;
        mov[1] = y;

        return mov;
    }
    
    public static String saltoMapa(Personaje personaje, Mapa map){
        String nombreSpawn = "";
        
        for(Spawn spawn: map.getSpawns()){
            if(personaje.getHitbox().getRectangulo().intersects(spawn.getHitbox().getRectangulo())){
                nombreSpawn = spawn.getNombre();
            }
        }
        return nombreSpawn;
    }

}
