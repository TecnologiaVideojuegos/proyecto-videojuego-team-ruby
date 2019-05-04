package services;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Dialog_Service {

    private static void cargarComun(Graphics grphcs, boolean habla_Ruby, boolean cont_hablando) throws SlickException {
        Image imagen_ruby = new Image("./resources/img/RubyAvatar.png");
        Image imagen_npc = new Image("./resources/img/npc_cabeza.png");
        Font font = new Font("Segoe UI", Font.BOLD, 30);
        Color marron = new Color(90, 60, 40);
        Color verde = new Color(180, 255, 180);
        Color transparente = new Color(180, 255, 180, 200);
        grphcs.setColor(marron);
        grphcs.fillRect(0, 475, 1280, 245);
        grphcs.setColor(verde);
        grphcs.fillRect(5, 480, 1270, 235);
        grphcs.setColor(marron);
        grphcs.fillRect(15, 491, 215, 215);
        grphcs.fillRect(1051, 491, 215, 215);
        grphcs.setColor(verde);
        grphcs.fillRect(20, 495, 205, 205);
        grphcs.fillRect(1056, 496, 205, 205);
        imagen_ruby.draw(20, 495, 205, 205);
        imagen_npc.draw(1056, 496, 205, 205);
        grphcs.setColor(marron);
        grphcs.setFont(new TrueTypeFont(font, true));
        if (cont_hablando) {
            grphcs.drawString("...", 1001, 679);
        }
        if (habla_Ruby) {
            grphcs.setColor(transparente);
            grphcs.fillRect(1056, 496, 205, 205);
        } else {
            grphcs.setColor(transparente);
            grphcs.fillRect(20, 495, 205, 205);
        }
        grphcs.setColor(marron);
    }

    /**
     *
     * @param gc
     * @param grphcs
     * @param frase1
     * @param habla_Ruby indica si está hablando Ruby
     * @param cont_hablando indica si luego se va a continuar hablando
     */
    public static void mostrarBocadillo(GameContainer gc, Graphics grphcs, String frase1, boolean habla_Ruby, boolean cont_hablando) {
        try {
            cargarComun(grphcs, habla_Ruby, cont_hablando);
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
    public static void mostrarBocadillo(GameContainer gc, Graphics grphcs, String frase1, String frase2, boolean habla_Ruby, boolean cont_hablando) {
        try {
            cargarComun(grphcs, habla_Ruby, cont_hablando);
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
    public static void mostrarBocadillo(GameContainer gc, Graphics grphcs, String frase1, String frase2, String frase3, boolean habla_Ruby, boolean cont_hablando) {
        try {
            cargarComun(grphcs, habla_Ruby, cont_hablando);
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
    public static void mostrarBocadillo(GameContainer gc, Graphics grphcs, String frase1, String frase2, String frase3, String frase4, boolean habla_Ruby, boolean cont_hablando) {
        try {
            cargarComun(grphcs, habla_Ruby, cont_hablando);
            grphcs.drawString(frase1, 252, 496);
            grphcs.drawString(frase2, 252, 496 + (183 / 4) * 1);
            grphcs.drawString(frase3, 252, 496 + (183 / 4) * 2);
            grphcs.drawString(frase4, 252, 496 + (183 / 4) * 3);
        } catch (SlickException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
