import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * c'est la classe depuis laquelle notre interface textuelle se lance
 */
public class JeuTerminal  {
	
	private  static DataBase dataBase = new DataBase();
	
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_MAGENTA = "\u001B[35m";
	public static final String ANSI_RESET = "\u001B[0m";
    	
	/**
	 * Lancement du jeu Terminal
	 * @param args
	 */
	public static void main(String[] args) {
		
	  Scanner scan = new Scanner(System.in);
	  int ch = 0;
	  boolean bool = true;
	  
	  do {
		System.out.println("");
		System.out.println("");
		System.out.println("                              Pet Rescue                              ");
		System.out.println("                                 saga                                 ");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("                1- Commencer la partie                                ");
		System.out.println("                                                                      ");
		System.out.println("                2- Reprendre une partie                               ");
		System.out.println("                                                                      ");
		System.out.println("                3- Jouer en tant que robot                            ");
		System.out.println("                                                                      ");
		System.out.println("                4- Quitter                                            ");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.print  ("  Veuillez choisir une action (1, 2, ou 3):");
		int choix = scan.nextInt();
		ch=choix;

		switch (choix) {
		case 1:
			System.out.println();
			System.out.println("Veuillez saisir votre identifiant: ");
			System.out.print("    identifiant: ");
			String	 identifiant = scan.next();
			
			Joueur joueur = dataBase.getJoueurInList(identifiant);
			
			int niv =0;
			
			do {
				System.out.println();
			    System.out.println(" Selectionner le niveau que vous voulez joue : \n");
			    System.out.println("               1- Niveau 1\n");
			
			    if(joueur.getNiveau().getPermissionLevel()>=1) System.out.println("               2- Niveau 2\n");
			    else System.out.println(ANSI_RED+"               2- Niveau2 (Locked)\n"+ANSI_RESET);
			    if(joueur.getNiveau().getPermissionLevel()>1) System.out.println("               3- Niveau 3\n niveau: ");
			    else System.out.println(ANSI_RED+"               3- Niveau3 (Locked)\n"+ANSI_RESET);
			
			    niv = scan.nextInt();
			    
			}while(niv <= 0 ||  niv-1 > joueur.getNiveau().getPermissionLevel()||niv>3);
			
			joueur.getNiveau().setNiveauChoisi(niv);
			joueur.setNbAnimSaved(0);
			joueur.setNbMouvement(0);
			Plateau p = new Plateau(joueur.getNiveau(),joueur);
			p.affichePlateau();
			p.jouer(scan);
			
			break;
			
		case 2:
		    System.out.println();
		    System.out.println("Veuillez saisir votre identifiant: ");
		    System.out.print("    identifiant: ");
		    String id = scan.next();
		
	     	if(!dataBase.contains(id)) {
	     		System.out.println();
	     		System.out.println(ANSI_RED+"Pas de partie sauvegardee pour ce joueur\n"+ANSI_RESET);
	     		bool = false;
	     	}
	     	else{
	     		Joueur j = dataBase.getJoueurInList(id);
	     	
	        	Plateau plat = new Plateau(j.getNiveau(), j);
	        	
	        	if(j.getCases() == null ) {
	        		System.out.println(ANSI_RED+"Pas de partie sauvegardee pour ce joueur\n"+ANSI_RESET);
	        	    bool= false;
	        	}else {
	        	
	     	        plat.setCases(j.getCases());
	     	        plat.affichePlateau();
	     	        Scanner sc = new Scanner(System.in);
			        plat.jouer(sc);
	        	}
	     	}
			break;
		case 3: 
			Joueur joueur2 = new Joueur("Robot");
			Random random = new Random();
			int nv = random.nextInt(3)+1;
			joueur2.getNiveau().setNiveauChoisi(nv);
			Plateau plateau = new Plateau(joueur2.getNiveau(), joueur2);
			plateau.setNbBoosterCasse(0);
			plateau.setNbFusilsADisposition(0);
			System.out.println();
			System.out.println(ANSI_MAGENTA+"              Voici une partie d'un joueur robot du niveau: "+ANSI_RESET+nv);
			System.out.println();
			System.out.println();
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			plateau.affichePlateau();
			plateau.jouerEnTantQueRobot();
			break;
			
		case 4:
			System.exit(0);
			break;

		default:
			break;
		}
		
	  }while(ch<1 || ch >4 || !bool);
		
	}

	
	
	

}
