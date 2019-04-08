package personajes;

import elementos.Hitbox;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Npc extends Personaje{
    
    private Animacion_estatica animacion;
    
    public Npc(Hitbox hitbox) throws SlickException {
        super("NPC", hitbox, 100, 1000);
        this.animacion = new Animacion_estatica("./resources/img/npc.png");
    }
    
    public Npc(String nombre, Hitbox hitbox, Animacion_estatica animacion, int vida, int dinero) {
        super(nombre, hitbox, vida, dinero);
        this.animacion = animacion;
    }
    
    // TODO: comprobacion de nivel del jugador para mostrar nivel    
    
     @Override
    public void renderPersonaje(GameContainer gc) {
        animacion.renderAnimacion(gc, hitbox.getRectangulo().getX(), hitbox.getRectangulo().getY());
    }
}
