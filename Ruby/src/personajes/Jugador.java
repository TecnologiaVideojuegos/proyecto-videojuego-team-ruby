package personajes;

import animaciones.Animacion_dinamica;
import elementos.Hitbox;
import objetos.Inventario;
import objetos.plantas.Planta_agua;
import objetos.plantas.Planta_fuego;
import objetos.plantas.Planta_rayo;
import objetos.pociones.Pocion_ataque;
import objetos.pociones.Pocion_vida;
import objetos.semillas.Semilla_agua;
import objetos.semillas.Semilla_fuego;
import objetos.semillas.Semilla_rayo;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Jugador extends Personaje {

    private int nivel;
    private boolean combate;
    private Animacion_dinamica animacion;
    private Inventario inventario = new Inventario();

    public Jugador(Hitbox hitbox) throws SlickException {
        super("Ruby", hitbox, 100, 100);
        this.animacion = new Animacion_dinamica("./resources/sprites/Ruby.png");
        inventario.anadirObjeto(new Planta_fuego(), 1);
        inventario.anadirObjeto(new Semilla_fuego(), 1);
        inventario.anadirObjeto(new Planta_agua(), 1);
        inventario.anadirObjeto(new Semilla_agua(), 1);
        inventario.anadirObjeto(new Planta_rayo(), 1);
        inventario.anadirObjeto(new Semilla_rayo(), 1);
        inventario.anadirObjeto(new Pocion_vida(), 1);
        inventario.anadirObjeto(new Pocion_ataque(), 1);
        inventario.eliminarObjeto(new Planta_fuego());
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

    public Inventario getInventario() {
        return inventario;
    }
}
