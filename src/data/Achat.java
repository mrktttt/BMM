package data;

import java.io.Serializable;

/**
 * The purchase done by the user.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class Achat implements Serializable{
	private static final long serialVersionUID = 1L;
	private int prix;
	private String date;
	private User buyer;
	private Entreprise e;
	
	public Achat(int prix, String date, User buyer, Entreprise e) {
		this.buyer=buyer;
		this.date=date;
		this.prix=prix;
		this.e=e;
	}

	@Override
	public String toString() {
		return prix + " € / " + date + " / " + e;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Entreprise getEntreprise() {
		return e;
	}

	public void setEntreprise(Entreprise e) {
		this.e = e;
	}
	
	
}

