package core;

import estados.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

    private AppGameContainer contenedor;

    public Main(String string) throws SlickException {
        super(string);
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(1920, 1080, true);
        contenedor.setVSync(true);
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
