package personajes;

import animaciones.Animacion_dinamica;
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
        super(nombre, hitbox, 100, 20*nivel);
        this.animacion = new Animacion_dinamica("./resources/sprites/Planta_enemiga.png");
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

    public boolean getCombate() {
        return combate;
    }

    public void setCombate(boolean combate) {
        this.combate = combate;
    }

    public Animacion_dinamica getAnimacion() {
        return animacion;
    }

    public void setAnimacion(Animacion_dinamica animacion) {
        this.animacion = animacion;
    }

    public float getMovX() {
        return movX;
    }

    public void setMovX(float movX) {
        this.movX = movX;
    }

    public float getMovY() {
        return movY;
    }

    public void setMovY(float movY) {
        this.movY = movY;
    }

    public void pararMovimiento() {
        movX = 0;
        movY = 0;
    }

    public boolean ataque(Personaje enemigo) {
        boolean exito = false;
        int numRandom = (int) (Math.random() * 8);
        int danio = 0;

        if (numRandom != 0 || numRandom != 1) {
            danio = 3 * nivel;
            if (numRandom == 2) {                   //Critico
                danio *= 2;
            }

            enemigo.setVida(enemigo.getVida() - danio);
            exito = true;
        }
        return exito;
    }

    //Movimiento dinamico del enemigo
    public void movimientoEnemigo(int i, Mapa map, GameContainer gc, Hitbox hitboxRuby) {
        float colision[];
        int distanciaAgro = 300;        //Ditancia para coger AGRO
        float reductorVelocidad = 6.f;  // Reductor de la velocidad de movimiento mayor con AGRO

        //Comprobamos distacia hasta Ruby (AGRO)
        int vectorX = (int) (hitboxRuby.getRectangulo().getCenterX() - hitbox.getRectangulo().getCenterX());
        int vectorY = (int) (hitboxRuby.getRectangulo().getCenterY() - hitbox.getRectangulo().getCenterY());
        if (distanciaAgro > (Math.sqrt((Math.pow(vectorX, 2)) + (Math.pow(vectorY, 2))))) {
            if (!combate) {
                reductorVelocidad = 4.f;    //  Aumentamos velocidad de movimiento

                //Ajustamos movimiento en dirección a Ruby
                //Horizaontal
                if (vectorX > -15 && vectorX < 15) {
                    movX = 0;
                } else if (vectorX > 15) {
                    movX = (i / reductorVelocidad);
                } else if (vectorX < -15) {
                    movX = -(i / reductorVelocidad);
                }
                //Vertical
                if (vectorY > -15 && vectorY < 15) {
                    movY = 0;
                } else if (vectorY > 15) {
                    movY = (i / reductorVelocidad);
                } else if (vectorY < -15) {
                    movY = -(i / reductorVelocidad);
                }
            }

        } else {    // MOVIMIENTO ALEATORIO SIN AGRO
            combate = false;
            //Movimiento vertical
            switch ((int) (Math.random() * 100)) {
                case 0:
                    movY = 0;                         //Para
                    break;
                case 1:
                    movY = (i / reductorVelocidad);   //Movimiento hacia abajo
                    break;
                case 2:
                    movY = -(i / reductorVelocidad);  //Movimiento hacia arriba
                    break;
                default:
                    break;                            //Movimiento no varia, se mantiene el anterior
            }

            //Movimiento lateral
            switch ((int) (Math.random() * 100)) {
                case 0:
                    movX = 0;                         //Para
                    break;
                case 1:
                    movX = (i / reductorVelocidad);   //Movimiento hacia derecha
                    break;
                case 2:
                    movX = -(i / reductorVelocidad);  //Movimiento hacia izq
                    break;
                default:
                    break;                            //Movimiento no varia, se mantiene el anterior
            }
        }

        hitbox.updatePos(movX, movY);

        colision = Colision_Service.colisionMuros(this, map, gc, i, movX, movY, reductorVelocidad);
    }

    @Override
    public void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY) {
        animacion.renderAnimacion(-movX, -movY, hitbox.getRectangulo().getX(), hitbox.getRectangulo().getY() - 32);
    }
}
