package personajes;

import animaciones.Animacion_dinamica;
import elementos.Dialogo;
import elementos.Hitbox;
import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Npc extends Personaje {

    private Animacion_dinamica animacion;

    public Npc(Hitbox hitbox) throws SlickException {
        super("NPC", hitbox, 100, 1000, new ArrayList<Dialogo>());
        //ArrayList<Dialogo> dialogos = super.getDialogos();
        //dialogos.add(new Dialogo(false, true, new ArrayList<>(Arrays.asList("Pipo recibe una patada", "Hostia.. Perro", "Pasan los meses....", "Pipo muere"))));
        //dialogos.add(new Dialogo(true, false, new ArrayList<>(Arrays.asList("Pues OK"))));
        //super.setDialogos(dialogos);
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("Pipo recibe una patada", "Hostia.. Perro", "Pasan los meses....", "Pipo muere"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Pues OK"))));
        animacion = new Animacion_dinamica("./resources/sprites/Abuela.png");
        animacion.direccionAnimacion(2);
    }

    public Npc(String nombre, Hitbox hitbox, Animacion_dinamica animacion, int vida, int dinero) {
        super(nombre, hitbox, vida, dinero, new ArrayList<Dialogo>());
        super.getDialogos().add(new Dialogo(true, true, new ArrayList<>(Arrays.asList("Pipo recibe una patada", "Hostia.. Perro", "Pasan los meses....", "Pipo muere"))));
        super.getDialogos().add(new Dialogo(false, false, new ArrayList<>(Arrays.asList("Pues OK"))));
        this.animacion = animacion;
        animacion.direccionAnimacion(2);
    }

    // TODO: comprobacion de nivel del jugador para mostrar nivel    
    @Override
    public void renderPersonaje(GameContainer gc, float movEjeX, float movEjeY) {
        animacion.renderAnimacion(-movEjeX, -movEjeY, hitbox.getRectangulo().getX() + 5, hitbox.getRectangulo().getY());
    }
}
