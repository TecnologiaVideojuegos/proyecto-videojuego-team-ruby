package services;

import java.awt.Font;
import objetos.Inventario;
import objetos.Objeto;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import personajes.Jugador;

public class Inventario_Service {
    
    public static void mostrarInventario(Graphics grphcs, Jugador jugador){
        Inventario inventario = jugador.getInventario();
        Image imagen = null;
        int y = 85;
        Font font = new Font("Segoe UI", Font.BOLD, 20);
        Color naranjaOscuro = new Color(210, 113, 18);
        Color naranja = new Color(254, 195, 117);
        grphcs.setColor(naranjaOscuro);
        grphcs.fillRect(0, 0, 360, 720);
        grphcs.setColor(naranja);
        grphcs.fillRect(5, 5, 350, 710);
        grphcs.setFont(new TrueTypeFont(new Font("Segoe UI", Font.BOLD, 30), true));
        grphcs.drawString("Inventario", 54, 36);
        grphcs.setFont(new TrueTypeFont(font, true));
        if(!inventario.getPlantas().isEmpty()){
            grphcs.setColor(Color.black);
            grphcs.drawString("Plantas", 54, y);
            grphcs.setColor(naranjaOscuro);
            y=y+36;
            grphcs.drawRect(53, y, 254, 1);
            y=y+9;
            for(Objeto objeto: inventario.getPlantas()){
                imagen = objeto.getIcono();
                imagen.draw(68, y, 32, 32);
                grphcs.setColor(Color.black);
                grphcs.drawString(objeto.getNombre()+" x "+objeto.getCantidad(), 109, y+3);
                grphcs.setColor(naranjaOscuro);
                y=y+37;
            }
            y=y+4;
        }
        if(!inventario.getSemillas().isEmpty()){
            grphcs.setColor(Color.black);
            grphcs.drawString("Semillas", 54, y);
            grphcs.setColor(naranjaOscuro);
            y=y+36;
            grphcs.drawRect(53, y, 254, 1);
            y=y+9;
            for(Objeto objeto: inventario.getSemillas()){
                imagen = objeto.getIcono();
                imagen.draw(68, y, 32, 32);
                grphcs.setColor(Color.black);
                grphcs.drawString(objeto.getNombre()+" x "+objeto.getCantidad(), 109, y+3);
                grphcs.setColor(naranjaOscuro);
                y=y+37;
            }
            y=y+4;
        }
        if(!inventario.getPociones().isEmpty()){
            grphcs.setColor(Color.black);
            grphcs.drawString("Pociones", 54, y);
            grphcs.setColor(naranjaOscuro);
            y=y+36;
            grphcs.drawRect(53, y, 254, 1);
            y=y+9;
            for(Objeto objeto: inventario.getPociones()){
                imagen = objeto.getIcono();
                imagen.draw(68, y, 32, 32);
                grphcs.setColor(Color.black);
                grphcs.drawString(objeto.getNombre()+" x "+objeto.getCantidad(), 109, y+3);
                grphcs.setColor(naranjaOscuro);
                y=y+37;
            }
            y=y+4;
        }
        grphcs.setColor(Color.black);
        grphcs.drawString("Dinero: "+jugador.getDinero()+"$", 54, 642);
    }
}