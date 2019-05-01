package estados;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import personajes.Jugador;

public class Menu extends BasicGameState {

    private Jugador ruby;
    private Image fondo;
    private Font font;
    private int p_Widht, p_Height;

    public Menu(Jugador ruby) throws SlickException {
        this.ruby = ruby;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        fondo = new Image("./resources/img/menu.png");
        p_Widht = gc.getWidth();
        p_Height = gc.getHeight();
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics grphcs) throws SlickException {
        fondo.draw(0, 0, p_Widht, p_Height);

        //Ruby
        grphcs.setColor(new Color(0x3A3335));
        grphcs.fillRect(p_Widht / 4 * 1-115, p_Height / 4 * 2-27, 300, 100);
        grphcs.setColor(new Color(0xF0544F));
        grphcs.drawRect(p_Widht / 4 * 1-115, p_Height / 4 * 2-27, 300, 100);
        font = new Font("Verdana", Font.BOLD, 40);
        grphcs.setFont(new TrueTypeFont(font, true));
        grphcs.drawString("RUBY", p_Widht / 4 * 1, p_Height / 4 * 2);

        //Botones
        font = new Font("Verdana", Font.BOLD, 40);
        grphcs.setFont(new TrueTypeFont(font, true));
        grphcs.drawString("Nueva partida", p_Widht / 4 * 3, p_Height / 4 * 1);
        grphcs.drawString("Cargar partida", p_Widht / 4 * 3, p_Height / 4 * 3);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {

    }

}
