package elementos;

import java.util.ArrayList;
import objetos.plantas.Planta;

public class Huerto {

    private ArrayList<ArrayList<Hitbox_Planta>> huerto;
    private int x, y;

    public Huerto() {
        huerto = new ArrayList<ArrayList<Hitbox_Planta>>();
        x = 0;
        y = 0;
    }

    public Huerto(int x, int y, ArrayList<Hitbox> hitbox_huerto, Huerto huerto_ant) {
        System.out.println("(" + x + ", " + y + ")");
        huerto = new ArrayList<ArrayList<Hitbox_Planta>>();
        ArrayList<Hitbox_Planta> hitboxs_planta;
        int i, j;
        this.x = x;
        this.y = y;
        if (x * y == hitbox_huerto.size()) {
            for (i = 0; i < x; i++) {
                hitboxs_planta = new ArrayList<Hitbox_Planta>();
                for (j = 0; j < y; j++) {
                    hitboxs_planta.add(new Hitbox_Planta(hitbox_huerto.get(j * x + i), null));
                }
                huerto.add(hitboxs_planta);
            }
        } else {
            System.out.println("Error al crear un huerto");
            System.exit(1);
        }
        if (!huerto_ant.getHuerto().isEmpty()) {
            if (huerto_ant.getHuerto().size() == x && huerto_ant.getHuerto().get(0).size() == y) {
                for (i = 0; i < x; i++) {
                    for (j = 0; j < y; j++) {
                        if (huerto_ant.hayPlanta(i, j)) {
                            System.out.println("Hay planta en la posición (" + i + ", " + j + ")");
                            anadirPlanta(i, j, huerto_ant.getHitbox_Planta(i, j).getPlanta());
                        }
                    }
                }
            } else {
                System.out.println("Me parece que algo falla");
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Obtienes la Hitbox_Planta de la matriz del huerto (la primera posicón de
     * la matriz en la (0,0))
     *
     * @param x
     * @param y
     * @return
     */
    public Hitbox_Planta getHitbox_Planta(int x, int y) {
        Hitbox_Planta planta = null;
        if (x <= huerto.size() && y <= huerto.get(0).size()) {
            planta = huerto.get(x).get(y);
        }
        return planta;
    }

    /**
     * Fijas la Hitbox_Planta de la matriz del huerto (la primera posicón de la
     * matriz en la (0,0))
     *
     * @param x
     * @param y
     * @param hitbox_planta
     * @return Devuelve un booleano que indica si se ha podido fijar con exito
     * la Hitbox_Planta
     */
    public boolean setHitbox_Planta(int x, int y, Hitbox_Planta hitbox_planta) {
        if (x == huerto.size() && y == huerto.get(0).size()) {
            ArrayList<Hitbox_Planta> array;
            array = huerto.get(x);
            array.set(y, hitbox_planta);
            huerto.set(x, array);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Hitbox> getHitboxs() {
        ArrayList<Hitbox> hitboxs = new ArrayList<>();
        for (int i = 0; i < huerto.size(); i++) {
            for (int j = 0; j < huerto.get(0).size(); j++) {
                hitboxs.add(huerto.get(i).get(j).getHitbox());
            }
        }
        return hitboxs;
    }

    public void renderPlantas() {
        for (int i = 0; i < huerto.size(); i++) {
            for (int j = 0; j < huerto.get(0).size(); j++) {
                if (huerto.get(i).get(j).getPlanta() != null) {
                    huerto.get(i).get(j).getPlanta().render();
                }
            }
        }
    }

    public void updatePos(float x, float y) {
        for (int i = 0; i < huerto.size(); i++) {
            for (int j = 0; j < huerto.get(0).size(); j++) {
                huerto.get(i).get(j).getHitbox().updatePos(x, y);
                if (huerto.get(i).get(j).getPlanta() != null) {
                    huerto.get(i).get(j).getPlanta().setCoordenadas(huerto.get(i).get(j).getHitbox().getRectangulo().getMinX(), huerto.get(i).get(j).getHitbox().getRectangulo().getMinY());
                }
            }
        }
    }

    public void anadirPlanta(int x, int y, Planta planta) {
        Planta planta_temp = planta;
        planta_temp.setCoordenadas(huerto.get(x).get(y).getHitbox().getRectangulo().getMinX(), huerto.get(x).get(y).getHitbox().getRectangulo().getMinY());
        huerto.get(x).get(y).setPlanta(planta_temp);
    }

    public Planta eliminarPlanta(int x, int y) {
        Planta planta = null;
        if (huerto.get(x).get(y).getPlanta().isCrecida()) {
            planta = huerto.get(x).get(y).getPlanta();
            huerto.get(x).get(y).setPlanta(null);
        }
        return planta;
    }

    public ArrayList<ArrayList<Hitbox_Planta>> getHuerto() {
        return huerto;
    }

    public void setHuerto(ArrayList<ArrayList<Hitbox_Planta>> huerto) {
        this.huerto = huerto;
    }

    public int getAltura() {
        return y;
    }

    public int getAnchura() {
        return x;
    }

    public int getX(Hitbox hitbox) {
        int x_temp = -1;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (huerto.get(i).get(j).getHitbox().equals(hitbox)) {
                    x_temp = i;
                }
            }
        }
        return x_temp;
    }

    public int getY(Hitbox hitbox) {
        int y_temp = -1;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (huerto.get(i).get(j).getHitbox().equals(hitbox)) {
                    y_temp = j;
                }
            }
        }
        return y_temp;
    }

    public boolean hayPlanta(int x, int y) {
        boolean hay = true;
        if ((this.x - 1) < x || (this.y - 1) < y) {
            return false;
        }
        if (huerto.get(x).get(y).getPlanta() == null) {
            hay = false;
        }
        return hay;
    }
}
