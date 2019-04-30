package objetos;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Objeto> inventario;
    
    public Inventario(){
        inventario = new ArrayList<>();
    }
    
    public void anadirObjeto(Objeto objeto, int i){
        boolean anadir = true;
        Objeto objeto_temp = null;
        int j = 0;
        for(Objeto objeto_inventario: inventario){
            if(objeto_inventario.equals(objeto)){
                anadir=false;
                objeto_temp=objeto_inventario;
                break;
            }
            j++;
        }
        if(anadir){
            objeto_temp.aumentarCantidad(i);
            inventario.set(j, objeto_temp);
        }else{
            objeto_temp = objeto;
            objeto_temp.setCantidad(i);
            inventario.add(objeto_temp);
        }
    }
    
    public void anadirObjeto(Objeto objeto){
        boolean anadir = true;
        Objeto objeto_temp = null;
        int j = 0;
        for(Objeto objeto_inventario: inventario){
            if(objeto_inventario.equals(objeto)){
                anadir=false;
                objeto_temp=objeto_inventario;
                break;
            }
            j++;
        }
        if(anadir){
            objeto_temp.aumentarCantidad(1);
            inventario.set(j, objeto_temp);
        }else{
            objeto_temp = objeto;
            objeto_temp.setCantidad(1);
            inventario.add(objeto_temp);
        }
    }
    
    /**
     * Elimina uno de cantidad del objeto introducido
     * @param objeto
     * @return Un boolean que indica si ese objeto se encontraba en el inventario
     */
    public boolean eliminarObjeto(Objeto objeto){
        boolean esta = false;
        Objeto objeto_temp = null;
        int j = 0;
        for(Objeto objeto_ineventario: inventario){
            if(objeto_ineventario.equals(objeto)){
                esta = true;
                break;
            }
            j++;
        }
        if(esta){
            objeto_temp = inventario.get(j);
            objeto_temp.reducirCantidad(1);
            inventario.set(j, objeto_temp);
        }
        return esta;
    }
}
