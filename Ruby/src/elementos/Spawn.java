package elementos;

public class Spawn {
    private Hitbox hitbox;
    private String nombre;

    public Spawn(Hitbox hitbox, String nombre) {
        this.hitbox = hitbox;
        this.nombre = nombre;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
