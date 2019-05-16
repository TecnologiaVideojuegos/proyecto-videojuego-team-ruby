package services;

import elementos.Dialogo;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Dialog_Service{

    private static void cargarComun(Graphics grphcs, boolean habla_Ruby, boolean cont_hablando, Image imagen) throws SlickException {
        Image imagen_ruby = new Image("./resources/img/RubyAvatar.png");
        Image imagen_npc = imagen;
        Font font = new Font("Segoe UI", Font.BOLD, 30);
        Color naranjaOscuro = new Color(210, 113, 18);
        Color naranja = new Color(254, 195, 117);
        Color transparente = new Color(251, 264, 204, 150);
        grphcs.setColor(naranjaOscuro);
        grphcs.fillRect(0, 475, 1280, 245);
        grphcs.setColor(naranja);
        grphcs.fillRect(5, 480, 1270, 235);
        grphcs.setColor(naranjaOscuro);
        grphcs.fillRect(15, 491, 215, 215);
        grphcs.fillRect(1051, 491, 215, 215);
        grphcs.setColor(naranja);
        grphcs.fillRect(20, 495, 205, 205);
        grphcs.fillRect(1056, 496, 205, 205);
        imagen_ruby.draw(20, 495, 205, 205);
        imagen_npc.draw(1056, 496, 205, 205);
        grphcs.setColor(naranjaOscuro);
        grphcs.setFont(new TrueTypeFont(font, true));
        if (cont_hablando) {
            grphcs.setColor(Color.black);
            grphcs.drawString("...", 1001, 665);
        }
        if (habla_Ruby) {
            grphcs.setColor(transparente);
            grphcs.fillRect(1056, 496, 205, 205);
        } else {
            grphcs.setColor(transparente);
            grphcs.fillRect(20, 495, 205, 205);
        }
        grphcs.setColor(naranjaOscuro);
    }

    /**
     *
     * @param gc
     * @param grphcs
     * @param frase1
     * @param habla_Ruby indica si está hablando Ruby
     * @param cont_hablando indica si luego se va a continuar hablando
     */
    private static void mostrarBocadillo1(GameContainer gc, Graphics grphcs, String frase1, boolean habla_Ruby, boolean cont_hablando, Image imagen) {
        try {
            cargarComun(grphcs, habla_Ruby, cont_hablando, imagen);
            grphcs.setColor(Color.black);
            grphcs.drawString(frase1, 252, 496);
        } catch (SlickException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    /**
     *
     * @param gc
     * @param grphcs
     * @param frase1
     * @param frase2
     * @param habla_Ruby indica si está hablando Ruby
     * @param cont_hablando indica si luego se va a continuar hablando
     */
    private static void mostrarBocadillo1(GameContainer gc, Graphics grphcs, String frase1, String frase2, boolean habla_Ruby, boolean cont_hablando, Image imagen) {
        try {
            cargarComun(grphcs, habla_Ruby, cont_hablando, imagen);
            grphcs.setColor(Color.black);
            grphcs.drawString(frase1, 252, 496);
            grphcs.drawString(frase2, 252, 496 + (183 / 2) * 1);
        } catch (SlickException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    /**
     *
     * @param gc
     * @param grphcs
     * @param frase1
     * @param frase2
     * @param frase3
     * @param habla_Ruby indica si está hablando Ruby
     * @param cont_hablando indica si luego se va a continuar hablando
     */
    private static void mostrarBocadillo1(GameContainer gc, Graphics grphcs, String frase1, String frase2, String frase3, boolean habla_Ruby, boolean cont_hablando, Image imagen) {
        try {
            cargarComun(grphcs, habla_Ruby, cont_hablando, imagen);
            grphcs.setColor(Color.black);
            grphcs.drawString(frase1, 252, 496);
            grphcs.drawString(frase2, 252, 496 + (183 / 3) * 1);
            grphcs.drawString(frase3, 252, 496 + (183 / 3) * 2);
        } catch (SlickException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    /**
     *
     * @param gc
     * @param grphcs
     * @param frase1
     * @param frase2
     * @param frase3
     * @param frase4
     * @param habla_Ruby indica si está hablando Ruby
     * @param cont_hablando indica si luego se va a continuar hablando
     */
    private static void mostrarBocadillo1(GameContainer gc, Graphics grphcs, String frase1, String frase2, String frase3, String frase4, boolean habla_Ruby, boolean cont_hablando, Image imagen) {
        try {
            cargarComun(grphcs, habla_Ruby, cont_hablando, imagen);
            grphcs.setColor(Color.black);
            grphcs.drawString(frase1, 252, 496);
            grphcs.drawString(frase2, 252, 496 + (183 / 4) * 1);
            grphcs.drawString(frase3, 252, 496 + (183 / 4) * 2);
            grphcs.drawString(frase4, 252, 496 + (183 / 4) * 3);
        } catch (SlickException e) {
            System.out.println("Error: " + e.toString());
        }
    }
    
    public static void mostrarBocadillo(GameContainer gc, Graphics grphcs, Dialogo dialogo, Image imagen){
        switch (dialogo.getFrases().size()) {
            case 1:
                mostrarBocadillo1(gc, grphcs, dialogo.getFrases().get(0), dialogo.isHabla_Ruby(), dialogo.isCont_hablando(), imagen);
                break;
            case 2:
                mostrarBocadillo1(gc, grphcs, dialogo.getFrases().get(0), dialogo.getFrases().get(1), dialogo.isHabla_Ruby(), dialogo.isCont_hablando(), imagen);
                break;
            case 3:
                mostrarBocadillo1(gc, grphcs, dialogo.getFrases().get(0), dialogo.getFrases().get(1), dialogo.getFrases().get(2), dialogo.isHabla_Ruby(), dialogo.isCont_hablando(), imagen);
                break;
            default:
                mostrarBocadillo1(gc, grphcs, dialogo.getFrases().get(0), dialogo.getFrases().get(1), dialogo.getFrases().get(2), dialogo.getFrases().get(3), dialogo.isHabla_Ruby(), dialogo.isCont_hablando(), imagen);
                break;
        }
    }
}