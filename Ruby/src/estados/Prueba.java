package estados;

import elementos.Hitbox;
import elementos.Mapa;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import personajes.*;

public class Prueba extends BasicGameState {

    private Mapa map;
    private float x, y;

    //PERSONAJE
    private Jugador ruby;
    private Animation esqueleto;
    private int x_homer = 500, y_homer = 500, size_esqueleto = 2, ancho_esqueleto = 64, largo_esqueleto = 65;

    //RATÓN
    private String coordenadas = "", click = "";
    private Image cursor;

    //HITBOX
    private boolean ver_hitbox = false;
    private Rectangle personaje_R;

    public Prueba() {
    }

    /**
     * Inicializa
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        cursor = new Image("./resources/sprites/cursor.png");
        map = new Mapa("./resources/maps/demo_map.tmx");
        ruby = new Jugador(new Hitbox(gc.getWidth() / 2 - (ancho_esqueleto - 30), (gc.getHeight() / 2 - (largo_esqueleto - 25)) + 60, (ancho_esqueleto - 30) * size_esqueleto, ((largo_esqueleto - 15) * size_esqueleto) - 60));
    }

    /**
     * Dibuja
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        map.renderMap(gc, x, y, grphcs, ver_hitbox);
        grphcs.drawString(click, gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());
        grphcs.drawString(coordenadas, 35, 35);

        //Jugador
        ruby.renderPersonaje(gc);

        //Cursor
        grphcs.drawImage(cursor, gc.getInput().getAbsoluteMouseX(), gc.getInput().getAbsoluteMouseY());

    }

    /**
     * Actualiza
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        float mov[] = ruby.capturaMovimiento(gc, i);
        x += mov[0];
        y += mov[1];
        map.actualizarElementos(mov[0], mov[1]);

        //MOVIMENTO DEL RATÓN
        coordenadas = "(" + gc.getInput().getMouseX() + "," + gc.getInput().getMouseY() + ")";
        if (gc.getInput().isMouseButtonDown(0)) {
            click = "click";
        } else {
            click = "";
        }

        //HITBOX
        /*ArrayList<Rectangle> blocksColision = new ArrayList<Rectangle>();
        Rectangle reNear = null;
        float reDistance, newReDistance;
        boolean hayColision;
        do {
            blocksColision.clear();
            hayColision = false;
            for (Hitbox re : map.getBlocks()) {
                if (personaje_R.intersects(re.getRectangulo())) {
                    hayColision = true;
                    blocksColision.add(re.getRectangulo());*/
 /*
                    y -= i / 3.f;
                    actualizaMuros(0, -(i / 3.f));
                    click = "colisión";
         */
 /* }
            }
            if (blocksColision.size() > 0) {
                reNear = blocksColision.get(0);
                reDistance = (float) Math.sqrt((Math.pow(reNear.getCenterX() - personaje_R.getCenterX(), 2)) - (Math.pow(reNear.getCenterY() - personaje_R.getCenterY(), 2)));
                for (int j = 1; j < blocksColision.size(); j++) {
                    newReDistance = (float) Math.sqrt((Math.pow(blocksColision.get(j).getCenterX() - personaje_R.getCenterX(), 2)) - (Math.pow(blocksColision.get(j).getCenterY() - personaje_R.getCenterY(), 2)));
                    if (newReDistance < reDistance) {
                        reDistance = newReDistance;
                        reNear = blocksColision.get(j);
                    }
                }
            }
            if (hayColision) {
                colision(reNear, i, gc);
            }
        } while (false);*/
    }

    public void colision(Rectangle re, int i, GameContainer gc) {
        boolean isUp, isLeft;

        if ((personaje_R.getCenterY() - re.getCenterY()) > 0) {
            isUp = false;
        } else {
            isUp = true;
        }
        if ((personaje_R.getCenterX() - re.getCenterX()) > 0) {
            isLeft = false;
        } else {
            isLeft = true;
        }

        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, -(i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y += i / 3.f;  //i=tiempo de update
            map.actualizarElementos(0, (i / 3.f));
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x -= i / 3.f;  //i=tiempo de update
            map.actualizarElementos(-(i / 3.f), 0);
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x += i / 3.f;  //i=tiempo de update
            map.actualizarElementos((i / 3.f), 0);
        }
    }

    @Override
    public int getID() {
        return 1;
    }
}
