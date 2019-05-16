package personajes;

import elementos.Dialogo;
import elementos.Hitbox;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Personaje{

    private String nombre;
    protected Hitbox hitbox;
    private int vida;
    private int dinero;
    private ArrayList<Dialogo> dialogos;
    private Image imagen;
    //private String[][] inventario;

    public Personaje(String nombre, Hitbox hitbox, int vida, int dinero) throws SlickException {
        this.nombre = nombre;
        this.hitbox = hitbox;
        this.vida = vida;
        this.dinero = dinero;
        if(nombre.equals("Enemigo")){
            imagen = new Image("./resources/sprites/Imagen_enemigo.png");
        }else if(nombre.equals("Boss")){
            imagen = new Image("./resources/sprites/Imagen_boss.png");
        }else if(nombre.equals("Ruby")){
            imagen = new Image("./resources/sprites/Imagen_ruby.png");
        }else if(nombre.equals("Abuela")){
            imagen = new Image("./resources/sprites/Imagen_abuela.png");
        }else if(nombre.equals("Gato")){
            imagen = new Image("./resources/sprites/Imagen_gato.png");
        }
    }
    
    public Personaje(String nombre, Hitbox hitbox, int vida, int dinero, ArrayList<Dialogo> dialogos) throws SlickException {
        this.nombre = nombre;
        this.hitbox = hitbox;
        this.vida = vida;
        this.dinero = dinero;
        this.dialogos = dialogos;
        if(nombre.equals("Enemigo")){
            imagen = new Image("./resources/sprites/Imagen_enemigo.png");
        }else if(nombre.equals("Boss")){
            imagen = new Image("./resources/sprites/Imagen_boss.png");
        }else if(nombre.equals("Ruby")){
            imagen = new Image("./resources/sprites/Imagen_ruby.png");
        }else if(nombre.equals("Abuela")){
            imagen = new Image("./resources/sprites/Imagen_abuela.png");
        }else if(nombre.equals("Gato")){
            imagen = new Image("./resources/sprites/Imagen_gato.png");
        }
    }

    public Image getImagen() {
        return imagen;
    }

    public void quitarDinero(int d){
        dinero = dinero - d;
    }
    
    public void sumarDinero(int d){
        dinero = dinero + d;
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

    public abstract void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY);
}
