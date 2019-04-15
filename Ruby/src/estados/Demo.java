package estados;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Demo extends BasicGameState {

    //MAPA
    private TiledMap map;
    private double x, y;

    //PERSONAJE
    private Animation Ruby_w, Ruby_a, Ruby_s, Ruby_d;
    private int size_Ruby = 3, ancho_Ruby = 32, largo_Ruby = 32, lado_animacion = 0;

    //RATÓN
    private String coordenadas = "", click = "";
    private Image cursor_img;
    private Circle cursor_hitbox;
    private ArrayList<Rectangle> blocks;
    private int dist_inter = 200;

    //HITBOX
    private boolean ver_hitbox = true;
    private Rectangle personaje_R;
    private Rectangle relleno = null;

    //INTERACCIONES 
    private Image bocadillo;
    
    private StateBasedGame game;
    
    public Demo() {
        blocks = new ArrayList<>();
    }

    /**
     * Inicializa
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //gc.setMouseGrabbed(true);
        //cursor_img = new Image("./resources/sprites/cursor.png");

        this.game = sbg;
        
        map = new TiledMap("./resources/maps/demo_map.tmx");
        SpriteSheet sprite_Ruby = new SpriteSheet("./resources/sprites/Ruby.png", ancho_Ruby, largo_Ruby);
        Ruby_w = new Animation();
        Ruby_a = new Animation();
        Ruby_s = new Animation();
        Ruby_d = new Animation();
        Ruby_s.addFrame(sprite_Ruby.getSprite(0, 0), 200);
        Ruby_s.addFrame(sprite_Ruby.getSprite(2, 0), 200);
        Ruby_s.addFrame(sprite_Ruby.getSprite(1, 0), 200);
        Ruby_a.addFrame(sprite_Ruby.getSprite(0, 1), 200);
        Ruby_a.addFrame(sprite_Ruby.getSprite(2, 1), 200);
        Ruby_a.addFrame(sprite_Ruby.getSprite(1, 1), 200);
        Ruby_d.addFrame(sprite_Ruby.getSprite(0, 2), 200);
        Ruby_d.addFrame(sprite_Ruby.getSprite(2, 2), 200);
        Ruby_d.addFrame(sprite_Ruby.getSprite(1, 2), 200);
        Ruby_w.addFrame(sprite_Ruby.getSprite(0, 3), 200);
        Ruby_w.addFrame(sprite_Ruby.getSprite(2, 3), 200);
        Ruby_w.addFrame(sprite_Ruby.getSprite(1, 3), 200);
        

        //Rectangulo colision personaje
        personaje_R = new Rectangle((gc.getWidth() / 2 - (ancho_Ruby - 30))-5, (gc.getHeight() / 2 - (largo_Ruby - 25)) + 45, 40, 32);

        //Carga de muros en memoria
        cargaMuros();

        //Circulo colision mouse
        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);
    }

    /**
     * Dibuja
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        map.render((int) x, (int) y, 0, 0, gc.getWidth(), gc.getHeight());
        grphcs.drawString(click, gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
        grphcs.drawString(coordenadas, 35, 35);

        //Jugador
        switch (lado_animacion) {
            case 0:
                Ruby_w.draw(gc.getWidth() / 2 - ancho_Ruby, gc.getHeight() / 2 - largo_Ruby, ancho_Ruby * size_Ruby, largo_Ruby * size_Ruby);
                break;
            case 1:
                Ruby_a.draw(gc.getWidth() / 2 - ancho_Ruby, gc.getHeight() / 2 - largo_Ruby, ancho_Ruby * size_Ruby, largo_Ruby * size_Ruby);
                break;
            case 2:
                Ruby_s.draw(gc.getWidth() / 2 - ancho_Ruby, gc.getHeight() / 2 - largo_Ruby, ancho_Ruby * size_Ruby, largo_Ruby * size_Ruby);
                break;
            case 3:
                Ruby_d.draw(gc.getWidth() / 2 - ancho_Ruby, gc.getHeight() / 2 - largo_Ruby, ancho_Ruby * size_Ruby, largo_Ruby * size_Ruby);
                break;
            default:
                break;
        }

        //Cursor
        //cursor_img.draw(gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY(), cursor_img.getHeight(), cursor_img.getWidth());
        //Dibujo de los elementos de colision
        if (ver_hitbox) {
            grphcs.drawRect(personaje_R.getX(), personaje_R.getY(), personaje_R.getWidth(), personaje_R.getHeight());
            grphcs.drawOval(cursor_hitbox.getX(), cursor_hitbox.getY(), cursor_hitbox.getHeight(), cursor_hitbox.getWidth());

            boolean rojo = true;
            for (Rectangle rectangle : blocks) {
                if (rojo) {
                    grphcs.setColor(Color.red);
                    rojo = false;
                } else {
                    grphcs.setColor(Color.yellow);
                    rojo = true;
                }
                //grphcs.drawRect(rectangle.getX() + (float) x, rectangle.getY() + (float) y, rectangle.getWidth(), rectangle.getHeight()); 
                grphcs.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            }
            grphcs.setColor(Color.white);
        }
        if (relleno != null) {
            grphcs.fillRect(relleno.getX(), relleno.getY(), relleno.getWidth(), relleno.getHeight());
        }
    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        //Actualización de la hitbox del cursor
        cursor_hitbox.setX(gc.getInput().getMouseX() - (cursor_hitbox.getHeight() / 2));
        cursor_hitbox.setY(gc.getInput().getMouseY() - (cursor_hitbox.getWidth() / 2));

        //Actualización de la hitbox del personaje
        //Movimiento del jugador
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de update
            actualizaMuros(0, (i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de update
            actualizaMuros(0, -(i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de update
            actualizaMuros((i / 3.f), 0);
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de update
            actualizaMuros(-(i / 3.f), 0);
        }

        animaPersonaje(gc);

        //MOVIMENTO DEL RATÓN
        coordenadas = "(" + gc.getInput().getMouseX() + "," + gc.getInput().getMouseY() + ")";
        if (gc.getInput().isMouseButtonDown(0)) {
            for (Rectangle re : blocks) {
                if (cursor_hitbox.intersects(re) && (dist_inter > (Math.sqrt((Math.pow(re.getX() - personaje_R.getX(), 2)) + (Math.pow(re.getY() - personaje_R.getY(), 2)))))) {
                    click = "click";
                    relleno = re;
                }
            }
        } else {
            click = "";
            relleno = null;
        }

        //HITBOX
        for (Rectangle re : blocks) {
            while (personaje_R.intersects(re)) {
                colision(re, i, gc);
                mejoraColision(i, gc);
            }
        }

    }

    public void colision(Rectangle re, int i, GameContainer gc) {
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y -= i / 3.f;  //i=tiempo de update
            actualizaMuros(0, -(i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y += i / 3.f;  //i=tiempo de update
            actualizaMuros(0, (i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x -= i / 3.f;  //i=tiempo de update
            actualizaMuros(-(i / 3.f), 0);
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x += i / 3.f;  //i=tiempo de update
            actualizaMuros((i / 3.f), 0);
        }
    }

    public void mejoraColision(int i, GameContainer gc) {
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de update
            actualizaMuros(0, (i / 3.f));
            for (Rectangle re : blocks) {
                if (re.intersects(personaje_R)) {
                    y -= i / 3.f;  //i=tiempo de update
                    actualizaMuros(0, -(i / 3.f));
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de update
            actualizaMuros(0, -(i / 3.f));
            for (Rectangle re : blocks) {
                if (re.intersects(personaje_R)) {
                    y += i / 3.f;  //i=tiempo de update
                    actualizaMuros(0, (i / 3.f));
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de update
            actualizaMuros((i / 3.f), 0);
            for (Rectangle re : blocks) {
                if (re.intersects(personaje_R)) {
                    x -= i / 3.f;  //i=tiempo de update
                    actualizaMuros(-(i / 3.f), 0);
                }
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de update
            actualizaMuros(-(i / 3.f), 0);
            for (Rectangle re : blocks) {
                if (re.intersects(personaje_R)) {
                    x += i / 3.f;  //i=tiempo de update
                    actualizaMuros((i / 3.f), 0);
                }
            }
        }
    }

    public void animaPersonaje(GameContainer gc) {
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            lado_animacion = 1;
            if(Ruby_a.getFrame() == 2) {
                Ruby_a.setCurrentFrame(0);
            }
        } else if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            Ruby_d.start();
            lado_animacion = 3;
            if(Ruby_d.getFrame() == 2) {
                Ruby_d.setCurrentFrame(0);
            }
        } else if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            Ruby_w.start();
            lado_animacion = 0;
            if(Ruby_w.getFrame() == 2) {
                Ruby_w.setCurrentFrame(0);
            }
        } else if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            Ruby_s.start();
            lado_animacion = 2;
            if(Ruby_s.getFrame() == 2) {
                Ruby_s.setCurrentFrame(0);
            }
        }
        if (!(gc.getInput().isKeyDown(Input.KEY_W)
                || gc.getInput().isKeyDown(Input.KEY_A)
                || gc.getInput().isKeyDown(Input.KEY_S)
                || gc.getInput().isKeyDown(Input.KEY_D)
                || gc.getInput().isKeyDown(Input.KEY_UP)
                || gc.getInput().isKeyDown(Input.KEY_DOWN)
                || gc.getInput().isKeyDown(Input.KEY_LEFT)
                || gc.getInput().isKeyDown(Input.KEY_RIGHT))) {
            Ruby_w.stop();
            Ruby_w.setCurrentFrame(2);
            Ruby_a.stop();
            Ruby_a.setCurrentFrame(2);
            Ruby_s.stop();
            Ruby_s.setCurrentFrame(2);
            Ruby_d.stop();
            Ruby_d.setCurrentFrame(2);
        } else {
            Ruby_w.start();
            Ruby_a.start();
            Ruby_s.start();
            Ruby_d.start();
        }
    }

    @Override
    public int getID() {
        return 0;
    }

    public void cargaMuros() {
        int wallLayer = map.getLayerIndex("Walls");
        //ArrayList<Integer> walls = new ArrayList<Integer>();

        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                //walls.add(map.getTileId(i, j, wallLayer));
                //System.out.format("%4d", map.getTileId(i, j, wallLayer));

                if (map.getTileId(i, j, wallLayer) != 0) {
                    blocks.add(new Rectangle((float) i * 32, (float) j * 32, 32, 32));  //32 = ancho del patron
                }
            }
            System.out.println("");
        }
    }

    public void actualizaMuros(float x_cor, float y_cor) {
        for (Rectangle rectangle : blocks) {
            rectangle.setX(rectangle.getX() + x_cor);
            rectangle.setY(rectangle.getY() + y_cor);
        }
    }

    
    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_F11){
            game.enterState(1); //Prueba
        }
    }
}
