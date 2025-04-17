package supervisorGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import data.Entreprise;
import process.ChartManager;
import process.Factory;
import process.Parametres;

/**
 * This class represents the sub-window of the chart.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class GraphS extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private ChartPanel pxActif;

	private int taille = Parametres.INITIAL_GRAPH_SIZE;
	
	private JFreeChart chart;
	
	private Factory fact;
	
	private HashMap<Entreprise,ChartManager> ents = new HashMap<Entreprise,ChartManager>();
	
	private Entreprise ent;
	
	public GraphS(Entreprise ent, Factory fact) {
		this.fact=fact;
		this.ent = ent;
		
		Iterator<Entreprise> it = fact.getEnts().iterator();
		while(it.hasNext()) {
			Entreprise e = it.next();
			ChartManager ct = new ChartManager();
			ents.put(e, ct);
		}
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		Color coul = new Color(235, 252, 235);
		
		Dimension d = new Dimension(650,450);
		setPreferredSize(d);
		
		BorderLayout border = new BorderLayout();
		setLayout(border);
		
		ChartManager chartManager = new ChartManager();
		chart = chartManager.mainGraph(taille);
		chart.setBackgroundPaint(coul);
		pxActif = new ChartPanel(chart);
		add(pxActif, BorderLayout.CENTER);
	}
	
	
	
	public void updateGraph() {
		removeAll();
		Color coul = new Color(235, 252, 235);
		Iterator<Entreprise> it = fact.getEnts().iterator();
		while(it.hasNext()) {
			Entreprise e = it.next();
			ChartManager chartM = ents.get(e);
			chartM.updateP(e.valeurAction());
		}
		ChartManager chartManager = ents.get(ent);
		chart = chartManager.mainGraph(taille);
		chart.setBackgroundPaint(coul);
		pxActif = new ChartPanel(chart);
		add(pxActif, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void setEnt(Entreprise ent) {
		this.ent = ent;
	}
	
	public void setTaille (int taille) {
		this.taille = taille;
	}
	
	public void setEnts(HashMap<Entreprise,ChartManager> hm) {
		ents = hm;
	}
	
	public HashMap<Entreprise,ChartManager> getEnts(){
		return ents;
	}
}
