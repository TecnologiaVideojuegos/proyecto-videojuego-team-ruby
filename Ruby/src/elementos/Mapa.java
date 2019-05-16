package elementos;

import java.util.ArrayList;
import objetos.plantas.Planta;
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
    //private ArrayList<Npc> npcs;
    private Abuela abuela = null;
    private Gato gato = null;
    private Huerto huerto;
    private ArrayList<Spawn> spawns;
    private int nivelRuby;

    //Constructor por defecto
    public Mapa() {
    }

    public Mapa(String ruta, int nivelRuby, Huerto huerto_ant) throws SlickException {
        this.map = new TiledMap(ruta);

        //Carga de elementos del mapa
        blocks = new ArrayList<>();
        enemigos = new ArrayList<>();
        bosses = new ArrayList<>();
        //npcs = new ArrayList<>();
        spawns = new ArrayList<>();
        this.nivelRuby = nivelRuby;
        cargaMuros();
        cargaEnemigos();
        cargaBosses();
        cargaGato();
        cargaAbuela();
        cargaHuerto(huerto_ant);
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

    public Abuela getAbuela() {
        return abuela;
    }

    public void setAbuela(Abuela abuela) {
        this.abuela = abuela;
    }

    public Gato getGato() {
        return gato;
    }

    public void setGato(Gato gato) {
        this.gato = gato;
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
        if(abuela!=null){
            hitboxNpcs.add(abuela.getHitbox());
        }
        if(gato!=null){
            hitboxNpcs.add(gato.getHitbox());
        }
        return hitboxNpcs;
    }
    
    public ArrayList<Npc> getNpcs(){
        ArrayList<Npc> npcs = new ArrayList<>();
        if(abuela!=null){
            npcs.add(abuela);
        }
        if(gato!=null){
            npcs.add(gato);
        }
        return npcs;
    }

    public Huerto getHuerto() {
        return huerto;
    }

    public void setHuerto(Huerto huerto) {
        this.huerto = huerto;
    }

    public void anadirPlanta(int x, int y, Planta planta) throws SlickException {
        huerto.anadirPlanta(x, y, planta);
    }

    //*****************************************************//
    //***              CARGA DE ELEMENTOS               ***//
    //*****************************************************//
    private void cargaMuros() {
        int wallLayer = map.getLayerIndex("Walls");

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
                                nivelRuby));  //32 = ancho del patron
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
                                new Hitbox((float) i * 32, (float) j * 32 - 32, 32, 64),
                                nivelRuby));  //32 = ancho del patron
                    }
                }
            }
        }
    }
    
    private void cargaGato() throws SlickException {
        int wallLayer = map.getLayerIndex("Gato");
        
        if(wallLayer != -1){
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        gato = new Gato(new Hitbox((float) i * 32, (float) j * 32 - 32, 32, 64));
                    }
                }
            }
        }
    }
    
    private void cargaAbuela() throws SlickException {
        int wallLayer = map.getLayerIndex("Abuela");
        
        if(wallLayer != -1){
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        abuela = new Abuela(new Hitbox((float) i * 32, (float) j * 32 - 32, 32, 64));
                    }
                }
            }
        }
    }
    
    private void cargaHuerto(Huerto huerto_ant) {
        int wallLayer = map.getLayerIndex("Huerto");
        ArrayList<Hitbox> huerto_array = new ArrayList<>();
        int x = 0, y = 0, x_temp = 0, y_temp = 0;

        if (wallLayer != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, wallLayer) != 0) {
                        if (i > x_temp) {
                            x_temp = i;
                            x++;
                        }
                        if (j > y_temp) {
                            y_temp = j;
                            y++;
                        }
                        huerto_array.add(new Hitbox((float) i * 32, (float) j * 32, 32, 32));  //32 = ancho del patron
                    }
                }
            }
        }
        huerto = new Huerto(x, y, huerto_array, huerto_ant);
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

        //Elementos bosses
        if (!bosses.isEmpty()) {
            bosses.forEach((boss) -> {
                boss.getHitbox().updatePos(pos_x, pos_y);
            });
        }

        //Elementos npc
        if(abuela!=null){
            abuela.getHitbox().updatePos(pos_x, pos_y);
        }
        if(gato!=null){
            gato.getHitbox().updatePos(pos_x, pos_y);
        }

        huerto.updatePos(pos_x, pos_y);

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

        //Elementos tipo Planta
        huerto.renderPlantas();

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
        if (abuela != null) {
            abuela.renderPersonaje(gc, 0, 0);
            if (ver_hitbox) {
                grphcs.setColor(Color.green);
                grphcs.drawRect(abuela.getHitbox().getRectangulo().getX(), abuela.getHitbox().getRectangulo().getY(), abuela.getHitbox().getRectangulo().getWidth(), abuela.getHitbox().getRectangulo().getHeight());
            }
        }
        if (gato != null) {
            gato.renderPersonaje(gc, 0, 0);
            if (ver_hitbox) {
                grphcs.setColor(Color.green);
                grphcs.drawRect(gato.getHitbox().getRectangulo().getX(), gato.getHitbox().getRectangulo().getY(), gato.getHitbox().getRectangulo().getWidth(), gato.getHitbox().getRectangulo().getHeight());
            }
        }

        if (ver_hitbox) {
            //Elementos tipo huerto
            amarillo = true;
            for (Hitbox hitbox : huerto.getHitboxs()) {
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
