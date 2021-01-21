import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * c'est la classe qui nous sert comme contrôleur c'est la ou on contrôle les aspects graphique et la logique de nos fenetre(l'ajout des actions listners, ...etc) 
 */
public class Controleur extends JFrame implements ActionListener,Serializable  { 

	/**
	 * les attributs
	 */
	private static final long serialVersionUID = -4730149690735159887L;
	private ModelAccueil modele; 
	private AccueilGraph vue;
	private Accueil2Graph vue2;
	private AccueilGraph3 vue3;
	private NiveauGraph niveauGraph;
	private PlateauGraph plateauGraph;
    private ModelAccueil model2 = new ModelAccueil("../images/jeu3.jpeg");
	private static DataBase dataBase;
	private Joueur joueur;
	private Reprendre vue_reprendre;
	private Plateau plateau;
	
public Controleur(ModelAccueil modele, AccueilGraph vue){
		 
	this.modele=modele;
    this.vue=vue;
    vue2= new Accueil2Graph(modele);
	vue2.setResizable(false);
	vue3= new AccueilGraph3(modele);
	vue3.setResizable(false);
	niveauGraph = new NiveauGraph();
	niveauGraph.setResizable(false);
	plateauGraph = new PlateauGraph(model2,this);
	plateauGraph.setResizable(false);
	vue_reprendre = new Reprendre(modele);
	vue_reprendre.setResizable(false);
	dataBase = new DataBase();

/**
 * controle de la première page d'affichage (l'accueil)	
 */
this.vue.startButton.addActionListener( ( event ) -> {
	
	this. modele = new ModelAccueil("../images/imageAccueil.jpeg");
	vue.setVisible(false);
	vue2.pack();
	vue2.setVisible(true);
	this.vue2.entrerButton.setEnabled(false);
	
			
    });

this.vue.replayButton.addActionListener((event)->{
	this.vue.setVisible(false);
	this.vue_reprendre.setVisible(true);
	this.vue_reprendre.pack();
	this.vue_reprendre.entrerButton.setEnabled(false);
    
});

this.vue.helpButton.addActionListener(  (event) -> {
	JOptionPane.showMessageDialog(null,"                                                             Bienvenue Dans \"Pet Rescue Saga\"\n" + 
			"\n" + 
			"Ce jeu a étè réalisé par HAMITOUCHE Boukhalfa et NASR Amira dans le cadre d'un projet universitaire encadré par l'université de Paris.\n" + 
			"\n" + 
			"Comment jouer ?\n" + 
			"\n" + 
			"  Principe du jeu:\n" + 
			"\n" + 
			"  tout ce qu'il faut faire pour franchir une étape dans Pet Rescue Saga,c'est d'éliminer le\n" + 
			"  maximum de briques en un minimum de mouevments. Pour ce faire, le joueur doit réaliser des associations de cubes\"\n" + 
			"  de même couleur afin qu'ils s'effondrent.Des points sont cumulés au fur et à mesure que l'on avance.\n" + 
			"  Les mouvements sont limités, alors planifiez-les soigneusement. Vos compétences en matière de puzzle seront\n" + 
			"  testées avec des heures de plaisir à casser des blocs!\n" + 
			"\n" + 
			"Affrontez cette saga déroutante seul ou regarder coomment un robot joue pour avoir une \n" + 
			"\n" + 
			"Pet Rescue Saga est un jeu totalement gratuit.\n" + 
			"\n" + 
			"En Cliquant sur ok , vous acceptez nos conditions d'utilisation.\n" + 
			"\n" + 
			"Caractéristiques de Pet Rescue Saga:\n" + 
			"• Des graphismes accrocheurs et un gameplay coloré\n" + 
			"• Animaux adorables des chiots.\n" + 
			"• Des Marteaux pour éclater des cases.\n" + 
			"• Des boosters spectaculaires et des récompenses bonus débloqués après de nombreux niveaux\n" + 
			"• Facile et amusant à jouer, difficile à maîtriser\n" + 
			"\n" + 
			"Déjà fan de Pet Rescue Saga? Vous avez quelques remarques, des idées à nous transmettre ? Merci de nous écrire sur\n" + 
			"nasramira8@gmail.com ou bien hamitoucheboukhalfa11@gmail.com.\n" + 
			"\n" + 
			"Enfin, un grand MERCI à tous ceux qui ont joué à notre version de Pet Rescue Saga! Amusez vous bien!","Aide",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("../images/logo.png")
			);
});

this.vue.quitButton.addActionListener( (event) -> {
			
	this.vue.dispose();
    System.exit(EXIT_ON_CLOSE);
});

/**
 * contrôle de la fenetre qui s'affiche apres avoir clique sur commencer la partie
 */
this.vue2.textField.addActionListener( (event) -> {
	
	if(this.vue2.textField.getText().equals("") ) {
	    JOptionPane.showMessageDialog(null,"Veuillez remplir les zones du texte");
	}
	else {
	    this.vue2.entrerButton.setEnabled(true);
       
	    this.vue2.entrerButton.addActionListener( (event1) -> {
	    	String identifiant = vue2.textField.getText();
			joueur = dataBase.getJoueurInList(identifiant);
			this. modele = new ModelAccueil("../images/imageAccueil.jpeg");
			if(joueur.getNiveau().getPermissionLevel()<1) this.vue3.b2.setEnabled(false);
			else this.vue3.b2.setEnabled(true);
			if(joueur.getNiveau().getPermissionLevel()<=1) this.vue3.b3.setEnabled(false);
			else this.vue3.b3.setEnabled(true);
			vue2.setVisible(false);	
			vue3.setVisible(true);
	    	vue3.pack();
	    		  				   
		});
	}
});
        
this.vue2.backButton.addActionListener( (event) -> {
	vue2.setVisible(false); 
	vue.setVisible(true );
	vue.pack();
	   	
});
	
this.vue2.quitButton.addActionListener( (event) -> {
	this.vue2.dispose();
    System.exit(EXIT_ON_CLOSE);	
});      

/**
 * controle de la fenetre des niveau (celle qui s'afficher apres avoir saisir son identifiant et cliquer sur entrer
 */
/**
 * gerer le bouton niveau 1
 */
this.vue3.b1.addActionListener( (event ) -> {
	
	plateauGraph.setCases( niveauGraph.chargerNiveau1());
	Case[][] c = plateauGraph.getCases();
	
	plateauGraph.getPlateau().setNbAnimToSave(2);
	plateauGraph.getPlateau().setNbMouvementAutorise(6);
	joueur.getNiveau().setNumLevel(1);
	joueur.getNiveau().setNbMouvementAutorise(6);
	plateauGraph.setScore(0);
	plateauGraph.getPlateau().setNbMouvement(0);
	plateauGraph.getPlateau().setNbAnimSaved(0);
	plateauGraph.setJoueur(joueur);
	plateauGraph.getPlateau().setNbFusilsADisposition(joueur.getFusilADisposition());
	plateauGraph.getPlateau().setNbBoosterCasse(joueur.getnbBoosterCasse());

	
	for(int i=0;i<c.length;i++) {
		for(int j=0;j<c[i].length;j++) {
			if(c[i][j] !=null) c[i][j].setPlateauGraph(plateauGraph);
		}
	}	
	vue3.setVisible(false);		
	plateauGraph.setVisible(true);
	plateauGraph.affichePlateauGraph();
	
});
		
/**
 * gerer le bouton niveau 2	
 */
this.vue3.b2.addActionListener((event) -> {
			
	plateauGraph.setCases(niveauGraph.chargerNiveau2());
	Case[][]c = plateauGraph.getCases();
	plateauGraph.getPlateau().setNbAnimToSave(5);
	plateauGraph.getPlateau().setNbMouvementAutorise(7);
	joueur.getNiveau().setNumLevel(2);
	joueur.getNiveau().setNbMouvementAutorise(7);
	plateauGraph.setJoueur(joueur);
	plateauGraph.setScore(0);
	plateauGraph.getPlateau().setNbMouvement(0);
	plateauGraph.getPlateau().setNbAnimSaved(0);
	plateauGraph.getPlateau().setNbFusilsADisposition(joueur.getFusilADisposition());
	plateauGraph.getPlateau().setNbBoosterCasse(joueur.getnbBoosterCasse());
	
	for(int i=0;i<c.length;i++) {
		for(int j=0;j<c[i].length;j++) {
			if(c[i][j]!=null) c[i][j].setPlateauGraph(plateauGraph);
		}
	}
	vue3.setVisible(false);	
	plateauGraph.setVisible(true);
	plateauGraph.affichePlateauGraph();
	
		
		
});
/**
 * gerer le bouton niveau 3		
 */
this.vue3.b3.addActionListener((event) ->{
			
	plateauGraph.setCases(niveauGraph.chargerNiveau3());
	Case[][] c = plateauGraph.getCases();	
	plateauGraph.getPlateau().setNbAnimToSave(4);
	plateauGraph.getPlateau().setNbMouvementAutorise(10);
	joueur.getNiveau().setNumLevel(3);
	joueur.getNiveau().setNbMouvementAutorise(10);
	plateauGraph.setJoueur(joueur);
	plateauGraph.setScore(0);
	plateauGraph.getPlateau().setNbMouvement(0);
	plateauGraph.getPlateau().setNbAnimSaved(0);
	plateauGraph.getPlateau().setNbFusilsADisposition(joueur.getFusilADisposition());
	plateauGraph.getPlateau().setNbBoosterCasse(joueur.getnbBoosterCasse());

	for(int i=0;i<c.length;i++) {
		for(int j=0;j<c[i].length;j++) {
			if(c[i][j]!=null) c[i][j].setPlateauGraph(plateauGraph);
		}
	}
	vue3.setVisible(false);	
	plateauGraph.setVisible(true);
	plateauGraph.affichePlateauGraph();
	
			
});
/**
 * gerer le bouton de retour en arrière
 */
this.vue3.backButton.addActionListener( (event) -> {
	vue3.setVisible(false);	
	this.vue2.setVisible(true );
	vue2.pack();
	
});

/**
 * controle de la fenetre d'affichage du jeu (la ou le jeu se deroule)
 */
this.plateauGraph.backButton.addActionListener((event) -> {
	plateauGraph.setJoueur(joueur);
	plateauGraph.setScore(joueur.getScore());
	joueur.chargerCases();
	plateauGraph.setCases(joueur.getCases());
	plateauGraph.getPlateau().setNbMouvement(joueur.getNbMouvement());
	plateauGraph.getPlateau().setNbAnimSaved(joueur.getNbAnimSaved());
	plateauGraph.getPlateau().setNbBoosterCasse(joueur.getnbBoosterCasse());
	plateauGraph.getPlateau().setNbFusilsADisposition(joueur.getFusilADisposition());
	this.plateauGraph.setVisible(false);
	this.vue.setVisible(true);
	this.vue.pack();
	
});

this.plateauGraph.saveButton.addActionListener((event) -> {
	try {
		Case[][] c = plateauGraph.getCases();
		Case[][] res = new Case[c.length][c[0].length];
		
		for(int i=0;i<c.length;i++) {
			for(int j=0;j<c[0].length;j++) {
				if(c[i][j] instanceof CaseNormal) res[i][j] = new CaseNormal(c[i][j].getFigure(), c[i][j].getCouleurDeLaCase(), plateauGraph.getPlateau());
				else if (c[i][j] instanceof CaseAnimal) res[i][j] = new CaseAnimal(c[i][j].getFigure(), c[i][j].getCouleurDeLaCase());
				else if(c[i][j] instanceof CaseObstacle) res[i][j] = new CaseObstacle(c[i][j].getFigure(), c[i][j].getCouleurDeLaCase());
				else res[i][j] = null;
			}
		}
		
		
		
		FileOutputStream fos = new FileOutputStream("../sauvegarde/sauv");
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(res);
		os.close();
		joueur.setNbAnimSaved(plateauGraph.getPlateau().getNbAnimSaved());
		joueur.setNbMouvement(plateauGraph.getPlateau().getNbMouvement());
		joueur.setScore(plateauGraph.getScore());
		joueur.setBoosterCasse(plateauGraph.getPlateau().getNbBoosterCasse());
		joueur.setFusilADisposition(plateauGraph.getPlateau().getNbFusilsADisposition());
		joueur.getNiveau().setNbAnimToSave(plateauGraph.getPlateau().getNbAnimToSave());
		joueur.getNiveau().setNbMouvementAutorise(plateauGraph.getPlateau().getNbMouvementAutorise());
		
		
		joueur.setCases(res);
		dataBase.ajouter(joueur);
		joueur.chargerCases();
		dataBase.ajouter(joueur);
		JOptionPane.showMessageDialog(null, "Sauvegarde effectuee");
	} catch (Exception e) {
		e.printStackTrace();
	}
});



/**
 * controle de la page de recuperation de la partie sauvegardee (reprendre une partie)
 */
this.vue_reprendre.textField.addActionListener( (event) -> {
	
	if (this.vue_reprendre.textField.getText().equals("") ) {
	        JOptionPane.showMessageDialog(null,"Veuillez remplir les zones du texte");
	}
	else {       
        this.vue_reprendre.entrerButton.setEnabled(true);
        
        this.vue_reprendre.entrerButton.addActionListener( (event1) -> {        
	        this. modele = new ModelAccueil("../images/imageAccueil.jpeg");
		   
	        if (!dataBase.contains(vue_reprendre.textField.getText())) {        	
		        JOptionPane.showMessageDialog(null,"Pas de partie sauvegardee pour ce joueur");
		    } else {         
		        joueur = dataBase.getJoueurInList(vue_reprendre.textField.getText());
		        
		        if(joueur.getCases()== null) {
		        	JOptionPane.showMessageDialog(null,"Pas de partie sauvegardee pour ce joueur");
		        }else {
			        plateauGraph.setCases(joueur.getCases());
			        Case[][] c = plateauGraph.getCases();
			        
			    	for(int i=0;i<c.length;i++) {
			    		for(int j=0;j<c[i].length;j++) {
			    			if(c[i][j]!=null) c[i][j].setPlateauGraph(plateauGraph);
			    		}
			    	}
			    	
			        plateauGraph.getPlateau().setNbAnimToSave(joueur.getNiveau().getNbAnimToSave());
			        plateauGraph.getPlateau().setNbMouvementAutorise(joueur.getNiveau().getNbMouvementAutorise());
			        plateauGraph.setScore(joueur.getScore());
			        plateauGraph.getPlateau().setNbAnimSaved(joueur.getNbAnimSaved());
			        plateauGraph.getPlateau().setNbBoosterCasse(joueur.getnbBoosterCasse());
			        plateauGraph.getPlateau().setNbFusilsADisposition(joueur.getFusilADisposition());
			        plateauGraph.getPlateau().setNbMouvement(joueur.getNbMouvement());
			        vue3.setVisible(false);
			        vue_reprendre.setVisible(false);
				    plateauGraph.affichePlateauGraph(); 
		        }
			}      	
		});
    }
});


this.vue_reprendre.backButton.addActionListener( (event) -> {
		
	vue_reprendre.setVisible(false);
	this.vue.setVisible(true );
	vue.pack();
	
						
});
	

}

 
/**
 * controle de la fenetre de victoire
 */
public void gagner() {
		
	Victoire victoire = new Victoire(model2,plateauGraph.getPlateau().getNbAnimSaved(),plateauGraph.score);
	this.plateauGraph.setVisible(false);
	victoire.setVisible(true);
	victoire.setResizable(false);
	victoire.pack();
	
	victoire.replayButton.addActionListener( (event) ->{
		    joueur.setBoosterCasse(joueur.getnbBoosterCasse()+1);
		    joueur.setFusilADisposition(joueur.getFusilADisposition()+1);
		    
		    plateauGraph.setCases(niveauGraph.chargerLeBonNiveau(joueur.getNiveau().getNumLevel()));
		    plateauGraph.setScore(0);
		    plateauGraph.getPlateau().setNbAnimSaved(0);
		    plateauGraph.getPlateau().setNbMouvement(0);
		    plateauGraph.getPlateau().setNbMouvementAutorise(joueur.getNiveau().getNbMouvementAutorise());
		    plateauGraph.getPlateau().setNbFusilsADisposition(joueur.getFusilADisposition());
		    plateauGraph.getPlateau().setNbBoosterCasse(joueur.getnbBoosterCasse());
		    
		    Case[][] c = plateauGraph.getCases();
			for(int i=0;i<c.length;i++) {
				for(int j=0;j<c[i].length;j++) {
					if(c[i][j] !=null) c[i][j].setPlateauGraph(plateauGraph);
				}
			}	
		    
		    victoire.setVisible(false);
		    plateauGraph.affichePlateauGraph();
			plateauGraph.pack();
	});
	
	victoire.backButton.addActionListener((event)-> {
		
	    if(joueur.getNiveau().getPermissionLevel()<=1) joueur.getNiveau().setPermissionLevel(joueur.getNiveau().getNumLevel());
		joueur.setBoosterCasse(joueur.getnbBoosterCasse()+1);
		joueur.setFusilADisposition(joueur.getFusilADisposition()+1);
	    dataBase.ajouter(joueur);
	    joueur.chargerCases();
	    plateauGraph.setCases(joueur.getCases());
		plateauGraph.setJoueur(joueur);
		plateauGraph.setScore(joueur.getScore());
		plateauGraph.getPlateau().setNbMouvement(joueur.getNbMouvement());
		plateauGraph.getPlateau().setNbAnimSaved(joueur.getNbAnimSaved());
		plateauGraph.getPlateau().setNbBoosterCasse(joueur.getnbBoosterCasse());
		plateauGraph.getPlateau().setNbFusilsADisposition(joueur.getFusilADisposition());
		victoire.setVisible(false);	
		vue.setVisible(true);
		vue.pack();
	});
	
	victoire.quitButton.addActionListener((event)-> {
		victoire.dispose();
		System.exit(EXIT_ON_CLOSE);
	});
	
	return;
}
/**
 * controle de la fenetre de defaite	
 */
	public void echouer() {
		Defaite def = new Defaite(model2);
		this.plateauGraph.setVisible(false);
		def.setVisible(true);
		def.setResizable(false);
		def.pack();
		
		def.replayButton.addActionListener( (event) ->{
			
		    plateauGraph.setCases(niveauGraph.chargerLeBonNiveau(joueur.getNiveau().getNumLevel()));
		    plateauGraph.setScore(0);
		    plateauGraph.getPlateau().setNbAnimSaved(0);
		    plateauGraph.getPlateau().setNbMouvement(0);
		    plateauGraph.getPlateau().setNbMouvementAutorise(joueur.getNiveau().getNbMouvementAutorise());
		    plateauGraph.getPlateau().setNbFusilsADisposition(joueur.getFusilADisposition());
		    plateauGraph.getPlateau().setNbBoosterCasse(joueur.getnbBoosterCasse());
		    
		    Case[][] c = plateauGraph.getCases();
			for(int i=0;i<c.length;i++) {
				for(int j=0;j<c[i].length;j++) {
					if(c[i][j] !=null) c[i][j].setPlateauGraph(plateauGraph);
				}
			}	
			
			def.setVisible(false);
		    plateauGraph.affichePlateauGraph();
			plateauGraph.pack();
		});
		def.retourButton.addActionListener((event)-> {
			
			joueur.chargerCases();
			plateauGraph.setCases(joueur.getCases());
			plateauGraph.setScore(joueur.getScore());
			plateauGraph.getPlateau().setNbMouvement(joueur.getNbMouvement());
			plateauGraph.getPlateau().setNbAnimSaved(joueur.getNbAnimSaved());
			plateauGraph.getPlateau().setNbBoosterCasse(joueur.getnbBoosterCasse());
			plateauGraph.getPlateau().setNbFusilsADisposition(joueur.getFusilADisposition());
			
			def.setVisible(false);	
			vue.setVisible(true);
			vue.pack();
			
		    });
		def.quitButton.addActionListener((event)-> {
			def.dispose();
			System.exit(EXIT_ON_CLOSE);
		});
		
		return ;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}


