package process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.apache.log4j.Logger;

import data.Entreprise;
import data.Evenement;
import data.FictUser;
import data.Historique;
import data.PorteFeuille;
import data.Profil;
import data.User;

/**
 * This class create all of the object needed for the simulation
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class Factory implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Entreprise> ents = new ArrayList<Entreprise>();
	private HashMap<String, Integer> tauxDomaine = new HashMap<String, Integer>();
	private HashMap<Entreprise, EntrepriseManager> entsManag = new HashMap<Entreprise, EntrepriseManager>();
	private ArrayList<Evenement> evs = new ArrayList<Evenement>();
	private ArrayList<TurboPut> turboPuts = new ArrayList<TurboPut>();
	private ArrayList<TurboCall> turboCalls = new ArrayList<TurboCall>();
	private ArrayList<FictUser> users = new ArrayList<FictUser>();
	private User u;

	private Profil prof;
	private Historique hist;
	private PorteFeuille portf;

	private static Logger logger;

	private Evenement ev1 = new Evenement("Nvidia développe une nouvelle technologie.", 1, true);
	private Evenement ev2 = new Evenement("Une pandémie est apparue.", 2, false);
	private Evenement ev3 = new Evenement(
			"Une nouvelle taxe internationale sur les véhicules thermiques fait du bruit.", 3, false);
	private Evenement ev4 = new Evenement("Un projet immobilier intéréssant relance l'économie en Australie.", 4, true);
	private Evenement ev5 = new Evenement("Pénurie de pétrole dans le monde.", 5, false);
	private Evenement ev6 = new Evenement("Mercedes annonce arrêter les véhicules essences d'ici 2030.", 6, false);
	private Evenement ev7 = new Evenement("Ferrari commercialise son dernier véhicule V12 atmosphérique.", 7, true);
	private Evenement ev8 = new Evenement("L'hybride est propulsé par un nouveau modèle de chez Toyota.", 8, true);
	private Evenement ev9 = new Evenement(
			"Microsoft est en procès avec l'UE pour utilisation abusive des données personnelles.", 9, false);
	private Evenement ev10 = new Evenement(
			"L'europe annonce devoir utiliser sa carte d'identitée pour utiliser internet.", 10, false);
	private Evenement ev11 = new Evenement("Google annonce des salaires et conditions de travail revues à la hausse.",
			11, true);
	private Evenement ev12 = new Evenement("Le dernier smartphone de Samsung est rappelé car les batteries explosent.",
			12, false);
	private Evenement ev13 = new Evenement(
			"Sony propose un téléphone bon marché concurrencant des téléphones bien plus cher.", 13, true);
	private Evenement ev14 = new Evenement(
			"Un pdg interviewé sur apple \"Apple est une entreprise fiable pour des investissements\".", 14, true);
	private Evenement ev15 = new Evenement(
			"Petro china diversifie son offre énergétique pour ne pas dépendre uniquement des énergies fossiles.", 15,
			true);
	private Evenement ev16 = new Evenement("Un rappel des dernières peugeot 508 pour problème moteur fait du bruit.",
			16, false);
	private Evenement ev17 = new Evenement("Volkswagen sort la première sportive électrique.", 17, true);
	private Evenement ev18 = new Evenement(
			"Un nouveau moteur thermique bmw dépasses toutes les attentes en terme de pollution.", 18, true);
	private Evenement ev19 = new Evenement("GM dévoile un nouveau design très critiqué.", 19, false);
	private Evenement ev20 = new Evenement("BYD étoffe sa gamme électrique.", 20, true);
	private Evenement ev21 = new Evenement(
			"Renault revient à ses origine et dévoile une 4L moderne très apprécié par le public.", 21, true);
	private Evenement ev22 = new Evenement("Une crise éconoique majeure touche le Japon.", 22, false);

	public Factory(Logger logger, Profil prof) {
		Factory.logger = logger;

		tauxDomaine.put("Mobilité", 5);
		tauxDomaine.put("Informatique", 5);
		tauxDomaine.put("Energie", 5);

		Entreprise ent1 = new Entreprise(1, "Tesla", "Mobilité", 100000, 100, 50, 1, logger);
		Entreprise ent2 = new Entreprise(2, "Mercedes", "Mobilité", 100000, 100, 50, 1, logger);
		Entreprise ent3 = new Entreprise(3, "Groupe Volkswagen", "Mobilité", 100000, 100, 50, 1, logger);
		Entreprise ent4 = new Entreprise(4, "Groupe PSA", "Mobilité", 100000, 100, 50, 1, logger);
		Entreprise ent5 = new Entreprise(5, "Renault", "Mobilité", 100000, 100, 50, 1, logger);
		Entreprise ent6 = new Entreprise(6, "Ferrari", "Mobilité", 100000, 100, 50, 1, logger);
		Entreprise ent7 = new Entreprise(7, "BMW", "Mobilité", 100000, 100, 50, 1, logger);
		Entreprise ent8 = new Entreprise(8, "GM", "Mobilité", 100000, 100, 50, 1, logger);
		Entreprise ent9 = new Entreprise(9, "Toyota", "Mobilité", 100000, 100, 50, 1, logger);
		Entreprise ent10 = new Entreprise(10, "BYD", "Mobilité", 100000, 100, 50, 1, logger);

		Entreprise ent11 = new Entreprise(11, "Microsoft", "Informatique", 100000, 100, 50, 1, logger);
		Entreprise ent12 = new Entreprise(12, "Apple", "Informatique", 100000, 100, 50, 1, logger);
		Entreprise ent13 = new Entreprise(13, "Sony", "Informatique", 100000, 100, 50, 1, logger);
		Entreprise ent14 = new Entreprise(14, "Samsung", "Informatique", 100000, 100, 50, 1, logger);
		Entreprise ent15 = new Entreprise(15, "Google", "Informatique", 100000, 100, 50, 1, logger);
		Entreprise ent16 = new Entreprise(16, "Nvidia", "Informatique", 100000, 100, 50, 1, logger);

		Entreprise ent17 = new Entreprise(17, "Total Energie", "Energie", 100000, 100, 50, 1, logger);
		Entreprise ent18 = new Entreprise(18, "PetroChina", "Energie", 100000, 100, 50, 1, logger);

		FictUser user1 = new FictUser("Francis Loiseau");
		FictUser user2 = new FictUser("Gradasso Monty");
		FictUser user3 = new FictUser("Forrest Poulin");
		FictUser user4 = new FictUser("Philippe Querry");
		FictUser user5 = new FictUser("Colette Courtemanche");
		FictUser user6 = new FictUser("Christophe Charbonneau");
		FictUser user7 = new FictUser("Rémy Lécuyer");
		FictUser user8 = new FictUser("Océane Rancourt");
		FictUser user9 = new FictUser("Dorothée Quessy");
		FictUser user10 = new FictUser("Ansel Dastous");

		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);
		users.add(user8);
		users.add(user9);
		users.add(user10);

		ents.add(ent1);
		ents.add(ent2);
		ents.add(ent3);
		ents.add(ent4);
		ents.add(ent5);
		ents.add(ent6);
		ents.add(ent7);
		ents.add(ent8);
		ents.add(ent9);
		ents.add(ent10);

		ents.add(ent11);
		ents.add(ent12);
		ents.add(ent13);
		ents.add(ent14);
		ents.add(ent15);
		ents.add(ent16);

		ents.add(ent17);
		ents.add(ent18);

		Iterator<Entreprise> it = ents.iterator();
		while (it.hasNext()) {
			Entreprise e = it.next();
			EntrepriseManager entM = new EntrepriseManager(e, tauxDomaine.get(e.getDomaine()));
			entsManag.put(e, entM);
		}

		evs.add(ev1);
		evs.add(ev2);
		evs.add(ev3);
		evs.add(ev4);
		evs.add(ev5);
		evs.add(ev6);
		evs.add(ev7);
		evs.add(ev8);
		evs.add(ev9);
		evs.add(ev10);
		evs.add(ev11);
		evs.add(ev12);
		evs.add(ev13);
		evs.add(ev14);
		evs.add(ev15);
		evs.add(ev16);
		evs.add(ev17);
		evs.add(ev18);
		evs.add(ev19);
		evs.add(ev20);
		evs.add(ev21);
		evs.add(ev22);

		hist = new Historique();

		this.prof = prof;
		// prof = new Profil ("Razafinony","Mario","razafinonymario@gmail.com","07 66 28
		// 63 63","bourse2024");
		portf = new PorteFeuille(50000, 0, hist);

		u = new User(prof, portf);
	}

	/**
	 * Apply the variations for the capital for all of the companies
	 * 
	 */
	public void update() {
		Iterator<Entreprise> it = ents.iterator();
		while (it.hasNext()) {
			Entreprise e = it.next();
			EntrepriseManager entM = entsManag.get(e);
			entM.variationPrice();
			if (e.getMax() <= e.valeurAction()) {
				e.setMax(e.valeurAction());
			}
			if (e.getMin() >= e.valeurAction()) {
				e.setMin(e.valeurAction());
			}
		}
	}

	public ArrayList<Entreprise> getEnts() {
		return ents;
	}

	public ArrayList<TurboPut> getTurboPuts() {
		return turboPuts;
	}

	public void addTurboPut(TurboPut turboP) {
		logger.info("Achat d'un TurboPut : " + turboP.getNbVente() + " * " + turboP.getNomEnt() + " "
				+ turboP.getPxVente() + " €");
		turboPuts.add(turboP);
	}

	public ArrayList<TurboCall> getTurboCalls() {
		return turboCalls;
	}

	public ArrayList<FictUser> getUsers() {
		return users;
	}

	public HashMap<Entreprise, EntrepriseManager> getEntsManag() {
		return entsManag;
	}

	public HashMap<String, Integer> getTauxDomaine() {
		return tauxDomaine;
	}

	public void addTurboCall(TurboCall turboC) {
		logger.info("Achat d'un TurboCall : " + turboC.getNbAchat() + " * " + turboC.getNomEnt() + " "
				+ turboC.getPxAchat() + " €");
		turboCalls.add(turboC);
	}

	public User getU() {
		return u;
	}

	public Entreprise getEnt(String name) {
		Iterator<Entreprise> it = ents.iterator();
		Entreprise ent = null;
		while (it.hasNext()) {
			Entreprise e = it.next();
			if (e.getNom().equals(name)) {
				ent = e;
			}
		}
		return ent;
	}

	public void changTauxEntreprise(Entreprise e, int taux) {
		logger.info("Changement du taux d'entreprise de : " + e + " / taux = " + taux);
		Entreprise ent = getEnt(e.getNom());
		EntrepriseManager entM = entsManag.get(ent);
		entM.setTaux(taux);
	}

	public void changTauxDomaine(String domaine, int taux) {
		logger.info("Changement du taux du domaine : " + domaine + " / taux = " + taux);
		Iterator<Entreprise> it = ents.iterator();
		while (it.hasNext()) {
			Entreprise e = it.next();
			if (e.getDomaine() == domaine) {
				changTauxEntreprise(e, taux);
			}
		}
		getTauxDomaine().put(domaine, taux);
	}

	public int evsLength() {
		int length = 0;
		Iterator<Evenement> it = evs.iterator();
		while (it.hasNext()) {
			it.next();
			length++;
		}
		return length;
	}
	
	public ArrayList<Evenement> getEvs(){
		return evs;
	}

	/**
	 * Choose a random event.
	 * 
	 * @return an object of the class Evenement.
	 */
	public Evenement aleat() {
		Random rand = new Random();
		Iterator<Evenement> it = evs.iterator();
		int nbAleat = rand.nextInt(evsLength() + 1);
		while (nbAleat == 0) {
			nbAleat = rand.nextInt(evsLength());
		}
		Evenement ev = null;
		for (int i = 0; i < nbAleat; i++) {
			ev = it.next();
		}
		logger.info("Nouvelle évènement aléatoire : " + ev.getText());
		return ev;
	}

	/**
	 * Update the number of action of the fictionnal users.
	 */
	public void updateUsers() {
		Iterator<FictUser> it = users.iterator();
		while (it.hasNext()) {
			FictUser us = it.next();
			Random rand = new Random();
			int addsup = rand.nextInt(2);
			if (addsup == 0) {
				if (us.getNbActif() > 5) {
					int nb = rand.nextInt(5);
					us.setNbActif(us.getNbActif() - nb);
				} else {
					int nb = rand.nextInt(us.getNbActif());
					us.setNbActif(us.getNbActif() - nb);
				}
			} else {
				int nb = rand.nextInt(5);
				us.setNbActif(us.getNbActif() + nb);
			}
		}
	}

	public Historique getHist() {
		return hist;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		Factory.logger = logger;
		for(Entreprise ent : ents) {
			ent.setLogger(logger);
		}
	}

	public Profil getProf() {
		return prof;
	}
}
