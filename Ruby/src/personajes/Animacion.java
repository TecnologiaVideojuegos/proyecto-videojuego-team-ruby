package personajes;

import org.newdawn.slick.GameContainer;

public abstract class Animacion {

    public Animacion() {
    }
    
    public abstract void renderAnimacion(GameContainer gc, float pos_x, float pos_y);
}
