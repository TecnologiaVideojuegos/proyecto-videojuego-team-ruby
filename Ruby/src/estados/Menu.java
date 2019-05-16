package estados;

import java.awt.Font;
import java.io.IOException;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import personajes.Jugador;
import services.GuardarPartidaIO_Service;

public class Menu extends BasicGameState {

    private Jugador ruby;
    private Image fondo;
    private Font font;
    private int p_Widht, p_Height;
    private Circle cursor_hitbox;
    private Rectangle nuevaPartida_Continuar, cargar_guardarPartida, salir;
    private boolean inicio = true;
    private boolean pulsado_nuevaPartidaContinuar = false, pulsado_cargarGuardarPartida = false, pulsado_salir = false;
    private StateBasedGame game;
    private int estadoAnterior = 0;
    private boolean ver_hitbox;

    public Menu(Jugador ruby, boolean ver_hitbox) throws SlickException {
        this.ruby = ruby;
        this.ver_hitbox = ver_hitbox;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        fondo = new Image("./resources/img/menu.png");
        p_Widht = gc.getWidth();
        p_Height = gc.getHeight();

        //Elementos de colisión/interacción
        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);
        nuevaPartida_Continuar = new Rectangle(p_Widht / 5 * 3 - 10, p_Height / 6 * 2 - 27, 380, 100);
        cargar_guardarPartida = new Rectangle(p_Widht / 5 * 3 - 10, p_Height / 6 * 3 - 27, 380, 100);
        salir = new Rectangle(p_Widht / 5 * 4 - 20, p_Height / 6 * 5 - 27, 150, 100);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics grphcs) throws SlickException {
        fondo.draw(0, 0, p_Widht, p_Height);

        //Texto Ruby
        grphcs.setColor(new Color(0xD27112));
        grphcs.fillRect(p_Widht / 4 * 1 - 115 - 5, p_Height / 9 * 4 - 27 - 5, 310, 110);    //Rectangulo exterior
        grphcs.setColor(new Color(0xFEC375));
        grphcs.fillRect(p_Widht / 4 * 1 - 115, p_Height / 9 * 4 - 27, 300, 100);            //Rectangulo interior
        grphcs.setColor(Color.black);
        font = new Font("Verdana", Font.BOLD, 40);
        grphcs.setFont(new TrueTypeFont(font, true));
        grphcs.drawString("RUBY", p_Widht / 4 * 1 - 30, p_Height / 9 * 4);                 //Impresion Ruby en pantalla

        //----------------------------- Botones ----------------------------- //
        //Boton Nueva partida
        grphcs.setColor(new Color(0xD27112));
        grphcs.fillRect(p_Widht / 5 * 3 - 10 - 5, p_Height / 6 * 2 - 27 - 5, 390, 110);    //Rectangulo exterior Nueva partida
        if (pulsado_nuevaPartidaContinuar) {
            grphcs.setColor(new Color(0xD4A05B));
        } else {
            grphcs.setColor(new Color(0xFEC375));
        }
        grphcs.fillRect(p_Widht / 5 * 3 - 10, p_Height / 6 * 2 - 27, 380, 100);            //Rectangulo interior Nueva partida

        //Boton Cargar/Guardar partida
        grphcs.setColor(new Color(0xD27112));
        grphcs.fillRect(p_Widht / 5 * 3 - 10 - 5, p_Height / 6 * 3 - 27 - 5, 390, 110);    //Rectangulo exterior Cargar/Guardar partida
        if (pulsado_cargarGuardarPartida) {
            grphcs.setColor(new Color(0xD4A05B));
        } else {
            grphcs.setColor(new Color(0xFEC375));
        }
        grphcs.fillRect(p_Widht / 5 * 3 - 10, p_Height / 6 * 3 - 27, 380, 100);            //Rectangulo interior Cargar/Guardar partida

        //Boton Salir
        grphcs.setColor(new Color(0xD27112));
        grphcs.fillRect(p_Widht / 5 * 4 - 20 - 5, p_Height / 6 * 5 - 27 - 5, 160, 110);    //Rectangulo exterior Salir
        if (pulsado_salir) {
            grphcs.setColor(new Color(0xD4A05B));
        } else {
            grphcs.setColor(new Color(0xFEC375));
        }
        grphcs.fillRect(p_Widht / 5 * 4 - 20, p_Height / 6 * 5 - 27, 150, 100);            //Rectangulo interior Salir
        
        grphcs.setColor(Color.black);
        font = new Font("Verdana", Font.BOLD, 40);
        grphcs.setFont(new TrueTypeFont(font, true));
        if (inicio) {
            grphcs.drawString(" Nueva partida", p_Widht / 5 * 3, p_Height / 6 * 2);
            grphcs.drawString(" Cargar partida", p_Widht / 5 * 3, p_Height / 6 * 3);
        } else {
            grphcs.drawString("     Continuar", p_Widht / 5 * 3, p_Height / 6 * 2);
            grphcs.drawString("Guardar partida", p_Widht / 5 * 3, p_Height / 6 * 3);
        }
        grphcs.drawString("Salir", p_Widht / 5 * 4, p_Height / 6 * 5);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        cursor_hitbox.setX(gc.getInput().getMouseX() - (cursor_hitbox.getHeight() / 2));
        cursor_hitbox.setY(gc.getInput().getMouseY() - (cursor_hitbox.getWidth() / 2));

        //Nueva partida/Continuar
        if (cursor_hitbox.intersects(nuevaPartida_Continuar)) {
            pulsado_nuevaPartidaContinuar = true;

            if (inicio) {   //Si es inicio entonces accion de nueva partida
                if (gc.getInput().isMousePressed(0)) {
                    inicio = false;
                    game.enterState(1);
                }
            } else {    //Si no es inicio entonces accion continuar, es decir, volver al estado anterior
                if (gc.getInput().isMousePressed(0) && estadoAnterior > 0) {
                    game.enterState(estadoAnterior);
                }
            }
        } else {
            pulsado_nuevaPartidaContinuar = false;
        }

        //Cargar/Guardar partida
        if (cursor_hitbox.intersects(cargar_guardarPartida)) {
            pulsado_cargarGuardarPartida = true;

            if (inicio) { //Cargar partida
                if (gc.getInput().isMousePressed(0)) {
                    try {
                        GuardarPartidaIO_Service.cargarPartida(ruby);
                        inicio = false;
                        game.enterState(1);
                    } catch (IOException ex) {
                        System.out.println("ErrorIO al cargar la partida: " + ex.getMessage());
                    } catch (ClassNotFoundException ex) {
                        System.out.println("ErrorClassNotFoundException al cargar la partida: " + ex.getMessage());
                    }
                }
            } else {  //Guardar partida
                if (gc.getInput().isMousePressed(0)) {
                    try {
                        GuardarPartidaIO_Service.guardarPartida(ruby/*, huerto*/);
                        inicio = false;
                        game.enterState(estadoAnterior);
                    } catch (IOException ex) {
                        System.out.println("ErrorIO al guardar la partida: " + ex.getMessage());
                    }
                }
            }

        } else {
            pulsado_cargarGuardarPartida = false;
        }

        //Salir del juego
        if (cursor_hitbox.intersects(salir)) {
            pulsado_salir = true;
            if (gc.getInput().isMousePressed(0)) {
                System.exit(0);
            }
        } else {
            pulsado_salir = false;
        }

        //System.out.println("Ruby dinero -> " + ruby.getDinero());
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE && estadoAnterior > 0) {
            game.enterState(estadoAnterior);
        }else if (key == Input.KEY_F11) {
            game.enterState(3);
        }
    }

    public void setEstadoAnterior(int i) {
        estadoAnterior = i;
    }
}
