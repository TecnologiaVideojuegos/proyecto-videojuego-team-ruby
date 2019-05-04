package objetos.plantas;

import animaciones.Animacion_planta;
import objetos.Objeto;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Planta extends Objeto {

    private float pos_x, pos_y;
    private boolean plantada;
    private boolean crecida;
    private Animacion_planta animacion;

    public Planta(String sprite) throws SlickException {
        crecida = false;
        plantada = false;
        animacion = new Animacion_planta(sprite);
    }

    public void setCoordenadas(float pos_x, float pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    
    public boolean isCrecida() {
        if (!crecida) {
            crecida = animacion.getFrame() == 1;
        }
        return crecida;
    }

    public float getPos_x() {
        return pos_x;
    }

    public float getPos_y() {
        return pos_y;
    }

    public Animacion_planta getAnimacion() {
        return animacion;
    }

    public void setPlantada(boolean plantada) {
        this.plantada = plantada;
    }

    public abstract void plantar(float pos_x, float pos_y);

    public void render() {
        animacion.renderAnimacion(pos_x, pos_y);
    }
    
    @Override
    public abstract String getNombre();
    
    @Override
    public String tipoObjeto(){
        return "Planta";
    }
    
    @Override
    public abstract Image getIcono();
}
