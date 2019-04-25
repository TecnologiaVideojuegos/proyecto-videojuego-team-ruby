package core;

import elementos.Hitbox;
import estados.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import personajes.Jugador;

public class Main extends StateBasedGame {

    private AppGameContainer contenedor;
    private Jugador ruby;
    private int size_Ruby = 3, ancho_Ruby = 32, largo_Ruby = 32;
    //TEST HITBOX
    private boolean ver_hitbox = true;
    public Main(String string) throws SlickException {
        super(string);
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(1280, 720, true);
        contenedor.setVSync(true);
        //contenedor.setShowFPS(true);
        contenedor.start();
    }

    //  Inicializa la lista de estados del juego
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        ruby = new Jugador(new Hitbox(gc.getWidth() / 2 - (ancho_Ruby - 30) - 5, (gc.getHeight() / 2 - (largo_Ruby - 25)) + 45, 40, 32));
        this.addState(new Prueba(ruby, ver_hitbox));
        this.addState(new Demo());
        this.addState(new Prueba_Mazmorra(ruby, ver_hitbox));
    }

    public static void main(String[] args) {
        try {
            new Main("Ruby");
        } catch (SlickException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}
