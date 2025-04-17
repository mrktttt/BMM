package gui.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the sub-window of the date.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class DatePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	
	protected JLabel date = new JLabel("Date : ");
	protected JLabel d = new JLabel();
	protected JLabel heure = new JLabel("Heure : ");
	protected JLabel h = new JLabel();

	public DatePanel() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		Dimension dim = new Dimension(300,80);
		setPreferredSize(dim);
		
		GridLayout grid1 = new GridLayout(1,4);
		setLayout(grid1);
		
		Date currentDate = new Date();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
		String currentDateTime1 = dateFormat1.format(currentDate);
		String currentDateTime2 = dateFormat2.format(currentDate);
		d.setText(currentDateTime1);
		h.setText(currentDateTime2);
		
		date.setFont(LABEL_FONT);
		d.setFont(LABEL_FONT);
		heure.setFont(LABEL_FONT);
		h.setFont(LABEL_FONT);
		
		add(date);
		add(d);
		add(heure);
		add(h);
	}
	
	public void updateDate() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
		String currentDateTime1 = dateFormat1.format(currentDate);
		String currentDateTime2 = dateFormat2.format(currentDate);
		d.setText(currentDateTime1);
		h.setText(currentDateTime2);
	}
}
