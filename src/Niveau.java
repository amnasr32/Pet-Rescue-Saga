import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * cette classe nous permet de gerer les informations d'un niveau
 */
public class Niveau implements Serializable {
	
	private Plateau plateau;
	private int nbAnimToSave ;
	private int niveauChoisi;
	private int numeroLevel;
    private int permissionLevel;
    private int nbMouvementAutorise;
	
	
	public Niveau() {
		
	}
	
	public int getNbAnimToSave(){
		return this.nbAnimToSave;
	}
	public int getNumLevel() {
		return numeroLevel;
	}
	public int getPermissionLevel() {
		return permissionLevel;
	}
	public int getNbMouvementAutorise(){
		return nbMouvementAutorise;
	}
	
	public void setNiveauChoisi(int niveau) {
		this.niveauChoisi = niveau;
	}
	
	public void setNumLevel(int l) {
		numeroLevel = l;
	}
	
	public void setPermissionLevel(int p) {
		this.permissionLevel = p;
	}
	public void setNbMouvementAutorise(int mouv) {
		this.nbMouvementAutorise = mouv;
	}
	public void setNbAnimToSave(int a) {
		this.nbAnimToSave = a;
	}
	
	/**
	 * Creer le niveau 1 et le stocker dans un fichier niveau1 se trouvant dans le dossier niveaux
	 */
	public void creeNiveau1() {
		Case[][] c = { {null,new CaseAnimal(" Animal ",null),null,null,null,new CaseAnimal(" Animal ",null),null},
				       {new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal("  Bleu  ",Color.BLUE,plateau),new CaseNormal("  Bleu  ",Color.BLUE,plateau)},
				       {new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal("  Bleu  ",Color.BLUE,plateau),new CaseNormal("  Bleu  ",Color.BLUE,plateau)},
				       {new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau)},
				       {new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau)},
				       {new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal("  Bleu  ",Color.BLUE,plateau),new CaseNormal("  Bleu  ",Color.BLUE,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau)},
				       {new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal("  Bleu  ",Color.BLUE,plateau),new CaseNormal("  Bleu  ",Color.BLUE,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau)}
		             };
		this.nbAnimToSave = 2;
		this.numeroLevel = 1;
		this.nbMouvementAutorise = 6;
		
		try {
			FileOutputStream fos = new FileOutputStream("../niveaux/niveau1");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(c);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * creer le niveau 2 et le stocker dans un fichier niveau2 se trouvant dans le dossier niveaux
	 */
	public void creeNiveau2() {
		Case[][] c2 = {{null,new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseAnimal(" Animal ",null),new CaseNormal(" Violet ",Color.MAGENTA,plateau),null},
				       {new CaseAnimal(" Animal ",null),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseAnimal(" Animal ",null),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseAnimal(" Animal ",null)},
				       {new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseAnimal(" Animal ",null),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau)},
				       {new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau)},
				       {new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau)},
				       {new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau)},
				       {new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau)},
				       {new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Rouge  ",Color.RED,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau)}				       
		};
		this.nbAnimToSave = 5;
        this.numeroLevel = 2;
        this.nbMouvementAutorise = 7;
		try {
			FileOutputStream fos2 = new FileOutputStream("../niveaux/niveau2");
			ObjectOutputStream os2 = new ObjectOutputStream(fos2);
			os2.writeObject(c2);
			os2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * creer le niveau 3 et le stocker dans un fichier niveau3 se trouvant dans le dossier niveaux
	 */
	public void creeNiveau3() {
		Case[][] c3 = { {null,null,null,null,new CaseAnimal(" Animal ",null),new CaseAnimal(" Animal ",null),new CaseAnimal(" Animal ",null),new CaseAnimal(" Animal ",null)},
				       {null,null,null,new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau)},
				       {null,new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau)},
				       {new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseObstacle("Obstacle",null)},
				       {new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Violet ",Color.MAGENTA,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseObstacle("Obstacle",null),new CaseObstacle("Obstacle",null)},
				       {new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseObstacle("Obstacle",null),new CaseObstacle("Obstacle",null),new CaseObstacle("Obstacle",null)},
				       {new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseNormal("  Vert  ",Color.GREEN,plateau),new CaseNormal(" Jaune  ",Color.YELLOW,plateau),new CaseObstacle("Obstacle",null),new CaseObstacle("Obstacle",null),new CaseObstacle("Obstacle",null),new CaseObstacle("Obstacle",null)}
		             };
		this.nbAnimToSave = 4;
        this.numeroLevel = 2;
        this.nbMouvementAutorise = 10;
		try {
			FileOutputStream fos3 = new FileOutputStream("../niveaux/niveau3");
			ObjectOutputStream os3 = new ObjectOutputStream(fos3);
			os3.writeObject(c3);
			os3.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * creer le niveau choisi par l'utilisateur et le stocker dans le bon fichier
	 */
	public void creeNiveauChoisi() {
		switch (niveauChoisi) {
		case 1:
			creeNiveau1();
			break;
			
		case 2:
				creeNiveau2();
				break;
            
		case 3:
				creeNiveau3();
				break;
			
		default:
			break;
		}
	}
	
	public String chargerNiveau() {
		switch (niveauChoisi) {
		case 1:
			return "niveau1";
		case 2:
			return "niveau2";
		case 3: 
			return "niveau3";
 
		default:
			return "";
		}
	}
	/**
	 * @return le plateau de case sauvegarder dans le fichier qui correspond au niveau choisi
	 */
	public Case [][] chargerNiveauChoisi(){
		
		Case [][] c = null;
		try {
			String str= "../niveaux/"+chargerNiveau();
			FileInputStream fis = new FileInputStream(str);
			ObjectInputStream ois = new ObjectInputStream(fis);
			c = (Case[][] )ois.readObject(); 
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	
	

}
