import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;


import javax.swing.*;

/**
 * c'est la classe qu'on a utilise pour afficher la fenetre qui demande l'identifiant du joueur au commencement ou lors de la reprise d'une partie
 */
public class Accueil2Graph extends JFrame {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -4907933437098733094L;
	
	public   JButton entrerButton = new JButton("Entrer");
	 public  JButton quitButton = new JButton("Quitter");
	 public JButton backButton = new JButton("Retour");
	 public JTextField textField = new JTextField() ;
	 private JLabel labelid= new JLabel();
	 private ImagePanel imagePane;
	 private Panel pannel= new Panel();
     ModelAccueil model;
     private JMenuBar menuBar = new JMenuBar();
	 
     public  Accueil2Graph(ModelAccueil m) {
		
		 this.model = m;
		 setTitle("Pet Rescue Sage");
		 imagePane= new ImagePanel();
		 imagePane.setLayout(null);
	     setJMenuBar(menuBar);
	     setContentPane(pannel);
	     pannel.add(imagePane);
	     Font f=new Font("Chandas", Font.BOLD, 20); 
	      
		 imagePane.add(textField);
				      textField. setBounds(520, 420,300, 40);
				      textField.setFont(new Font ("Chandas", Font.BOLD, 15)); 
				      labelid.setForeground( new Color(139,0,0));
				      labelid.setText("Veuillez saisir votre identifiant:");
				      imagePane.add(labelid);
				      labelid. setBounds(520, 380,300, 40);
		      
		 imagePane.add(entrerButton);
				      entrerButton.setFont(f); 
				      entrerButton. setBounds(520, 470,300, 40);
				      entrerButton.setForeground(new Color(139,0,0));
				      entrerButton.setContentAreaFilled(false);
				      entrerButton.setFont(f); 
		 imagePane.add(backButton);
						 backButton.setBounds(520,520,300, 40);
						 backButton.setForeground(new Color(139,0,0));
						 backButton.setContentAreaFilled(false);
						 backButton.setFont(f); 		    
				      
		  imagePane.add(quitButton);
				       quitButton.setBounds(520, 570,300, 40);
				       quitButton.setForeground(new Color(139,0,0));
				       quitButton.setContentAreaFilled(false);
				       quitButton.setFont(f); 
				       
		  pannel.add(imagePane);
		  pannel.add(imagePane);
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
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
