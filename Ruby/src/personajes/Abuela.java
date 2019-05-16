package personajes;

import animaciones.Animacion_dinamica;
import elementos.Dialogo;
import elementos.Hitbox;
import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.SlickException;

public class Abuela extends Npc{
    
    public Abuela(Hitbox hitbox) throws SlickException {
        super("Abuela", hitbox, new Animacion_dinamica("./resources/sprites/Abuela.png"), 1, 0, new ArrayList<Dialogo>());
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Ruby,", "¿Me quieres comprar algo?"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Comercio_Service"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Ya se ha vuelto a escapar el gato"))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Buenos días Ruby,","¿qué tal has dormido?"))));
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("Muy bien abuela"))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("gato.huir()"))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Uy, Manchas se ha escapado,","tendrás que ir a por él,","no vaya a ser que le pase algo"))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Pero antes de ir a por Manchas","tendrás que prepararte, la cueva por la","que se ha ido es muy peligrosa."))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Toma estas semillas y plántalas en el huerto,","acercate al huerto y haz click para plantarlas"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Cuando acabes vuelve a hablar conmigo"))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Toma estas plantas Ruby,","te serviran para lanzar los hechizos como te enseñé"))));
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("Gracias abuela"))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Manaña las semillas habrán crecido y"+"las podrás recoger haciendo click en ellas"))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Por cierto,"+" si quieres ver tu inventario","recuerda que es pulsando la E"))));
        super.getDialogos().add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Y si quieres comprar algo,","te estaré esperando aquí"))));
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("Ok abuela,"," me voy a por Manchas"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Ten mucho cuidado"))));
    }
}
