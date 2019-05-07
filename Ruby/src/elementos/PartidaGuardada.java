package elementos;

import java.io.Serializable;
import objetos.plantas.Planta_agua;
import objetos.plantas.Planta_fuego;
import objetos.plantas.Planta_rayo;
import objetos.pociones.Pocion_ataque;
import objetos.pociones.Pocion_vida;
import objetos.semillas.Semilla_agua;
import objetos.semillas.Semilla_fuego;
import objetos.semillas.Semilla_rayo;
import org.newdawn.slick.SlickException;
import personajes.Jugador;

public class PartidaGuardada implements Serializable{
    //Ruby
    private int dinero;
    private int nivel;
    private int numSAgua, numSFuego, numSRayo, numFAgua, numFFuego, numFRayo, numPVida, numPDanio;
    
    public PartidaGuardada(Jugador ruby) throws SlickException{
        setDatosRuby(ruby);
    }
    
    public void setDatosRuby(Jugador ruby) throws SlickException{
        dinero = ruby.getDinero();
        nivel = ruby.getNivel();
        
        numSAgua = ruby.getInventario().getCantidad(new Semilla_agua());
        numSFuego = ruby.getInventario().getCantidad(new Semilla_fuego());
        numSRayo = ruby.getInventario().getCantidad(new Semilla_rayo());
        numFAgua = ruby.getInventario().getCantidad(new Planta_agua());
        numFFuego = ruby.getInventario().getCantidad(new Planta_fuego());
        numFRayo = ruby.getInventario().getCantidad(new Planta_rayo());
        numPVida = ruby.getInventario().getCantidad(new Pocion_vida());
        numPDanio = ruby.getInventario().getCantidad(new Pocion_ataque());
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNumSAgua() {
        return numSAgua;
    }

    public void setNumSAgua(int numSAgua) {
        this.numSAgua = numSAgua;
    }

    public int getNumSFuego() {
        return numSFuego;
    }

    public void setNumSFuego(int numSFuego) {
        this.numSFuego = numSFuego;
    }

    public int getNumSRayo() {
        return numSRayo;
    }

    public void setNumSRayo(int numSRayo) {
        this.numSRayo = numSRayo;
    }

    public int getNumFAgua() {
        return numFAgua;
    }

    public void setNumFAgua(int numFAgua) {
        this.numFAgua = numFAgua;
    }

    public int getNumFFuego() {
        return numFFuego;
    }

    public void setNumFFuego(int numFFuego) {
        this.numFFuego = numFFuego;
    }

    public int getNumFRayo() {
        return numFRayo;
    }

    public void setNumFRayo(int numFRayo) {
        this.numFRayo = numFRayo;
    }

    public int getNumPVida() {
        return numPVida;
    }

    public void setNumPVida(int numPVida) {
        this.numPVida = numPVida;
    }

    public int getNumPDanio() {
        return numPDanio;
    }

    public void setNumPDanio(int numPDanio) {
        this.numPDanio = numPDanio;
    }
    
    
}
