import java.awt.*;

import javax.swing.*;

/**
 * c'est la classe qu'on a utilise pour afficher la fenetre des niveaux
 */
public class AccueilGraph3 extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6231670080885511882L;
	
	private JMenuBar menuBar = new JMenuBar();
	 	public JButton b1 = new JButton("Niveau1");
	    public  JButton b2 = new JButton("Niveau2");
	    public  JButton b3 = new JButton("Niveau3");
	    public JButton backButton = new JButton("Retour");
	
	    Controleur controleur;
	    private ImagePanel imagePane;
	    ModelAccueil model;
	    
	    
	    public AccueilGraph3(ModelAccueil model ) {
	
			    this.model = model;
			    setTitle("Pet Rescue Sage");
			    imagePane = new ImagePanel();
			    Panel pannel= new Panel();
			    imagePane.setLayout(null);
			    setTitle("Pet Rescue Saga ");
			    setJMenuBar(menuBar);
			    setContentPane(pannel);
			    pannel.add(imagePane);
			    
				imagePane.add(b1);
					    b1. setBounds(520, 420,300, 40);
					    b1.setContentAreaFilled(false);
					    b1.setFont(new Font ("Chandas", Font.BOLD, 15)); 
					    b1.setForeground( new Color(139,0,0));
					    
				imagePane.add(b2);
					    b2. setBounds(520, 470,300, 40);
					    b2.setContentAreaFilled(false);
					    b2.setFont(new Font ("Chandas", Font.BOLD, 15)); 
					    b2.setForeground( new Color(139,0,0));
					    
				imagePane.add(b3);
					    b3. setBounds(520, 520,300, 40);
					    b3.setContentAreaFilled(false);
					    b3.setFont(new Font ("Chandas", Font.BOLD, 15)); 
					    b3.setForeground( new Color(139,0,0));
					    
				imagePane.add(backButton);
						 backButton.setBounds(520,570,300, 40);
						 backButton.setForeground(new Color(139,0,0));
						 backButton.setContentAreaFilled(false);
						 backButton.setFont(new Font ("Chandas", Font.BOLD, 15)); 		    
    
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
