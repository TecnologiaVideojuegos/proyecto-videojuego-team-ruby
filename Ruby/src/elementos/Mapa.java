package elementos;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import personajes.*;

public class Mapa {

    private TiledMap map;

    //Elementos del mapa
    private ArrayList<Hitbox> blocks;   //Elementos del mapa bloqueados al movimiento
    //private ArrayList<Hitbox> enemigos;
    private ArrayList<Enemigo> enemigos;
    //private ArrayList<Hitbox> npcs;
    private ArrayList<Npc> npcs;
    private ArrayList<Hitbox> huerto;

    //Constructor por defecto
    public Mapa() {

    }

    public Mapa(String ruta) throws SlickException {
        this.map = new TiledMap(ruta);;

        //Carga de elementos del mapa
        blocks = new ArrayList<>();
        enemigos = new ArrayList<>();
        npcs = new ArrayList<>();
        huerto = new ArrayList<>();
        cargaMuros();
        cargaEnemigos();
        cargaNPCs();
        cargaHuerto();
        /*TODO: cargaSaltosEstado */
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public ArrayList<Hitbox> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Hitbox> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(ArrayList<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }

    public ArrayList<Npc> getNpcs() {
        return npcs;
    }

    public void setNpcs(ArrayList<Npc> npcs) {
        this.npcs = npcs;
    }

    public ArrayList<Hitbox> getHuerto() {
        return huerto;
    }

    public void setHuerto(ArrayList<Hitbox> huerto) {
        this.huerto = huerto;
    }

    //*****************************************************//
    //***              CARGA DE ELEMENTOS               ***//
    //*****************************************************//
    private void cargaMuros() {
        int wallLayer = map.getLayerIndex("Walls"); //TODO: definir otro nombre para la capa

        if (wallLayer != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        blocks.add(new Hitbox((float) i * 32, (float) j * 32, 32, 32));  //32 = ancho del patron
                    }
                }
            }
        }
    }

    private void cargaEnemigos() throws SlickException {
        int wallLayer = map.getLayerIndex("Enemigos");

        if (wallLayer != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        enemigos.add(new Enemigo(
                                "Boss",
                                new Hitbox((float) i * 32, (float) j * 32, 32, 32),
                                10));  //32 = ancho del patron
                    }
                }
            }
        }
    }

    private void cargaNPCs() throws SlickException {
        int wallLayer = map.getLayerIndex("NPCs");

        if (wallLayer != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        npcs.add(new Npc(new Hitbox((float) i * 32, (float) j * 32, 32, 32)));  //32 = ancho del patron
                    }
                }
            }
        }
    }

    private void cargaHuerto() {
        int wallLayer = map.getLayerIndex("Huerto");

        if (wallLayer != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        huerto.add(new Hitbox((float) i * 32, (float) j * 32, 32, 32));  //32 = ancho del patron
                    }
                }
            }
        }
    }

    //*****************************************************//
    //***               UPDATE ELEMENTOS                ***//
    //*****************************************************//
    public void actualizarElementos(float pos_x, float pos_y) {

        //Elementos muro
        if (!blocks.isEmpty()) {
            blocks.forEach((hitbox) -> {
                hitbox.updatePos(pos_x, pos_y);
            });
        }

        //Elementos enemigos
        if (!enemigos.isEmpty()) {
            enemigos.forEach((enemigo) -> {
                enemigo.getHitbox().updatePos(pos_x, pos_y);
            });
        }

        //Elementos npc
        if (!npcs.isEmpty()) {
            npcs.forEach((npc) -> {
                npc.getHitbox().updatePos(pos_x, pos_y);
            });
        }

        //Elementos huerto
        if (!huerto.isEmpty()) {
            huerto.forEach((hitbox) -> {
                hitbox.updatePos(pos_x, pos_y);
            });
        }
    }

    //*****************************************************//
    //***               RENDER ELEMENTOS                ***//
    //*****************************************************//
    public void renderMap(GameContainer gc, double x, double y, Graphics grphcs, boolean ver_hitbox) {
        map.render((int) x, (int) y, 0, 0, gc.getWidth(), gc.getHeight());

        //Dibujo de los elementos de colision
        boolean amarillo = true;

        //Elementos tipo muro
        if (ver_hitbox) {
            for (Hitbox hitbox : blocks) {
                if (amarillo) {
                    grphcs.setColor(Color.cyan);
                    amarillo = false;
                } else {
                    grphcs.setColor(Color.yellow);
                    amarillo = true;
                }
                grphcs.drawRect(hitbox.getRectangulo().getX(), hitbox.getRectangulo().getY(), hitbox.getRectangulo().getWidth(), hitbox.getRectangulo().getHeight());
            }
        }

        //Elementos tipo enemigo
        for (Enemigo enemigo : enemigos) {
            enemigo.renderPersonaje(gc);
            if (ver_hitbox) {
                grphcs.setColor(Color.red);
                grphcs.drawRect(enemigo.getHitbox().getRectangulo().getX(), enemigo.getHitbox().getRectangulo().getY(), enemigo.getHitbox().getRectangulo().getWidth(), enemigo.getHitbox().getRectangulo().getHeight());
            }
        }

        //Elementos tipo npc
        for (Npc npc : npcs) {
            npc.renderPersonaje(gc);
            if (ver_hitbox) {
                grphcs.setColor(Color.green);
                grphcs.drawRect(npc.getHitbox().getRectangulo().getX(), npc.getHitbox().getRectangulo().getY(), npc.getHitbox().getRectangulo().getWidth(), npc.getHitbox().getRectangulo().getHeight());
            }
        }

        if (ver_hitbox) {
            //Elementos tipo huerto
            amarillo = true;
            for (Hitbox hitbox : huerto) {
                if (amarillo) {
                    grphcs.setColor(Color.orange);
                    amarillo = false;
                } else {
                    grphcs.setColor(Color.yellow);
                    amarillo = true;
                }
                grphcs.drawRect(hitbox.getRectangulo().getX(), hitbox.getRectangulo().getY(), hitbox.getRectangulo().getWidth(), hitbox.getRectangulo().getHeight());
            }
            grphcs.setColor(Color.white);
        }
    }
}
