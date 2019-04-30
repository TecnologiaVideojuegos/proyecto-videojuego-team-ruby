package objetos;

public abstract class Objeto {
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
    
    public void reducirCantidad(int i){
        cantidad = cantidad - 1;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int i){
        cantidad = i;
    }
    
    public boolean equals(Objeto objeto){
        return objeto.getNombre().equals(this.getNombre());
    }
    
    public abstract String getNombre();
}
