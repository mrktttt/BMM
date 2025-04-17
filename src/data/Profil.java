package data;

import java.io.Serializable;

/**
 * The user's profile.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class Profil implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private String mail;
	private String numPhone;
	private String motDePasse;
	
	public Profil(String nom, String prenom, String mail, String numPhone, String motDePasse) {
		this.mail=mail;
		this.nom=nom;
		this.prenom=prenom;
		this.numPhone=numPhone;
		this.motDePasse=motDePasse;
	}

	public String getNumPhone() {
		return numPhone;
	}

	public void setNumPhone(String numPhone) {
		this.numPhone = numPhone;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
