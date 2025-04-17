package data;

import java.io.Serializable;

/**
 * The action of a company.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class Actif implements Serializable{
	private static final long serialVersionUID = 1L;
	private int dividende;
	private Entreprise ent;
	
	public Actif(int dividende, Entreprise ent) {
		this.dividende=dividende;
		this.ent=ent;
	}

	public int getDividende() {
		return dividende;
	}

	public void setDividende(int dividende) {
		this.dividende = dividende;
	}

	public Entreprise getEnt() {
		return ent;
	}

	public void setEnt(Entreprise ent) {
		this.ent = ent;
	}
	
	
}
