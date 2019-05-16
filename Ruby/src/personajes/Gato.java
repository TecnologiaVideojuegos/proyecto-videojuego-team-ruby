package personajes;

import animaciones.Animacion_dinamica;
import elementos.Dialogo;
import elementos.Hitbox;
import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.SlickException;

public class Gato extends Npc {

    public Gato(Hitbox hitbox, int vida, int dinero) throws SlickException {
        super("Gato", hitbox, new Animacion_dinamica("./resources/sprites/Gato.png"), vida, dinero, new ArrayList<Dialogo>());
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Miau"))));
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("Menos mal, estás bien"))));
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("Volvamos a casa Manchas"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Miau"))));
    }

    public void huir() {
        this.getHitbox().updatePos(8, 0);
        animacion.setAnimaPersonaje(1);
    }
}
