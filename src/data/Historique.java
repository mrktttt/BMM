package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The historic of all of user's sales and purchases.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class Historique implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Vente> ventes = new ArrayList<Vente>();
	private ArrayList<Achat> achats = new ArrayList<Achat>();
	
	public Historique() {
	}
	
	public void addVente (Vente v) {
		ventes.add(v);
	}
	
	public void addAchat (Achat a) {
		achats.add(a);
	}
	
	public Vente rechVente(Vente v){
		Vente result = null;
		Iterator<Vente> iterator = ventes.iterator();
		while (iterator.hasNext()) {
			Vente vent = iterator.next();
			if (vent.equals(v)) {
				result = vent;
			}
		}
		return result;
	}
	
	public Achat rechAchat(Achat a){
		Achat result = null;
		Iterator<Achat> iterator = achats.iterator();
		while (iterator.hasNext()) {
			Achat ach = iterator.next();
			if (ach.equals(a)) {
				result = ach;
			}
		}
		return result;
	}

	public ArrayList<Vente> getVentes() {
		return ventes;
	}
	
	public ArrayList<Achat> getAchats() {
		return achats;
	}
}
