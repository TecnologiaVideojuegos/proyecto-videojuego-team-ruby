package elementos;

import java.util.ArrayList;

public class Huerto {

    private ArrayList<ArrayList<Hitbox_Planta>> huerto = new ArrayList<>();
    private int x, y;

    public Huerto(int x, int y, ArrayList<Hitbox> hitbox_huerto) {
        ArrayList<Hitbox_Planta> hitboxs_planta;
        int i, j;
        this.x = x;
        this.y = y;
        if (x * y == hitbox_huerto.size()) {
            for (i = 0; i < x; i++) {
                hitboxs_planta = new ArrayList<Hitbox_Planta>();
                for (j = 0; j < y; j++) {
                    hitboxs_planta.set(j, new Hitbox_Planta(hitbox_huerto.get(j * i + i), null));
                }
                huerto.add(hitboxs_planta);
            }
        } else {
            System.out.println("Error al crear un huerto");
            System.exit(1);
        }
    }
    
    /**
     * Obtienes la Hitbox_Planta de la matriz del huerto (la primera posicón de la matriz en la (0,0))
     * @param x
     * @param y
     * @return 
     */
    public Hitbox_Planta getHitbox_Planta(int x, int y){
        Hitbox_Planta planta = null;
        if(x==huerto.size() && y==huerto.get(0).size()){
            planta=huerto.get(x).get(y);
        }
        return planta;
    }
    
    /**
     * Fijas la Hitbox_Planta de la matriz del huerto (la primera posicón de la matriz en la (0,0))
     * @param x
     * @param y
     * @param hitbox_planta
     * @return Devuelve un booleano que indica si se ha podido fijar con exito la Hitbox_Planta
     */
    public boolean setHitbox_Planta(int x, int y, Hitbox_Planta hitbox_planta){
        if(x==huerto.size() && y==huerto.get(0).size()){
            ArrayList<Hitbox_Planta> array;
            array = huerto.get(x);
            array.set(y, hitbox_planta);
            huerto.set(x, array);
            return true;
        }else{
            return false;
        }
    }
}
