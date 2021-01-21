import java.awt.EventQueue;

/**
 * c'est la classe qui nous permet de lancer notre interface graphique
 */
public class Jeu {

		public  Controleur controleur;
		private  ModelAccueil model;
	    private AccueilGraph view;
	    private Plateau plateau;
		private Defaite defaite;
		private Victoire  victoire;
				
		public Jeu(){
			
				model = new ModelAccueil("../images/imageAccueil.jpeg");
			    AccueilGraph view = new AccueilGraph(model,controleur);
				controleur= new Controleur(model,view);
				view.setVisible(true);
				view.setResizable(false);
				view.pack();
				
		}

    /**
     * Lancement du jeu graphique
     * @param args
     */
		public static void main(String[] args) {
			
			new Jeu();
			
	
	    }	
	
	
	
	
	
}
