package objetos.pociones;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Pocion_ataque extends Pocion{
    private String nombre = "Pocion de ataque";
    private Image icono;

    public Pocion_ataque() throws SlickException {
        icono = new Image("./resources/objetos/pociones/Icono_pocion_ataque.png");
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