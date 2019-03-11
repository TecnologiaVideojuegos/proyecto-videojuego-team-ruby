package estados;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Demo extends BasicGameState{

    public Demo() {
        
    }

    
    /**
     * Inicializa
     */
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    /**
     * Dibuja
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.drawString("Hola slick", 100 ,200);
        grphcs.drawLine(50, 60, 100, 90);
        grphcs.fillRect(250, 100, 100, 20);
        grphcs.fillOval(60, 90, 100, 100);
    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
    }

    
    
    @Override
    public int getID() {
        return 0;
    }

}
