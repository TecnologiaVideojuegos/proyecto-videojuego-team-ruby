package objetos.semillas;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Semilla_agua extends Semilla{
    private String nombre = "Semilla de agua";
    private Image icono;
    
    public Semilla_agua() throws SlickException{
        super();
        icono = new Image("./resources/objetos/semillas/Icono_semilla_agua.png");
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