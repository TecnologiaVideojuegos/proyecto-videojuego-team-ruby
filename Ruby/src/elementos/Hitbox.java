package elementos;

import org.newdawn.slick.geom.Rectangle;

public class Hitbox{

    private Rectangle rectangulo;

    //constructor por defecto
    public Hitbox() {
        rectangulo = new Rectangle(0, 0, 0, 0);
    }

    /**
     * Constuctor con parametros
     *
     * @param pos_x -> cordenada x en pantalla
     * @param pos_y -> cordenada y en pantalla
     * @param width -> ancho de la rectangulo
     * @param height -> altura de la rectangulo
     */
    public Hitbox(float pos_x, float pos_y, float width, float height) {
        rectangulo = new Rectangle(pos_x, pos_y, width, height);
    }

    public Rectangle getRectangulo() {
        return rectangulo;
    }

    public void setRectangulo(Rectangle rectangulo) {
        this.rectangulo = rectangulo;
    }

    /**
     * Actualizacion de posicion del rectangulo
     *
     * @param pos_x -> cordenada x en pantalla
     * @param pos_y -> cordenada y en pantalla
     */
    public void updatePos(float pos_x, float pos_y) {
        rectangulo.setX(rectangulo.getX() + pos_x);
        rectangulo.setY(rectangulo.getY() + pos_y);
    }
}
