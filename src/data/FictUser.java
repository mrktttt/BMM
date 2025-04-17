package data;

import java.io.Serializable;

/**
 * The fictionnal user for the supervisor mode.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class FictUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nom;
	private int nbActif = 20;
	
	public FictUser(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbActif() {
		return nbActif;
	}

	public void setNbActif(int nbActif) {
		this.nbActif = nbActif;
	}

}
