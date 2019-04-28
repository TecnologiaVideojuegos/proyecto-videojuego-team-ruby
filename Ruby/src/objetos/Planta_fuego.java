package objetos;

import static java.lang.Thread.sleep;
import org.newdawn.slick.SlickException;

public class Planta_fuego extends Planta{
    
    public Planta_fuego() throws SlickException{
        super("./resources/sprites/Planta_fuego.png");
    }

    public void plantar(float pos_x, float pos_y){
        super.setCoordenadas(pos_x, pos_y);
        super.setPlantada(true);
    }
    
}
