package personajes;

import elementos.Dialogo;
import elementos.Hitbox;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;

public abstract class Personaje{

    private String nombre;
    protected Hitbox hitbox;
    private int vida;
    private int dinero;
    private ArrayList<Dialogo> dialogos;
    //private String[][] inventario;

    public Personaje(String nombre, Hitbox hitbox, int vida, int dinero) {
        this.nombre = nombre;
        this.hitbox = hitbox;
        this.vida = vida;
        this.dinero = dinero;
    }
    
    public Personaje(String nombre, Hitbox hitbox, int vida, int dinero, ArrayList<Dialogo> dialogos) {
        this.nombre = nombre;
        this.hitbox = hitbox;
        this.vida = vida;
        this.dinero = dinero;
        this.dialogos = dialogos;
    }

    public void quitarDinero(int d){
        dinero = dinero - d;
    }
    
    public void sumarDinero(int d){
        dinero = + d;
    }
    
    public ArrayList<Dialogo> getDialogos() {
        return dialogos;
    }

    public void setDialogos(ArrayList<Dialogo> dialogos) {
        this.dialogos = dialogos;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if(vida<0){
            this.vida = 0;
        }else if(vida>100){
            this.vida = 100;
        }else{
            this.vida = vida;
        }
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    // TODO: Update hitbox
    /* TODO: Update animacion
             Render animacion
     */
    public abstract void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY);
}
