package services;

import elementos.Hitbox;
import elementos.Mapa;
import java.util.ArrayList;
import objetos.plantas.Planta;
import objetos.plantas.Planta_agua;
import objetos.plantas.Planta_fuego;
import objetos.plantas.Planta_rayo;
import objetos.semillas.Semilla;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import personajes.Jugador;
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

    public static Plantar_Service clickHuerto1(GameContainer gc, Mapa map, int x, int y, Jugador ruby, Circle cursor_hitbox, Plantar_Service plantar) throws SlickException {
        int distancia = 100;
        if (gc.getInput().isMouseButtonDown(0)) {
            for (Hitbox hitbox_terreno : map.getHuerto().getHitboxs()) {
                if (hitbox_terreno.getRectangulo().intersects(cursor_hitbox) && !map.getHuerto().hayPlanta(map.getHuerto().getX(hitbox_terreno), map.getHuerto().getY(hitbox_terreno)) && !ruby.getInventario().getSemillas().isEmpty() && (distancia > (Math.sqrt((Math.pow(hitbox_terreno.getRectangulo().getX() - ruby.getHitbox().getRectangulo().getX(), 2)) + (Math.pow(hitbox_terreno.getRectangulo().getY() - ruby.getHitbox().getRectangulo().getY(), 2)))))) {
                    plantar = new Plantar_Service(gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY(), map.getHuerto().getX(hitbox_terreno), map.getHuerto().getY(hitbox_terreno));
                }
                if (hitbox_terreno.getRectangulo().intersects(cursor_hitbox) && map.getHuerto().hayPlanta(map.getHuerto().getX(hitbox_terreno), map.getHuerto().getY(hitbox_terreno)) && (distancia > (Math.sqrt((Math.pow(hitbox_terreno.getRectangulo().getX() - ruby.getHitbox().getRectangulo().getX(), 2)) + (Math.pow(hitbox_terreno.getRectangulo().getY() - ruby.getHitbox().getRectangulo().getY(), 2)))))) {
                    Planta planta = map.getHuerto().eliminarPlanta(map.getHuerto().getX(hitbox_terreno), map.getHuerto().getY(hitbox_terreno));
                    if (planta != null) {
                        ruby.getInventario().anadirObjeto(planta, 1);
                    }
                }
            }
        }
        return plantar;
    }
    
    public static boolean clickHuerto2(GameContainer gc, Mapa map, int x, int y, Jugador ruby, Circle cursor_hitbox, Plantar_Service plantar) throws SlickException {
        int distancia = 100;
        if (gc.getInput().isMouseButtonDown(0)) {
            for (Hitbox hitbox_terreno : map.getHuerto().getHitboxs()) {
                if (hitbox_terreno.getRectangulo().intersects(cursor_hitbox) && !map.getHuerto().hayPlanta(map.getHuerto().getX(hitbox_terreno), map.getHuerto().getY(hitbox_terreno)) && !ruby.getInventario().getSemillas().isEmpty() && (distancia > (Math.sqrt((Math.pow(hitbox_terreno.getRectangulo().getX() - ruby.getHitbox().getRectangulo().getX(), 2)) + (Math.pow(hitbox_terreno.getRectangulo().getY() - ruby.getHitbox().getRectangulo().getY(), 2)))))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Npc clickNpc(GameContainer gc, Mapa map, Circle cursor_hitbox, Personaje personajeReferencia) {
        Hitbox x;
        int distancia = 100;
        int i = 0;
        if (gc.getInput().isMouseButtonDown(0)) {
            x = actualizarMouse(map.getHitboxNpc(), cursor_hitbox, personajeReferencia);
            if (x != null) {
                if ((distancia > (Math.sqrt((Math.pow(x.getRectangulo().getX() - personajeReferencia.getHitbox().getRectangulo().getX(), 2)) + (Math.pow(x.getRectangulo().getY() - personajeReferencia.getHitbox().getRectangulo().getY(), 2)))))) {
                    return map.getNpcs().get(i);
                    
                }
            }
            i++;
        }
        return null;
    }

    public static Hitbox actualizarMouse(ArrayList<Hitbox> hitbox, Circle cursor_hitbox, Personaje personajeReferencia) {
        int distancia = 100;
        for (Hitbox re : hitbox) {
            if (cursor_hitbox.intersects(re.getRectangulo()) && (distancia > (Math.sqrt((Math.pow(re.getRectangulo().getX() - personajeReferencia.getHitbox().getRectangulo().getX(), 2)) + (Math.pow(re.getRectangulo().getY() - personajeReferencia.getHitbox().getRectangulo().getY(), 2)))))) {
                return re;
            }
        }
        return null;
    }
}
