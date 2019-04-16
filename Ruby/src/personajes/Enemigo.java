package personajes;

import elementos.Hitbox;
import elementos.Mapa;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import services.Colision_Service;

public class Enemigo extends Personaje {

    private int nivel;
    private boolean combate;
    private Animacion_dinamica animacion;
    private float movX = 0, movY = 0;

    public Enemigo(String nombre, Hitbox hitbox, int nivel) throws SlickException {
        // TODO: modificar animación original por Animacion_dinamica cuando haya sprite
        super(nombre, hitbox, 100, 20);
        this.animacion = new Animacion_dinamica("./resources/sprites/Ruby.png"); //TODO pendiente de modificacion de la ruta
        this.nivel = nivel;
        this.combate = false;
    }

    public Enemigo(String nombre, Hitbox hitbox, Animacion_dinamica animacion, int vida, int dinero, int nivel) {
        super(nombre, hitbox, vida, dinero);
        this.nivel = nivel;
        this.combate = false;
        this.animacion = animacion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isCombate() {
        return combate;
    }

    public void setCombate(boolean combate) {
        this.combate = combate;
    }

    //Movimiento dinamico del enemigo
    public void movimientoEnemigo(int i, Mapa map, GameContainer gc) {

        //Movimiento vertical
        switch ((int) (Math.random() * 100)) {
            case 0:
                movY = 0;   //Para
                break;
            case 1:
                movY = (i / 5.f);   //Movimiento hacia abajo
                break;
            case 2:
                movY = -(i / 5.f);  //Movimiento hacia arriba
                break;
            default:
                //Movimiento no varia, se mantiene el anterior
                break;
        }
        
        //Movimiento lateral
        switch ((int) (Math.random() * 100)) {
            case 0:
                movX = 0;   //Para
                break;
            case 1:
                movX = (i / 5.f);   //Movimiento hacia derecha
                break;
            case 2:
                movX = -(i / 5.f);  //Movimiento hacia izq
                break;
            default:
                //Movimiento no varia, se mantiene el anterior
                break;
        }
        
        hitbox.updatePos(movX, movY);
        
        /*float movColision[] = Colision_Service.colisionMuros(this, map, gc, i, movX, movY);
        
        hitbox.updatePos(movColision[0], movColision[1]);*/
    }

    @Override
    public void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY) {
        animacion.renderAnimacion(-movX, -movY, hitbox.getRectangulo().getX(), hitbox.getRectangulo().getY());
    }
}
