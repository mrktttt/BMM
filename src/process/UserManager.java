package process;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;

import data.Achat;
import data.Actif;
import data.Entreprise;
import data.Historique;
import data.User;
import data.Vente;
import gui.elements.HistFrame;

/**
 * This class simulate the purchase and the sale made by the user
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class UserManager {
	private User u;
	private Historique hist;
	private static Logger logger;
	
	public UserManager(User u, Logger logger) {
		this.u=u;
		this.hist= u.getPrtF().getHist();
		UserManager.logger = logger;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public Historique getHist() {
		return hist;
	}

	public void setHist(Historique hist) {
		this.hist = hist;
	}
	
	public Achat achat(Entreprise ent) {
		if (u.getPrtF().getCapital() >= ent.valeurAction() && ent.getNbActions()>0) {
			u.getPrtF().setCapital(u.getPrtF().getCapital()-ent.valeurAction());
			ent.setNbActions(ent.getNbActions()-1);
			Actif a = new Actif(ent.valeurAction(),ent);
			u.addActif(a);
			if(!ent.getUsers().containsValue(u)) {
				ent.addUser(u);
			}
			if (!u.getPrtF().getEntreprises().contains(ent)) {
				u.getPrtF().addEntreprise(ent);
			}
			u.getPrtF().setNbActifs(u.getPrtF().getNbActifs()+1);
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentDateTime = dateFormat.format(currentDate);
			Achat ach = new Achat(ent.valeurAction(),currentDateTime,u,ent);
			hist.addAchat(ach);
			logger.info("Nouvelle achat sur l'entreprise : " + ent.getNom());
			return ach;
		}
		return null;
	}
	
	public Vente vente(Entreprise ent) {
		if (u.getPrtF().getEntreprises().contains(ent) && u.actifOfEntreprise(ent)!=null){
			u.getPrtF().setCapital(u.getPrtF().getCapital()+ent.valeurAction());
			u.removeActif(u.actifOfEntreprise(ent));
			ent.setNbActions(ent.getNbActions()+1);
			if(u.actifOfEntreprise(ent)==null) {
				ent.removeUser(u);
				u.getPrtF().removeEntreprise(ent);
			}
			u.getPrtF().setNbActifs(u.getPrtF().getNbActifs()-1);
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentDateTime = dateFormat.format(currentDate);
			Vente v = new Vente(ent.valeurAction(),currentDateTime,u,ent);
			hist.addVente(v);
			logger.info("Nouvelle vente sur l'entreprise : " + ent.getNom());
			return v;
		}
		return null;
	}
	
	public Vente getLastVente() {
		Vente result = null;
		Iterator<Vente> iterator = hist.getVentes().iterator();
		while (iterator.hasNext()) {
			Vente v = iterator.next();
			result = v;
		}
		return result;
	}
	
	public Achat getLastAchat() {
		Achat result = null;
		Iterator<Achat> iterator = hist.getAchats().iterator();
		while (iterator.hasNext()) {
			Achat a = iterator.next();
			result = a;
		}
		return result;
	}
	
	public void useTurboPut(Factory fact, HistFrame hf) {
		int nbVente = 0;
		Entreprise venteEnt = null;
		TurboPut turboPutEnt = null;
		Iterator<TurboPut> iter = fact.getTurboPuts().iterator();
		while (iter.hasNext()) {
			TurboPut turboP = iter.next();
			if (fact.getEnt(turboP.getNomEnt()).valeurAction() <= turboP.getPxVente()) {
				venteEnt = fact.getEnt(turboP.getNomEnt());
				nbVente = turboP.getNbVente();
				turboPutEnt = turboP;
			}
		}
		for (int i=0 ; i<nbVente ; i++ ) {
			Vente v = vente(venteEnt);
			if(v!=null) {
				hf.updateTableVente(v);
			}
		}
		if (turboPutEnt != null) {
			fact.getTurboPuts().remove(turboPutEnt);
		}
	}
	
	public void useTurboCall(Factory fact, HistFrame hf) {
		int nbAchat = 0;
		Entreprise achatEnt = null;
		TurboCall turboCallEnt = null;
		Iterator<TurboCall> iter = fact.getTurboCalls().iterator();
		while (iter.hasNext()) {
			TurboCall turboC = iter.next();
			if (fact.getEnt(turboC.getNomEnt()).valeurAction() >= turboC.getPxAchat()) {
				achatEnt = fact.getEnt(turboC.getNomEnt());
				nbAchat = turboC.getNbAchat();
				turboCallEnt = turboC;
			}
		}
		for (int i=0 ; i<nbAchat ; i++ ) {
			Achat a = achat(achatEnt);
			if(a!=null) {
				hf.updateTableAchat(a);
			}
		}
		if (turboCallEnt != null) {
			fact.getTurboCalls().remove(turboCallEnt);
		}
	}
}

