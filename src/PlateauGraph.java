
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;


import java.io.*;
import java.net.MalformedURLException;

/**
 * cette classe nous sert a gerer notre plateau graphique et actualiser son affichage
 */
public class PlateauGraph extends JFrame implements Serializable{
	
		/**
	 * 
	 */
	    private static final long serialVersionUID = 2936204441335331562L;
	
		public JButton backButton = new JButton("Retour");
		private JLabel labelid= new JLabel();
		private JLabel label= new JLabel();
		private JLabel labelid1= new JLabel();
		private JLabel label1= new JLabel();
		private JLabel labelid2= new JLabel();
		private JLabel label2= new JLabel();
		
		private JButton fusil = new JButton();
		private JLabel fusilLab = new JLabel();
		
		private JButton marteau = new JButton();
		private JLabel marteauLabel = new JLabel();
		
		public JButton saveButton = new JButton("Enregistrer une partie");
		private Case[][] cases;
		JPanel conteneur = new JPanel();
		private JMenuBar menuBar = new JMenuBar();
		private Plateau plateau;
		private ModelAccueil model;
		private ImagePanel imagepan;
		private ModelAccueil model2= new ModelAccueil("../images/fondd.png");
		
		public int score;
	    private Joueur joueur;
		private static DataBase dataBase = new DataBase();
		private Controleur controleur;
		
		
	
