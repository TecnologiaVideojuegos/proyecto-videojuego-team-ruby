package personajes;

import elementos.Hitbox;
import org.newdawn.slick.SlickException;

public class Enemigo extends Personaje {

    private int nivel;
    private boolean combate;

    public Enemigo(String nombre, Hitbox hitbox, int nivel) throws SlickException {
        // TODO: modificar animación original por Animacion_dinamica cuando haya sprite
        super(nombre, hitbox, new Animacion_estatica("./resources/img/enemigo.gif"), 100, 20);

        if (nombre.equals("Boss")) {
            setAnimacion(new Animacion_estatica("./resources/img/enemigo.gif"));
        }
        
        this.nivel = nivel;
        this.combate = false;
    }

    public Enemigo(String nombre, Hitbox hitbox, Animacion animacion, int vida, int dinero, int nivel) {
        super(nombre, hitbox, animacion, vida, dinero);
        this.nivel = nivel;
        this.combate = false;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isCombate() {
        return combate;
    }

    public void setCombate(boolean combate) {
        this.combate = combate;
    }

    // TODO: Immplementar movimiento del enemigo dinamico
}
