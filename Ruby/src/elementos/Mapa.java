package elementos;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Mapa {
    
    private TiledMap map; 
    
    //Elementos del mapa bloqueados al movimiento
    private ArrayList<Hitbox> blocks; 
    
    //Constructor por defecto
    public Mapa(){
        
    }

    public Mapa(String ruta) throws SlickException {
        this.map = new TiledMap(ruta);;
        
        //Carga de elementos del mapa
        cargaMuros();
        /*TODO: cargaNPCs
                cargaHuerto
                cargaSaltosEstado
        */
    }
    
    
    //*****************************************************//
    //***              CARGA DE ELEMENTOS               ***//
    //*****************************************************//
    public void cargaMuros() {
        int wallLayer = map.getLayerIndex("Walls"); //TODO: definir otro nombre par la capa

        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                if (map.getTileId(i, j, wallLayer) != 0) {
                    blocks.add(new Hitbox((float) i * 32, (float) j * 32, 32, 32));  //32 = ancho del patron
                }
            }
        }
    }
    
    
    //*****************************************************//
    //***               UPDATE ELEMENTOS                ***//
    //*****************************************************//
    public void actualizaMuros(float pos_x, float pos_y) {
        for (Hitbox hitbox : blocks) {
            hitbox.updatePos(pos_x, pos_y);
        }
    }
    
}
