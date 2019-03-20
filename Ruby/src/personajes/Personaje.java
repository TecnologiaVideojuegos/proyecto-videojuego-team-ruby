package personajes;

import elementos.Hitbox;

public abstract class Personaje {

    private String nombre;
    private Hitbox hitbox;
    private Animacion animacion;
    private int vida;
    private int dinero;
    //private String[][] inventario;

    public Personaje(String nombre, Hitbox hitbox, Animacion animacion, int vida, int dinero) {
        this.nombre = nombre;
        this.hitbox = hitbox;
        this.animacion = animacion;
        this.vida = vida;
        this.dinero = dinero;
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

    public Animacion getAnimacion() {
        return animacion;
    }

    public void setAnimacion(Animacion animacion) {
        this.animacion = animacion;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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
}
