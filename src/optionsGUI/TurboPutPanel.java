package optionsGUI;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import data.Entreprise;
import process.Factory;
import process.TurboPut;

public class TurboPutPanel extends JPanel{

	/**
	 * This class represents the sub-window for buying a turboput.
	 * 
	 * @author RAZAFINONY TREUIL OUAMMOUR
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font TABLE_FONT = new Font("SansSerif", Font.PLAIN, 16);
	private static final Font FIELD_FONT = new Font(Font.MONOSPACED, Font.BOLD, 18);
	
	private JLabel entreprise = new JLabel("Choisir vos actifs", SwingConstants.CENTER);
	private JLabel vente = new JLabel("Poser un prix de vente", SwingConstants.CENTER);
	private JLabel prix = new JLabel("Prix d'un TurboPut : 100 €", SwingConstants.CENTER);
	private JLabel title = new JLabel("FAIRE UN TURBOPUT", SwingConstants.CENTER);
	private JLabel ventes = new JLabel("Nombre de ventes", SwingConstants.CENTER);
	
	private JTable table = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JScrollPane tableScrollPane = new JScrollPane(table);
	
    private JButton turboPut = new JButton("Acheter un TurboPut");
    
    private JTextField prixVente = new JTextField();
    private JTextField nbVente = new JTextField();
    
    private JPanel tablePanel = new JPanel();
    private JPanel pxPanel = new JPanel();
    private JPanel turboPanel = new JPanel();
    
    private Factory fact;
    private boolean open = false;

	public TurboPutPanel(Factory fact) {
    	this.fact = fact;
    	
    	Color borderColor1 = new Color(21, 32, 48);
    	setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
    	title.setBorder(BorderFactory.createLineBorder(borderColor1, 3));
    	
    	Color c = new Color(204, 202, 217);
		setBackground(c);
		
		entreprise.setFont(LABEL_FONT);
		vente.setFont(LABEL_FONT);
		prix.setFont(LABEL_FONT);
		title.setFont(LABEL_FONT2);
		turboPut.setFont(BUTTON_FONT);
		prixVente.setFont(FIELD_FONT);
		nbVente.setFont(FIELD_FONT);
		ventes.setFont(LABEL_FONT);
		
		Color c1 = new Color(99, 99, 99);
		turboPut.setBackground(c1);
		turboPut.setForeground(Color.YELLOW);
		
		setLayout(new BorderLayout());
		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBackground(c);
		pxPanel.setLayout(new GridLayout(4,1,10,10));
		pxPanel.setBackground(c);
		turboPanel.setLayout(new GridLayout(2,1,30,30));
		turboPanel.setBackground(c);
		
		pxPanel.add(vente);
		pxPanel.add(prixVente);
		pxPanel.add(ventes);
		pxPanel.add(nbVente);
		
		Color couleur = new Color(237, 236, 218);
		prixVente.setBackground(couleur);
		nbVente.setBackground(couleur);
		
		turboPanel.add(prix);
		turboPanel.add(turboPut);
		
		table.setFont(TABLE_FONT);
		table.setModel(tableModel);
		tableScrollPane.setBackground(c);
		
		updateTable();
		
		table.setBackground(couleur);
		table.setForeground(Color.DARK_GRAY);
		
		Color borderColor = new Color(67, 67, 74);
		
		tablePanel.setBorder(BorderFactory.createLineBorder(borderColor, 3));
		pxPanel.setBorder(BorderFactory.createLineBorder(borderColor, 3));
		turboPanel.setBorder(BorderFactory.createLineBorder(borderColor, 3));
		
		tablePanel.add(entreprise, BorderLayout.NORTH);
		tablePanel.add(tableScrollPane, BorderLayout.CENTER);
		
		Dimension dim = new Dimension(1200,210);
		setPreferredSize(dim);
		
		Dimension d1 = new Dimension(500,190);
        tablePanel.setSize(d1);
        
        Dimension d2 = new Dimension(300,190);
        pxPanel.setSize(d2);
        
        Dimension d3 = new Dimension(300,190);
        turboPanel.setSize(d3);
        
        add(pxPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        add(turboPanel, BorderLayout.EAST);
        add(title, BorderLayout.NORTH);
        
        turboPut.addActionListener(new Turbo());
    }
    
    public void updateTable() {
    	Iterator<Entreprise> it = fact.getU().getPrtF().getEntreprises().iterator();
		tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Entreprises");
        tableModel.addColumn("Domaines");
        tableModel.addColumn("Nombre d'actifs");
        tableModel.addColumn("Valeur des actifs");
		while (it.hasNext()) {
			Entreprise ent = it.next();
			tableModel.addRow(new String[]{ent.getNom(),ent.getDomaine(),String.valueOf(fact.getU().getNbActOfEnt(ent)),String.valueOf(ent.valeurAction())});
		}
    }
    
    public void update() {
    	int lignes = tableModel.getRowCount();
        for (int i = 0; i < lignes; i++) {
        	String nomEnt = tableModel.getValueAt(i, 0).toString();
			Entreprise e = fact.getEnt(nomEnt);
			tableModel.setValueAt(e.valeurAction(), i, 3);
        }
    }
    
    public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
    
    private class Turbo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (fact.getU().getPrtF().getCapital() >= 100) {
				fact.getU().getPrtF().setCapital(fact.getU().getPrtF().getCapital() - 100);
				int userSelectedRow = table.getSelectedRow();
				
				String nomEnt = tableModel.getValueAt(userSelectedRow, 0).toString();
				int nb = Integer.valueOf(nbVente.getText());
				int val = Integer.valueOf(prixVente.getText());
				
				TurboPut turboP = new TurboPut(nomEnt, nb, val);
				fact.addTurboPut(turboP);
			}
			setOpen(false);
		}
    	
    }
}
