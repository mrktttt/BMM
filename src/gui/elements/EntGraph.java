package gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import process.EntChartManager;
import process.Factory;

/**
 * This class represents the sub-window of the companies bar chart.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class EntGraph extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JFreeChart chart;
	
	private ChartPanel pxActif;
	private Factory fact;
	private EntChartManager chartManager;
	
	public EntGraph(Factory fact) {
		this.fact = fact;
		chartManager = new EntChartManager(this.fact);
		
		Color coul = new Color(235, 252, 235);
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		Color c = new Color(204, 202, 217);
		setBackground(c);
		
		Dimension d = new Dimension(650,450);
		setPreferredSize(d);
		
		BorderLayout border = new BorderLayout();
		setLayout(border);
		
		chart = chartManager.actEntreprise();
		chart.setBackgroundPaint(coul);
		pxActif = new ChartPanel(chart);
		add(pxActif, BorderLayout.CENTER);
	}
	
	public void updateEntGraph() {
		Color coul = new Color(235, 252, 235);
		chartManager = new EntChartManager(fact);
		chart = chartManager.actEntreprise();
		chart.setBackgroundPaint(coul);
		pxActif = new ChartPanel(chart);
		removeAll();
		add(pxActif, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public Factory getFact() {
		return fact;
	}
}
