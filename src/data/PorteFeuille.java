package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The user's wallet.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class PorteFeuille implements Serializable{
	private static final long serialVersionUID = 1L;
	private int capital;
	private int nbActifs;
	private Historique hist;
	private ArrayList<Entreprise> entreprises = new ArrayList<Entreprise>();
	
	public PorteFeuille(int capital, int nbActifs, Historique hist) {
		this.capital=capital;
		this.nbActifs=nbActifs;
		this.hist=hist;
	}

	public ArrayList<Entreprise> getEntreprises() {
		return entreprises;
	}

	public Historique getHist() {
		return hist;
	}

	public void setHist(Historique hist) {
		this.hist = hist;
	}

	public int getNbActifs() {
		return nbActifs;
	}

	public void setNbActifs(int nbActifs) {
		this.nbActifs = nbActifs;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}
	
	public void addEntreprise(Entreprise ent){
		if (entreprises.contains(ent)) {
		}
		else {
			entreprises.add(ent);
		}
	}
	
	public Entreprise getEntreprise(String nom){
		Entreprise ent = null;
		Iterator<Entreprise> iterator = entreprises.iterator();
		while (iterator.hasNext()) {
			Entreprise e = iterator.next();
			if (e.getNom().equals(nom)) {
				ent = e;
			}
		}
		return ent;
	}
	
	
	public void removeEntreprise(Entreprise ent){
		if (entreprises.contains(ent)) {
			entreprises.remove(ent);
		}
	}
}
