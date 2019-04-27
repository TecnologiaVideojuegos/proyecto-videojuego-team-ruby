package services;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

public class Dialog_Service {

    public static void mostrarBocadillo(GameContainer gc, Graphics grphcs, String frase1) {
        try {
            Image imagen = new Image("./resources/img/RubyAvatar.png");
            grphcs.setColor(Color.lightGray);
            grphcs.fillRect(gc.getWidth() / 16, gc.getHeight() * 5 / 8, gc.getWidth() * 7 / 8, gc.getHeight() * 5 / 16);
            grphcs.setColor(Color.black);
            Font font = new Font("Verdana", Font.BOLD, 30);
            imagen.draw(gc.getWidth() * 3 / 32, gc.getHeight() * 21 / 32, gc.getHeight()/4, gc.getHeight()/4);
            grphcs.setFont(new TrueTypeFont(font, true));
            grphcs.drawString(frase1, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 0) / (16 * 1));
            grphcs.setColor(Color.white);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static void mostrarBocadillo(GameContainer gc, Graphics grphcs, String frase1, String frase2) {
        try {
            Image imagen = new Image("./resources/img/RubyAvatar.png");
            grphcs.setColor(Color.lightGray);
            grphcs.fillRect(gc.getWidth() / 16, gc.getHeight() * 5 / 8, gc.getWidth() * 7 / 8, gc.getHeight() * 5 / 16);
            grphcs.setColor(Color.black);
            Font font = new Font("Verdana", Font.BOLD, 30);
            imagen.draw(gc.getWidth() * 3 / 32, gc.getHeight() * 21 / 32, gc.getHeight()/4, gc.getHeight()/4);
            grphcs.setFont(new TrueTypeFont(font, true));
            grphcs.drawString(frase1, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 0) / (16 * 2));
            grphcs.drawString(frase2, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 1) / (16 * 2));
            grphcs.setColor(Color.white);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static void mostrarBocadillo(GameContainer gc, Graphics grphcs, String frase1, String frase2, String frase3) {
        try {
            Image imagen = new Image("./resources/img/RubyAvatar.png");
            grphcs.setColor(Color.lightGray);
            grphcs.fillRect(gc.getWidth() / 16, gc.getHeight() * 5 / 8, gc.getWidth() * 7 / 8, gc.getHeight() * 5 / 16);
            grphcs.setColor(Color.black);
            Font font = new Font("Verdana", Font.BOLD, 30);
            imagen.draw(gc.getWidth() * 3 / 32, gc.getHeight() * 21 / 32, gc.getHeight()/4, gc.getHeight()/4);
            grphcs.setFont(new TrueTypeFont(font, true));
            grphcs.drawString(frase1, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 0) / (16 * 3));
            grphcs.drawString(frase2, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 1) / (16 * 3));
            grphcs.drawString(frase3, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 2) / (16 * 3));
            grphcs.setColor(Color.white);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static void mostrarBocadillo(GameContainer gc, Graphics grphcs, String frase1, String frase2, String frase3, String frase4) {
        try {
            Image imagen = new Image("./resources/img/RubyAvatar.png");
            grphcs.setColor(Color.lightGray);
            grphcs.fillRect(gc.getWidth() / 16, gc.getHeight() * 5 / 8, gc.getWidth() * 7 / 8, gc.getHeight() * 5 / 16);
            grphcs.setColor(Color.black);
            Font font = new Font("Verdana", Font.BOLD, 30);
            imagen.draw(gc.getWidth() * 3 / 32, gc.getHeight() * 21 / 32, gc.getHeight()/4, gc.getHeight()/4);
            grphcs.setFont(new TrueTypeFont(font, true));
            grphcs.drawString(frase1, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 0) / (16 * 4));
            grphcs.drawString(frase2, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 1) / (16 * 4));
            grphcs.drawString(frase3, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 2) / (16 * 4));
            grphcs.drawString(frase4, gc.getWidth() * 5 / 32 + gc.getHeight()/4, gc.getHeight() * 11 / 16 + gc.getHeight() * (3 * 3) / (16 * 4));
            grphcs.setColor(Color.white);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
