package gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * This class represents the sub-window of the news.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class News extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final Font TEXT_AREA_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
	private static final Font LABEL_FONT2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	
	private DefaultStyledDocument document = new DefaultStyledDocument();
	private JLabel journal = new JLabel("Journal", SwingConstants.CENTER);
	private JTextPane event = new JTextPane(document);
    private JScrollPane newsScrollPane = new JScrollPane(event);

	public News() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color couleur = new Color(235, 234, 230);
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		journal.setFont(LABEL_FONT2);
		event.setFont(TEXT_AREA_FONT);
		event.setText("La bourse vient d'ouvrir !");
		
		BorderLayout border = new BorderLayout();
		setLayout(border);
		
		Dimension dim = new Dimension(300,570);
		setPreferredSize(dim);
		
		Color borderColor = new Color(67, 67, 74);
		newsScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		newsScrollPane.setBorder(BorderFactory.createLineBorder(borderColor, 3));
		journal.setBorder(BorderFactory.createLineBorder(borderColor, 3));
		
		newsScrollPane.setBackground(c);
		event.setBackground(couleur);
        
        add(journal, BorderLayout.NORTH);
        add(newsScrollPane, BorderLayout.CENTER);
	}
	
	public void addNews(String news, boolean influence) throws BadLocationException {
		Color green = new Color(41, 148, 47);
		Color red = new Color(184, 31, 31);
		StyleContext context = new StyleContext();
		Style style = context.addStyle("test", null);
		if(influence) {
			StyleConstants.setForeground(style, green);
		} else {
			StyleConstants.setForeground(style, red);
		}
		
		ArrayList<String> text = new ArrayList<String>();
		while (news.length() >= 35) {
			int j = 0;
			for(int i=0 ; i <= 33 ; i++) {
				if(news.substring(i,i+1).equals(" ")) {
					j = i+1;
				}
			}
			String line = news.substring(0,j);
			text.add(line);
			news = "\n" + news.substring(j,news.length());
		}
		Iterator<String> it = text.iterator();
		String tmp = "";
		while(it.hasNext()) {
			String line = it.next();
			tmp = tmp + line;
		}
		news = tmp + news;
		document.insertString(0,news + "\n\n", style);
	}

	public DefaultStyledDocument getDocument() {
		return document;
	}

	public void setDocument(DefaultStyledDocument document) {
		this.document = document;
		event.setDocument(document);
	}
}
