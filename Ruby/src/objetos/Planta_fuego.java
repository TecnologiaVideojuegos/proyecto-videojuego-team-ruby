package objetos;

import org.newdawn.slick.SlickException;

public class Planta_fuego extends Planta{
    private String nombre = "Planta de fuego";
    
    public Planta_fuego() throws SlickException{
        super("./resources/sprites/Planta_fuego.png");
    }

    @Override
    public void plantar(float pos_x, float pos_y){
        super.setCoordenadas(pos_x, pos_y);
        super.setPlantada(true);
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
