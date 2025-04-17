package optionsGUI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import process.Factory;

/**
 * The option frame for more sales or purchases.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class OptionFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private SwapPanel sp;
	private TurboPutPanel tpp;
	private TurboCallPanel tcp;
	
	private Factory fact;
	
	public OptionFrame(Factory fact) {
		super("Plus d'options");
		
		Image logo;
		try {
			logo = ImageIO.read(new File("src/pictures/bmm.png"));
			setIconImage(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.fact = fact;
	
		sp = new SwapPanel(fact);
		tpp = new TurboPutPanel(fact);
		tcp = new TurboCallPanel(fact);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(3,1));
		contentPane.add(tcp);
		contentPane.add(tpp);
		contentPane.add(sp);
		
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(sp.isOpen() && tpp.isOpen() && tcp.isOpen());
	}
	
	public void updateVisible() {
		sp.updateTables();
		tpp.updateTable();
		setVisible(sp.isOpen() && tpp.isOpen() && tcp.isOpen());
	}

	public SwapPanel getSp() {
		return sp;
	}
	
	public TurboPutPanel getTpp() {
		return tpp;
	}
	
	public TurboCallPanel getTcp() {
		return tcp;
	}
	
	public Factory getFact() {
		return fact;
	}
}
