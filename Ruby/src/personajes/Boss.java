package personajes;

import animaciones.Animacion_dinamica;
import elementos.Dialogo;
import elementos.Hitbox;
import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Boss extends Personaje {

    private int nivel;
    private boolean combate;
    private Animacion_dinamica animacion;

    public Boss(String nombre, Hitbox hitbox, int nivel) throws SlickException {
        super(nombre, hitbox, 100, 50*nivel, new ArrayList<Dialogo>());
        animacion = new Animacion_dinamica("./resources/sprites/Boss.png");
        animacion.direccionAnimacion(2);
        this.nivel = nivel;
        this.combate = false;
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("¿Has visto a mi gato Manchas?"))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Sí, ahora es mio"))));
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("¿Has visto a mi gato Manchas?"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("¿Has visto a mi gato Manchas?"))));
    }

    public Boss(String nombre, Hitbox hitbox, Animacion_dinamica animacion, int vida, int dinero, int nivel) throws SlickException {
        super(nombre, hitbox, vida, dinero);
        this.nivel = nivel;
        this.combate = false;
        this.animacion = animacion;
        animacion.direccionAnimacion(2);
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
        animacion.renderAnimacion(-movEjeX, -movEjeY, hitbox.getRectangulo().getX() + 5, hitbox.getRectangulo().getY());
    }

    public boolean ataque(Personaje enemigo) {
        boolean exito = false;
        int numRandom = (int) (Math.random() * 8);
        int danio = 0;

        if (numRandom != 0) {
            danio = 5 * nivel;
            if (numRandom == 2) {                   //Critico
                danio *= 2;
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
