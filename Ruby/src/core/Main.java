package core;

import elementos.Hitbox;
import elementos.Huerto;
import estados.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import personajes.Jugador;

public class Main extends StateBasedGame {

    private AppGameContainer contenedor;
    private Jugador ruby;
    private Huerto huerto;
    private int size_Ruby = 3, ancho_Ruby = 32, largo_Ruby = 32;
    //TEST HITBOX
    private boolean ver_hitbox = false;

    public Main(String string) throws SlickException {
        super(string);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize.getHeight() + "-" + screenSize.getWidth());
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(1280, 720, true);
        //contenedor.setDisplayMode((int) screenSize.getWidth(), (int) screenSize.getHeight(), false);
        contenedor.setVSync(true);
        //contenedor.setShowFPS(true);
        contenedor.start();
    }

    //  Inicializa la lista de estados del juego
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        ruby = new Jugador(new Hitbox(gc.getWidth() / 2 - (ancho_Ruby - 30) - 5, (gc.getHeight() / 2 - (largo_Ruby - 25)) + 45, 40, 32));
        huerto = new Huerto();
        this.addState(new Menu(ruby, ver_hitbox));              //0
        this.addState(new Casa(ruby, ver_hitbox, huerto));              //1
        this.addState(new Mazmorra(ruby, ver_hitbox));          //2
        this.addState(new Combate(ruby));                       //3
        this.addState(new Prueba(ruby, ver_hitbox));            //4
        this.addState(new Prueba_Mazmorra(ruby, ver_hitbox));   //5
        this.addState(new Demo());                              //6
        
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
