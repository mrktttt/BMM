package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The main user.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Actif> actifs = new ArrayList<Actif>();
	private Profil prof;
	private PorteFeuille prtF;
	
	public User( Profil prof, PorteFeuille prtF) {
		this.prof=prof;
		this.prtF=prtF;
	}

	public ArrayList<Actif> getActifs() {
		return actifs;
	}

	public void setActifs(ArrayList<Actif> actifs) {
		this.actifs = actifs;
	}

	public Profil getProf() {
		return prof;
	}

	public void setProf(Profil prof) {
		this.prof = prof;
	}

	public PorteFeuille getPrtF() {
		return prtF;
	}

	public void setPrtF(PorteFeuille prtF) {
		this.prtF = prtF;
	}
	
	public void addActif(Actif act) {
		actifs.add(act);
	}
	
	public Actif actifOfEntreprise(Entreprise ent){
		Actif result = null;
		Iterator<Actif> iterator = actifs.iterator();
		while (iterator.hasNext()) {
			Actif act = iterator.next();
			if (act.getEnt().equals(ent)) {
				result = act;
			}
		}
		return result;
	}
	
	public void removeActif(Actif actif) {
		if (actifs.contains(actif)) {
			actifs.remove(actif);
		}
	}
	
	public int getNbActOfEnt(Entreprise ent) {
		int s = 0;
		Iterator<Actif> iterator = actifs.iterator();
		while (iterator.hasNext()) {
			Actif act = iterator.next();
			if (act.getEnt().equals(ent)) {
				s++;
			}
		}
		return s;
	}
}
