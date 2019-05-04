package objetos.pociones;

import objetos.Objeto;

public abstract class Pocion extends Objeto{

    @Override
    public abstract String getNombre();

    @Override
    public String tipoObjeto() {
        return "Pocion";
    }
    
}
