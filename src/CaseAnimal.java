import java.awt.Color;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * une classe fille de la classe case, en particulier elle represente les cases qui contiennent un animal
 */
public class CaseAnimal extends Case{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7155120395979285213L;
	JLabel lab;
	
	public CaseAnimal(String figure, Color couleur) {
		super(figure,couleur);
		this.setLayout(null);
		this.setBackground(couleur);	
		
		setVisible(true);
	}
	
}
