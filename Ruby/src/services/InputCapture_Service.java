package services;

import elementos.Hitbox;
import elementos.Huerto;
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
    
    public static void clickHuerto(GameContainer gc, Mapa map, int x, int y, Jugador ruby, Semilla semilla) throws SlickException{
        if (gc.getInput().isMouseButtonDown(0)){
            boolean hay = false;
            if(semilla.getNombre().equals("Semilla de fuego")){
                map.getHuerto().anadirPlanta(x, y, new Planta_fuego());
            }else if(semilla.getNombre().equals("Semilla de agua")){
                map.getHuerto().anadirPlanta(x, y, new Planta_agua());
            }else if(semilla.getNombre().equals("Semilla de rayo")){
                map.getHuerto().anadirPlanta(x, y, new Planta_rayo());
            }else{
                System.out.println("Error al plantar una semilla en la función clickHuerto()");
            }
            ruby.getInventario().eliminarObjeto(semilla);
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
