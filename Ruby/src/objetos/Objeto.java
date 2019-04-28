package objetos;

public class Objeto {
    private int cantidad;
    
    public Objeto(){
        cantidad = 0;
    }
    
    public Objeto(int i){
        cantidad = i;
    }
    
    public void aumentarCantidad(int i){
        cantidad = cantidad + i;
    }
}
