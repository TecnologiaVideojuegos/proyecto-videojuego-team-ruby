package objetos.semillas;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Semilla_rayo extends Semilla{
    private String nombre = "Semilla de rayo";
    private Image icono;
    
    public Semilla_rayo() throws SlickException{
        super();
        icono = new Image("./resources/objetos/semillas/Icono_semilla_rayo.png");
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