package core;

import estados.Demo;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{

    private AppGameContainer contenedor;
    
    public Main(String string) throws SlickException {
        super(string);
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(1280, 720, false);
        //contenedor.setShowFPS(false);
        contenedor.start();
    }

    
    //  Inicializa la lista de estados del juego
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new Demo());
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
