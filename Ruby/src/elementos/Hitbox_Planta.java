package elementos;

import objetos.plantas.Planta;

public class Hitbox_Planta {
    private Planta planta;
    private Hitbox hitbox;

    public Hitbox_Planta(Hitbox hitbox, Planta planta) {
        this.planta = planta;
        this.hitbox = hitbox;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }
}
