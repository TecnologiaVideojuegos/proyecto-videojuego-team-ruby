package objetos.pociones;

import objetos.Objeto;
import org.newdawn.slick.Image;

public abstract class Pocion extends Objeto{

    @Override
    public abstract String getNombre();
    
    @Override
    public abstract Image getIcono();

    @Override
    public String tipoObjeto() {
        return "Pocion";
    }
    
}
