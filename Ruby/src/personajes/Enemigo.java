package personajes;

import elementos.Hitbox;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Enemigo extends Personaje {

    private int nivel;
    private boolean combate;
    private Animacion animacion;

    public Enemigo(String nombre, Hitbox hitbox, int nivel) throws SlickException {
        // TODO: modificar animación original por Animacion_dinamica cuando haya sprite
        super(nombre, hitbox, 100, 20);
        this.animacion = new Animacion_dinamica("./resources/sprites/Ruby.png"); //TODO pendiente de modificacion de la ruta

        if (nombre.equals("Boss")) {
            animacion = new Animacion_estatica("./resources/img/enemigo.png");
        }
        
        this.nivel = nivel;
        this.combate = false;
    }

    public Enemigo(String nombre, Hitbox hitbox, Animacion animacion, int vida, int dinero, int nivel) {
        super(nombre, hitbox, vida, dinero);
        this.nivel = nivel;
        this.combate = false;
        this.animacion = animacion;
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

    
    @Override
    public void renderPersonaje(GameContainer gc) {
        animacion.renderAnimacion(gc, hitbox.getRectangulo().getX(), hitbox.getRectangulo().getY());
    }
    // TODO: Immplementar movimiento del enemigo dinamico
}