	public PlateauGraph(ModelAccueil model,Controleur controleur) {
		
		this.model=model;
		this.controleur=controleur;
		setSize(1280, 720);
		setTitle("Pet Rescue Saga");
		imagepan= new ImagePanel();
		imagepan.setLayout(null);
		setJMenuBar(menuBar);

		//son();
		
		joueur = new Joueur("");
		plateau = new Plateau(joueur.getNiveau(), joueur);
		setContentPane(conteneur);
		
		score = 0;
		
		conteneur.add(imagepan);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public Case[][] getCases(){
		return plateau.getCases();
	}
	public Plateau getPlateau() {
		return plateau;
	}
	public int getScore() {
		return this.score;
	}
	public void setCases(Case[][] c) {
		plateau.setCases(c);
	}
	
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
		this.plateau.setJoueur(joueur);
	}
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * afficher et reafficher notre plateau
	 */
	public void affichePlateauGraph() {
	    
		
		 //son();
		
		  if(gagne()) {
			 controleur.gagner();
	    	
	    	
	    	return;
	    }else if(perdre()){
	    	controleur.echouer();
	    	return;
	    }
	    
		imagepan.removeAll();	
		
		fixButtonsAndLabels();
				
		for(int i=0;i<plateau.getCases().length;i++) {
			for(int j=0;j<plateau.getCases()[i].length;j++) {
				if(plateau.getCases()[i][j] instanceof CaseNormal) {
					imagepan.setLayout(null);
					imagepan.add(plateau.getCases()[i][j]);
					plateau.getCases()[i][j].setCase(j*52+400, i*52+250);
				}
				else if(plateau.getCases()[i][j] instanceof CaseAnimal) {
					try{
						imagepan.setLayout(null);
						
						JButton animal = new JButton();
						animal.setIcon(new ImageIcon("../images/dog.png"));
						imagepan.add(animal);
						animal.setBounds(j*52+400, i*52+250, 50,50);
						animal.setContentAreaFilled(false);

					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				else if (plateau.getCases()[i][j] == null) {
					imagepan.setLayout(null);
					JPanel gray = new JPanel();
					gray.setBackground(Color.GRAY);
					imagepan.add(gray);
					gray.setBounds(j*52+400, i*52+250, 50, 50);
				}
				else {
					imagepan.setLayout(null);
					
					JButton obstacle = new JButton();
					obstacle.setIcon(new ImageIcon("../images/obstacle.jpg"));
					imagepan.add(obstacle);
					obstacle.setBounds(j*52+400,i*52+250,50,50);
				}
				
			}
		}
		setVisible(true);
	}

	/**
	 * initialiser et bien placer nos boutons et nos Jlabels (pour eviter trop de code dans la fonction d'affichage)
	 */
	public void fixButtonsAndLabels() {
		
		imagepan.add(backButton);
		backButton.setBounds(980,20,250, 50);
		backButton.setForeground(new Color(139,0,0));
		backButton.setContentAreaFilled(false);
		backButton.setFont(new Font("Chandas", Font.BOLD, 15)); 	    
		
		imagepan.add(saveButton);
		saveButton.setBounds(980,80,250, 50);
		saveButton.setForeground(new Color(139,0,0));
		saveButton.setContentAreaFilled(false);
		saveButton.setFont(new Font("Chandas", Font.BOLD, 15));
		
		imagepan.add(fusil);
		fusil.setIcon(new ImageIcon("../images/rocket.png"));
		fusil.setBounds(850, 275, 60, 60);
		fusil.setContentAreaFilled(false);
		
		imagepan.add(fusilLab);
		fusilLab.setForeground(new Color(139,0,0));
		fusilLab.setText(""+plateau.getNbFusilsADisposition());
		fusilLab.setBounds(920, 285, 400, 40);
		fusilLab.setFont(new Font("Chandas", Font.BOLD, 25));
		
		imagepan.add(marteau);
		marteau.setIcon(new ImageIcon("../images/marteau.png"));
		marteau.setBounds(850,340,60,60);
		marteau.setContentAreaFilled(false);
		
		imagepan.add(marteauLabel);
		marteauLabel.setForeground(new Color(139,0,0));
		marteauLabel.setText(""+plateau.getNbBoosterCasse());
		marteauLabel.setBounds(920, 350, 400, 40);
		marteauLabel.setFont(new Font("Chandas", Font.BOLD, 25));

		imagepan.add(labelid);
	    labelid.setForeground( new Color(139,0,0));	
		labelid.setText("Votre Score est:");
		labelid.setBounds(190,50,200, 40);
		labelid.setFont(new Font ("Chandas", Font.BOLD, 15));
		imagepan.add(label);
	    label.setForeground( new Color(139,0,0));
		if(plateau !=null) label.setText(""+score);	
		label.setBounds(250, 80,200, 40);
		label.setFont(new Font ("Chandas", Font.BOLD, 15));
		//***********//
		imagepan.add(labelid1);
	    labelid1.setForeground( new Color(139,0,0));	
		labelid1.setText("Animaux sauves:");
		labelid1.setBounds(180,120,200, 40);
		labelid1.setFont(new Font ("Chandas", Font.BOLD, 15));
		
		imagepan.add(label1);
		label1.setForeground( new Color(139,0,0));	
		label1.setText(""+plateau.getNbAnimSaved());
		label1.setBounds(250,150,200, 40);
		label1.setFont(new Font ("Chandas", Font.BOLD, 15));	
		
		imagepan.add(labelid2);
	    labelid2.setForeground( new Color(139,0,0));	
		labelid2.setText("Mouvements restants:");
		labelid2.setBounds(170,190,200, 40);
		labelid2.setFont(new Font ("Chandas", Font.BOLD, 15));
		
		imagepan.add(label2);
	    label2.setForeground( new Color(139,0,0));	
		label2.setText(""+(plateau.getNbMouvementAutorise()-plateau.getNbMouvement()));
		label2.setBounds(250,220,200, 40);
		label2.setFont(new Font ("Chandas", Font.BOLD, 15));
		
		
		for( ActionListener al : fusil.getActionListeners() ) { fusil.removeActionListener( al ); }
		
		fusil.addActionListener((event) -> {
			
			if(plateau.getNbFusilsADisposition()==0) {
				JOptionPane.showMessageDialog(fusil, "Pas assez de fusils", "Fusil Booster", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("../images/rocket.png"));
				return;
			}
			
			String input = JOptionPane.showInputDialog("indiquer le numero de la colonne que vous voulez eliminer");
			
			if(input!=null) input = input.trim();
			
			if("".equals(input) || input == null) JOptionPane.showMessageDialog(fusil, "aucun numero detecte","Format incorrect",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("../images/rocket.png"));
			else{
				plateau.eliminationFusil(Integer.valueOf(input));
			    plateau.reorganisation();
			    affichePlateauGraph();
			}
		});
	
		for( ActionListener al : marteau.getActionListeners() ) { marteau.removeActionListener( al ); }
		
		marteau.addActionListener((event)->{
			if(plateau.getNbBoosterCasse()==0) {
				JOptionPane.showMessageDialog(marteau, "Pas assez de marteaux","Marteau Booster",0,new ImageIcon("../images/marteau.png"));
				return;
			}
			String input = JOptionPane.showInputDialog("indiquer le numero de ligne et de la colonne de la case que vous voulez casser \n        sous forme lc");
			
			if(input != null) input = input.trim();
			
			if("".equals(input) || input == null ||input.length()!=2) JOptionPane.showMessageDialog(marteau, "Veuillez resaisir le champs","Format incorrect",0,new ImageIcon("../images/marteau.png"));
			else {
				int a = Integer.valueOf(String.valueOf(input.charAt(0)));
				int b = Integer.valueOf(String.valueOf(input.charAt(1)));
				plateau.eliminationMarteau(a, b);
				affichePlateauGraph();
			}
		});
		
		
		
		
		
		
		
	}
	
	public boolean gagne() {
		
		score += plateau.getExplosedBlocks()*plateau.getExplosedBlocks()*10;
		plateau.setExplosedBlock(0);
		
		if(plateau.getNbAnimSaved() == plateau.getNbAnimToSave()) {
			if(joueur.getNiveau().getPermissionLevel()<=1) joueur.getNiveau().setPermissionLevel(joueur.getNiveau().getNumLevel());
			plateau.setNbFusilsADisposition(plateau.getNbFusilsADisposition()+1);
			plateau.setNbBoosterCasse(plateau.getNbBoosterCasse()+1);
			dataBase.ajouter(joueur);
			return true;
		}
		return false;
	}
	
	public boolean perdre() {
		if(plateau.getNbMouvementAutorise()-plateau.getNbMouvement() == 0 || plateau.simplificationImpossible()) {
			return true;
		}
		return false;
	}
	
	/**
	 * ajouter du son pour le jeu
	 */
	public void son() {
		Clip sound;
		File file = new File("../Audio/son.wav");
		try {
			AudioInputStream in = AudioSystem.getAudioInputStream(file.toURI().toURL());
			try {
				sound = AudioSystem.getClip();
				sound.open(in);
				sound.loop(100);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	 public class ImagePanel extends JPanel{
		  

	     public ImagePanel() {
	           setPreferredSize(new Dimension(model.getImage().getWidth(), model.getImage().getHeight()));
	          
	       }

	    
	  	public void paintComponent(Graphics g) {
	  		super.paintComponent(g);
	  		g.drawImage(model.getImage(), 0, 0, this);
	  	}
	  }//ImagePanel

	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
	


