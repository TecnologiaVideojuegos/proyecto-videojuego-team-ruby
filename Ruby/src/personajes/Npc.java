package personajes;

import animaciones.Animacion_estatica;
import elementos.Dialogo;
import elementos.Hitbox;
import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Npc extends Personaje {

    private Animacion_estatica animacion;

    public Npc(Hitbox hitbox) throws SlickException {
        super("NPC", hitbox, 100, 1000, new ArrayList<Dialogo>());
        //ArrayList<Dialogo> dialogos = super.getDialogos();
        //dialogos.add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Pipo recibe una patada", "Hostia.. Perro", "Pasan los meses....", "Pipo muere"))));
        //dialogos.add(new Dialogo(true, false, new ArrayList<>(Arrays.asList("Pues OK"))));
        //super.setDialogos(dialogos);
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("Pipo recibe una patada", "Hostia.. Perro", "Pasan los meses....", "Pipo muere"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Pues OK"))));
        this.animacion = new Animacion_estatica("./resources/img/npc.png");
    }

    public Npc(String nombre, Hitbox hitbox, Animacion_estatica animacion, int vida, int dinero) {
        super(nombre, hitbox, vida, dinero, new ArrayList<Dialogo>());
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("Pipo recibe una patada", "Hostia.. Perro", "Pasan los meses....", "Pipo muere"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Pues OK"))));
        this.animacion = animacion;
    }

    // TODO: comprobacion de nivel del jugador para mostrar nivel    
    @Override
    public void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY) {
        animacion.renderAnimacion(hitbox.getRectangulo().getX(), hitbox.getRectangulo().getY());
    }
}
