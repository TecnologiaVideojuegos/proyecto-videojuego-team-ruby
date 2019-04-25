package services;

import javafx.scene.text.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Dialog_Service {
    
    public static void mostrarBocadillo(Graphics grphcs, String frase1){
        
    }
    
    public static void mostrarBocadillo(Graphics grphcs, String frase1, String frase2){
        
    }
    
    public static void mostrarBocadillo(Graphics grphcs, String frase1, String frase2, String frase3){
        grphcs.setColor(Color.lightGray);
        grphcs.fillRect(50, 830, 1820, 200);
        grphcs.setColor(Color.black);
        grphcs.drawString(frase1, 800, 870);
        grphcs.drawString(frase2, 800, 930);
        grphcs.drawString(frase3, 800, 990);
    }
    
    public static void mostrarBocadillo(Graphics grphcs, String frase1, String frase2, String frase3, String frase4){
        
    }
}
