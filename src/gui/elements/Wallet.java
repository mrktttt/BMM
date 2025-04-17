package gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import data.Actif;
import data.Entreprise;
import process.Factory;

/**
 * This class represents the sub-window of the wallet.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class Wallet extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font TABLE_FONT = new Font("SansSerif", Font.PLAIN, 12);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	
	private JLabel wallet = new JLabel("Wallet", SwingConstants.CENTER);
	
	private JLabel capital = new JLabel("Capital : ");
	private JLabel cap = new JLabel();
	private JLabel nbActif = new JLabel("Nombre d'actifs : ");
	private JLabel nb = new JLabel();
	private JLabel valActif = new JLabel("Valeurs des actifs : ");
	private JLabel val = new JLabel();
	
	private JButton hist = new JButton("Historique");
	private JButton exit = new JButton("Sauvegarder et quitter");
	
	private JPanel button = new JPanel();
	private JPanel info = new JPanel();
	
	private HistFrame hf;
	
	private Factory fact;
	
	private boolean continu = true;
	
	private JTable table = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JScrollPane tableScrollPane = new JScrollPane(table);

	public Wallet(Factory fact) {
		this.fact=fact;
		hf = new HistFrame(fact);
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color couleur = new Color(237, 236, 218);
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		wallet.setFont(LABEL_FONT2);
		
		capital.setFont(LABEL_FONT);
		cap.setFont(LABEL_FONT);
		nbActif.setFont(LABEL_FONT);
		nb.setFont(LABEL_FONT);
		valActif.setFont(LABEL_FONT);
		val.setFont(LABEL_FONT);
		
		cap.setText(String.valueOf(fact.getU().getPrtF().getCapital()) + " €");
		nb.setText(String.valueOf(fact.getU().getPrtF().getNbActifs()));
		nb.setText(String.valueOf(getVal()) + " €");
		
		Color c1 = new Color(99, 99, 99);
		hist.setFont(BUTTON_FONT);
		hist.setForeground(Color.YELLOW);
		hist.setBackground(c1);
		exit.setFont(BUTTON_FONT);
		exit.setBackground(c1);
		exit.setForeground(Color.YELLOW);
		
		setLayout(new BorderLayout());
		
		GridLayout grid1 = new GridLayout(3,2);
		info.setLayout(grid1);
		info.setBackground(c);
		
		info.add(capital);
		info.add(cap);
		info.add(nbActif);
		info.add(nb);
		info.add(valActif);
		info.add(val);
		
		Dimension d = new Dimension(220,30);
		
		exit.setSize(d);
		hist.setSize(d);
		
		Dimension dim = new Dimension(250,450);
		setPreferredSize(dim);
		
		table.setFont(TABLE_FONT);
		
		table.setModel(tableModel);
		Color borderColor = new Color(67, 67, 74);
		tableScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tableScrollPane.setBorder(BorderFactory.createLineBorder(borderColor, 3));
		tableScrollPane.setBackground(c);
        
		updateTable();
        
        table.setBackground(couleur);
        table.setForeground(Color.DARK_GRAY);
        
        button.setLayout(new BorderLayout());
		button.setBackground(c);
		button.setPreferredSize(d);
		button.add(info, BorderLayout.NORTH);
		button.add(tableScrollPane, BorderLayout.CENTER);
		button.add(hist, BorderLayout.SOUTH);
        
		hist.addActionListener(new Historique());
		exit.addActionListener(new Quitter());
		
        add(wallet, BorderLayout.NORTH);
        add(button, BorderLayout.CENTER);
        add(exit, BorderLayout.SOUTH);
	}
	
	public void updateTable() {
		Iterator<Entreprise> it = fact.getU().getPrtF().getEntreprises().iterator();
		tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Entreprises");
        tableModel.addColumn("Nombre d'actifs");
		while (it.hasNext()) {
			Entreprise ent = it.next();
			tableModel.addRow(new String[]{ent.getNom(),String.valueOf(fact.getU().getNbActOfEnt(ent))});
		}
	}
	
	public void updateValue() {
		updateTable();
		cap.setText(String.valueOf(fact.getU().getPrtF().getCapital()) + " €");
		nb.setText(String.valueOf(fact.getU().getPrtF().getNbActifs()));
		val.setText(String.valueOf(getVal() + " €"));
	}
	
	public HistFrame getHF() {
		return hf;
	}
	
	public int getVal() {
		int som = 0;
		Iterator<Actif> it = fact.getU().getActifs().iterator();
		while (it.hasNext()) {
			Actif act = it.next();
			som = som + act.getEnt().valeurAction();
		}
		return som;
	}
	
	public boolean getCont() {
		return continu;
	}
	
	 private class Historique implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				hf.setVisible(true);
				hf.toFront();
		}
	    	
	 }
	 
	 private class Quitter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			continu = false;
		}
	    	
	 }
}