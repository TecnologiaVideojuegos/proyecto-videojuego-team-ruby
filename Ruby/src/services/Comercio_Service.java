package services;

import java.awt.Font;
import objetos.Inventario;
import objetos.Objeto;
import objetos.plantas.Planta_agua;
import objetos.plantas.Planta_fuego;
import objetos.plantas.Planta_rayo;
import objetos.pociones.Pocion_ataque;
import objetos.pociones.Pocion_vida;
import objetos.semillas.Semilla_agua;
import objetos.semillas.Semilla_fuego;
import objetos.semillas.Semilla_rayo;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import personajes.Jugador;

public class Comercio_Service {
    
    public static void comerciar(Graphics grphcs, Jugador ruby, int x2, int y2, boolean click) throws SlickException{
        Circle mouse = new Circle(x2, y2, 2);
        Inventario inventario = ruby.getInventario();
        Objeto objeto = null;
        Font font = new Font("Regular", Font.BOLD, 17);
        Color naranjaOscuro = new Color(210, 113, 18);
        Color naranja = new Color(254, 195, 117);
        Rectangle vender_planta_agua = new Rectangle(46,127,283,38);
        Rectangle vender_planta_fuego = new Rectangle(46,164,283,38);
        Rectangle vender_planta_rayo = new Rectangle(46,201,283,38);
        Rectangle vender_pocion_vida = new Rectangle(46,285,283,38);
        Rectangle vender_pocion_ataque = new Rectangle(46,322,283,38);
        Rectangle comprar_planta_agua = new Rectangle(985,127,264,38);
        Rectangle comprar_planta_fuego = new Rectangle(985,164,264,38);
        Rectangle comprar_planta_rayo = new Rectangle(985,201,264,38);
        Rectangle comprar_semilla_agua = new Rectangle(985,285,264,38);
        Rectangle comprar_semilla_fuego = new Rectangle(985,322,264,38);
        Rectangle comprar_semilla_rayo = new Rectangle(985,359,264,38);
        Rectangle comprar_pocion_vida = new Rectangle(985,447,264,38);
        Rectangle comprar_pocion_ataque = new Rectangle(985,484,264,38);
        
        grphcs.setColor(naranjaOscuro);
        grphcs.fillRect(0, 0, 360, 720);
        grphcs.fillRect(920, 0, 360, 720);
        grphcs.setColor(naranja);
        grphcs.fillRect(5, 5, 350, 710);
        grphcs.fillRect(925, 5, 350, 710);
        grphcs.setColor(Color.black);
        grphcs.setFont(new TrueTypeFont(new Font("Regular", Font.BOLD, 30), true));
        grphcs.drawString("Vender:", 37, 36);
        grphcs.drawString("Comprar:", 974, 36);
        grphcs.setFont(new TrueTypeFont(font, true));
        grphcs.drawString("Plantas", 37, 85);
        grphcs.drawString("Plantas", 974, 85);
        grphcs.drawString("Pociones", 37, 243);
        grphcs.drawString("Semillas", 974, 243);
        grphcs.drawString("Pociones", 974, 405);
        grphcs.setColor(naranjaOscuro);
        grphcs.drawRect(36, 121, 284, 1);
        grphcs.drawRect(36, 279, 284, 1);
        grphcs.drawRect(973, 121, 254, 1);
        grphcs.drawRect(973, 279, 254, 1);
        grphcs.drawRect(973, 439, 254, 1);
        grphcs.setColor(naranjaOscuro);
        if(vender_planta_agua.intersects(mouse) && inventario.getCantidad(new Planta_agua())!=0){
            grphcs.fillRect(44, 125, 287, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(46, 127, 283, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(vender_planta_fuego.intersects(mouse) && inventario.getCantidad(new Planta_fuego())!=0){
            grphcs.fillRect(44, 162, 287, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(46, 164, 283, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(vender_planta_rayo.intersects(mouse) && inventario.getCantidad(new Planta_rayo())!=0){
            grphcs.fillRect(44, 199, 287, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(46, 201, 283, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(vender_pocion_vida.intersects(mouse) && inventario.getCantidad(new Pocion_vida())!=0){
            grphcs.fillRect(44, 283, 287, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(46, 285, 283, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(vender_pocion_ataque.intersects(mouse) && inventario.getCantidad(new Pocion_ataque())!=0){
            grphcs.fillRect(44, 320, 287, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(46, 322, 283, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(comprar_planta_agua.intersects(mouse) && ruby.getDinero()>=100){
            grphcs.fillRect(983, 125, 268, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(985, 127, 264, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(comprar_planta_fuego.intersects(mouse) && ruby.getDinero()>=250){
            grphcs.fillRect(983, 162, 268, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(985, 164, 264, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(comprar_planta_rayo.intersects(mouse) && ruby.getDinero()>=750){
            grphcs.fillRect(983, 199, 268, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(985, 201, 264, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(comprar_semilla_agua.intersects(mouse) && ruby.getDinero()>=20){
            grphcs.fillRect(983, 283, 268, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(985, 285, 264, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(comprar_semilla_fuego.intersects(mouse) && ruby.getDinero()>=50){
            grphcs.fillRect(983, 320, 268, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(985, 322, 264, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(comprar_semilla_rayo.intersects(mouse) && ruby.getDinero()>=150){
            grphcs.fillRect(983, 357, 268, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(985, 359, 264, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(comprar_pocion_vida.intersects(mouse) && ruby.getDinero()>=300){
            grphcs.fillRect(983, 445, 268, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(985, 447, 264, 38);
            grphcs.setColor(naranjaOscuro);
        }else if(comprar_pocion_ataque.intersects(mouse) && ruby.getDinero()>=1000){
            grphcs.fillRect(983, 482, 268, 42);
            grphcs.setColor(naranja);
            grphcs.fillRect(985, 484, 264, 38);
            grphcs.setColor(naranjaOscuro);
        }
        objeto = new Planta_agua();
        objeto.getIcono().draw(51, 130, 32, 32);
        objeto.getIcono().draw(988, 130, 32, 32);
        objeto = new Planta_fuego();
        objeto.getIcono().draw(51, 167, 32, 32);
        objeto.getIcono().draw(988, 167, 32, 32);
        objeto = new Planta_rayo();
        objeto.getIcono().draw(51, 204, 32, 32);
        objeto.getIcono().draw(988, 204, 32, 32);
        objeto = new Pocion_vida();
        objeto.getIcono().draw(51, 288, 32, 32);
        objeto.getIcono().draw(988, 450, 32, 32);
        objeto = new Pocion_ataque();
        objeto.getIcono().draw(51, 325, 32, 32);
        objeto.getIcono().draw(988, 487, 32, 32);
        objeto = new Semilla_agua();
        objeto.getIcono().draw(988, 288, 32, 32);
        objeto = new Semilla_fuego();
        objeto.getIcono().draw(988, 325, 32, 32);
        objeto = new Semilla_rayo();
        objeto.getIcono().draw(988, 362, 32, 32);
        grphcs.setColor(Color.black);
        grphcs.drawString("Planta de agua 50$ (x" + inventario.getCantidad(new Planta_agua()) + ")", 92, 133);
        grphcs.drawString("Planta de fuego 125$ (x" + inventario.getCantidad(new Planta_fuego()) + ")", 92, 170);
        grphcs.drawString("Planta de rayo 375$ (x" + inventario.getCantidad(new Planta_rayo()) + ")", 92, 207);
        grphcs.drawString("Pocion de vida 150$ (x" + inventario.getCantidad(new Pocion_vida()) + ")", 92, 291);
        grphcs.drawString("Pocion de ataque 500$ (x" + inventario.getCantidad(new Pocion_ataque()) + ")", 92, 330);
        grphcs.drawString("Planta de agua  100$", 1029, 133);
        grphcs.drawString("Planta de fuego  250$", 1029, 170);
        grphcs.drawString("Planta de rayo  750$", 1029, 207);
        grphcs.drawString("Semilla de agua  20$", 1029, 291);
        grphcs.drawString("Semilla de fuego  50$", 1029, 328);
        grphcs.drawString("Semilla de rayo  150$", 1029, 365);
        grphcs.drawString("Pocion de vida  300$", 1029, 453);
        grphcs.drawString("Pocion de ataque  1000$", 1029, 490);
        if(vender_planta_agua.intersects(mouse) && inventario.getCantidad(new Planta_agua())!=0 && click){
            ruby.getInventario().eliminarObjeto(new Planta_agua());
            ruby.sumarDinero(50);
        }else if(vender_planta_fuego.intersects(mouse) && inventario.getCantidad(new Planta_fuego())!=0 && click){
            ruby.getInventario().eliminarObjeto(new Planta_fuego());
            ruby.sumarDinero(125);
        }else if(vender_planta_rayo.intersects(mouse) && inventario.getCantidad(new Planta_rayo())!=0 && click){
            ruby.getInventario().eliminarObjeto(new Planta_rayo());
            ruby.sumarDinero(375);
        }else if(vender_pocion_vida.intersects(mouse) && inventario.getCantidad(new Pocion_vida())!=0 && click){
            ruby.getInventario().eliminarObjeto(new Pocion_vida());
            ruby.sumarDinero(150);
        }else if(vender_pocion_ataque.intersects(mouse) && inventario.getCantidad(new Pocion_ataque())!=0 && click){
            ruby.getInventario().eliminarObjeto(new Pocion_ataque());
            ruby.sumarDinero(500);
        }else if(comprar_planta_agua.intersects(mouse) && ruby.getDinero()>=100 && click){
            ruby.getInventario().anadirObjeto(new Planta_agua(), 1);
            ruby.quitarDinero(100);
        }else if(comprar_planta_fuego.intersects(mouse) && ruby.getDinero()>=250 && click){
            ruby.getInventario().anadirObjeto(new Planta_fuego(), 1);
            ruby.quitarDinero(250);
        }else if(comprar_planta_rayo.intersects(mouse) && ruby.getDinero()>=750 && click){
            ruby.getInventario().anadirObjeto(new Planta_rayo(), 1);
            ruby.quitarDinero(750);
        }else if(comprar_semilla_agua.intersects(mouse) && ruby.getDinero()>=20 && click){
            ruby.getInventario().anadirObjeto(new Semilla_agua(), 1);
            ruby.quitarDinero(20);
        }else if(comprar_semilla_fuego.intersects(mouse) && ruby.getDinero()>=50 && click){
            ruby.getInventario().anadirObjeto(new Semilla_fuego(), 1);
            ruby.quitarDinero(50);
        }else if(comprar_semilla_rayo.intersects(mouse) && ruby.getDinero()>=150 && click){
            ruby.getInventario().anadirObjeto(new Semilla_rayo(), 1);
            ruby.quitarDinero(150);
        }else if(comprar_pocion_vida.intersects(mouse) && ruby.getDinero()>=300 && click){
            ruby.getInventario().anadirObjeto(new Pocion_ataque(), 1);
            ruby.quitarDinero(300);
        }else if(comprar_pocion_ataque.intersects(mouse) && ruby.getDinero()>=1000 && click){
            ruby.getInventario().anadirObjeto(new Pocion_vida(), 1);
            ruby.quitarDinero(1000);
        }
        grphcs.drawString("Dinero: "+ruby.getDinero()+"$", 54, 642);
    }
}