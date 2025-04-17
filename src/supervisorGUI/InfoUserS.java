package supervisorGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import data.FictUser;
import process.Factory;

/**
 * This class represents the sub-window for the fictionnals users.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class InfoUserS extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font TABLE_FONT = new Font("SansSerif", Font.PLAIN, 12);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	
	private JLabel user = new JLabel("Utilisateurs", SwingConstants.CENTER);
	
	private JButton exit = new JButton("Sauvegarder et quitter");
	
	private Factory fact;
	
	private boolean continu = true;
	
	private JTable table = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JScrollPane tableScrollPane = new JScrollPane(table);

	public InfoUserS(Factory fact) {
		this.fact=fact;
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color couleur = new Color(237, 236, 218);
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		user.setFont(LABEL_FONT2);
		
		Color c1 = new Color(99, 99, 99);
		
		exit.setFont(BUTTON_FONT);
		exit.setBackground(c1);
		exit.setForeground(Color.YELLOW);
		
		setLayout(new BorderLayout());
		
		Dimension d = new Dimension(220,30);
		
		exit.setSize(d);
		
		Dimension dim = new Dimension(250,400);
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
        
		exit.addActionListener(new Quitter());
		
        add(user, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(exit, BorderLayout.SOUTH);
	}
	
	public void updateTable() {
		Iterator<FictUser> it = fact.getUsers().iterator();
		tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Utilisateurs");
        tableModel.addColumn("Nombre d'actifs");
        tableModel.addRow(new String[]{fact.getU().getProf().getNom()+" "+fact.getU().getProf().getPrenom(),String.valueOf(fact.getU().getPrtF().getNbActifs())});
		while (it.hasNext()) {
			FictUser us = it.next();
			tableModel.addRow(new String[]{us.getNom(),String.valueOf(us.getNbActif())});
		}
		fact.updateUsers();
	}
	public boolean getCont() {
		return continu;
	}
	 
	private class Quitter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			continu = false;
		}
	    	
	 }
}
