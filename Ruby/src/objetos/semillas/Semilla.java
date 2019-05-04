package objetos.semillas;

import objetos.Objeto;
import org.newdawn.slick.Image;

public abstract class Semilla extends Objeto{

    @Override
    public abstract String getNombre();

    @Override
    public String tipoObjeto() {
        return "Semilla";
    }
    
    @Override
    public abstract Image getIcono();
}
