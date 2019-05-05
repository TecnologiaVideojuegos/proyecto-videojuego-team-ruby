package personajes;

import animaciones.Animacion_estatica;
import elementos.Hitbox;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Boss extends Personaje {

    private int nivel;
    private boolean combate;
    private Animacion_estatica animacion;

    public Boss(String nombre, Hitbox hitbox, int nivel) throws SlickException {
        // TODO: modificar animación original por Animacion_dinamica cuando haya sprite
        super(nombre, hitbox, 100, 20);
        animacion = new Animacion_estatica("./resources/img/enemigo.png");

        this.nivel = nivel;
        this.combate = false;
    }

    public Boss(String nombre, Hitbox hitbox, Animacion_estatica animacion, int vida, int dinero, int nivel) {
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
    public void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY) {
        animacion.renderAnimacion(hitbox.getRectangulo().getX(), hitbox.getRectangulo().getY());
    }

    public boolean ataque(Personaje enemigo) {
        boolean exito = false;
        int numRandom = (int) (Math.random() * 8);
        int danio = 0;

        if (numRandom != 0) {
            if (numRandom == 2) {                   //Critico
                danio = 10 * 2; //TODO: sustituir por el daño de la planta
            } else {
                danio = 10;
            }

            enemigo.setVida(enemigo.getVida() - danio);
            exito = true;
        }
        return exito;
    }

    public void tomarPocionVida() {
        super.setVida(super.getVida() + 20);
    }
}
