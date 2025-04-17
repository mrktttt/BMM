package process;

import java.awt.Color;
import java.util.Iterator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import data.Entreprise;

/**
 * This class create the bar chart for all of the companies.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class EntChartManager {

	private Factory fact;
	
	public EntChartManager(Factory fact) {
		this.fact = fact;
	}
	
	/**
	 * Create the bar chart
	 * 
	 * @return the JFreeChart
	 */
	public JFreeChart actEntreprise() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Iterator<Entreprise> it = fact.getEnts().iterator();
		while (it.hasNext()) {
			Entreprise ent = it.next();
			dataset.setValue(ent.valeurAction(), "Prix des actifs", String.valueOf(ent.getNumero()));
		}
		JFreeChart chart = ChartFactory.createBarChart("", "Entreprises", "Prix des actifs", dataset, PlotOrientation.VERTICAL, true, true, false);
		Color couleur = new Color(48, 48, 48);
		Color couleur2 = new Color(163, 163, 163);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(couleur);
		plot.setRangeGridlinePaint(couleur2);
		plot.setDomainGridlinePaint(couleur2);
		return chart;
	}
}
