package supervisorGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Entreprise;
import process.Factory;

/**
 * This class represents the sub-window of the top part.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class SearchPanelS extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 16);
	
	private Factory fact;
	private Entreprise ent;
	
	protected ImageIcon logo = new ImageIcon("src/pictures/logo.png");
    protected JLabel logoL = new JLabel(logo, SwingConstants.CENTER);
    protected JPanel panel = new JPanel();
    protected JPanel panelLogo = new JPanel();
    protected SelectFrameS selectF;
    
    protected JButton select = new JButton("Selectionner une entreprise");
    
    public SearchPanelS(Factory fact, Entreprise ent) {
    	this.fact=fact;
    	this.ent = ent;
    	selectF = new SelectFrameS(fact,ent);
    	
    	ImageIcon im1 = new ImageIcon(logo.getImage().getScaledInstance(102, 102, Image.SCALE_SMOOTH));
    	logoL.setIcon(im1);
    	
    	Dimension d1 = new Dimension(300,40);
    	select.setFont(BUTTON_FONT);
    	Color c1 = new Color(99, 99, 99);
    	select.setBackground(c1);
    	select.setForeground(Color.YELLOW);
    	select.setPreferredSize(d1);
    	
    	setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		panel.setBackground(c);
		panel.add(select);

		Dimension d2 = new Dimension(200,80);
		panelLogo.setLayout(new BorderLayout());
		panelLogo.setBackground(c);
		panelLogo.add(logoL);
		panelLogo.setPreferredSize(d2);
		
		setLayout(new BorderLayout());
		
		Dimension dim = new Dimension(800,80);
		setPreferredSize(dim);
		
		select.addActionListener(new ChoixEntreprise());
		
		add(panelLogo, BorderLayout.WEST);
		add(panel, BorderLayout.CENTER);
    }
    
    public Factory getFact() {
    	return fact;
    }
    
    public void updateEntreprise() {
    	ent = selectF.getEnt();
    }
    
    public void updateSelect() {
    	selectF.updateEnts();
    }
    
    private class ChoixEntreprise implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			selectF.setVisible(true);
			selectF.toFront();
		}
    	
    }
    
    public Entreprise getEnt() {
    	return ent;
    }
    
    public SelectFrameS getSelectF() {
    	return selectF;
    }
}
