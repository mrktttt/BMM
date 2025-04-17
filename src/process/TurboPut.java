package process;

import java.io.Serializable;

/**
 * The turboput purchased by the user.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class TurboPut implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomEnt;
	private int nbVente;
	private int pxVente;
	
	public TurboPut(String nomEnt, int nbVente, int pxVente) {
		this.nomEnt = nomEnt;
		this.nbVente = nbVente;
		this.pxVente = pxVente;
	}

	public String getNomEnt() {
		return nomEnt;
	}

	public int getNbVente() {
		return nbVente;
	}

	public int getPxVente() {
		return pxVente;
	}

}
