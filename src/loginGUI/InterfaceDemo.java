package loginGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceDemo extends JFrame implements Runnable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	
	private JPanel panel = new JPanel(); 
    private Connexion connex = new Connexion();
	protected JLabel title;
    private JButton clientButton, supervisorButton;
    private boolean bool;
    private int nb;
    private Indentify ident;

	public InterfaceDemo() throws IOException {
        super("Bienvenue chez BMM");
        Image logo;
		try {
			logo = ImageIO.read(new File("src/pictures/bmm.png"));
			setIconImage(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        BufferedImage img = ImageIO.read(new File("src/pictures/imBourse.jpg"));
        ImageIcon icon = new ImageIcon(img);
        title = new JLabel(icon);
        
        title.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        clientButton = new JButton("Espace Client");
        supervisorButton = new JButton("Espace Superviseur");
        
        panel.setLayout(new FlowLayout());
        
        setLayout(new BorderLayout());
        
        Color couleur = new Color(99, 99, 99);
		
		Color c = new Color(204, 202, 217);
		
		panel.setBackground(c);
		
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        clientButton.setFont(BUTTON_FONT);
        supervisorButton.setFont(BUTTON_FONT);
        
        clientButton.setForeground(Color.YELLOW);
        supervisorButton.setForeground(Color.YELLOW);
        
        clientButton.setBackground(couleur);
        supervisorButton.setBackground(couleur);

        clientButton.addActionListener(new Client());
            
        supervisorButton.addActionListener(new Supervisor());

        panel.add(clientButton);
        panel.add(supervisorButton);
        add(panel, BorderLayout.NORTH);
        add(title, BorderLayout.CENTER);

        setResizable(false);
        pack();
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private class Supervisor implements ActionListener{
    	 public void actionPerformed(ActionEvent e) {
    		 nb = 1;
    		 connex.setVisible(true);
         }
    }
    
    private class Client implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
    		 nb = 0;
    		 connex.setVisible(true);
    	 }
    }
    
    public Connexion getConnexion() {
    	return connex;
    }
    
    public Indentify getIdent() {
		return ident;
	}
	
	public boolean isBool() {
		return bool;
	}
	
	public int getNb() {
		return nb;
	}
    
    public void run() {
		bool = false;
		while (!isBool()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			bool = connex.isBool();
		}
		dispose();
	}
	
}