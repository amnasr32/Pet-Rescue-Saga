import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;



public class Victoire extends JFrame {


	 /**
	 * 
	 */
	private static final long serialVersionUID = 6867454563081779139L;
	
	public  JButton replayButton = new JButton("Rejouer");
	 public  JButton quitButton = new JButton("Quitter");
	 public  JButton backButton = new JButton("Retour au menu principal");
	 private JLabel labelid= new JLabel();
	 private JLabel label= new JLabel();
	 private JLabel labelid3= new JLabel();
	 private JLabel label3= new JLabel();
	 private JLabel scoreLabel = new JLabel();
	 
	 private ImagePanel imagePane;
	 private Panel pannel= new Panel();
     ModelAccueil model;
     private JMenuBar menuBar = new JMenuBar();
     
     
	public Victoire(ModelAccueil m, int nbAnimSaved, int score) {
			
		 this.model = m;
		 setTitle("Pet Rescue Sage");
		 imagePane= new ImagePanel();
		 imagePane.setLayout(null);
	     setJMenuBar(menuBar);
	     setContentPane(pannel);
	     pannel.add(imagePane);
	     
	     fixButtonsAndLabels(nbAnimSaved,score);
	
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
	}



	public void fixButtonsAndLabels(int nbAnimSaved, int score) {
		
	    imagePane.add(labelid);
	    labelid.setForeground( new Color(0,126,11));
	    labelid.setText("Niveau termine avec succes !");
	    labelid. setBounds(450, 320,400, 40);
	    labelid.setFont(new Font("Chandas", Font.BOLD, 20));
	    
	    imagePane.add(labelid3);
	    labelid3.setForeground( new Color(139,0,0));
	    labelid3.setText("animaux ont ete sauves");
	    labelid3. setBounds(490, 350,400, 40);
	    labelid3.setFont(new Font("Chandas", Font.BOLD, 20));
	    
	    imagePane.add(label3);
	    label3.setForeground( new Color(0,35,255));
	    label3.setText(""+nbAnimSaved);
	    label3. setBounds(450, 350,400, 40);
	    label3.setFont(new Font("Chandas", Font.BOLD, 20));
	    
	    imagePane.add(scoreLabel);
	    scoreLabel.setForeground(Color.BLACK);
	    scoreLabel.setText("Votre score est:  "+score);
	    scoreLabel.setBounds(450, 400, 400, 40);
	    scoreLabel.setFont(new Font("Chandas", Font.BOLD, 25));
	    
	    Font f=new Font("Chandas", Font.BOLD, 15); 
		 
		 imagePane.add(replayButton);
		 replayButton.setBounds(430,450,400, 40);
		 replayButton.setForeground(new Color(139,0,0));
		 replayButton.setContentAreaFilled(false);
		 replayButton.setFont(f); 
		 
		 imagePane.add(backButton);
		 backButton.setBounds(430,500,400, 40);
		 backButton.setForeground(new Color(139,0,0));
		 backButton.setContentAreaFilled(false);
		 backButton.setFont(f); 
		 
		 imagePane.add(quitButton);
		 quitButton.setBounds(430,550,400, 40);
		 quitButton.setForeground(new Color(139,0,0));
		 quitButton.setContentAreaFilled(false);
		 quitButton.setFont(f);				
			      
		 pannel.add(imagePane);
		 pannel.add(imagePane);
		 
		 quitButton.addActionListener((event)->{
			 this.dispose();
			 System.exit(EXIT_ON_CLOSE);
		 });
		
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
