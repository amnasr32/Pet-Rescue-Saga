import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * une classe qui nous sert comme une base de donnees pour nos joueurs
 */
public class DataBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4355559482106448387L;
	private LinkedList<Joueur> listJoueurs  = new LinkedList<Joueur>();
	
	public DataBase() {
		recupererListeSauv();
	}
	
	public LinkedList<Joueur> getList(){
		return listJoueurs;
	}
	public void setList(LinkedList<Joueur> l) {
		this.listJoueurs=l;
	}
	
	
	/**
	 * ajouter un joueur a la base de donnee si le joueur est nouveau sinon mettre a jour les informations du joueur en argument
	 * @param j
	 */
	public void ajouter(Joueur j) {
		if(contains(j.getIdentifiant())) {
			Joueur tmp = getJoueurInList(j.getIdentifiant());
			tmp.setToutesLesInfo(j);
			sauvegarder();
			return;
		}
		listJoueurs.add(j);
		sauvegarder();
	}
	
    public boolean contains(String id) {
    	int n = 0;
    	while(n<listJoueurs.size()) {
    		if(listJoueurs.get(n).getIdentifiant().equals(id)) return true;
    		n++;
    	}
    	return false;
    }
    
    /**
     * sauvegarder la liste de nos joueurs
     */
    public void sauvegarder() {
    	try {
			
    		FileOutputStream fos = new FileOutputStream("../data/dataBase");
    		ObjectOutputStream os = new ObjectOutputStream(fos);
    		os.writeObject(listJoueurs);
    		os.close();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /** 
     * @param identifiant
     * @return le joueur dont l'identifiant est "identifiant" s'il existe dans notre liste sinon elle cree un nouveau joueur qui cet identifiant
     */
    public Joueur getJoueurInList(String identifiant) {
    	int n = 0;
    	while(n<listJoueurs.size()) {
    		if(listJoueurs.get(n).getIdentifiant().equals(identifiant)) {
    			return listJoueurs.get(n);
    		}
    		n++;
    	}
    	Joueur joueur =	new Joueur(identifiant);
    	ajouter(joueur);
    	return joueur;
    }
    
    /**
     * recuperation de notre liste de joueurs
     */
    @SuppressWarnings("unchecked")
    public void recupererListeSauv(){
		try{
			FileInputStream fis = new FileInputStream("../data/dataBase");
			ObjectInputStream ois = new ObjectInputStream(fis);
			listJoueurs=((LinkedList<Joueur>) ois.readObject());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

}
