import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

//import java.util.Random;
 

public class Plateau  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4459339009987810993L;
	
	private Case [][] cases;
	private Niveau niveau;
	private int nbAnimToSave;
	private int nbAnimSaved;
	private int explosedBlock;
	private int fusilADisposition;
	private int nbBoosterCasse;
	public int score;
	public int nbMouvement;
	public int nbMouvementAutorise;
	
	private Joueur joueur;
	
	private DataBase dataBase;
	
	/**
	 * Une liste des unicode pour afficher le text en couleur ou un emoji au niveau du terminal
	 */
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_MAGENTA = "\u001B[35m";
	public static final String ANSI_CYANB = "\u001B[46m";
	public static final String ANSI_REDB = "\u001B[41m";
	public static final String ROCKET = "\uD83D\uDE80";
	public static final String HAMMER = "\uD83D\uDD28";
	public static final String ANIMAL ="\uD83D\uDC08";
	
	
	/**
	 * Le constructeur de plateau permet de cree un plateau a partir d'un joueur et d'un niveau
	 * @param niveau
	 * @param joueur
	 */
	public Plateau(Niveau niveau, Joueur joueur) {
		
		dataBase = new DataBase();
		this.niveau = niveau;
		niveau.creeNiveauChoisi();
		nbAnimToSave = niveau.getNbAnimToSave();
		nbAnimSaved = joueur.getNbAnimSaved();
		nbMouvementAutorise =niveau.getNbMouvementAutorise();
		nbMouvement = joueur.getNbMouvement();
		score = joueur.getScore();
		explosedBlock = 0;
		fusilADisposition = joueur.getFusilADisposition();
		nbBoosterCasse = joueur.getnbBoosterCasse();
		
		this.joueur = joueur;
		
		cases = niveau.chargerNiveauChoisi();
						
	}	
	
	public Case[][] getCases(){
		return cases;
	}
	public int getScore() {
		return this.score;
	}
	public int getExplosedBlocks() {
		return this.explosedBlock;
	}
	public void setCases(Case[][] c) {
		cases = c;
	}
	public int getNbAnimSaved() {
		return this.nbAnimSaved;
	}
    public int getNbAnimToSave() {
    	return this.nbAnimToSave;
    }
    public int getNbFusilsADisposition() {
    	return this.fusilADisposition;
    }
    public int getNbBoosterCasse() {
    	return this.nbBoosterCasse;
    }
    public int getNbMouvementAutorise() {
    	return this.nbMouvementAutorise;
    }
    public int getNbMouvement() {
    	return this.nbMouvement;
    }
    public void setNbFusilsADisposition(int fus) {
    	this.fusilADisposition = fus;
    }
    public void setNbBoosterCasse(int c) {
    	this.nbBoosterCasse = c;
    }
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setNbAnimToSave(int anim) {
		this.nbAnimToSave = anim;
	}
	public void setExplosedBlock(int block) {
		this.explosedBlock = block;
	}
	public void setNbMouvement(int mouv) {
		this.nbMouvement = mouv;
	}
	public void setNbMouvementAutorise(int mouv) {
		this.nbMouvementAutorise = mouv;
	}
	public void setNbAnimSaved(int anim) {
		this.nbAnimSaved = anim;
	}
		
	/**
	 * affichage de notre plateau
	 */
	public void affichePlateau() {
		for(int i=0;i<cases.length;i++) {
			for(int j=0;j<cases[i].length;j++) {
				
				if(cases[i][j] == null) {
					System.out.print("|          |");
				}
				
			    else if("Obstacle".equals(cases[i][j].getFigure())) {
					System.out.print("| ");
				    System.out.print(ANSI_RED + "Obstacle"+ANSI_RESET);
					System.out.print(" |");
				}
				else if(cases[i][j].getFigure().equals(" Animal ")) {
					System.out.print("| ");
					System.out.print("   "+ANIMAL+"   "/*ANSI_CYAN + " Animal "+ANSI_RESET*/);
					System.out.print(" |");
				}
				
				else{
					System.out.print("| "+cases[i][j].getFigure()+" |");
				}
			}
			System.out.println();
			System.out.println();
		}
	}
	
	/**
	 * 
	 * @param c : une case
	 * @return un tableau de deux entiers res, res[0] correspond a l'indice de la ligne et res[1] a l'indice de la colonne 
	 */
	public int[] recupereIetJ(Case c) {
		int [] res = new int[2];
 		for(int i=0;i<cases.length;i++) {
			for(int j=0; j<cases[i].length;j++) {
				if(cases[i][j] == c) {
					res[0] = i;
					res[1] = j;
				}
			}
		}
 		return res;
	}
	
	/**
	 * 
	 * @param i : indice d'une ligne
	 * @param j : indice d'une colonne
	 * @return un tableau de cases de 4 elements qui representent les 4 adjacents directs de la case dont les coordonnes sont i et j
	 */
	public Case[] adjacentsDirectMmFigure(int i, int j) {
		Case c = cases[i][j];
		if(c == null || !(c instanceof CaseNormal)) return null;
		
		Case[] res = new Case[4];
		
		if(i+1 < cases.length) {
			if(c.estMmFigure(cases[i+1][j])) res[0] = cases[i+1][j];
		}
		if(i-1 >=0) {
			if(c.estMmFigure(cases[i-1][j])) res[1] = cases[i-1][j];
		}
		if(j-1 >=0) {
			if(c.estMmFigure(cases[i][j-1])) res[2] = cases[i][j-1];
		}
		if(j+1 < cases[i].length) {
			if(c.estMmFigure(cases[i][j+1])) res[3] = cases[i][j+1];
		}
		
	    return res;
		
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return le nombre d'adjacents que la case de coordonnes i et j possede
	 */
	public int nbAdjacentsDirectMmFigure(int i, int j) {
		int nb = 0;
		Case[] tmp = adjacentsDirectMmFigure(i, j);
		
		for(Case o : tmp) {
			if(o !=null) nb++;
		}
		
		return nb;
	}
	
	/**
	 *elimination de toutes les cases de la meme couleur autour de la case des coordonnes i et j
	 * @param i
	 * @param j
	 * @param bool
	 */
	public void eliminationCasesMmFig(int i, int j,boolean bool) {
		
		if(cases[i][j] == null) {
			return;
		}
		if(!(cases[i][j] instanceof CaseNormal)) return;
		
		if(nbAdjacentsDirectMmFigure(i,j) == 0) {
			if(!bool) {
				cases[i][j] = null;
				explosedBlock++;
			}
			return;
		}
		
		Case [] res = adjacentsDirectMmFigure(i,j);
		
		cases[i][j] = null; //on efface la case
	    explosedBlock++;
		
	    if(bool) {
	    	nbMouvement++;
	    }
	    
		for(Case o : res) {
			int k = recupereIetJ(o)[0];
			int l = recupereIetJ(o)[1];
			eliminationCasesMmFig(k, l, false);
		}
	
	}
		
	public boolean colonneVide(int j) {
		
		for(int i=0; i<cases.length;i++) {
			if(cases[i][j] != null ) return false;
		}
		return true;
		
	}
	/**
	 * 
	 * @param j
	 * @return le nombre de cases vide dans une colonne donnee
	 */
	public int nbrVideCol(int j) {
		int nb=0;
		for(int i=0;i<cases.length;i++) {
			if( cases[i][j] == null) nb++;
		}
		return nb;
	}
	
	/**
	 * 
	 * @param j
	 * @return true si la colonne donne contient un obstacle
	 */
	public boolean colContientObstacle(int j) {
		
		for(int i=0;i<cases.length;i++) {
			if(cases[i][j] instanceof CaseObstacle) return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param j
	 * @return true si la colonne donnee contient un animal
	 */
	public boolean colContientAnimal(int j) {
		
		for(int i=0;i<cases.length;i++) {
			if(cases[i][j] instanceof CaseAnimal) return true;
		}
		return false;
	}
	/**
	 * 
	 * @param j
	 * @return le nombre d'animaux dans une colonne donnee
	 */
	public int nbAnimalCol(int j) {
		int nb=0;
		for(int i = 0;i<cases.length;i++) {
			if(cases[i][j] instanceof CaseAnimal) nb++;
		}
		return nb;
	}
	
	public int recupererLigneObstacle(int j) {
		for(int i=0;i<cases.length;i++) {
			if(cases[i][j] instanceof CaseObstacle) return i;
		}
		return -1;
	}
	
	public boolean ttLeResteEstObstacle(int j) {
		int i = recupereIndicePremierObstacleCol(j)+1;
		while(i<cases.length) {
			if(!(cases[i][j] instanceof CaseObstacle)) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	public int recupereIndicePremierObstacleCol(int j) {
		int i = 0;
		while(i<cases.length) {
			if(cases[i][j] instanceof CaseObstacle) {
				return i;
			}
			i++;
		}
		return -1;
	}
	/**
	 * reorganisation d'une colonne donnee
	 * @param j
	 */
	public void reorganisationCol(int j) {
	
		  Case [] c = new Case[cases.length-nbrVideCol(j)];
		
		  int p=0;
		  for(int i=0;i<cases.length;i++) {
			 if(cases[i][j] != null ) {
				c[p] = cases[i][j];
				p++; 
			 }
		  }
		  int i =0;
		  int nbr = nbrVideCol(j);
	      while(i<nbr) {
			cases[i][j] = null;
			i++;
		  }
	      int k=0;
		  for(int r=i;r<cases.length;r++) {
			cases[r][j] = c[k];
			k++;
		  }
 		
	}
	/**
	 * reorganisation de toutes les colonnes du plateau
	 */
	public void ttReorganiserCol() {
		for(int j=0;j<cases[0].length;j++) {
			reorganisationCol(j);
			elimAnimParTerre();
			reorganisationCol(j);
		}
	}
	
	/**
	 * reorganisation de tout le plateau
	 */
	public void reorganisation() {
		
		ttReorganiserCol();
		ttReorganiserAGauche();
		ttReorganiserCol();
		ttReorganiserAGauche();
	
	}
	/**
	 * 
	 * @return le nombre de colonnes qui sont vides
	 */
	public int nbrColVide() {
		int nb = 0;
		for(int j=0;j<cases[0].length;j++) {
			if(colonneVide(j)) nb++;
		}
		return nb;
	}
	
	public Case[] colToTab(int j) {
		Case [] c = new Case[cases.length];
		for(int i=0;i<cases.length;i++) {
			c[i] = cases[i][j];
		}
		return c;
	}
	/**
	 * elimination de toutes les cases coloree d'une colonne donnee
	 * @param j 
	 */
	public void eliminationFusil(int j) {
		int cpt = 0;
		if(colContientAnimal(j)) nbAnimSaved += nbAnimalCol(j);
		for(int i=0;i<cases.length;i++) {
			if(cases[i][j] != null && cases[i][j] instanceof CaseNormal) cpt++;
			if(!(cases[i][j] instanceof CaseObstacle)) cases[i][j] = null;
		}
		nbMouvement++;
		fusilADisposition -- ;
		joueur.setFusilADisposition(fusilADisposition);
		dataBase.ajouter(joueur);
		explosedBlock = cpt;
	}
	/**
	 * elimination de la case de coordonnes i et j
	 * @param i
	 * @param j
	 */
	public void eliminationMarteau(int i,int j) {
		
		if(cases[i][j] instanceof CaseNormal) {
			nbMouvement++;
			explosedBlock = 1; 
			cases[i][j] = null;
			nbBoosterCasse -- ;
			joueur.setBoosterCasse(nbBoosterCasse);
			dataBase.ajouter(joueur);
		}
		
		reorganisation();
	
	}
	// pas utilisé 
	public void remplirCol(int j, Case [] c) {
		for(int i=0;i<cases.length;i++) {
			cases[i][j] = c[i];
		}
	}
	
	public void remplirColObstcl(int j, Case [] c) {
		int k=cases.length-1;
		int l=c.length-1;
		for(int i =cases.length-1;i>=0;i--) {
			if(cases[i][j] instanceof CaseObstacle) {
				k--;
			}else {
				if(c[l] instanceof CaseObstacle) {
					l--;
				}else {
					cases[k][j] = c[l];
					k--;
					l--;
				}
			}
		}

	}
	public void nullColSaufObstcl(int j) {
		for(int i=0;i<cases.length;i++) {
			if(!(cases[i][j] instanceof CaseObstacle)) {
				cases[i][j] = null;
			}
		}
	}
	
	public boolean colContientQueObstcl(int j) {
		for(int i = 0;i<cases.length;i++) {
			if(cases[i][j] instanceof CaseAnimal || cases[i][j] instanceof CaseNormal) return false;
		}
		return true;
	}
	/**
	 * reorganisation du plateau a gauche
	 */
	public void reorganisationAGauche() {
		boolean bool = true;
			int k = cases.length-1;
			int j=1;
			while(j<cases[0].length-1 && ((!colonneVide(j-1) && !colContientObstaclAuDessus(j-1)) || (colonneVide(j) || colContientQueObstcl(j)))) {
				  j++;
			}
			
			for(int i=cases.length-1;i>=0;i--) {
				    
				    while(j<cases[0].length && cases[i][j] instanceof CaseObstacle) {
					   i--;
				    }
				    while(k>=0 && (j-1<cases[0].length) &&(cases[k][j-1] instanceof CaseObstacle || cases[k][j-1]!=null) ) {
				    	k--;
				    }
				    if(k<i) {
				    	if(j<cases[0].length-1) j++;
				    }else {
				    	cases[k][j-1] = cases[i][j];
				    	cases[i][j] = null;
				        k--;
				    }
				
			}
			ttReorganiserCol();
	}
	/**
	 * 
	 * @param j
	 * @return true si il existe au moins une colonne simplifiable apres la colonne j
	 */
	public boolean colonneSimplifiableApresJ(int j) {
		if(j+1>=cases[0].length) return false;
		for(int i=j+1;j<cases[0].length;j++) {
			if(!colonneVide(i) || !colContientObstaclAuDessus(i)) return true;
		}
		return false;
	}
	
	/**
	 * faire toute la reorganisation a gauche
	 */
	public void ttReorganiserAGauche() {
		
		for(int j=0;j<cases[0].length-1;j++) {
			if((!pasDeColVide() || !pasDeColQuiContientObstclAudessus()) && colonneSimplifiableApresJ(j)) {
				reorganisationAGauche();
				elimAnimParTerre();
			}else {
				return;
			}
		}
	}
	
	public boolean pasDeColVide() {
		for(int j=0;j<cases[0].length;j++) {
			if(colonneVide(j)) return false;
		}
		return true;
	}
	public boolean pasDeColQuiContientObstclAudessus() {
		for(int j=0;j<cases[0].length;j++) {
			if(colContientObstaclAuDessus(j)) return false;
		}
		return true;
	}

	/*public void reorganisationAGaucheSansObstcl() {
		
		Case [][] c = new Case[cases[0].length][cases.length];
		int k=0;
		for(int i=0;i<cases[0].length;i++) {
			if(!colonneVide(i)) {
				c[k]=colToTab(i);
				k++;
			}
		}
		
		for(int i=0;i<c.length;i++) {
			for(int j=0;j<c[i].length;j++) {
				if(c[i][j] !=null) System.out.print(c[i][j].getFigure()+"  ");
				else System.out.print(" null  ");
			}
			System.out.println();
		}
		
		for(int i=0;i<cases[0].length;i++) {
			
			 remplirCol(i, c[i]);
		}	
	}*/
	public boolean colContientObstaclAuDessus(int j) {
		for(int i=0;i<cases.length;i++) {
			if (cases[i][j] instanceof CaseObstacle) return true;
			if(cases[i][j] instanceof CaseAnimal || cases[i][j] instanceof CaseNormal) return false;
		}
		return false;
	}
	
	public int nbObstacle(int j) {
		int cpt = 0;
		for(int i=0;i<cases.length;i++) {
			if(cases[i][j] instanceof CaseObstacle) cpt++;
		}
		return cpt;
	}
	/**
	 * elimination des animaux qui sont sauves
	 */
	public void elimAnimParTerre() {
		for(int j=0;j<cases[0].length;j++) {
			if(cases[cases.length-1][j] instanceof CaseAnimal) {
				cases[cases.length-1][j] = null;
				this.nbAnimSaved++;
			}
		}
	}
	/**
	 * l'ordinateur choisit un niveau au hazard et commence a joueur a des coups aleatoirement
	 */
	public void jouerEnTantQueRobot() {
		while(!gagne()) {
			if(perdre()) return ;
			
			Random random = new Random();
			int i=0,j=0;
			
			do {
				i = random.nextInt(cases.length);
				j = random.nextInt(cases[0].length);
			}while(!(cases[i][j] instanceof CaseNormal));
			
			 eliminationCasesMmFig(i, j, true);
			 reorganisation();
			 System.out.println();
			 System.out.println();
				 
			 try{
			     Thread.sleep(2000);
			 }catch (InterruptedException e) {
				 e.printStackTrace();
			 }
			 affichePlateau();
		}
	}
	
	
	public boolean gagne() {
		 score += explosedBlock*explosedBlock*10;
		 explosedBlock = 0;
		 if(nbAnimSaved == nbAnimToSave) {
			System.out.println(ANSI_GREEN+"Felicitations ! Vous avez gagne: "+ANSI_RESET+" Animaux sauves: "+nbAnimSaved+" | Mouvements restants : "+(nbMouvementAutorise-nbMouvement)+" | score: "+score);
			if(score > joueur.getMeilleureScore()) joueur.setMeilleurScore(score);
			joueur.getNiveau().setPermissionLevel(niveau.getNumLevel());
			fusilADisposition++;
			nbBoosterCasse++;
			joueur.setFusilADisposition(fusilADisposition);
			joueur.setBoosterCasse(nbBoosterCasse);
			dataBase.ajouter(joueur);
			System.out.println();
			return true;
		 }
	 	 System.out.println("Animaux Sauves: "+nbAnimSaved+ " |Mouvement restants : "+ (nbMouvementAutorise-nbMouvement)+" | score: "+score);
	 	 return false;
	}
	/**
	 * 
	 * @return true si on peut plus eliminer de cases
	 */
	public boolean simplificationImpossible() {
		for(int i=0;i<cases.length;i++) {
			for(int j=0;j<cases[i].length;j++) {
				if(cases[i][j] instanceof CaseNormal) {
					if(nbAdjacentsDirectMmFigure(i, j) > 0) return false;
				}
			}
		}
		return true;
	}
	
	public boolean perdre() {
		if(nbMouvementAutorise-nbMouvement == 0 || (simplificationImpossible() && nbBoosterCasse == 0 && fusilADisposition==0) ) {
			System.out.println(ANSI_RED+" Niveau non termine: trop peu d'animaux sauves\n"+ANSI_RESET);
			return true;
		}
		return false;
	}
	
	public void jouer(Scanner scan) {
		
		
		while(!gagne()) {
		   
		   if(perdre()) return ;
		 
		   System.out.println();
		   System.out.println(" Quelle action voulez-vous effectuer ? \n");
		   System.out.println("      1- Selectionner une case a elimine\n");
		   System.out.println("      2- Utiliser le booster Fusil "+ROCKET+" (pour eliminer une colonne entiere)\n");
		   System.out.println("      3- Utiliser le booster Marteau"+HAMMER+" (pour casser une seule case de votre choix)\n");
		   System.out.println("      4- Enregistrer la partie \n");
		   System.out.println("      5- Retourner au menu\n");
		   int choix = scan.nextInt();
		   action(choix, scan);
		}
		
	}
	
	public void action(int choix,Scanner scan) {
		switch (choix) {
		case 1:
			  int i=0,j=0;
			   do{
				   System.out.println(" Veuillez entre les cordonnées de la case que vous voulez elimine: ");
				   System.out.print(" i = ");
				   i = scan.nextInt();
			 	   System.out.print(" j = ");
				   j = scan.nextInt();
			   }while(i<0 || i>=cases.length || j<0 || j>=cases[0].length);
			   
			   eliminationCasesMmFig(i, j, true);
			   reorganisation();
			   affichePlateau();
			
			
			break;
			
		case 2: 
			if(fusilADisposition == 0) {
				System.out.println(ANSI_RED+"Dommage pas assez de fusils :( \n"+ANSI_RESET);
				affichePlateau();
				return;
			}
			int c = 0;
			
			do{
				System.out.println(" Veuillez selectionner la colonne que vous voulez faire disparaître :) ");
				System.out.print(" NumCol = ");
				c = scan.nextInt();
			}while(c<0 || c>=cases[0].length);
			
			eliminationFusil(c);
			reorganisation();
			affichePlateau();
			break;
			
		case 3: 
			if(nbBoosterCasse == 0) {
				System.out.println(ANSI_RED+"Dommage :( pas assez de booster casse\n"+ANSI_RESET);
				affichePlateau();
				return;
			}
			
			int indi=0,indj=0;
			do{
				System.out.println(" Veuillez selectionner les coordonnees de la case que vous voulez casser ");
				System.out.print(" indi = ");
				indi = scan.nextInt();
				System.out.print(" indj = ");
			    indj = scan.nextInt();
			}while(indi<0 || indi>=cases.length || indj<0 || indj>=cases[0].length);
			
			eliminationMarteau(indi, indj);
			affichePlateau();
			
			break;
			
		case 4:
			enregistrerPartie();
			System.out.println(ANSI_GREEN+"Sauvegarde effectuee\n"+ANSI_RESET);
			joueur.setNbAnimSaved(nbAnimSaved);
			joueur.setNbMouvement(nbMouvement);
			joueur.setScore(score);
			dataBase.ajouter(joueur);
			joueur.chargerCases();
			dataBase.ajouter(joueur);
			break;
			
		case 5:
			JeuTerminal.main(new String[0]);
            break;
		default:
			break;
		}
	}
	
	public void enregistrerPartie() {
		try {
			FileOutputStream fos = new FileOutputStream("../sauvegarde/sauv");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(cases);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}