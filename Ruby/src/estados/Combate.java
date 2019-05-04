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

public class Combate extends BasicGameState {

    private Jugador ruby;
    private Image imgRuby, imgEnemy, imgBoss, iconoFAgua, iconoPFuego, iconoPRayo, iconoPocionVida, iconoPocionDanio;
    private int p_Widht, p_Height;
    private Circle cursor_hitbox;
    //Menu opciones
    private Rectangle btnAtaques, btnPociones, btnHuir;
    private Boolean sobreAtaques = false, sobrePociones = false, sobreHuir = false;
    private Boolean pulsadoAtaques = true, pulsadoPociones = false, pulsadoHuir = false;
    //Submenu ataques
    private Rectangle btnAgua, btnFuego, btnRayo;
    private Boolean sobreAgua = false, sobreFuego = false, sobreRayo = false;
    //Submenu pociones
    private Rectangle btnPocionVida, btnPocionDanio;
    private Boolean sobrePocionVida = false, sobrePocionDanio = false;

    private StateBasedGame game;
    private int estadoAnterior = 0;
    private Font font;

    public Combate(Jugador ruby) throws SlickException {
        this.ruby = ruby;
        this.imgRuby = new Image("./resources/img/RubyAvatar.png");
        this.imgEnemy = new Image("./resources/img/Slime.png");
        this.imgBoss = new Image("./resources/img/Boss.png");
        this.iconoFAgua = new Image("./resources/img/florAzul.png");
        this.iconoPFuego = new Image("./resources/img/florRoja.png");
        this.iconoPRayo = new Image("./resources/img/florAmarilla.png");
        this.iconoPocionVida = new Image("./resources/img/florAzul.png");
        this.iconoPocionDanio = new Image("./resources/img/florRoja.png");
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        p_Widht = gc.getWidth();
        p_Height = gc.getHeight();

        //Elementos de colisión/interacción
        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);
        btnAtaques = new Rectangle(954, 478, 270, 50);
        btnPociones = new Rectangle(954, 560, 270, 50);
        btnHuir = new Rectangle(954, 642, 270, 50);
        btnAgua = new Rectangle(50, 560, 230, 50);
        btnFuego = new Rectangle(350, 560, 230, 50);
        btnRayo = new Rectangle(626, 560, 230, 50);
        btnPocionVida = new Rectangle(150, 560, 230, 50);
        btnPocionDanio = new Rectangle(525, 560, 230, 50);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics grphcs) throws SlickException {
        // --------- FONDO --------- //
        grphcs.setColor(new Color(0xFFFFFF));
        grphcs.fillRect(0, 0, p_Widht, p_Height);

        // --------- ZONA COMBATE --------- //
        grphcs.setColor(Color.black);
        font = new Font("Verdana", Font.PLAIN, 30);
        grphcs.setFont(new TrueTypeFont(font, true));
        //Enemigo Texto
        grphcs.drawString("HP", 750, 15);
        grphcs.drawString("Boss", 980, 120);    //Tipo de enemigo   //TODO: completar para tipo enemigo
        //Ruby Texto
        grphcs.drawString("HP", 40, 140);
        grphcs.drawString("Ruby", 230, 250);

        int eVida = 70;     //TODO: eliminar variable sustituir por detección de vida del personaje
        //Enemigo cuadro Vida
        grphcs.setColor(new Color(0x61BE6D));
        grphcs.fillRect(750, 60, eVida * 5, 30);
        grphcs.setColor(new Color(0xFF7272));
        grphcs.fillRect(750 + (eVida * 5), 60, (100 - eVida) * 5, 30);
        //Ruby cuadro Vida
        grphcs.setColor(new Color(0x61BE6D));
        grphcs.fillRect(40, 190, eVida * 5, 30);
        grphcs.setColor(new Color(0xFF7272));
        grphcs.fillRect(40 + (eVida * 5), 190, (100 - eVida) * 5, 30);

        //Enemigo imagen    //TODO: seleccion de imagen por tipo de enemigo
        imgBoss.draw(1080, 120, 170, 170);
        //Ruby imagen
        imgRuby.draw(40, 250, 170, 170);

        // --------- ZONA MENU--------- //
        //Marcos
        grphcs.setColor(new Color(0xFFAEAE));
        grphcs.fillRect(0, 450, p_Widht, 5);
        grphcs.fillRect(900, 450, 5, 270);

        //MENU OPCIONES
        grphcs.setColor(new Color(0xFFAEAE));   //Bordes
        if (pulsadoAtaques) {
            grphcs.fillRect(952, 476, 274, 54);
        } else if (pulsadoPociones) {
            grphcs.fillRect(952, 558, 274, 54);
        } else if (pulsadoHuir) {
            grphcs.fillRect(952, 640, 274, 54);
        }

        if (sobreAtaques) {       //btn ataque
            grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
        } else {
            grphcs.setColor(new Color(0xF0F0F0));
        }
        grphcs.fillRect(954, 478, 270, 50);

        if (sobrePociones) {       //btn pociones
            grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
        } else {
            grphcs.setColor(new Color(0xF0F0F0));
        }
        grphcs.fillRect(954, 560, 270, 50);

        if (sobreHuir) {       //btn huir
            grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
        } else {
            grphcs.setColor(new Color(0xF0F0F0));
        }
        grphcs.fillRect(954, 642, 270, 50);

        grphcs.setColor(Color.black);               //Texto
        font = new Font("Verdana", Font.PLAIN, 35);
        grphcs.setFont(new TrueTypeFont(font, true));
        grphcs.drawString("ATAQUES", 1007, 482);
        grphcs.drawString("POCIONES", 1003, 566);
        grphcs.drawString("HUIDA", 1031, 646);

        //SUBMENUS
        if (pulsadoAtaques) {//MENU ATAQUES

            if (sobreAgua) {       //btn ataque
                grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
            } else {
                grphcs.setColor(new Color(0xF0F0F0));
            }
            grphcs.fillRect(50, 560, 230, 50);

            if (sobreFuego) {       //btn pociones
                grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
            } else {
                grphcs.setColor(new Color(0xF0F0F0));
            }
            grphcs.fillRect(350, 560, 230, 50);

            if (sobreRayo) {       //btn huir
                grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
            } else {
                grphcs.setColor(new Color(0xF0F0F0));
            }
            grphcs.fillRect(626, 560, 230, 50);

            iconoFAgua.draw(60, 565, 40, 40);
            iconoPFuego.draw(359, 565, 40, 40);
            iconoPRayo.draw(636, 565, 40, 40);

            grphcs.setColor(Color.black);               //Texto
            font = new Font("Verdana", Font.PLAIN, 35);
            grphcs.setFont(new TrueTypeFont(font, true));
            grphcs.drawString("Selecciona ataque:", 15, 470);
            font = new Font("Verdana", Font.PLAIN, 30);
            grphcs.setFont(new TrueTypeFont(font, true));
            grphcs.drawString("Agua", 125, 568);
            grphcs.drawString("x10", 218, 568); //TODO: cambiar por detección de inventario
            grphcs.drawString("Fuego", 423, 568);
            grphcs.drawString("x10", 517, 568); //TODO: cambiar por detección de inventario
            grphcs.drawString("Rayo", 700, 568);
            grphcs.drawString("x10", 794, 568); //TODO: cambiar por detección de inventario

        } else if (pulsadoPociones) {

            if (sobrePocionVida) {       //btn ataque
                grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
            } else {
                grphcs.setColor(new Color(0xF0F0F0));
            }
            grphcs.fillRect(150, 560, 230, 50);

            if (sobrePocionDanio) {       //btn pociones
                grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
            } else {
                grphcs.setColor(new Color(0xF0F0F0));
            }
            grphcs.fillRect(525, 560, 230, 50);

            iconoPocionVida.draw(160, 565, 40, 40);
            iconoPocionDanio.draw(535, 565, 40, 40);

            grphcs.setColor(Color.black);               //Texto
            font = new Font("Verdana", Font.PLAIN, 35);
            grphcs.setFont(new TrueTypeFont(font, true));
            grphcs.drawString("Selecciona poción:", 15, 470);
            font = new Font("Verdana", Font.PLAIN, 30);
            grphcs.setFont(new TrueTypeFont(font, true));
            grphcs.drawString("Vida", 224, 568);
            grphcs.drawString("x10", 318, 568); //TODO: cambiar por detección de inventario
            grphcs.drawString("Daño", 599, 568);
            grphcs.drawString("x10", 693, 568); //TODO: cambiar por detección de inventario

        } else if (pulsadoHuir) {
            System.out.println("Huir");
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
        cursor_hitbox.setX(gc.getInput().getMouseX() - (cursor_hitbox.getHeight() / 2));
        cursor_hitbox.setY(gc.getInput().getMouseY() - (cursor_hitbox.getWidth() / 2));

        //MENU OPCIONES
        if (cursor_hitbox.intersects(btnAtaques)) {
            sobreAtaques = true;
            if (gc.getInput().isMousePressed(0)) {
                pulsadoAtaques = true;
                pulsadoPociones = false;
                pulsadoHuir = false;
            }
        } else {
            sobreAtaques = false;
        }
        if (cursor_hitbox.intersects(btnPociones)) {
            sobrePociones = true;
            if (gc.getInput().isMousePressed(0)) {
                pulsadoAtaques = false;
                pulsadoPociones = true;
                pulsadoHuir = false;
            }
        } else {
            sobrePociones = false;
        }

        if (cursor_hitbox.intersects(btnHuir)) {
            sobreHuir = true;
            if (gc.getInput().isMousePressed(0)) {
                pulsadoAtaques = false;
                pulsadoPociones = false;
                pulsadoHuir = true;
            }
        } else {
            sobreHuir = false;
        }

        //MENU ATAQUES
        if (pulsadoAtaques) {
            if (cursor_hitbox.intersects(btnAgua)) {
                sobreAgua = true;
                if (gc.getInput().isMousePressed(0)) {
                    System.out.println("Ataque agua");
                }
            } else {
                sobreAgua = false;
            }

            if (cursor_hitbox.intersects(btnFuego)) {
                sobreFuego = true;
                if (gc.getInput().isMousePressed(0)) {
                    System.out.println("Ataque fuego");
                }
            } else {
                sobreFuego = false;
            }

            if (cursor_hitbox.intersects(btnRayo)) {
                sobreRayo = true;
                if (gc.getInput().isMousePressed(0)) {
                    System.out.println("Ataque rayo");
                }
            } else {
                sobreRayo = false;
            }
        }

        //MENU POCIONES
        if (pulsadoPociones) {
            if (cursor_hitbox.intersects(btnPocionVida)) {
                sobrePocionVida = true;
                if (gc.getInput().isMousePressed(0)) {
                    System.out.println("Poción vida");
                }
            } else {
                sobrePocionVida = false;
            }

            if (cursor_hitbox.intersects(btnPocionDanio)) {
                sobrePocionDanio = true;
                if (gc.getInput().isMousePressed(0)) {
                    System.out.println("Poción daño");
                }
            } else {
                sobrePocionDanio = false;
            }
        }

    }

    @Override
    public int getID() {
        return 4;
    }
}
