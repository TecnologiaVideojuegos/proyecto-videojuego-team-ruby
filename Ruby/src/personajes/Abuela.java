package personajes;

import animaciones.Animacion_dinamica;
import elementos.Hitbox;
import org.newdawn.slick.SlickException;

public class Abuela extends Npc{
    
    public Abuela(Hitbox hitbox, int vida, int dinero) throws SlickException {
        super("Abuela", hitbox, new Animacion_dinamica("./resources/sprites/Abuela.png"), vida, dinero);
    }
}
