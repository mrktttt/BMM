package data;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * The company in which the user can buy actions.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class Entreprise implements Serializable{
    private static final long serialVersionUID = 1L;
	private String domaine;
    private int capital;
    private int nbAction;
    private int nbSA;
    private int numero;
    private int min;
    private int max;
    private String nom;
    private int mult;
    private HashMap<String,User> users = new HashMap<String,User>();
    private static Logger logger;

    public Entreprise(int numero, String nom, String domaine, int capital, int nbAction, int nbSA, int mult, Logger logger){
        this.nom = nom;
        this.numero = numero;
        this.capital = capital;
        this.nbAction = nbAction;
        this.nbSA = nbSA;
        this.domaine = domaine;
        this.mult = mult;
        Entreprise.logger = logger;
        min = valeurAction();
        max = valeurAction();
    }

    public int getMult() {
		return mult;
	}

	public void setMult(int mult) {
		logger.info("Le multiplicateur de l'entreprise " + nom + " a changé de " + this.mult + " en " + mult);
		this.mult = mult;
	}

	//Getter
    public int getCapital(){
        return capital;
    }
    
    public int getNumero(){
        return numero;
    }

    public int getNbActions(){
        return nbAction;
    }

    public int getNbSA(){
        return nbSA;
    }

    public String getDomaine(){
        return domaine;
    }

    public String getNom(){
        return nom;
    }

    //Setter
    public void setCapital(int x){
        capital = x;
    }
    
    public void setNumero(int x){
        numero = x;
    }
    
    public void setNbActions(int x){
        nbAction = x;
    }

    public void setNbSA (int x){
        nbSA = x;
    }
    
    public void setLogger(Logger logger) {
    	Entreprise.logger = logger;
    }

    //Méthodes
    public int valeurAction(){
    	if (nbAction == 0) {
    		return 0;
    	}
        return capital/nbAction;
    }

    public void addUser(User u) {
    	users.put(u.getProf().getNom(), u);
	}
	
	public User getUser(String nom) {
		return users.get(nom);
	}
	
	public HashMap<String,User> getUsers() {
		return users;
	}
	
	public void removeUser(User u) {
		users.remove(u.getProf().getNom(), u);
	}

	@Override
	public String toString() {
		return nom;
	}

	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
}

