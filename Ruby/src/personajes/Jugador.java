package personajes;

import elementos.Hitbox;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
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

    public float[] capturaMovimiento(GameContainer gc, int i) {
        float mov[] = new float[2];
        float x = 0, y = 0;

        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de update
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de update
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de update
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de update
        }

        mov[0] = x;
        mov[1] = y;

        return mov;
    }

    // TODO: implementar movimiento jugador
    // TODO: pelear
    // TODO: comprar
    // TODO: vender
    // TODO: plantar
    // TODO: recolectar
}
