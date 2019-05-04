package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import personajes.Jugador;

public class GuardarPartidaIO_Service {

    private static String rubyPath = "save_ruby.txt";
    private static String huertoPath = "save_huerto.txt";

    public static Object[] cargarPartida() throws FileNotFoundException, IOException, ClassNotFoundException {
        Object objetos_leidos[] = new Object[2];
        FileInputStream fileIn;
        ObjectInputStream objectIn;

        //Cargar Ruby
        fileIn = new FileInputStream(new File(rubyPath));
        objectIn = new ObjectInputStream(fileIn);
        objetos_leidos[0] = (Object) objectIn.readObject();
        objectIn.close();
        fileIn.close();
        System.out.println("Objeto Jugador ruby leido con exito");

        //Cargar Huerto
        /*fileIn = new FileInputStream(new File(huertoPath));
        objectIn = new ObjectInputStream(fileIn);
        objetos_leidos[1] = (Object) objectIn.readObject();
        objectIn.close();
        fileIn.close();
        System.out.println("Objeto Jugador ruby leido con exito");*/
        return objetos_leidos;
    }

    public static void guardarPartida(Jugador ruby/*, Huerto huerto*/) throws FileNotFoundException, IOException {

        FileOutputStream fileOut;
        ObjectOutputStream objectOut;

        //Guardado de Ruby
        fileOut = new FileOutputStream(new File(rubyPath));
        objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(ruby);
        objectOut.close();
        fileOut.close();
        System.out.println("Objeto Jugador ruby guardado con exito");

        //Guardado del Huerto
        /*fileOut = new FileOutputStream(new File(huertoPath));
        objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(huerto);
        objectOut.close();
        fileOut.close();
        System.out.println("Objeto Huerto huerto guardado con exito");*/
    }
}
