package personajes;

import animaciones.Animacion_dinamica;
import elementos.Hitbox;
import org.newdawn.slick.SlickException;

public class Gato extends Npc{

    public Gato(Hitbox hitbox, int vida, int dinero) throws SlickException {
        super("Gato", hitbox, new Animacion_dinamica("./resources/sprites/Gato.png"), vida, dinero);
    }
}
