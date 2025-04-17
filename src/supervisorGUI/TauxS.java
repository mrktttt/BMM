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
 * This class represents the sub-window for more informations about the company.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class TauxS extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	private static final Font LABEL_FONT3 = new Font(Font.SANS_SERIF, Font.BOLD, 16);

	protected JLabel infos = new JLabel("INFOS SUP", SwingConstants.CENTER);
	protected JLabel tauxE = new JLabel("Taux : ");
	protected JLabel tE = new JLabel();
	protected JLabel multE = new JLabel("Multiplicateur : ");
	protected JLabel mE = new JLabel();
	protected JLabel tauxD = new JLabel("Taux : ");
	protected JLabel tD = new JLabel();
	protected JLabel ent = new JLabel("Entreprise", SwingConstants.CENTER);
	protected JLabel dom = new JLabel("Domaine", SwingConstants.CENTER);
	
	protected JPanel grid = new JPanel();
	protected JPanel panelGrid1 = new JPanel();
	protected JPanel panelGrid2 = new JPanel();
	protected JPanel panelBorder1 = new JPanel();
	protected JPanel panelBorder2 = new JPanel();
	
	private Factory fact;
	private Entreprise e;
	
	public TauxS(Factory fact, Entreprise e) {
		this.fact=fact;
		this.e=e;
		
		Dimension dim = new Dimension(250,250);
		setPreferredSize(dim);
		
		infos.setFont(LABEL_FONT2);
		ent.setFont(LABEL_FONT3);
		dom.setFont(LABEL_FONT3);
		tauxE.setFont(LABEL_FONT);
		tE.setFont(LABEL_FONT);
		multE.setFont(LABEL_FONT);
		mE.setFont(LABEL_FONT);
		tauxD.setFont(LABEL_FONT);
		tD.setFont(LABEL_FONT);
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		BorderLayout border = new BorderLayout();
		setLayout(border);
		
		Dimension d = new Dimension(250,200);
		grid.setSize(d);
		GridLayout gridL = new GridLayout(2,1);
		grid.setLayout(gridL);
		grid.setBackground(c);
		
		
		Dimension d1 = new Dimension(250,50);
		panelGrid1.setSize(d1);
		panelGrid2.setSize(d1);
		GridLayout grid1 = new GridLayout(2,2);
		GridLayout grid2 = new GridLayout(1,2);
		panelGrid1.setLayout(grid1);
		panelGrid1.setBackground(c);
		panelGrid2.setLayout(grid2);
		panelGrid2.setBackground(c);
		
		Dimension d2 = new Dimension(250,100);
		panelBorder1.setSize(d2);
		panelBorder2.setSize(d2);
		panelBorder1.setLayout(new BorderLayout());
		panelBorder1.setBackground(c);
		panelBorder2.setLayout(new BorderLayout());
		panelBorder2.setBackground(c);
		
		tE.setText(String.valueOf(fact.getEntsManag().get(e).getTaux()));
		mE.setText(String.valueOf(e.getMult()));
		tD.setText(String.valueOf(fact.getTauxDomaine().get(e.getDomaine())));
		
		panelGrid1.add(tauxE);
		panelGrid1.add(tE);
		panelGrid1.add(multE);
		panelGrid1.add(mE);
		panelGrid2.add(tauxD);
		panelGrid2.add(tD);
		
		panelBorder1.add(ent, BorderLayout.NORTH);
		panelBorder1.add(panelGrid1, BorderLayout.CENTER);
		
		panelBorder2.add(dom, BorderLayout.NORTH);
		panelBorder2.add(panelGrid2, BorderLayout.CENTER);
		
		grid.add(panelBorder1);
		grid.add(panelBorder2);
		
		add(infos, BorderLayout.NORTH);
		add(grid, BorderLayout.CENTER);
	}
	
	public void setEntreprise(Entreprise e) {
		this.e = e;
	}
	
	public void update() {
		tE.setText(String.valueOf(fact.getEntsManag().get(e).getTaux()));
		mE.setText(String.valueOf(e.getMult()));
		tD.setText(String.valueOf(fact.getTauxDomaine().get(e.getDomaine())));
	}
}
