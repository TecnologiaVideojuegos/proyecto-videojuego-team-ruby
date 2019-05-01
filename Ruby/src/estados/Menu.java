package estados;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import personajes.Jugador;

public class Menu extends BasicGameState {

    private Jugador ruby;
    private Image fondo;
    private Font font;
    private int p_Widht, p_Height;
    private Circle cursor_hitbox;
    private Rectangle nuevaPartida, cargarPartida, salir;
    private boolean inicio = true;
    private boolean pulsado_nuevaPartida = false, pulsado_cargarPartida = false, pulsado_salir = false;

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

        //Elementos de colisión/interacción
        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);
        nuevaPartida = new Rectangle(p_Widht / 5 * 3 - 10, p_Height / 6 * 2 - 27, 380, 100);
        cargarPartida = new Rectangle(p_Widht / 5 * 3 - 10, p_Height / 6 * 3 - 27, 380, 100);
        salir = new Rectangle(p_Widht / 5 * 4 - 20, p_Height / 6 * 5 - 27, 150, 100);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics grphcs) throws SlickException {
        fondo.draw(0, 0, p_Widht, p_Height);

        /*
        COLORES:
        Rojo->0xF0544F
        Gris->0x3A3335
        MoradoOscuro->0x685762
        RosaOscuro->0xE2B4BD
         */
        //Texto Ruby
        grphcs.setColor(new Color(0xF0544F));
        grphcs.fillRect(p_Widht / 4 * 1 - 115 - 5, p_Height / 9 * 4 - 27 - 5, 310, 110);    //Rectangulo exterior
        grphcs.setColor(new Color(0x685762));
        grphcs.fillRect(p_Widht / 4 * 1 - 115, p_Height / 9 * 4 - 27, 300, 100);            //Rectangulo interior
        grphcs.setColor(new Color(0xF0544F));
        font = new Font("Verdana", Font.BOLD, 40);
        grphcs.setFont(new TrueTypeFont(font, true));
        grphcs.drawString("RUBY", p_Widht / 4 * 1 - 30, p_Height / 9 * 4);                 //Impresion Ruby en pantalla

        //----------------------------- Botones ----------------------------- //
        //Boton Nueva partida
        grphcs.setColor(new Color(0xF0544F));
        grphcs.fillRect(p_Widht / 5 * 3 - 10 - 5, p_Height / 6 * 2 - 27 - 5, 390, 110);    //Rectangulo exterior Nueva partida
        if (pulsado_nuevaPartida) {
            grphcs.setColor(new Color(0xE2B4BD));
        } else {
            grphcs.setColor(new Color(0x685762));
        }
        grphcs.fillRect(p_Widht / 5 * 3 - 10, p_Height / 6 * 2 - 27, 380, 100);            //Rectangulo interior Nueva partida

        
        //Boton Cargar/Guardar partida
        grphcs.setColor(new Color(0xF0544F));
        grphcs.fillRect(p_Widht / 5 * 3 - 10 - 5, p_Height / 6 * 3 - 27 - 5, 390, 110);    //Rectangulo exterior Cargar/Guardar partida
        if (pulsado_cargarPartida) {
            grphcs.setColor(new Color(0xE2B4BD));
        } else {
            grphcs.setColor(new Color(0x685762));
        }
        grphcs.fillRect(p_Widht / 5 * 3 - 10, p_Height / 6 * 3 - 27, 380, 100);            //Rectangulo interior Cargar/Guardar partida
        
        
        //Boton Salir
        grphcs.setColor(new Color(0xF0544F));
        grphcs.fillRect(p_Widht / 5 * 4 - 20 - 5, p_Height / 6 * 5 - 27 - 5, 160, 110);    //Rectangulo exterior Salir
        if (pulsado_salir) {
            grphcs.setColor(new Color(0xE2B4BD));
        } else {
            grphcs.setColor(new Color(0x685762));
        }
        grphcs.fillRect(p_Widht / 5 * 4 - 20, p_Height / 6 * 5 - 27, 150, 100);            //Rectangulo interior Salir

        grphcs.setColor(Color.yellow);
        grphcs.drawRect(nuevaPartida.getX(), nuevaPartida.getY(), nuevaPartida.getWidth(), nuevaPartida.getHeight());
        grphcs.drawRect(cargarPartida.getX(), cargarPartida.getY(), cargarPartida.getWidth(), cargarPartida.getHeight());
        grphcs.drawRect(salir.getX(), salir.getY(), salir.getWidth(), salir.getHeight());

        grphcs.setColor(new Color(0xF0544F));
        font = new Font("Verdana", Font.BOLD, 40);
        grphcs.setFont(new TrueTypeFont(font, true));
        grphcs.drawString("Nueva partida", p_Widht / 5 * 3, p_Height / 6 * 2);
        if(inicio){
            grphcs.drawString("Cargar partida", p_Widht / 5 * 3, p_Height / 6 * 3);
        }else{
             grphcs.drawString("Guardar partida", p_Widht / 5 * 3, p_Height / 6 * 3);
        }       
        grphcs.drawString("Salir", p_Widht / 5 * 4, p_Height / 6 * 5);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        cursor_hitbox.setX(gc.getInput().getMouseX() - (cursor_hitbox.getHeight() / 2));
        cursor_hitbox.setY(gc.getInput().getMouseY() - (cursor_hitbox.getWidth() / 2));

        if (cursor_hitbox.intersects(nuevaPartida)) {
            pulsado_nuevaPartida = true;
            if (gc.getInput().isMouseButtonDown(0)){
                inicio = false;
                game.enterState(1);
            }
        } else {
            pulsado_nuevaPartida = false;
        }

        if (cursor_hitbox.intersects(cargarPartida)) {
            pulsado_cargarPartida = true;
            if (gc.getInput().isMouseButtonDown(0)){
                inicio = false;
                game.enterState(1);
            }
        } else {
            pulsado_cargarPartida = false;
        }

        if (cursor_hitbox.intersects(salir)) {
            pulsado_salir = true;
            if (gc.getInput().isMouseButtonDown(0)){
                System.exit(1);
            }
        } else {
            pulsado_salir = false;
        }
    }

}
