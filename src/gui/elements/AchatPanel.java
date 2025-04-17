package gui.elements;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import data.Achat;
import data.Entreprise;
import data.Vente;
import optionsGUI.OptionFrame;
import process.Factory;
import process.Parametres;
import process.UserManager;

/**
 * This class represents the sub-window of the buying/selling control.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class AchatPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 16);
	private static final Font FIELD_FONT = new Font(Font.MONOSPACED, Font.BOLD, 18);
	
	protected JButton buyActif = new JButton("Acheter");
	protected JButton sellActif = new JButton("Vendre");
	protected JButton plus = new JButton("Autres options");
	protected JButton zoom = new JButton("Zoom +");
	protected JButton dezoom = new JButton("Zoom -");
	
	private int taille = Parametres.INITIAL_GRAPH_SIZE;;
	
	protected JTextField nombre = new JTextField();
	
	protected JLabel option = new JLabel("Options d'achats", SwingConstants.CENTER);
	protected JLabel ordre = new JLabel("Placer un ordre", SwingConstants.CENTER);
	
	protected JPanel b1 = new JPanel();
	protected JPanel b2 = new JPanel();
	protected JPanel b3 = new JPanel();
	protected JPanel b4 = new JPanel();
	protected JPanel b5 = new JPanel();
	protected JPanel b6 = new JPanel();
	protected JPanel b7 = new JPanel();
	
	
	protected JPanel g1 = new JPanel();
	protected JPanel g2 = new JPanel();
	protected JPanel g3 = new JPanel();
	protected JPanel g4 = new JPanel();
	protected JPanel g5 = new JPanel();
	protected JPanel g6 = new JPanel();
	protected JPanel g7 = new JPanel();
	
	protected OptionFrame of;
	
	private Factory fact;
	private Entreprise ent;
	private UserManager uM;
	private HistFrame hf;
	
	public AchatPanel(Factory fact, Entreprise ent, HistFrame hf) {
		this.fact=fact;
		this.ent=ent;
		this.hf=hf;
		uM = new UserManager(fact.getU(),fact.getLogger());
		of = new OptionFrame(fact);
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color couleur = new Color(237, 236, 218);
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		GridLayout grid = new GridLayout(1,1);
		
		Dimension d = new Dimension(148,40);
		g1.setPreferredSize(d);
		g2.setPreferredSize(d);
		g3.setPreferredSize(d);
		g4.setPreferredSize(d);
		g5.setPreferredSize(d);
		g6.setPreferredSize(d);
		g7.setPreferredSize(d);
		
		g1.setLayout(grid);
		g1.setBackground(c);
		g1.add(nombre);
		
		g2.setLayout(grid);
		g2.setBackground(c);
		g2.add(ordre);
		
		g3.setLayout(grid);
		g3.setBackground(c);
		g3.add(buyActif);
		
		g4.setLayout(grid);
		g4.setBackground(c);
		g4.add(sellActif);
		
		g5.setLayout(grid);
		g5.setBackground(c);
		g5.add(zoom);
		
		g6.setLayout(grid);
		g6.setBackground(c);
		g6.add(dezoom);
		
		g7.setLayout(grid);
		g7.setBackground(c);
		g7.add(plus);
		
		b1.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b1.setBackground(c);
		b1.add(g1);
		
		b2.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b2.setBackground(c);
		b2.add(g2);
		
		b3.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b3.setBackground(c);
		b3.add(g3);
		
		b4.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b4.setBackground(c);
		b4.add(g4);
		
		b5.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b5.setBackground(c);
		b5.add(g5);
		
		b6.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b6.setBackground(c);
		b6.add(g6);
		
		b7.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b7.setBackground(c);
		b7.add(g7);
		
		GridLayout grid2 = new GridLayout(8,1);
		setLayout(grid2);
		
		Dimension dim = new Dimension(150,450);
		setPreferredSize(dim);
		
		ordre.setFont(LABEL_FONT2);
		option.setFont(LABEL_FONT2);
		buyActif.setFont(BUTTON_FONT);
		sellActif.setFont(BUTTON_FONT);
		plus.setFont(BUTTON_FONT);
		zoom.setFont(BUTTON_FONT);
		dezoom.setFont(BUTTON_FONT);
		nombre.setFont(FIELD_FONT);
		
		nombre.setBackground(couleur);
		
		Color c1 = new Color(99, 99, 99);
		
        buyActif.setBackground(c1);
        buyActif.setForeground(Color.YELLOW);
        
        sellActif.setBackground(c1);
        sellActif.setForeground(Color.YELLOW);
        
        plus.setBackground(c1);
        plus.setForeground(Color.YELLOW);
        
        zoom.setBackground(c1);
        zoom.setForeground(Color.YELLOW);
        
        dezoom.setBackground(c1);
        dezoom.setForeground(Color.YELLOW);
        
        add(option);
        add(nombre);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        
        buyActif.addActionListener(new BuyActif());
        sellActif.addActionListener(new SellActif());
        plus.addActionListener(new Opt());
        zoom.addActionListener(new Zoom());
		dezoom.addActionListener(new Dezoom());
	}
	
	public Factory getFact() {
		return fact;
	}
	
	public void setEnt(Entreprise ent) {
		this.ent=ent;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public void setTaille(int i) {
		taille = i;
	}
	
	public OptionFrame getOf() {
		return of;
	}
	
	private class BuyActif implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for(int i=0 ; i<Integer.valueOf(nombre.getText()) ; i++) {
				Achat a = uM.achat(ent);
				if(a!=null) {
					hf.updateTableAchat(a);
				}
			}
		}
	}
	
	private class SellActif implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for(int i=0 ; i<Integer.valueOf(nombre.getText()) ; i++) {
				Vente v = uM.vente(ent);
				if(v!=null) {
					hf.updateTableVente(v);
				}
			}
		}
	}
	
	private class Opt implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			of.getSp().setOpen(true);
			of.getTpp().setOpen(true);
			of.getTcp().setOpen(true);
			of.updateVisible();
			of.toFront();
		}
	}
	
	private class Zoom implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (taille == Parametres.GRAPH_SIZE_2) {
				setTaille(Parametres.GRAPH_SIZE_1);
			}
			if (taille == Parametres.GRAPH_SIZE_3) {
				setTaille(Parametres.GRAPH_SIZE_2);
			}
		}
	}
	
	private class Dezoom implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (taille == Parametres.GRAPH_SIZE_2) {
				setTaille(Parametres.GRAPH_SIZE_3);
			}
			if (taille == Parametres.GRAPH_SIZE_1) {
				setTaille(Parametres.GRAPH_SIZE_2);
			}
		}
	}
}
