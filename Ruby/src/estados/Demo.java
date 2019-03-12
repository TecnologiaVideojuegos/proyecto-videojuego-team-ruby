package estados;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Demo extends BasicGameState {

    //MAPA
    private TiledMap map;
    private double x, y;

    //PERSONAJE
    private Animation esqueleto;
    private int x_homer = 500, y_homer = 500, size_esqueleto = 2, ancho_esqueleto = 64, largo_esqueleto = 65;

    //RATÓN
    private String coordenadas="", click="";

    //HITBOX
    private boolean ver_hitbox = true;
    private Rectangle personaje_R;
    private ArrayList<Rectangle> blocks;

    public Demo() {
        blocks = new ArrayList<>();
    }

    /**
     * Inicializa
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new TiledMap("./resources/maps/demo_map.tmx");
        SpriteSheet sprite_esqueleto = new SpriteSheet("./resources/sprites/sprite_esqueleto.png", ancho_esqueleto, largo_esqueleto);
        esqueleto = new Animation();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 9; i++) {
                esqueleto.addFrame(sprite_esqueleto.getSprite(i, j), 50);
            }
        }

        //Rectangulo colision personaje
        personaje_R = new Rectangle(gc.getWidth() / 2 - (ancho_esqueleto - 30), gc.getHeight() / 2 - (largo_esqueleto - 25), (ancho_esqueleto - 30) * size_esqueleto, (largo_esqueleto - 15) * size_esqueleto);

        //Carga de muros en memoria
        cargaMuros();
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
        esqueleto.draw(gc.getWidth() / 2 - ancho_esqueleto, gc.getHeight() / 2 - largo_esqueleto, ancho_esqueleto * size_esqueleto, largo_esqueleto * size_esqueleto);

        //Dibujo de los elementos de colision
        if (ver_hitbox) {
            grphcs.drawRect(personaje_R.getX(), personaje_R.getY(), personaje_R.getWidth(), personaje_R.getHeight());

            boolean rojo = true;
            for (Rectangle rectangle : blocks) {
                if (rojo) {
                    grphcs.setColor(Color.red);
                    rojo = false;
                } else {
                    grphcs.setColor(Color.yellow);
                    rojo = true;
                }
                grphcs.drawRect(rectangle.getX() + (float) x, rectangle.getY() + (float) y, rectangle.getWidth(), rectangle.getHeight());
            }
            grphcs.setColor(Color.white);
        }
    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        //Movimiento del jugador
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i / 3.f;  //i=tiempo de update
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i / 3.f;  //i=tiempo de update
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i / 3.f;  //i=tiempo de update
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i / 3.f;  //i=tiempo de update
        }
        
        //Animación del personaje
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            if ((8 < esqueleto.getFrame()) && (esqueleto.getFrame() < 17)) {
                esqueleto.start();
            } else {
                esqueleto.setCurrentFrame(9);
            }
        }else if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            if ((26 < esqueleto.getFrame()) && (esqueleto.getFrame() < 35)) {
                esqueleto.start();
            } else {
                esqueleto.setCurrentFrame(27);
            }
        }else if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            if (esqueleto.getFrame() < 8) {
                esqueleto.start();
            } else {
                esqueleto.setCurrentFrame(0);
            }
        }else if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            if ((17 < esqueleto.getFrame()) && (esqueleto.getFrame() < 26)) {
                esqueleto.start();
            } else {
                esqueleto.setCurrentFrame(18);
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
            esqueleto.stop();
        } else {
            esqueleto.start();
        }

        //MOVIMENTO DEL RATÓN
        coordenadas="("+gc.getInput().getMouseX()+","+gc.getInput().getMouseY()+")";
        if(gc.getInput().isMouseButtonDown(0)){
            click="click";
        }else{
            click="";
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

}
