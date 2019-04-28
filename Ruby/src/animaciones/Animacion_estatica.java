package animaciones;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Animacion_estatica{

    private Image img;

    //Constructor por ruta/direccion de imagen
    public Animacion_estatica(String dir) throws SlickException {
        super();
        this.img = new Image(dir);
    }    

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    
    public void renderAnimacion(float pos_x, float pos_y) {  
        img.draw(pos_x, pos_y - img.getHeight()/2);
    }

}
