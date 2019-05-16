package personajes;

import animaciones.Animacion_dinamica;
import elementos.Dialogo;
import elementos.Hitbox;
import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Npc extends Personaje {

    protected Animacion_dinamica animacion;

    public Npc(String nombre, Hitbox hitbox, Animacion_dinamica animacion, int vida, int dinero, ArrayList<Dialogo> dialogos) throws SlickException {
        super(nombre, hitbox, vida, dinero, dialogos);
        this.animacion = animacion;
        animacion.direccionAnimacion(2);
    }

    @Override
    public void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY) {
        animacion.renderAnimacion(-movEjeX, -movEjeY, hitbox.getRectangulo().getX() + 5, hitbox.getRectangulo().getY());
    }
}
