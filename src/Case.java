import java.awt.Color;

import javax.swing.JPanel;
/**
 * La classe abstraite qui désigne l'aspect génèral de la case
 */
public abstract class Case extends JPanel{
	
	protected String figure;
	protected Color couleurDeLaCase;
	
	public Case(String figure, Color couleur) {
		this.figure = figure; 
		this.couleurDeLaCase = couleur;
	}
	
	public String getFigure() {
		return this.figure;
	}
	
	public Color getCouleurDeLaCase() {
		return this.couleurDeLaCase;
	}
	
	public void setCase(int x,int y) {
		this.setBounds(x, y, 50, 50);
	}
	
	public void setFigure(String fig) {
		this.figure = fig;
	}
	public void setPlateauGraph(PlateauGraph p) {
		
	}
	public void setCouleurDeLaCase(Color coul) {
		
	}
	
	public boolean estMmFigure(Case c) {
		if(figure == null) return c == null;
		return (c!=null)?this.figure.equals(c.figure):false;
	}
	
	public boolean estDeMmCol(Case c) {
		
		if(c != null) {
			return  this.couleurDeLaCase.getRGB() == c.couleurDeLaCase.getRGB();
		}else {
			return false;		
		}
	}
	
	public String ColToString() {
		
		if(couleurDeLaCase.getRed() == 255 && couleurDeLaCase.getBlue()==0 && couleurDeLaCase.getGreen()==0) {
			return " Rouge  ";
		}
		else if(couleurDeLaCase.getBlue()==255 && couleurDeLaCase.getRed()==0 && couleurDeLaCase.getGreen()==0) {
			return "  Bleu  ";
		}
		else if(couleurDeLaCase.getGreen()==255 && couleurDeLaCase.getRed()==0 && couleurDeLaCase.getBlue()==0) {
			return "  Vert  ";
		}
		else if(couleurDeLaCase.getRed()==255 && couleurDeLaCase.getGreen()==255 && couleurDeLaCase.getBlue()==0) {
			return " Jaune  ";
		}
		else return " Violet ";
	}

}
