package personajes;

import elementos.Hitbox;
import org.newdawn.slick.SlickException;

public class Jugador extends Personaje {

    private int nivel;
    private boolean combate;
    
    public Jugador(Hitbox hitbox) throws SlickException {
        super("Ruby", hitbox, new Animacion_dinamica("./resources/sprites/sprite_esqueleto.png"), 100, 100);
    }

    public Jugador(String nombre, Hitbox hitbox, Animacion animacion, int vida, int dinero) {
        super(nombre, hitbox, animacion, vida, dinero);
    }

    // TODO: implementar movimiento jugador
    // TODO: pelear
    // TODO: comprar
    // TODO: vender
    // TODO: plantar
    // TODO: recolectar
}
