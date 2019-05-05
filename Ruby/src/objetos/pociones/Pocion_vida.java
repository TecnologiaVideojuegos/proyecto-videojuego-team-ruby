package objetos.pociones;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Pocion_vida extends Pocion{
    private String nombre = "Pocion de vida";
    private Image icono;

    public Pocion_vida() throws SlickException {
        icono = new Image("./resources/objetos/pociones/Icono_pocion_vida.png");
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Image getIcono() {
        return icono;
    }
    
}
