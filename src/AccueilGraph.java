import javax.swing.*;
import java.awt.*;

/**
 * c'est la classe qu'on a utilise pour afficher la fenetre d'accueil (la toute première fenetre lors du lancement du jeu)
 */
public class AccueilGraph extends JFrame{
	
		/**
	 * 
	 */
	    private static final long serialVersionUID = -7709079423902157833L;
	
		private JMenuBar menuBar = new JMenuBar();
		public JButton startButton = new JButton("Commencer la partie");
	 	public  JButton replayButton = new JButton("Reprendre une partie");
	 	public  JButton helpButton = new JButton("Aide");
	    public  JButton quitButton = new JButton("Quitter");
	    public Controleur controleur;
	    private ImagePanel imagePane;
		private ModelAccueil model;
	    private  Panel pannel= new Panel();
	    PlateauGraph plat;

		public AccueilGraph(ModelAccueil m,Controleur controleur ) {
		    
			this.model = m;
		    this.controleur=controleur;
		    imagePane= new ImagePanel();	
		    setTitle("Pet Rescue Sage");
		    imagePane.setLayout(null);
		    setJMenuBar(menuBar);
		    setContentPane(pannel);
		    pannel.add(imagePane);
		    Font f=new Font("Chandas", Font.BOLD, 20); 
		    imagePane.add(startButton);
		    
		    startButton.setBounds(520, 400,300, 40);
		    startButton.setForeground(new Color(139,0,0));
		    startButton.setContentAreaFilled(false);
			startButton.setFont(f);   
			  
				  
		    imagePane.add(replayButton);
		    replayButton.setBounds(520,450,300, 40);
		    replayButton.setForeground(new Color(139,0,0));
		    replayButton.setContentAreaFilled(false);
		    replayButton.setFont(f); 
		    
			imagePane.add(helpButton);
			helpButton.setBounds(520, 500,300, 40);
			helpButton.setForeground(new Color(139,0,0));
			helpButton.setContentAreaFilled(false);
			helpButton.setFont(f); 	
			
		    imagePane.add(quitButton);
		    quitButton.setBounds(520, 550,300, 40);
		    quitButton.setForeground(new Color(139,0,0));
		    quitButton.setContentAreaFilled(false);
		    quitButton.setFont(f);	     
				    
		    pannel.add(imagePane);
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

		public void setPlateauGraph(PlateauGraph plateauGraph) {
			this.plat = plateauGraph;
		}
		public PlateauGraph getPlateauGraph() {
			return this.plat;
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

