package estados;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Demo extends BasicGameState{

    private TiledMap map; //Mapa de la demo
    private double x,y;
    
    public Demo() {
        
    }

    
    /**
     * Inicializa
     */
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new TiledMap("./resources/maps/demo_map.tmx");
    }

    /**
     * Dibuja
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        map.render((int)x, (int)y, 0, 0, gc.getWidth(), gc.getHeight());
        
        //Esfera que simula jugador
        grphcs.fillOval(gc.getWidth()/2-40, gc.getHeight()/2-40, 40, 40);
    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        //Movimiento del jugador
        if(gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)){
            y += i/3.f;  //i=tiempo de update
        }
        if(gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)){
            y -= i/3.f;  //i=tiempo de update
        }
        if(gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)){
            x += i/3.f;  //i=tiempo de update
        }
        if(gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)){
            x -= i/3.f;  //i=tiempo de update
        }
    }

    
    
    @Override
    public int getID() {
        return 0;
    }

}
