package services;

import elementos.Mapa;
import java.awt.Font;
import objetos.Inventario;
import objetos.plantas.Planta_agua;
import objetos.plantas.Planta_fuego;
import objetos.plantas.Planta_rayo;
import objetos.semillas.Semilla;
import objetos.semillas.Semilla_agua;
import objetos.semillas.Semilla_fuego;
import objetos.semillas.Semilla_rayo;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import personajes.Jugador;

public class Plantar_Service {
    private int x, y, pos_x, pos_y;

    public Plantar_Service(int x, int y, int pos_x, int pos_y) {
        this.x = x+10;
        this.y = y-10;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    
    public Semilla elegirSemilla(Graphics grphcs, int x2, int y2, Inventario inventario, boolean click) throws SlickException{
        //Se fija a que distancia del ratón se muestra la selección se semillas
        Circle cursor = new Circle(x2, y2, 2);
        //grphcs.draw(cursor);
        Semilla semilla= null;
        Rectangle semilla_fuego = new Rectangle(x+32,y-95,211,28);
        Rectangle semilla_agua = new Rectangle(x+32,y-66,211,28);
        Rectangle semilla_rayo = new Rectangle(x+32,y-37,211,28);
        Font font = new Font("Segoe UI", Font.BOLD, 16);
        Color naranjaOscuro = new Color(210, 113, 18);
        Color naranja = new Color(254, 195, 117);
        Image imagen;
        grphcs.setColor(naranjaOscuro);
        grphcs.fillRect(x, y-130, 280, 130);
        grphcs.setColor(naranja);
        grphcs.fillRect(x+5, y-125, 270, 120);
        grphcs.setColor(Color.black);
        grphcs.setFont(new TrueTypeFont(new Font("Segoe UI", Font.BOLD, 24), true));
        grphcs.drawString("Plantar:", x+11, y-127);
        grphcs.setColor(naranjaOscuro);
        if(cursor.intersects(semilla_fuego) && inventario.getCantidad(new Semilla_fuego())>0){
            grphcs.fillRect(x+29, y-98, 217, 34);
            grphcs.setColor(naranja);
            grphcs.fillRect(x+32, y-95, 211, 28);
            grphcs.setColor(naranjaOscuro);
        }else if(cursor.intersects(semilla_agua) && inventario.getCantidad(new Semilla_agua())>0){
            grphcs.fillRect(x+29, y-69, 217, 34);
            grphcs.setColor(naranja);
            grphcs.fillRect(x+32, y-66, 211, 28);
            grphcs.setColor(naranjaOscuro);
        }else if(cursor.intersects(semilla_rayo) && inventario.getCantidad(new Semilla_rayo())>0){
            grphcs.fillRect(x+29, y-39, 217, 34);
            grphcs.setColor(naranja);
            grphcs.fillRect(x+32, y-36, 211, 28);
            grphcs.setColor(naranjaOscuro);
        }
        grphcs.setColor(Color.black);
        grphcs.setFont(new TrueTypeFont(font, true));
        grphcs.drawString("Semilla de fuego x "+inventario.getCantidad(new Semilla_fuego()), x+83, y-93);
        grphcs.drawString("Semilla de agua x "+inventario.getCantidad(new Semilla_agua()), x+83, y-64);
        grphcs.drawString("Semilla de rayo x "+inventario.getCantidad(new Semilla_rayo()), x+83, y-35);
        grphcs.drawImage(new Image("./resources/objetos/semillas/Icono_semilla_fuego.png").getScaledCopy(24, 24), x+34, y-93);
        grphcs.drawImage(new Image("./resources/objetos/semillas/Icono_semilla_agua.png").getScaledCopy(24, 24), x+34, y-64);
        grphcs.drawImage(new Image("./resources/objetos/semillas/Icono_semilla_rayo.png").getScaledCopy(24, 24), x+34, y-35);
        if(cursor.intersects(semilla_fuego) && click && inventario.getCantidad(new Semilla_fuego())>0){
            semilla = new Semilla_fuego();
        }else if(cursor.intersects(semilla_agua) && click && inventario.getCantidad(new Semilla_agua())>0){
            semilla = new Semilla_agua();
        }else if(cursor.intersects(semilla_rayo) && click && inventario.getCantidad(new Semilla_rayo())>0){
            semilla = new Semilla_rayo();
        }
        return semilla;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }
    
    public void plantar(GameContainer gc, Mapa map, int x, int y, Jugador ruby, Semilla semilla) throws SlickException {
        if (gc.getInput().isMouseButtonDown(0)) {
            boolean hay = false;
            if (semilla.getNombre().equals("Semilla de fuego")) {
                map.getHuerto().anadirPlanta(x, y, new Planta_fuego());
            } else if (semilla.getNombre().equals("Semilla de agua")) {
                map.getHuerto().anadirPlanta(x, y, new Planta_agua());
            } else if (semilla.getNombre().equals("Semilla de rayo")) {
                map.getHuerto().anadirPlanta(x, y, new Planta_rayo());
            } else {
                System.out.println("Error al plantar una semilla en la función clickHuerto()");
            }
            ruby.getInventario().eliminarObjeto(semilla);
        }
    }
}
