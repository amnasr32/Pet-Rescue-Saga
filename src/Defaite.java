import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * c'est la classe qu'on a utilise pour afficher une fenetre de defaite si le joueur perd la partie
 */
public class Defaite extends JFrame {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 4815912951493718440L;
	
	 public  JButton replayButton = new JButton("Rejouer");
	 public  JButton retourButton = new JButton("Retour au menu principal");
	 public  JButton quitButton = new JButton("Quitter");
	 private JLabel labelid= new JLabel();
	 private ImagePanel imagePane;
	 private Panel pannel= new Panel();
     private ModelAccueil model;
     private JMenuBar menuBar = new JMenuBar();
     
	public Defaite(ModelAccueil m) {
			
		 this.model = m;
		 setTitle("Pet Rescue Sage");
		 imagePane= new ImagePanel();
		 imagePane.setLayout(null);
		 setJMenuBar(menuBar);
		 setContentPane(pannel);
		 pannel.add(imagePane);
		 
		 labelid.setText("Niveau non termine: trop peu d'animaux sauves");
		 imagePane.add(labelid);
		 labelid.setForeground( new Color(218,0,0));
		 labelid.setBounds(380, 300,1000, 50);
		 labelid.setFont(new Font("Chandas", Font.BOLD, 20));;
		 Font f=new Font("Chandas", Font.BOLD, 15); 
	     
		 imagePane.add(replayButton);
		 replayButton.setBounds(430,400,400, 40);
		 replayButton.setForeground(new Color(139,0,0));
		 replayButton.setContentAreaFilled(false);
		 replayButton.setFont(f);
		 
		 imagePane.add(retourButton);
		 retourButton.setBounds(430,450,400, 40);
		 retourButton.setForeground(new Color(139,0,0));
		 retourButton.setContentAreaFilled(false);
		 retourButton.setFont(f); 
		 
		 imagePane.add(quitButton);
		 quitButton.setBounds(430,500,400, 40);
		 quitButton.setForeground(new Color(139,0,0));
		 quitButton.setContentAreaFilled(false);
		 quitButton.setFont(f);
	
		 
	
		 pannel.add(imagePane);
		 pannel.add(imagePane);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 }

	public class ImagePanel extends JPanel{
			  
		     public ImagePanel() {
		           if(model.getImage()!=null)setPreferredSize(new Dimension(model.getImage().getWidth(), model.getImage().getHeight()));
		          
		     }
	
		    
		  	 public void paintComponent(Graphics g) {
		  		super.paintComponent(g);
		  		g.drawImage(model.getImage(), 0, 0, this);
		  	 }
    }//ImagePanel
}
