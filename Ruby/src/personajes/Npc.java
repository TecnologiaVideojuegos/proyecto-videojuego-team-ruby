package personajes;

import elementos.Hitbox;
import org.newdawn.slick.SlickException;

public class Npc extends Personaje{
    
    public Npc(Hitbox hitbox) throws SlickException {
        super("NPC", hitbox, new Animacion_estatica("./resources/img/npc.png"), 100, 1000);
    }
    
    public Npc(String nombre, Hitbox hitbox, Animacion animacion, int vida, int dinero) {
        super(nombre, hitbox, animacion, vida, dinero);
    }
    
    // TODO: comprobacion de nivel del jugador para mostrar nivel    
}
