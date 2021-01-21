import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * cette classe nous sert a stocker toutes les informations d'un joueur
 */
public class Joueur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5522319317583750336L;
	private String identifiant; 
	private Niveau niveau;
	private int meilleureScore;
	private int fusilADisposition;
	private int nbBoosterCasse;
	private int nbAnimSaved;
	private Case[][] cases;
	private int nbMouvement;
	private int score;
	
	public Joueur (String identifiant){
		this.identifiant=identifiant;
		niveau = new Niveau();
		fusilADisposition = 1; // on suppose que chaque nouveau joueur aura un booster Fusil
		nbBoosterCasse = 2;  //On suppose que chaque nouveau joueur aura 2 boosters marteau
		nbMouvement = 0;
	}
	
	public String getIdentifiant() {
		return identifiant;
	}
	public Niveau getNiveau() {
		return niveau;
	}
	public int getMeilleureScore() {
		return meilleureScore;
	}
	public int getFusilADisposition() {
		return fusilADisposition;
	}
	public int getnbBoosterCasse() {
		return nbBoosterCasse;
	}
	public int getScore() {
		return this.score;
	}
	public Case[][] getCases(){
		return cases;
	}
	public int getNbAnimSaved() {
		return nbAnimSaved;
	}
	public int getNbMouvement() {
		return this.nbMouvement;
	}
	public void setNiveau(Niveau niv) {
		this.niveau = niv;
	}
	public void setMeilleurScore(int m) {
		this.meilleureScore = m;
	}
	public void setFusilADisposition(int f) {
		fusilADisposition = f;
	}
	public void setBoosterCasse(int c) {
		this.nbBoosterCasse = c ;
	}
	public void setCases(Case[][] c) {
		this.cases = c;
	}
	public void setNbAnimSaved(int a) {
		this.nbAnimSaved = a ;
	}
	public void setNbMouvement(int mouv) {
		this.nbMouvement=mouv;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setToutesLesInfo(Joueur j) {
		this.niveau = j.niveau;
		this.meilleureScore = j.meilleureScore;
		this.fusilADisposition = j.fusilADisposition;
		this.nbBoosterCasse = j.nbBoosterCasse;
		this.cases = j.cases;
		this.nbAnimSaved = j.nbAnimSaved;
		this.nbMouvement = j.nbMouvement;
		this.score = score;
	}
	/**
	 * remplir le plateau d'un joueur this a partir de ce qu'il a sauvegarde
	 */
	public void chargerCases(){
		try {
			FileInputStream fis = new FileInputStream("../sauvegarde/sauv");
			ObjectInputStream ois = new ObjectInputStream(fis);
			cases = (Case[][]) ois.readObject();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
