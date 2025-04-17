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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import data.Actif;
import data.Entreprise;
import data.User;
import process.Factory;

public class SwapPanel extends JPanel{

	/**
	 * This class represents the sub-window for buying a swap.
	 * 
	 * @author RAZAFINONY TREUIL OUAMMOUR
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font TABLE_FONT = new Font("SansSerif", Font.PLAIN, 12);
	
	private JLabel entreprise = new JLabel("Choisir vos actifs", SwingConstants.CENTER);
	private JLabel echange = new JLabel("Echanges possibles", SwingConstants.CENTER);
	private JLabel nombre = new JLabel("0", SwingConstants.CENTER);
	private JLabel title = new JLabel("FAIRE UN SWAP", SwingConstants.CENTER);
	private JLabel nombreEnt = new JLabel("Nombre d'entreprises à échanger", SwingConstants.CENTER);
	
	private JTable entTable = new JTable();
    private DefaultTableModel entTableModel = new DefaultTableModel();
    private JScrollPane entTableScrollPane = new JScrollPane(entTable);
    
    private JTable echangeTable = new JTable();
    private DefaultTableModel echangeTableModel = new DefaultTableModel();
    private JScrollPane echangeTableScrollPane = new JScrollPane(echangeTable);
    
    private JButton plus = new JButton("+");
    private JButton moins = new JButton("-");
    private JButton swap = new JButton("Swap");
    
    private JPanel entPanel = new JPanel();
    private JPanel echangePanel = new JPanel();
    private JPanel centralPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    
    private Factory fact;
    private boolean open = false;

	public SwapPanel(Factory fact) {
    	this.fact = fact;
    	
    	Color borderColor1 = new Color(21, 32, 48);
    	setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
    	title.setBorder(BorderFactory.createLineBorder(borderColor1, 3));
    	
    	Color c = new Color(204, 202, 217);
		setBackground(c);
		
		entreprise.setFont(LABEL_FONT);
		echange.setFont(LABEL_FONT);
		nombre.setFont(LABEL_FONT);
		nombreEnt.setFont(LABEL_FONT);
		plus.setFont(BUTTON_FONT);
		moins.setFont(BUTTON_FONT);
		swap.setFont(BUTTON_FONT);
		title.setFont(LABEL_FONT2);
		
		Color c1 = new Color(99, 99, 99);
		plus.setBackground(c1);
		moins.setBackground(c1);
		swap.setBackground(c1);
		plus.setForeground(Color.YELLOW);
		moins.setForeground(Color.YELLOW);
		swap.setForeground(Color.YELLOW);
		
		
		setLayout(new BorderLayout());
		centralPanel.setLayout(new GridLayout(4,1));
		centralPanel.setBackground(c);
		echangePanel.setLayout(new BorderLayout());
		echangePanel.setBackground(c);
		entPanel.setLayout(new BorderLayout());
		entPanel.setBackground(c);
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.setBackground(c);
		
		buttonPanel.add(moins);
		buttonPanel.add(plus);
		
		centralPanel.add(buttonPanel);
		centralPanel.add(nombreEnt);
		centralPanel.add(nombre);
		centralPanel.add(swap);
		
		Color borderColor = new Color(67, 67, 74);
		
		entTable.setFont(TABLE_FONT);
		echangeTable.setFont(TABLE_FONT);
		
		entTable.setModel(entTableModel);
		entTableScrollPane.setBackground(c);
		
		echangeTable.setModel(echangeTableModel);
		echangeTableScrollPane.setBackground(c);
		
		initTables();
		
		Color couleur = new Color(237, 236, 218);
        
		entTable.setBackground(couleur);
        entTable.setForeground(Color.DARK_GRAY);
		
        echangeTable.setBackground(couleur);
        echangeTable.setForeground(Color.DARK_GRAY);
        
        centralPanel.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        
        entPanel.add(entTableScrollPane, BorderLayout.CENTER);
        entPanel.add(entreprise, BorderLayout.NORTH);
        entPanel.setBorder(BorderFactory.createLineBorder(borderColor, 3));

        echangePanel.add(echangeTableScrollPane, BorderLayout.CENTER);
        echangePanel.add(echange, BorderLayout.NORTH);
        echangePanel.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        
        Dimension dim = new Dimension(1200,210);
        
        Dimension d1 = new Dimension(450,190);
        echangePanel.setSize(d1);
        
        Dimension d2 = new Dimension(450,190);
        entPanel.setSize(d2);
        
        Dimension d3 = new Dimension(300,190);
        centralPanel.setSize(d3);
        
        add(entPanel, BorderLayout.WEST);
        add(centralPanel, BorderLayout.CENTER);
        add(echangePanel, BorderLayout.EAST);
        add(title, BorderLayout.NORTH);
        
        setPreferredSize(dim);
        
        plus.addActionListener(new Plus());
        moins.addActionListener(new Moins());
        swap.addActionListener(new Swap());
    }
    
    public void initTables() {
    	Iterator<Entreprise> it = fact.getU().getPrtF().getEntreprises().iterator();
		entTableModel.setRowCount(0);
        entTableModel.setColumnCount(0);
        entTableModel.addColumn("Entreprises");
        entTableModel.addColumn("Valeur des actifs");
        entTableModel.addColumn("Nombre d'actifs");
		while (it.hasNext()) {
			Entreprise ent = it.next();
			entTableModel.addRow(new String[]{ent.getNom(),String.valueOf(ent.valeurAction()),String.valueOf(fact.getU().getNbActOfEnt(ent))});
		}
		
		Iterator<Entreprise> iter = fact.getEnts().iterator();
		echangeTableModel.setRowCount(0);
        echangeTableModel.setColumnCount(0);
        echangeTableModel.addColumn("Entreprises");
        echangeTableModel.addColumn("Prix des actifs");
        echangeTableModel.addColumn("Domaines");
        while (iter.hasNext()) {
        	Entreprise e = iter.next();
        	echangeTableModel.addRow(new String[]{e.getNom(),String.valueOf(e.valeurAction()),e.getDomaine()});
        }	
    }
    
    public void updateTables() {
    	Iterator<Entreprise> it = fact.getU().getPrtF().getEntreprises().iterator();
		entTableModel.setRowCount(0);
        entTableModel.setColumnCount(0);
        entTableModel.addColumn("Entreprises");
        entTableModel.addColumn("Valeur des actifs");
        entTableModel.addColumn("Nombre d'actifs");
		while (it.hasNext()) {
			Entreprise ent = it.next();
			entTableModel.addRow(new String[]{ent.getNom(),String.valueOf(ent.valeurAction()),String.valueOf(fact.getU().getNbActOfEnt(ent))});
		}
    }
    
    public void update() {
    	int lignes1 = entTableModel.getRowCount();
        for (int i = 0; i < lignes1; i++) {
        	String nomEnt = entTableModel.getValueAt(i, 0).toString();
			Entreprise e = fact.getEnt(nomEnt);
			entTableModel.setValueAt(e.valeurAction(), i, 1);
        }
        
        int lignes2 = echangeTableModel.getRowCount();
        for (int i = 0; i < lignes2; i++) {
        	String nomEnt = echangeTableModel.getValueAt(i, 0).toString();
			Entreprise e = fact.getEnt(nomEnt);
			echangeTableModel.setValueAt(e.valeurAction(), i, 1);
        }
    }
    
    public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
    
    private class Plus implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			nombre.setText(String.valueOf(1+Integer.valueOf(nombre.getText())));
		}
    	
    }
    
    private class Moins implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Integer.valueOf(nombre.getText())>0) {
				nombre.setText(String.valueOf(Integer.valueOf(nombre.getText())-1));
			}
		}
    	
    }
    
    private class Swap implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int nb = Integer.valueOf(nombre.getText());
			
			int userSelectedRow = entTable.getSelectedRow();
			String userNomEnt = entTableModel.getValueAt(userSelectedRow, 0).toString();
			Entreprise userEnt = fact.getEnt(userNomEnt);
			
			int echangeSelectedRow = echangeTable.getSelectedRow();
			String echangeNomEnt = echangeTableModel.getValueAt(echangeSelectedRow, 0).toString();
			Entreprise echangeEnt = fact.getEnt(echangeNomEnt);
			
			User u = fact.getU();
			
			int i =0;
			
			while (i<nb && echangeEnt.getNbActions()>0 && u.getPrtF().getEntreprises().contains(userEnt) && u.actifOfEntreprise(userEnt)!=null) {
				Actif echangeA = new Actif(echangeEnt.valeurAction(),echangeEnt);
				u.addActif(echangeA);
				echangeEnt.setNbActions(echangeEnt.getNbActions()-1);
				if(!echangeEnt.getUsers().containsValue(u)) {
					echangeEnt.addUser(u);
				}
				if (!u.getPrtF().getEntreprises().contains(echangeEnt)) {
					u.getPrtF().addEntreprise(echangeEnt);
				}
				u.getPrtF().setNbActifs(u.getPrtF().getNbActifs()+1);
				
				u.removeActif(u.actifOfEntreprise(userEnt));
				userEnt.setNbActions(userEnt.getNbActions()+1);
				if(u.actifOfEntreprise(userEnt)==null) {
					userEnt.removeUser(u);
					u.getPrtF().removeEntreprise(userEnt);
				}
				u.getPrtF().setNbActifs(u.getPrtF().getNbActifs()-1);
				
				i++;
			}
			
			fact.getLogger().info(nb + " swaps effectués : " + userNomEnt + " contre " + echangeNomEnt);
			
			setOpen(false);
		}
    	
    }

}
