package slick2dprueba;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Juego extends BasicGame {

    private boolean f, v, w, a, s, d;
    private boolean esPantallaCompleta = false, esVSync = false;
    private float x, y;
    private int x_homer = 500, y_homer = 500, size_homer = 3, ancho_homer = 64, largo_homer = 65, x_homersapien = 0, y_homersapien = 0;
    private Animation homer;
    private Image homersapien;

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer((Game) new Juego());
        container.setShowFPS(true);
        container.setVSync(true);
        container.setDisplayMode(1920, 1080, true);
        container.setFullscreen(false);
        container.start();
    }

    public Juego() {
        super("Juego");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        SpriteSheet sprite_homer = new SpriteSheet("sprites/sprite_esqueleto.png", ancho_homer, largo_homer);
        homersapien = new Image("sprites/background_homersapien.jpg");
        homer = new Animation();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 9; i++) {
                homer.addFrame(sprite_homer.getSprite(i, j), 50);
            }
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        f = container.getInput().isKeyDown(Input.KEY_F);
        //v = container.getInput().isKeyDown(Input.KEY_V);
        w = container.getInput().isKeyDown(Input.KEY_W);
        a = container.getInput().isKeyDown(Input.KEY_A);
        s = container.getInput().isKeyDown(Input.KEY_S);
        d = container.getInput().isKeyDown(Input.KEY_D);
        if (f) {
            teclaPulsada("f", container);
        }
        if (v) {
            teclaPulsada("v", container);
        }
        if (w) {
            teclaPulsada("w", container);
        }
        if (a) {
            teclaPulsada("a", container);
        }
        if (s) {
            teclaPulsada("s", container);
        }
        if (d) {
            teclaPulsada("d", container);
        }
        if(!(w||a||s||d)) {
            homer.stop();
        }else{
            homer.start();
            homersapien.draw(x_homersapien, y_homersapien);
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        homersapien.draw(x_homersapien, y_homersapien, 3840, 2160);
        g.setColor(Color.blue);
        g.drawString("Pulsa 'f' para poner/quitar el modo de pantalla completa", 20, 50);
        //g.drawString("Pulsa 'v' para poner/quitar el VSync", 20, 70);
        homer.draw(x_homer, y_homer, largo_homer*size_homer, largo_homer*size_homer);
        homer.setLooping(false);
    }

    public void teclaPulsada(String tecla, GameContainer container) throws SlickException {
        if (null != tecla) switch (tecla) {
            case "f":
                if (esPantallaCompleta) {
                    container.setFullscreen(false);
                    esPantallaCompleta = false;
                } else {
                    container.setFullscreen(true);
                    esPantallaCompleta = true;
                }   break;
            case "v":
                if (esVSync) {
                    container.setVSync(false);
                    esVSync = false;
                } else {
                    container.setVSync(true);
                    esVSync = true;
                }   break;
            case "w":
                y_homersapien = y_homersapien - 4;
                if(homer.getFrame() < 8) {
                    homer.start();
                } else {
                    homer.setCurrentFrame(0);
                }
                break;
            case "a":
                x_homersapien = x_homersapien - 4;
                if((8 < homer.getFrame()) && (homer.getFrame()< 17)) {
                    homer.start();
                } else {
                    homer.setCurrentFrame(9);
                }
                break;
            case "s":
                y_homersapien = y_homersapien + 4;
                if((17 < homer.getFrame()) && (homer.getFrame()< 26)) {
                    homer.start();
                } else {
                    homer.setCurrentFrame(18);
                }
                break;
            case "d":
                x_homersapien = x_homersapien + 4;
                if((26 < homer.getFrame()) && (homer.getFrame()< 35)) {
                    homer.start();
                } else {
                    homer.setCurrentFrame(27);
                }
                break;
            default:
                break;
        }
    }
}
