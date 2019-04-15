package personajes;

import elementos.Hitbox;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Jugador extends Personaje {

    private int nivel;
    private boolean combate;
    private Animacion_dinamica animacion;

    public Jugador(Hitbox hitbox) throws SlickException {
        super("Ruby", hitbox, 100, 100);
        this.animacion = new Animacion_dinamica("./resources/sprites/Ruby.png");
    }

    public Jugador(String nombre, Hitbox hitbox, Animacion_dinamica animacion, int vida, int dinero) {
        super(nombre, hitbox, vida, dinero);
        this.animacion = animacion;
    }

    // TODO: implementar movimiento jugador
    // TODO: pelear
    // TODO: comprar
    // TODO: vender
    // TODO: plantar
    // TODO: recolectar
    @Override
    public void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY) {
        animacion.renderAnimacion(movEjeX, movEjeY, (gc.getWidth() / 2), (gc.getHeight() / 2)); 
    }
}
