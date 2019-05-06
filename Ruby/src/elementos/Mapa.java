package elementos;

import java.util.ArrayList;
import objetos.plantas.Planta;
import objetos.plantas.Planta_agua;
import objetos.plantas.Planta_fuego;
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
    private ArrayList<Enemigo> enemigos;
    private ArrayList<Boss> bosses;
    private ArrayList<Npc> npcs;
    private ArrayList<Hitbox> huerto;
    private ArrayList<Spawn> spawns;
    private ArrayList<Planta> plantas;

    //Constructor por defecto
    public Mapa() {
    }

    public Mapa(String ruta) throws SlickException {
        this.map = new TiledMap(ruta);

        //Carga de elementos del mapa
        blocks = new ArrayList<>();
        enemigos = new ArrayList<>();
        bosses = new ArrayList<>();
        npcs = new ArrayList<>();
        huerto = new ArrayList<>();
        spawns = new ArrayList<>();
        plantas = new ArrayList<>();
        cargaMuros();
        cargaEnemigos();
        cargaBosses();
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

    public ArrayList<Boss> getBosses() {
        return bosses;
    }

    public void setBosses(ArrayList<Boss> bosses) {
        this.bosses = bosses;
    }

    public ArrayList<Spawn> getSpawns() {
        return spawns;
    }

    public void setSpawns(ArrayList<Spawn> spawns) {
        this.spawns = spawns;
    }

    public ArrayList<Hitbox> getHitboxNpc() {
        ArrayList<Hitbox> hitboxNpcs = new ArrayList<>();
        for (Npc npc : npcs) {
            hitboxNpcs.add(npc.getHitbox());
        }
        return hitboxNpcs;
    }

    public ArrayList<Planta> getPlantas() {
        return plantas;
    }

    public void anadirPlanta_fuego(float pos_x, float pos_y) throws SlickException {
        Planta planta = new Planta_agua();
        planta.setCoordenadas(pos_x, pos_y);
        plantas.add(planta);
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
                                "Enemigo",
                                new Hitbox((float) i * 32, (float) j * 32, 32, 32),
                                10));  //32 = ancho del patron
                    }
                }
            }
        }
    }

    private void cargaBosses() throws SlickException {
        int wallLayer = map.getLayerIndex("Bosses");

        if (wallLayer != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        bosses.add(new Boss(
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
        //Plantas
        if (!plantas.isEmpty()) {
            plantas.forEach((planta) -> {
                planta.setCoordenadas(planta.getPos_x() + pos_x, planta.getPos_y() + pos_y);
            });
        }

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

        //Elementos bosses
        if (!bosses.isEmpty()) {
            bosses.forEach((boss) -> {
                boss.getHitbox().updatePos(pos_x, pos_y);
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

        //Elementos spawn
        if (!spawns.isEmpty()) {
            spawns.forEach((spawn) -> {
                spawn.getHitbox().updatePos(pos_x, pos_y);
            });
        }
    }

    public void movimientoEnemigos(int i, GameContainer gc, Hitbox hitboxRuby) {
        //Elementos enemigos
        if (!enemigos.isEmpty()) {
            enemigos.forEach((enemigo) -> {
                enemigo.movimientoEnemigo(i, this, gc, hitboxRuby);
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

        //Elementos tipo Planta
        for (Planta planta : plantas) {
            planta.render();
        }

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
            enemigo.renderPersonaje(gc, 0, 0);
            if (ver_hitbox) {
                grphcs.setColor(Color.red);
                grphcs.drawRect(enemigo.getHitbox().getRectangulo().getX(), enemigo.getHitbox().getRectangulo().getY(), enemigo.getHitbox().getRectangulo().getWidth(), enemigo.getHitbox().getRectangulo().getHeight());
            }
        }

        //Elementos tipo boss
        for (Boss boss : bosses) {
            boss.renderPersonaje(gc, 0, 0);
            if (ver_hitbox) {
                grphcs.setColor(Color.red);
                grphcs.drawRect(boss.getHitbox().getRectangulo().getX(), boss.getHitbox().getRectangulo().getY(), boss.getHitbox().getRectangulo().getWidth(), boss.getHitbox().getRectangulo().getHeight());
            }
        }

        //Elementos tipo npc
        for (Npc npc : npcs) {
            npc.renderPersonaje(gc, 0, 0);
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

            //Elementos tipo spawn
            for (Spawn spawn : spawns) {
                grphcs.setColor(Color.pink);
                grphcs.drawRect(spawn.getHitbox().getRectangulo().getX(), spawn.getHitbox().getRectangulo().getY(), spawn.getHitbox().getRectangulo().getWidth(), spawn.getHitbox().getRectangulo().getHeight());
            }
            grphcs.setColor(Color.white);
        }
    }

    //*****************************************************//
    //***                    SPAWNS                     ***//
    //*****************************************************//
    public float[] getPosicionSpawn(String spawn) {
        int wallLayer = map.getLayerIndex(spawn);
        float posSpawn[] = new float[2];
        if (wallLayer != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        posSpawn[0] = (float) i * 32;
                        posSpawn[1] = (float) j * 32;
                    }
                }
            }
        }

        return posSpawn;
    }

    public void agregarSpawn(String spawn) {
        int wallLayer = map.getLayerIndex(spawn);

        if (wallLayer != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        spawns.add(new Spawn(new Hitbox((float) (i * 32), (float) (j * 32), 32, 32), spawn));  //32 = ancho del patron
                    }
                }
            }
        }
    }
}
