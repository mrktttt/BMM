package loginGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import data.Profil;

public class Account extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
	private static final Font FIELD_FONT = new Font(Font.MONOSPACED, Font.BOLD, 18);
	
	private JPanel accountPanel = new JPanel();
	private JTextField email = new JTextField(20);
	private JTextField phone = new JTextField(20);
	private JTextField firstName = new JTextField(20);
	private JTextField lastName = new JTextField(20);
	private JPasswordField newPassword = new JPasswordField(20);
	private JLabel mail = new JLabel("Adresse e-mail :", SwingConstants.CENTER);
	private JLabel num = new JLabel("Numéro de téléphone :", SwingConstants.CENTER);
	private JLabel nom = new JLabel("Nom :", SwingConstants.CENTER);
	private JLabel prenom = new JLabel("Prénom ", SwingConstants.CENTER);
	private JLabel mdp = new JLabel("Mot de passe :", SwingConstants.CENTER);
	private JButton creer = new JButton("Créer un compte");
	private JPanel jp = new JPanel();
	private JLabel msg = new JLabel("", SwingConstants.CENTER);
	private ArrayList<Profil> profils = new ArrayList<Profil>();

	public Account() {
		super("Créer un compte");
		Image logo;
		try {
			logo = ImageIO.read(new File("src/pictures/bmm.png"));
			setIconImage(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		email.setFont(FIELD_FONT);
		phone.setFont(FIELD_FONT);
		firstName.setFont(FIELD_FONT);
		lastName.setFont(FIELD_FONT);
		newPassword.setFont(FIELD_FONT);
		
		msg.setFont(LABEL_FONT);
		
		Color couleur = new Color(99, 99, 99);
		
		Color c = new Color(204, 202, 217);

		msg.setBackground(c);
		msg.setForeground(Color.RED);
		
		jp.setBackground(c);
		accountPanel.setBackground(c);
		
		creer.setBackground(couleur);
		creer.setForeground(Color.yellow);
		
		mail.setFont(LABEL_FONT);
		nom.setFont(LABEL_FONT);
		prenom.setFont(LABEL_FONT);
		num.setFont(LABEL_FONT);
		mdp.setFont(LABEL_FONT);
		
		creer.setFont(BUTTON_FONT);
        
        accountPanel.setLayout(new GridLayout(5,2));
        
        accountPanel.add(prenom);
        accountPanel.add(firstName);
        
        accountPanel.add(nom);
        accountPanel.add(lastName);

        accountPanel.add(mail);
        accountPanel.add(email);

        accountPanel.add(num);
        accountPanel.add(phone);
       
        accountPanel.add(mdp);
        accountPanel.add(newPassword);
        
        jp.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        accountPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        jp.add(creer);
        
        setLayout(new BorderLayout());
        
        add(msg, BorderLayout.NORTH);
        add(accountPanel, BorderLayout.CENTER);
        add(jp, BorderLayout.SOUTH);
        
        creer.addActionListener(new Creer());
        
        Dimension dim = new Dimension(600,200);
        
        setPreferredSize(dim);
		setResizable(false);
		setVisible(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	public ArrayList<Profil> getProfils(){
		return profils;
	}
	
	private class Creer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 Indentify i = new Indentify();
	            if(i.createFich(email.getText(), firstName.getText(), lastName.getText(), phone.getText(), new String(newPassword.getPassword()))){
	                setVisible(false);
	                profils.add(i.getProf());
	            } 
	            else {
	            	msg.setText("Ce mail est déjà relié à un compte");
	            }
   		}
	}
	
}
