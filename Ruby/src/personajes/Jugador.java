package personajes;

import animaciones.Animacion_dinamica;
import elementos.Hitbox;
import objetos.Inventario;
import objetos.plantas.Planta;
import objetos.plantas.Planta_agua;
import objetos.plantas.Planta_fuego;
import objetos.plantas.Planta_rayo;
import objetos.pociones.Pocion_ataque;
import objetos.pociones.Pocion_vida;
import objetos.semillas.Semilla_agua;
import objetos.semillas.Semilla_fuego;
import objetos.semillas.Semilla_rayo;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Jugador extends Personaje {
    
    private int nivel;
    private boolean multiplicadorDanio;
    private int contadorTurnos;
    private Animacion_dinamica animacion;
    private Inventario inventario = new Inventario();

    public Jugador(Hitbox hitbox) throws SlickException {
        super("Ruby", hitbox, 100, 100);
        this.animacion = new Animacion_dinamica("./resources/sprites/Ruby.png");
        inventario.anadirObjeto(new Planta_fuego(), 25);
        inventario.anadirObjeto(new Semilla_fuego(), 3);
        inventario.anadirObjeto(new Planta_agua(), 1);
        inventario.anadirObjeto(new Semilla_agua(), 5);
        inventario.anadirObjeto(new Planta_rayo(), 1);
        inventario.anadirObjeto(new Semilla_rayo(), 2);
        inventario.anadirObjeto(new Pocion_vida(), 5);
        inventario.anadirObjeto(new Pocion_ataque(), 2);
        inventario.eliminarObjeto(new Semilla_rayo());
        inventario.anadirObjeto(new Planta_agua(), 2);
        this.nivel = 1;
        this.multiplicadorDanio = false;
    }

    public Jugador(String nombre, Hitbox hitbox, Animacion_dinamica animacion, int vida, int dinero) throws SlickException {
        super(nombre, hitbox, vida, dinero);
        this.animacion = animacion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public boolean ataque(Personaje enemigo, Planta planta) {
        boolean exito = false;
        int numRandom = (int) (Math.random() * 10);
        int danio = 0;
        
        inventario.eliminarObjeto(planta);
        
        if (numRandom != 0) {
            danio = planta.getDanio();
            if (numRandom == 2) {   //Critico
                danio *= 2;
            }
            
            if(multiplicadorDanio){
                danio*=2;
                contadorTurnos--;
                if(contadorTurnos == 0){
                    multiplicadorDanio = false;
                }
            }
            
            enemigo.setVida(enemigo.getVida() - danio);
            exito = true;
        }
        return exito;
    }
    
    public void tomarPocionDanio() throws SlickException{
        inventario.eliminarObjeto(new Pocion_ataque());
        contadorTurnos = 3;
        multiplicadorDanio = true;
    }

    public void tomarPocionVida() throws SlickException{
        inventario.eliminarObjeto(new Pocion_vida());
        super.setVida(super.getVida()+ 25 + 5 * nivel);
    }
    
    // TODO: comprar
    // TODO: vender
    // TODO: plantar
    // TODO: recolectar
    @Override
    public void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY) {
        animacion.renderAnimacion(movEjeX, movEjeY, (gc.getWidth() / 2), (gc.getHeight() / 2));
    }

    public Inventario getInventario() {
        return inventario;
    }
}
