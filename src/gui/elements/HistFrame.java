package gui.elements;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.Achat;
import data.Historique;
import data.Vente;
import process.Factory;

/**
 * The historic frame. It contains all of the transactions.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class HistFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static final Font TABLE_FONT = new Font("SansSerif", Font.PLAIN, 20);
	
	private Factory fact;
	
	private JTable tableVente = new JTable();
    private DefaultTableModel tableModelVente = new DefaultTableModel();
    private JScrollPane tableScrollPaneVente = new JScrollPane(tableVente);
    
    private JTable tableAchat = new JTable();
    private DefaultTableModel tableModelAchat = new DefaultTableModel();
    private JScrollPane tableScrollPaneAchat = new JScrollPane(tableAchat);
    
    public HistFrame(Factory fact) {
    	super("Historique des transactions");
    	this.fact = fact;
    	
    	Image logo;
		try {
			logo = ImageIO.read(new File("src/pictures/bmm.png"));
			setIconImage(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Color c = new Color(204, 202, 217);
    	Color couleur = new Color(237, 236, 218);
    	Dimension dim = new Dimension(950,450);
    	
    	tableVente.setModel(tableModelVente);
		tableScrollPaneVente.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tableScrollPaneVente.setBackground(c);
		
		tableAchat.setModel(tableModelAchat);
		tableScrollPaneAchat.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tableScrollPaneAchat.setBackground(c);
        
		initTable();
        
		tableVente.setFont(TABLE_FONT);
        tableVente.setBackground(couleur);
        tableVente.setForeground(Color.DARK_GRAY);
        
        tableAchat.setFont(TABLE_FONT);
        tableAchat.setBackground(couleur);
        tableAchat.setForeground(Color.DARK_GRAY);
        
        Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(1,2));
        contentPane.add(tableScrollPaneAchat);
        contentPane.add(tableScrollPaneVente);
        
        setBackground(c);
		setPreferredSize(dim);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
    public void initTable() {
    	Historique hist = fact.getHist();
    	
    	tableModelAchat.setRowCount(0);
        tableModelAchat.setColumnCount(0);
        tableModelAchat.addColumn("Historique des achats");
        Iterator<Achat> iterA = hist.getAchats().iterator();
        while (iterA.hasNext()) {
			Achat a = iterA.next();
			tableModelAchat.addRow(new String[]{a.toString()});
		}
        
        tableModelVente.setRowCount(0);
        tableModelVente.setColumnCount(0);
        tableModelVente.addColumn("HIstorique des ventes");
        Iterator<Vente> iterV = hist.getVentes().iterator();
        while (iterV.hasNext()) {
			Vente v = iterV.next();
			tableModelVente.addRow(new String[]{v.toString()});
		}
    }
    
    public void updateTableVente(Vente v) {
    	tableModelVente.addRow(new String[]{v.toString()});
    }
    
    public void updateTableAchat(Achat a) {
    	tableModelAchat.addRow(new String[]{a.toString()});
    }
}
