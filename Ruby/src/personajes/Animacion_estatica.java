package personajes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Animacion_estatica extends Animacion{

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
    
    @Override
    public void renderAnimacion(GameContainer gc, float pos_x, float pos_y) {  
        img.draw(pos_x, pos_y - img.getHeight()/2);
    }

}
