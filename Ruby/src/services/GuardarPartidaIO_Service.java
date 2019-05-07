package services;

import elementos.PartidaGuardada;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class GuardarPartidaIO_Service {

    private static String rubyPath = "save_ruby.txt";

    public static void cargarPartida(Jugador ruby/*, Huerto huerto*/) throws FileNotFoundException, IOException, ClassNotFoundException, SlickException {
        PartidaGuardada guardado;
        FileInputStream fileIn;
        ObjectInputStream objectIn;

        //Cargar guardado
        fileIn = new FileInputStream(new File(rubyPath));
        objectIn = new ObjectInputStream(fileIn);
        guardado = (PartidaGuardada) objectIn.readObject();
        objectIn.close();
        fileIn.close();
        System.out.println("Objeto Jugador ruby leido con exito");

        //Actualizar ruby 
        ruby.setDinero(guardado.getDinero());
        ruby.setNivel(guardado.getNivel());
        ruby.getInventario().getPlantas().clear();
        ruby.getInventario().getSemillas().clear();
        ruby.getInventario().getPociones().clear();
        ruby.getInventario().anadirObjeto(new Planta_agua(), guardado.getNumFAgua());
        ruby.getInventario().anadirObjeto(new Planta_fuego(), guardado.getNumFFuego());
        ruby.getInventario().anadirObjeto(new Planta_rayo(), guardado.getNumFRayo());
        ruby.getInventario().anadirObjeto(new Semilla_agua(), guardado.getNumSAgua());
        ruby.getInventario().anadirObjeto(new Semilla_fuego(), guardado.getNumSFuego());
        ruby.getInventario().anadirObjeto(new Semilla_rayo(), guardado.getNumSRayo());
        ruby.getInventario().anadirObjeto(new Pocion_vida(), guardado.getNumPVida());
        ruby.getInventario().anadirObjeto(new Pocion_ataque(), guardado.getNumPDanio());
    }

    public static void guardarPartida(Jugador ruby/*, Huerto huerto*/) throws FileNotFoundException, IOException, SlickException {

        PartidaGuardada guardado = new PartidaGuardada(ruby/*, Huerto huerto*/);
        
        FileOutputStream fileOut;
        ObjectOutputStream objectOut;

        //Guardado de Ruby
        fileOut = new FileOutputStream(new File(rubyPath));
        objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(guardado);
        objectOut.close();
        fileOut.close();
        System.out.println("Objeto Jugador ruby guardado con exito");

    }
}
