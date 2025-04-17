package supervisorGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import data.Entreprise;
import gui.elements.EntGraph;
import process.Factory;

/**
 * The selection frame for the user to select a company.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class SelectFrameS extends JFrame{

	private static final long serialVersionUID = 1L;

	private static final Font TABLE_FONT = new Font("SansSerif", Font.PLAIN, 16);
	
	private Factory fact;
	private Entreprise ent;
	
	protected EntGraph eg;
	
	protected JLabel select = new JLabel("Selectionner une entreprise", SwingConstants.CENTER);
	protected JPanel panel = new JPanel();
	protected JPanel panel2 = new JPanel();
	
	private static final Font LABEL_FONT = new Font(Font.DIALOG, Font.BOLD, 16);
	
	private JTable table = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JScrollPane tableScrollPane = new JScrollPane(table);
    
    public SelectFrameS(Factory fact, Entreprise ent) {
    	super("Sélection de l'entreprise");
    	this.fact = fact;
    	this.ent = ent;
    	
    	Image logo;
		try {
			logo = ImageIO.read(new File("src/pictures/bmm.png"));
			setIconImage(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	eg = new EntGraph(fact);
		
		Color c = new Color(204, 202, 217);
		Color couleur = new Color(237, 236, 218);
		
		select.setFont(LABEL_FONT);
    	
    	panel.setBackground(c);
    	panel.setLayout(new FlowLayout());
    	panel.add(select);
		
		Dimension dim = new Dimension(1250,450);
		
		table.setModel(tableModel);
		tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		tableScrollPane.setBackground(c);
        
		initTable();
        
		table.setFont(TABLE_FONT);
        table.setBackground(couleur);
        table.setForeground(Color.DARK_GRAY);
        
        table.getSelectionModel().addListSelectionListener(new TableAction());
        
        Dimension d1 = new Dimension(1250,400);
		panel2.setSize(d1);
		panel2.setBackground(c);
		GridLayout grid = new GridLayout(1,2);
		panel2.setLayout(grid);
		panel2.add(eg);
		panel2.add(tableScrollPane);
        
        Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
        contentPane.add(panel2, BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.SOUTH);
        
		setBackground(c);
		setPreferredSize(dim);
		setResizable(false);
		setVisible(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
    public void initTable() {
    	Iterator<Entreprise> it = fact.getEnts().iterator();
    	tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Entreprises");
        tableModel.addColumn("Prix d'un actif");
        tableModel.addColumn("Domaine");
        tableModel.addColumn("Nombre d'actifs");
		while (it.hasNext()) {
			Entreprise ent = it.next();
			tableModel.addRow(new String[]{String.valueOf(ent.getNumero())+"-"+ent.getNom(),ent.valeurAction() + " €",ent.getDomaine(),String.valueOf(ent.getNbActions())});
		}
    }
    
    public void updateTable() {
    	int lignes = tableModel.getRowCount();
        for (int i = 0; i < lignes; i++) {
        	String nomEnt = tableModel.getValueAt(i, 0).toString();
			while (!nomEnt.substring(0, 1).equals("-")) {
				nomEnt = nomEnt.substring(1);
			}
			nomEnt = nomEnt.substring(1);
			Entreprise e = fact.getEnt(nomEnt);
			tableModel.setValueAt(e.valeurAction(), i, 1);
        }
    }
    
    public void updateEnts() {
    	updateTable();
    	eg.updateEntGraph();
    }
    
    public Entreprise getEnt() {
    	return ent;
    }
    
    public void setEnt(Entreprise e) {
    	ent = e;
    }
    
    private class TableAction implements ListSelectionListener{            
        @Override
        public void valueChanged(ListSelectionEvent e)
        {
        	int selectedRow = table.getSelectedRow();
			String nomEnt = tableModel.getValueAt(selectedRow, 0).toString();
			while (!nomEnt.substring(0, 1).equals("-")) {
				nomEnt = nomEnt.substring(1);
			}
			nomEnt = nomEnt.substring(1);
			ent = fact.getEnt(nomEnt);
			setVisible(false);               
        }
    }
}

