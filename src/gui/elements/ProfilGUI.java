package gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Profil;
import process.Factory;

/**
 * This class represents the sub-window of the user's profile.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class ProfilGUI extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);

	protected JLabel profil = new JLabel("PROFIL", SwingConstants.CENTER);
	protected JLabel nom = new JLabel("Nom : ");
	protected JLabel n = new JLabel();
	protected JLabel prenom = new JLabel("Prenom : ");
	protected JLabel p = new JLabel();
	protected JLabel mail = new JLabel("Mail : ");
	protected JLabel m = new JLabel();
	protected JLabel num = new JLabel("Téléphone : ");
	protected JLabel nu = new JLabel();
	
	protected JPanel info = new JPanel();
	protected JPanel panelGrid1 = new JPanel();
	protected JPanel panelGrid2 = new JPanel();
	
	private Factory fact;
	
	public ProfilGUI(Factory fact) {
		this.fact=fact;
		
		Dimension dim = new Dimension(250,200);
		setPreferredSize(dim);
		
		profil.setFont(LABEL_FONT2);
		nom.setFont(LABEL_FONT);
		n.setFont(LABEL_FONT);
		prenom.setFont(LABEL_FONT);
		p.setFont(LABEL_FONT);
		mail.setFont(LABEL_FONT);
		m.setFont(LABEL_FONT);
		num.setFont(LABEL_FONT);
		n.setFont(LABEL_FONT);
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		BorderLayout border = new BorderLayout();
		setLayout(border);
		
		info.setLayout(new BorderLayout());
		info.setBackground(c);
		
		
		Dimension d2 = new Dimension(100,160);
		panelGrid1.setSize(d2);
		GridLayout grid2 = new GridLayout(4,1);
		panelGrid1.setLayout(grid2);
		panelGrid1.setBackground(c);
		
		Dimension d3 = new Dimension(150,160);
		panelGrid2.setSize(d3);
		GridLayout grid3 = new GridLayout(4,1);
		panelGrid2.setLayout(grid3);
		panelGrid2.setBackground(c);
		
		Profil prof = fact.getU().getProf();
		
		n.setText(prof.getNom());
		m.setText(prof.getMail());
		p.setText(prof.getPrenom());
		nu.setText(prof.getNumPhone());
		
		panelGrid1.add(nom);
		panelGrid2.add(n);
		panelGrid1.add(prenom);
		panelGrid2.add(p);
		panelGrid1.add(mail);
		panelGrid2.add(m);
		panelGrid1.add(num);
		panelGrid2.add(nu);
		
		info.add(panelGrid1, BorderLayout.WEST);
		info.add(panelGrid2, BorderLayout.CENTER);
		
		add(profil, BorderLayout.NORTH);
		add(info, BorderLayout.CENTER);
	}
	
	public void update() {
		Profil prof = fact.getU().getProf();
		n.setText(prof.getNom());
		m.setText(prof.getMail());
		p.setText(prof.getPrenom());
		nu.setText(prof.getNumPhone());
	}
}
