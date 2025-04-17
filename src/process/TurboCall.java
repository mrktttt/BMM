package process;

import java.io.Serializable;

/**
 * The turbocall purchased by the user.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class TurboCall implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomEnt;
	private int nbAchat;
	private int pxAchat;
	
	public TurboCall(String nomEnt, int nbAchat, int pxAchat) {
		this.nomEnt = nomEnt;
		this.nbAchat = nbAchat;
		this.pxAchat = pxAchat;
	}

	public String getNomEnt() {
		return nomEnt;
	}

	public int getNbAchat() {
		return nbAchat;
	}

	public int getPxAchat() {
		return pxAchat;
	}
	
	

}