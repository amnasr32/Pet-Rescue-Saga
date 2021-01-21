import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NiveauGraph extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6986741121939692359L;
	
	private Niveau niveau;
	
	public NiveauGraph() {
		niveau = new Niveau();

	}
	
	public void creeNiveaux() {
		niveau.creeNiveau1();
		niveau.creeNiveau2();
		niveau.creeNiveau3();
	}
	
	public Case[][] chargerNiveau1() {
		creeNiveaux();
		niveau.setNiveauChoisi(1);
		return niveau.chargerNiveauChoisi();
	}
	public Case[][] chargerNiveau2() {
		creeNiveaux();
		niveau.setNiveauChoisi(2);
		return niveau.chargerNiveauChoisi();
	}
	public Case[][] chargerNiveau3() {
		creeNiveaux();
		niveau.setNiveauChoisi(3);
		return niveau.chargerNiveauChoisi();
	}
	
	public Case[][] chargerLeBonNiveau(int niv){
		creeNiveaux();
		niveau.setNiveauChoisi(niv);
		return niveau.chargerNiveauChoisi();
	}
	 
	
	
	
	
	

}
