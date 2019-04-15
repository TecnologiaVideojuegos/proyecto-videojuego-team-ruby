package personajes;

import elementos.Hitbox;
import elementos.Mapa;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

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

    public float[] capturaMovimiento(GameContainer gc, int i, Mapa map) {
        float mov[] = new float[2];
        float x = 0, y = 0;
        
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de updateç
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de updateç
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de updateç
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de updateç
        }

        mov[0] = x;
        mov[1] = y;

        return mov;
    }

    public float[] colision(ArrayList<Hitbox> hitboxes, int i, GameContainer gc, Mapa map, Rectangle personaje) {
        float mov[] = new float[2];
        float x = 0, y = 0;
        
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, -(i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y += i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, (i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(-(i / 3.f), 0);
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x += i / 3.f;  //i=tiempo de update
            map.actualizarElementos((i / 3.f), 0);
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, (i / 3.f));
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    y -= i / 3.f;  //i=tiempo de update
                    map.actualizarElementos(0, -(i / 3.f));
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, -(i / 3.f));
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    y += i / 3.f;  //i=tiempo de update
                    map.actualizarElementos(0, (i / 3.f));
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de update
            map.actualizarElementos((i / 3.f), 0);
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    x -= i / 3.f;  //i=tiempo de update
                    map.actualizarElementos(-(i / 3.f), 0);
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(-(i / 3.f), 0);
            for (Hitbox re : hitboxes) {
                if (re.getRectangulo().intersects(personaje)) {
                    x += i / 3.f;  //i=tiempo de update
                    map.actualizarElementos((i / 3.f), 0);
                }
            }
        }
        mov[0] = x;
        mov[1] = y;

        return mov;
    }
    /*
    public float[] colision(ArrayList<Hitbox> re, int i, GameContainer gc, Mapa map) {
        float mov[] = new float[2];
        float x = 0, y = 0;
        
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, -(i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y += i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, (i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(-(i / 3.f), 0);
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x += i / 3.f;  //i=tie mpo de update
            map.actualizarElementos((i / 3.f), 0);
        }
        
        mov[0] = x;
        mov[1] = y;

        return mov;
    }

    public void mejoraColision(int i, GameContainer gc, Mapa map) {
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de update
            actualizaMuros(0, (i / 3.f));
            for (Rectangle re : blocks) {
                if (re.intersects(personaje_R)) {
                    y -= i / 3.f;  //i=tiempo de update
                    actualizaMuros(0, -(i / 3.f));
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de update
            actualizaMuros(0, -(i / 3.f));
            for (Rectangle re : blocks) {
                if (re.intersects(personaje_R)) {
                    y += i / 3.f;  //i=tiempo de update
                    actualizaMuros(0, (i / 3.f));
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de update
            actualizaMuros((i / 3.f), 0);
            for (Rectangle re : blocks) {
                if (re.intersects(personaje_R)) {
                    x -= i / 3.f;  //i=tiempo de update
                    actualizaMuros(-(i / 3.f), 0);
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de update
            actualizaMuros(-(i / 3.f), 0);
            for (Rectangle re : blocks) {
                if (re.intersects(personaje_R)) {
                    x += i / 3.f;  //i=tiempo de update
                    actualizaMuros((i / 3.f), 0);
                }
            }
        }
    }
     */
    // TODO: implementar movimiento jugador
    // TODO: pelear
    // TODO: comprar
    // TODO: vender
    // TODO: plantar
    // TODO: recolectar

    @Override
    public void renderPersonaje(GameContainer gc) {
        animacion.renderAnimacion(gc, hitbox.getRectangulo().getX(), hitbox.getRectangulo().getY());
    }
}
