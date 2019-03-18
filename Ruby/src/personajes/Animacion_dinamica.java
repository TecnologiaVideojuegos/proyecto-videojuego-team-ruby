package personajes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;



public class Animacion_dinamica extends Animacion{

    // TODO: fijar valores para todos los sprites
    private final int ANCHO = 64;   // Ancho de cada frame del sprite
    private final int ALTO = 65;    // Alto de cada frame del sprite
    
    private Animation animacion;
    private SpriteSheet sprite;
    
    
    public Animacion_dinamica(String dir) throws SlickException {
        super();
        
        sprite = new SpriteSheet(dir, ANCHO, ALTO);
        animacion = new Animation();
        
        //Recorte del sprite
        for (int j = 0; j < 4; j++) {   // TODO: fijar, parametizar o automatizar filas/columnas
            for (int i = 0; i < 9; i++) {
                animacion.addFrame(sprite.getSprite(i, j), 50);
            }
        }
    }
    
    @Override
    public void ActualizarPosicion(float pos_x, float pos_y) {
        //TODO: Actualiza la posicion del objeto en el pantalla
    }
    
    // TODO: Actualizar posicion
}
