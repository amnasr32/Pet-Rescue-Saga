import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.undo.UndoManager;

/**
 * c'est la classe qui designe le model de la vue
 */
public class ModelAccueil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2243622694854827547L;
	
	UndoManager undoManager = new UndoManager();
    private  BufferedImage image;
    
    public void setImage(BufferedImage img) {
    	this.image  = img;
    }

    public ModelAccueil(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getImage() {
        return image;
    }


}
