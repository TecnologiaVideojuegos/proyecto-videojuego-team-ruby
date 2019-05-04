package objetos.semillas;

import objetos.semillas.Semilla;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Semilla_fuego extends Semilla{
    private String nombre = "Semilla de fuego";
    private Image icono;
    
    public Semilla_fuego() throws SlickException{
        super();
        icono = new Image("./resources/objetos/semillas/Icono_semilla_fuego.png");
    }
    
    @Override
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public Image getIcono(){
        return icono;
    }
    
}