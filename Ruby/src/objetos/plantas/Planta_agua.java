package objetos.plantas;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Planta_agua extends Planta{
    private String nombre = "Planta de agua";
    private Image icono;
    
    public Planta_agua() throws SlickException{
        super("./resources/objetos/plantas/Planta_agua.png");
        icono = new Image("./resources/objetos/plantas/Icono_planta_agua.png");
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
    
    @Override
    public Image getIcono(){
        return icono;
    }
}
