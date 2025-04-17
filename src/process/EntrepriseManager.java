package process;

import java.io.Serializable;
import java.util.Random;

import data.Entreprise;

/**
 * This class make the change in the capital of a company.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class EntrepriseManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private Entreprise e;
	private int taux;

	public EntrepriseManager(Entreprise e, int taux) {
		this.e = e;
		this.taux = taux;
	}

	/**
	 * Increase or decrease a company's capital randomly.
	 * 
	 * 'taux' and 'mult' have an influence for the variation.
	 */
	public void variationPrice() {
		// utiliser taux;
		int mult = e.getMult();

		Random n = new Random();
		double priceVar = n.nextDouble() + 0.1;
		int increaseDecrease = n.nextInt(10) + 1;
		double capital;
		// la valeur de comparaison en dessous pourra être modifiée via la tendance à
		// venir pour faire varier le cours d'une actions en sa faveur.
		if (increaseDecrease > taux) {
			capital = e.getCapital() + (e.getCapital() * priceVar / 100) * mult;
		} else if ((e.getCapital() - (e.getCapital() * priceVar / 100)) >= 0) {
			capital = e.getCapital() - (e.getCapital() * priceVar / 100) * mult;
		} else {
			capital = 0;
		}
		e.setCapital((int) capital);
	}

	public Entreprise getE() {
		return e;
	}

	public void setE(Entreprise e) {
		this.e = e;
	}

	public int getTaux() {
		return taux;
	}

	public void setTaux(int taux) {
		this.taux = taux;
	}
}
