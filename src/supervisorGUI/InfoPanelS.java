package supervisorGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Entreprise;
import process.Factory;

/**
 * This class represents the sub-window of the informations of the company.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class InfoPanelS extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Factory fact;

	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	
	protected JLabel nameEntreprise = new JLabel("Nom : ");
	protected JLabel name = new JLabel();
	protected JLabel domEntreprise = new JLabel("Domaine : ");
	protected JLabel dom = new JLabel();
	protected JLabel capEntreprise = new JLabel("Capital : ");
	protected JLabel cap = new JLabel();
	protected JLabel nbAction = new JLabel("Nombre d'actifs : ");
	protected JLabel nb = new JLabel();
	protected JLabel pxAction = new JLabel("Prix d'un actif : ");
	protected JLabel px = new JLabel();
	protected JLabel prixMin = new JLabel("Prix minimum : ");
	protected JLabel pMin = new JLabel();
	protected JLabel prixMax = new JLabel("Prix maximum : ");
	protected JLabel pMax = new JLabel();
	protected JLabel diffPrix = new JLabel("Différence prix min/max : ");
	protected JLabel dPrix = new JLabel();
	
	protected JPanel panelInfo = new JPanel();
	protected JPanel panelGrid1 = new JPanel();
	protected JPanel panelGrid2 = new JPanel();
	protected JLabel labelInfo = new JLabel("Information sur l'entreprise selectionnée", SwingConstants.CENTER);
	
	private Entreprise ent;
	
	private int max;
	private int min;
	
	public InfoPanelS(Factory fact, Entreprise ent) {
		this.fact = fact;
		this.ent = ent;
		max = ent.getMax();
		min = ent.getMin();
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		Dimension d = new Dimension(800,120);
		setPreferredSize(d);
		
		Dimension d1 = new Dimension(800,80);
		panelInfo.setSize(d1);
		panelInfo.setLayout(new BorderLayout());
		panelInfo.setBackground(c);
		
		Dimension d2 = new Dimension(240,80);
		panelGrid1.setSize(d2);
		GridLayout grid2 = new GridLayout(4,2);
		panelGrid1.setLayout(grid2);
		panelGrid1.setBackground(c);

		Dimension d3 = new Dimension(560,80);
		panelGrid2.setSize(d3);
		GridLayout grid3 = new GridLayout(4,2);
		panelGrid2.setLayout(grid3);
		panelGrid2.setBackground(c);
		
		Color borderColor = new Color(67, 67, 74);
		labelInfo.setBorder(BorderFactory.createLineBorder(borderColor, 2));
		panelGrid1.setBorder(BorderFactory.createLineBorder(borderColor, 2));
		panelGrid2.setBorder(BorderFactory.createLineBorder(borderColor, 2));
		
		name.setText(ent.getNom());
		dom.setText(ent.getDomaine());
		cap.setText(String.valueOf(ent.getCapital()) + " €");
		px.setText(String.valueOf(ent.valeurAction()) + " €");
		nb.setText(String.valueOf(ent.getNbActions()));
		pMax.setText(String.valueOf(max) + " €");
		pMin.setText(String.valueOf(min) + " €");
		dPrix.setText(String.valueOf(max-min) + " €" );
		
		nameEntreprise.setFont(LABEL_FONT);
		name.setFont(LABEL_FONT);
		domEntreprise.setFont(LABEL_FONT);
		dom.setFont(LABEL_FONT);
		capEntreprise.setFont(LABEL_FONT);
		cap.setFont(LABEL_FONT);
		nbAction.setFont(LABEL_FONT);
		nb.setFont(LABEL_FONT);
		pxAction.setFont(LABEL_FONT);
		px.setFont(LABEL_FONT);
		prixMin.setFont(LABEL_FONT);
		pMin.setFont(LABEL_FONT);
		prixMax.setFont(LABEL_FONT);
		pMax.setFont(LABEL_FONT);
		diffPrix.setFont(LABEL_FONT);
		dPrix.setFont(LABEL_FONT);
		
		labelInfo.setFont(LABEL_FONT2);
		
		panelGrid1.add(nameEntreprise);
		panelGrid1.add(name);
		
		panelGrid2.add(nbAction);
		panelGrid2.add(nb);
		
		panelGrid1.add(domEntreprise);
		panelGrid1.add(dom);
		
		panelGrid2.add(prixMin);
		panelGrid2.add(pMin);
		
		panelGrid1.add(capEntreprise);
		panelGrid1.add(cap);
		
		panelGrid2.add(prixMax);
		panelGrid2.add(pMax);
		
		panelGrid1.add(pxAction);
		panelGrid1.add(px);
		
		panelGrid2.add(diffPrix);
		panelGrid2.add(dPrix);
		
		panelInfo.add(panelGrid1, BorderLayout.WEST);
		panelInfo.add(panelGrid2, BorderLayout.CENTER);
		
		BorderLayout border= new BorderLayout();
		setLayout(border);
		
		add(labelInfo, BorderLayout.NORTH);
		add(panelInfo, BorderLayout.CENTER);
	}
	
	public void updateValue() {	
		name.setText(ent.getNom());
		dom.setText(ent.getDomaine());
		cap.setText(String.valueOf(ent.getCapital() + " €"));
		px.setText(String.valueOf(ent.valeurAction()) + " €");
		nb.setText(String.valueOf(ent.getNbActions()));
		max = ent.getMax();
		min = ent.getMin();
		pMax.setText(String.valueOf(max) + " €");
		pMin.setText(String.valueOf(min) + " €");
		dPrix.setText(String.valueOf(max-min) + " €" );
	}

	public void setEntreprise(Entreprise ent) {
		this.ent = ent;
	}
	
	public Factory getFact() {
		return fact;
	}
	
	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
}
