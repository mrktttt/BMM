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
import process.TurboCall;

public class TurboCallPanel extends JPanel{

	/**
	 * This class represents the sub-window for buying a turbocall.
	 * 
	 * @author RAZAFINONY TREUIL OUAMMOUR
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font TABLE_FONT = new Font("SansSerif", Font.PLAIN, 16);
	private static final Font FIELD_FONT = new Font(Font.MONOSPACED, Font.BOLD, 18);
	
	private JLabel entreprise = new JLabel("Choisir une entreprise", SwingConstants.CENTER);
	private JLabel achat = new JLabel("Poser un prix d'achat", SwingConstants.CENTER);
	private JLabel prix = new JLabel("Prix d'un TurboCall : 100 €", SwingConstants.CENTER);
	private JLabel title = new JLabel("FAIRE UN TURBOCALL", SwingConstants.CENTER);
	private JLabel achats = new JLabel("Nombre d'achats", SwingConstants.CENTER);
	
	private JTable table = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JScrollPane tableScrollPane = new JScrollPane(table);
	
    private JButton turboCall = new JButton("Acheter un TurboCall");
    
    private JTextField prixAchat = new JTextField();
    private JTextField nbAchat = new JTextField();
    
    private JPanel tablePanel = new JPanel();
    private JPanel pxPanel = new JPanel();
    private JPanel turboPanel = new JPanel();
    
    private Factory fact;
    private boolean open = false;
    
    public TurboCallPanel(Factory fact) {
    	this.fact = fact;
    	
    	Color borderColor1 = new Color(21, 32, 48);
    	setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
    	title.setBorder(BorderFactory.createLineBorder(borderColor1, 3));
    	
    	Color c = new Color(204, 202, 217);
		setBackground(c);
		
		entreprise.setFont(LABEL_FONT);
		achat.setFont(LABEL_FONT);
		prix.setFont(LABEL_FONT);
		title.setFont(LABEL_FONT2);
		turboCall.setFont(BUTTON_FONT);
		prixAchat.setFont(FIELD_FONT);
		nbAchat.setFont(FIELD_FONT);
		achats.setFont(LABEL_FONT);
		
		Color c1 = new Color(99, 99, 99);
		turboCall.setBackground(c1);
		turboCall.setForeground(Color.YELLOW);
		
		setLayout(new BorderLayout());
		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBackground(c);
		pxPanel.setLayout(new GridLayout(4,1,10,10));
		pxPanel.setBackground(c);
		turboPanel.setLayout(new GridLayout(2,1,30,30));
		turboPanel.setBackground(c);
		
		pxPanel.add(achat);
		pxPanel.add(prixAchat);
		pxPanel.add(achats);
		pxPanel.add(nbAchat);
		
		Color couleur = new Color(237, 236, 218);
		prixAchat.setBackground(couleur);
		nbAchat.setBackground(couleur);
		
		turboPanel.add(prix);
		turboPanel.add(turboCall);
		
		table.setFont(TABLE_FONT);
		table.setModel(tableModel);
		tableScrollPane.setBackground(c);
		
		initTable();
		
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
        
        turboCall.addActionListener(new Turbo());
    }
    
    public void initTable() {
    	Iterator<Entreprise> iter = fact.getEnts().iterator();
		tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Entreprises");
        tableModel.addColumn("Prix des actifs");
        tableModel.addColumn("Dommaines");
        tableModel.addColumn("Nombre d'actifs");
        while (iter.hasNext()) {
        	Entreprise e = iter.next();
        	tableModel.addRow(new String[]{e.getNom(),String.valueOf(e.valeurAction()),e.getDomaine(),String.valueOf(e.getNbActions())});
        }	
    }
    
    public void update() {
    	int lignes = tableModel.getRowCount();
        for (int i = 0; i < lignes; i++) {
        	String nomEnt = tableModel.getValueAt(i, 0).toString();
			Entreprise e = fact.getEnt(nomEnt);
			tableModel.setValueAt(e.valeurAction(), i, 1);
			tableModel.setValueAt(e.getNbActions(), i, 3);
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
				int nb = Integer.valueOf(nbAchat.getText());
				int val = Integer.valueOf(prixAchat.getText());
				
				TurboCall turboC = new TurboCall(nomEnt, nb, val);
				fact.addTurboCall(turboC);
			}
			setOpen(false);
		}
    	
    }
}
