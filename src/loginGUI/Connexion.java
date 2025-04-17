package loginGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Connexion extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
	private static final Font FIELD_FONT = new Font(Font.MONOSPACED, Font.BOLD, 18);
	
	private JPanel loginPanel = new JPanel();
	private JTextField username = new JTextField(20);
    private JPasswordField password = new JPasswordField(20);
    private JButton createAccountButton = new JButton("Créer un compte");
    private JButton connect = new JButton("Se connecter");
    private JPanel buttons = new JPanel();
    private JLabel msg = new JLabel("", SwingConstants.CENTER);
    
    private boolean bool;
    private String email;
    
    private Account acc = new Account();
	
	
	public Connexion() {
		super("Connexion");
		Image logo;
		try {
			logo = ImageIO.read(new File("src/pictures/bmm.png"));
			setIconImage(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bool = false;
		
		loginPanel.setLayout(new FlowLayout());
		
		connect.setFont(BUTTON_FONT);
		createAccountButton.setFont(BUTTON_FONT);
		
		msg.setFont(LABEL_FONT);
		
		Color couleur = new Color(99, 99, 99);
		
		Color c = new Color(204, 202, 217);
		
		loginPanel.setBackground(c);
		buttons.setBackground(c);
		msg.setBackground(c);
		msg.setForeground(Color.RED);
		
		connect.setBackground(couleur);
		createAccountButton.setBackground(couleur);
		
		username.setFont(FIELD_FONT);
		password.setFont(FIELD_FONT);
		
		connect.setForeground(Color.YELLOW);
		createAccountButton.setForeground(Color.YELLOW);

        loginPanel.add(new JLabel("Adresse mail :"));
        loginPanel.add(username);
        loginPanel.add(new JLabel("Mot de passe :"));
        loginPanel.add(password);
        
        setLayout(new BorderLayout());
        
        buttons.setLayout(new FlowLayout());
        
        buttons.add(connect);
        buttons.add(createAccountButton);
        
        add(loginPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        add(msg, BorderLayout.NORTH);

        createAccountButton.addActionListener(new Compte());
        
        connect.addActionListener(new Connect());
        
        Dimension dim = new Dimension(700,120);
        
        setSize(dim);
		setResizable(false);
		setVisible(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	public boolean isBool() {
		return bool;
	}
	
	public Account getAccount() {
		return acc;
	}
	
	public String getEmail() {
		return email;
	}
	
	private class Compte implements ActionListener{
   	 	public void actionPerformed(ActionEvent e) {
   		 	acc.setVisible(true);
   	 	}
	}
	
	private class Connect implements ActionListener{
   	 	public void actionPerformed(ActionEvent e) {
   	 		Indentify id = new Indentify();
   	 		try {
   	 			if (id.connectionCheck(username.getText(), new String(password.getPassword()))){
   	 				email = username.getText();
   	 				setVisible(false);
   	 				bool = true;
   	 			} else {
   	 				msg.setText("Mot de passe ou mail invalide");
   	 			}
   	 		} catch (IOException e1) {
   	 			// TODO Auto-generated catch block
   	 			e1.printStackTrace();
   	 		}
   	 	}
	}

}
