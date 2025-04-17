package gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import data.Entreprise;
import data.Evenement;
import process.Factory;
import process.Parametres;
import process.UserManager;

/**
 * The principal frame for the user mode.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class MainFrame extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	
	protected JPanel j1 = new JPanel();
	protected JPanel j2 = new JPanel();
	protected JPanel j3 = new JPanel();
	protected JPanel j4 = new JPanel();
	protected JPanel j5 = new JPanel();
	
	private Factory fact;
	private Entreprise ent;

	private AchatPanel ap;
	private DatePanel dp;
	private Graph g;
	private InfoPanel ip;
	private News n;
	private ProfilGUI p;
	private SearchPanel sp;
	private Wallet w;
	
	public MainFrame(Factory fact) {
		super("BMM");
		this.fact=fact;
		Iterator<Entreprise> it = fact.getEnts().iterator();
		ent = it.next();
		
		Image logo;
		try {
			logo = ImageIO.read(new File("src/pictures/bmm.png"));
			setIconImage(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dp = new DatePanel();
		g = new Graph(ent, fact);
		ip = new InfoPanel(fact,ent);
		n = new News();
		p = new ProfilGUI(fact);
		sp = new SearchPanel(fact,ent);
		w = new Wallet(fact);
		ap = new AchatPanel(fact,ent,w.getHF());
		
		Color c = new Color(204, 202, 217);
		
		Dimension d1 = new Dimension(800,570);
		j1.setLayout(new BorderLayout());
		j1.add(g, BorderLayout.CENTER);
		j1.add(ip, BorderLayout.SOUTH);
		j1.add(ap, BorderLayout.WEST);
		j1.setBackground(c);
		j1.setPreferredSize(d1);
		
		Dimension d2 = new Dimension(300,650);
		j2.setLayout(new BorderLayout());
		j2.add(dp, BorderLayout.NORTH);
		j2.add(n, BorderLayout.CENTER);
		j2.setBackground(c);
		j2.setPreferredSize(d2);
		
		Dimension d3 = new Dimension(250,650);
		j3.setLayout(new BorderLayout());
		j3.add(p, BorderLayout.NORTH);
		j3.add(w, BorderLayout.CENTER);
		j3.setBackground(c);
		j3.setPreferredSize(d3);
		
		Dimension d4 = new Dimension(800,650);
		j4.setLayout(new BorderLayout());
		j4.add(sp, BorderLayout.NORTH);
		j4.add(j1, BorderLayout.CENTER);
		j4.setBackground(c);
		j4.setPreferredSize(d4);
		
		Dimension d5 = new Dimension(1100,650);
		j5.setLayout(new BorderLayout());
		j5.add(j2, BorderLayout.EAST);
		j5.add(j4, BorderLayout.CENTER);
		j5.setBackground(c);
		j5.setPreferredSize(d5);
		
		Dimension dim = new Dimension(1350,650);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(j3, BorderLayout.EAST);
		contentPane.add(j5, BorderLayout.CENTER);
		
		setBackground(c);
		setPreferredSize(dim);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setEnt(Entreprise ent) {
		this.ent = ent;
		g.setEnt(ent);
		ap.setEnt(ent);
		ip.setEntreprise(ent);
	}
	
	public void ajoutJournal(String evenement, boolean influence) throws BadLocationException {
		n.addNews(evenement, influence);	
	}
	
	/**
	 * The entry point of the user mode simulation.
	 */
	public void run() {
		int time = 0;
		UserManager uM = new UserManager(fact.getU(),fact.getLogger());
		boolean continu = w.getCont();
		while (time<=999999999 && continu) {
			try {
				Thread.sleep(Parametres.INITIAL_CHRONO_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			if( (time % 20) == 0 && time!=0) {
				Evenement ev = fact.aleat();
				useEvent(ev);
				try {
					ajoutJournal(ev.getText(), ev.isInfluence());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			if( (time % 10) == 0 && time!=0) {
				List<Entreprise> ents = fact.getEnts();
				for(Entreprise entre : ents) {
					if(fact.getEntsManag().get(entre).getTaux() > 5) {
						fact.changTauxEntreprise(entre,fact.getEntsManag().get(entre).getTaux() - 1);
					}
					if(fact.getEntsManag().get(entre).getTaux() < 5) {
						fact.changTauxEntreprise(entre,fact.getEntsManag().get(entre).getTaux() + 1);
					}
					if(entre.getMult() > 1) {
						entre.setMult(entre.getMult() - 1);
					}
				}
			}
			continu = w.getCont();
			int size = ap.getTaille();
			sp.updateSelect();
			sp.updateEntreprise();
			g.setTaille(size);
			fact.update();
			g.updateGraph();
			p.update();
			w.updateValue();
			ip.updateValue();
			ap.setEnt(ent);
			ap.getOf().getSp().update();
			ap.getOf().getTpp().update();
			ap.getOf().getTcp().update();
			g.setEnt(ent);
			ip.setEntreprise(ent);
			ent = sp.getEnt();
			dp.updateDate();
			if (!ap.getOf().getSp().isOpen() || !ap.getOf().getTpp().isOpen() || !ap.getOf().getTcp().isOpen()) {
				ap.getOf().updateVisible();
			}
			uM.useTurboPut(fact,w.getHF());
			uM.useTurboCall(fact,w.getHF());
			
			time++;
		}
		if (!continu) {
			dispose();
		}
	}
	
	/**
	 * Apply the effect of an event in the simulation
	 * 
	 * @param ev
	 *          A random event
	 */
	public void useEvent(Evenement ev) {
		switch (ev.getType()) {
			case 1 :
				//pour changer le taux d'un entreprise "Tesla" :
				//fact.changTauxEntreprise(fact.getEnt("Tesla"),6);
				
				//pour changer le taux des entreprises d'un domaine "medical" :
				//fact.changTauxDomaine("medical",6);
				
				//pour changer le multiplicateur d'une entreprise "Toyota" :
				//fact.getEnt("Toyota").setMult(8);
				
				//pour obtenir le taux d'un domaine "medical" :
				//int tauxDom = fact.getTauxDomaine().get("medical");
				
				//pour obtenir le taux d'une entreprise "Tesla" :
				//int tauxEnt = fact.getEntsManag().get(fact.getEnt("Tesla")).getTaux();
				fact.changTauxEntreprise(fact.getEnt("Nvidia"), 3);
				break;
			case 2:
				fact.changTauxDomaine("Informatique", 6);
				fact.changTauxDomaine("Mobilité", 4);
				break;
			case 3:
				fact.changTauxDomaine("Mobilité", 3);
				break;
			case 4:
				break;
			case 5:
				fact.changTauxDomaine("Energie", 8);
				break;
			case 6:
				fact.changTauxEntreprise(fact.getEnt("Mercedes"), 6);
				break;
			case 7:
				fact.changTauxEntreprise(fact.getEnt("Ferrari"), 3);
				fact.getEnt("Ferrari").setMult(2);
				break;
			case 8:
				fact.changTauxEntreprise(fact.getEnt("Toyota"), 4);
				break;
			case 9:
				fact.changTauxEntreprise(fact.getEnt("Microsoft"), 7);
				break;
			case 10:
				fact.changTauxDomaine("Informatique", 7);
				break;
			case 11:
				fact.changTauxEntreprise(fact.getEnt("Google"), 4);
				break;
			case 12:
				fact.changTauxEntreprise(fact.getEnt("Samsung"), 7);
				fact.getEnt("Samsung").setMult(2);
				break;
			case 13:
				fact.changTauxEntreprise(fact.getEnt("Sony"), 2);
				break;
			case 14:
				fact.changTauxEntreprise(fact.getEnt("Apple"), 4);
				break;
			case 15:
				fact.changTauxEntreprise(fact.getEnt("PetroChina"), 3);
				break;
			case 16:
				fact.changTauxEntreprise(fact.getEnt("Groupe PSA"), 7);
				break;
			case 17:
				fact.changTauxEntreprise(fact.getEnt("Groupe Volkswagen"), 3);
				break;
			case 18:
				fact.changTauxEntreprise(fact.getEnt("BMW"), 4);
				fact.getEnt("BMW").setMult(2);
				break;
			case 19:
				fact.changTauxEntreprise(fact.getEnt("GM"), 8);
				break;
			case 20:
				fact.changTauxEntreprise(fact.getEnt("BYD"), 4);
				break;
			case 21:
				fact.changTauxEntreprise(fact.getEnt("Renault"), 4);
				break;
			case 22:
				fact.changTauxEntreprise(fact.getEnt("Toyota"), 6);
				fact.changTauxEntreprise(fact.getEnt("Sony"), 6);
				break;
				
		}
	}
	
	public Factory getFact() {
		return fact;
	}
	
	public Graph getG() {
		return g;
	}
	
	public News getN() {
		return n;
	}
	
	public SearchPanel getSP() {
		return sp;
	}
}
