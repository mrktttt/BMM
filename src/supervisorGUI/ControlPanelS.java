package supervisorGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import process.Factory;
import process.Parametres;

/**
 * This class represents the sub-window for the controls of the simulation.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class ControlPanelS extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 16);
	
	protected JButton accelerer = new JButton("Simulation >>>");
	protected JButton ralentir = new JButton("Simulation <<<");
	protected JButton zoom = new JButton("Zoom +");
	protected JButton dezoom = new JButton("Zoom -");
	
	private int taille = Parametres.INITIAL_GRAPH_SIZE;
	private int timeSpeed = Parametres.INITIAL_CHRONO_SPEED;
	
	protected JLabel cont = new JLabel("Simulation", SwingConstants.CENTER);
	
	protected JPanel b1 = new JPanel();
	protected JPanel b2 = new JPanel();
	protected JPanel b3 = new JPanel();
	protected JPanel b4 = new JPanel();
	
	protected JPanel g1 = new JPanel();
	protected JPanel g2 = new JPanel();
	protected JPanel g3 = new JPanel();
	protected JPanel g4 = new JPanel();
	
	private Factory fact;
	
	public ControlPanelS(Factory fact) {
		this.fact=fact;
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		GridLayout grid = new GridLayout(1,1);
		
		Dimension d = new Dimension(148,70);
		g1.setPreferredSize(d);
		g2.setPreferredSize(d);
		g3.setPreferredSize(d);
		g4.setPreferredSize(d);
		
		g1.setLayout(grid);
		g1.setBackground(c);
		g1.add(accelerer);
		
		g2.setLayout(grid);
		g2.setBackground(c);
		g2.add(ralentir);
		
		
		g3.setLayout(grid);
		g3.setBackground(c);
		g3.add(zoom);
		
		g4.setLayout(grid);
		g4.setBackground(c);
		g4.add(dezoom);
		
		b1.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b1.setBackground(c);
		b1.add(g1);
		
		b2.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b2.setBackground(c);
		b2.add(g2);
		
		b3.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b3.setBackground(c);
		b3.add(g3);
		
		b4.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		b4.setBackground(c);
		b4.add(g4);
		
		GridLayout grid2 = new GridLayout(5,1);
		setLayout(grid2);
		
		Dimension dim = new Dimension(150,450);
		setPreferredSize(dim);
		
		cont.setFont(LABEL_FONT2);
		accelerer.setFont(BUTTON_FONT);
		ralentir.setFont(BUTTON_FONT);
		zoom.setFont(BUTTON_FONT);
		dezoom.setFont(BUTTON_FONT);
		
		Color c1 = new Color(99, 99, 99);
		
        accelerer.setBackground(c1);
        accelerer.setForeground(Color.YELLOW);
        
        ralentir.setBackground(c1);
        ralentir.setForeground(Color.YELLOW);
        
        zoom.setBackground(c1);
        zoom.setForeground(Color.YELLOW);
        
        dezoom.setBackground(c1);
        dezoom.setForeground(Color.YELLOW);
        
        add(cont);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        
        accelerer.addActionListener(new Acc());
        ralentir.addActionListener(new Ral());
        zoom.addActionListener(new Zoom());
		dezoom.addActionListener(new Dezoom());
	}
	
	public Factory getFact() {
		return fact;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public void setTaille(int i) {
		taille = i;
	}
	
	public int getTimeSpeed() {
		return timeSpeed;
	}
	
	public void setTimeSpeed(int i) {
		timeSpeed = i;
	}
	
	private class Acc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (timeSpeed == Parametres.CHRONO_SPEED_2) {
				setTimeSpeed(Parametres.CHRONO_SPEED_1);
			}
			if (timeSpeed == Parametres.CHRONO_SPEED_3) {
				setTimeSpeed(Parametres.CHRONO_SPEED_2);
			}
		}
	}
	
	private class Ral implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (timeSpeed == Parametres.CHRONO_SPEED_2) {
				setTimeSpeed(Parametres.CHRONO_SPEED_3);
			}
			if (timeSpeed == Parametres.CHRONO_SPEED_1) {
				setTimeSpeed(Parametres.CHRONO_SPEED_2);
			}
		}
	}
	
	private class Zoom implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (taille == Parametres.GRAPH_SIZE_2) {
				setTaille(Parametres.GRAPH_SIZE_1);
			}
			if (taille == Parametres.GRAPH_SIZE_3) {
				setTaille(Parametres.GRAPH_SIZE_2);
			}
		}
	}
	
	private class Dezoom implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (taille == Parametres.GRAPH_SIZE_2) {
				setTaille(Parametres.GRAPH_SIZE_3);
			}
			if (taille == Parametres.GRAPH_SIZE_1) {
				setTaille(Parametres.GRAPH_SIZE_2);
			}
		}
	}
}

