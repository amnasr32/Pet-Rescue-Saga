import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

/**
 * une classe fille de la classe Case, en particulier elle represente les cases colorees
 */
public class CaseNormal extends Case implements MouseInputListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3114720832406191502L;
	
	Plateau plateau;
	PlateauGraph plateauGraph;
	
	public CaseNormal(String figure, Color couleur, Plateau plateau) {
		
		super(figure, couleur);
		
		this.setLayout(null);
		this.setBackground(couleur);
		
		addMouseListener(this);
		
		setVisible(true);
		
	}
	@Override
	public void setCouleurDeLaCase(Color coul) {
		// TODO Auto-generated method stub
		super.couleurDeLaCase = coul;
	}
	
	@Override
	public void setPlateauGraph(PlateauGraph p) {
		plateauGraph = p;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		int i = plateauGraph.getPlateau().recupereIetJ(this)[0];
		int j = plateauGraph.getPlateau().recupereIetJ(this)[1];
		plateauGraph.getPlateau().eliminationCasesMmFig(i,j,true);
		plateauGraph.getPlateau().reorganisation();
		plateauGraph.affichePlateauGraph();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
