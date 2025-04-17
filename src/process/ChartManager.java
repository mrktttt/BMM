package process;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This class put all of the positions of the points in a list, to put it in the graph.
 * 
 * We can also choose the numbers of points we will put on the graph.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class ChartManager implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Point> points1 = new ArrayList<Point>();
	private ArrayList<Point> points2 = new ArrayList<Point>();
	private ArrayList<Point> points3 = new ArrayList<Point>();

	public ChartManager() {
		for (int j = 0 ; j<=Parametres.GRAPH_SIZE_1 ; j++) {
			Point p = new Point(j,0);
			points1.add(p);
		}
		
		for (int j = 0 ; j<=Parametres.GRAPH_SIZE_2 ; j++) {
			Point p = new Point(j,0);
			points2.add(p);
		}
		
		for (int j = 0 ; j<=Parametres.GRAPH_SIZE_3 ; j++) {
			Point p = new Point(j,0);
			points3.add(p);
		}
	}
	
	/**
	 * Add a new point to the list to update the graph
	 * 
	 * @param px
	 *          The actual price of a company's action
	 */
	public void updateP(int px) {
		Point p1 = new Point(Parametres.GRAPH_SIZE_1,px);
		Iterator<Point> it1 = points1.iterator();
		while(it1.hasNext()) {
			Point p = it1.next();
			p.setX(p.getX()-1);
		}
		points1.add(p1);
		points1.remove(0);
		
		Point p2 = new Point(Parametres.GRAPH_SIZE_2,px);
		Iterator<Point> it2 = points2.iterator();
		while(it2.hasNext()) {
			Point p = it2.next();
			p.setX(p.getX()-1);
		}
		points2.add(p2);
		points2.remove(0);
		
		Point p3 = new Point(Parametres.GRAPH_SIZE_3,px);
		Iterator<Point> it3 = points3.iterator();
		while(it3.hasNext()) {
			Point p = it3.next();
			p.setX(p.getX()-1);
		}
		points3.add(p3);
		points3.remove(0);
	}
	
	/**
	 * Create the JFreeChart
	 * 
	 * @param nb
	 *          An integer for the size of the graph
	 * @return the JFreeChart
	 */
	public JFreeChart mainGraph(int nb) {
		
		
		XYSeries serie = new XYSeries("Prix des actifs");
		if (nb==Parametres.GRAPH_SIZE_1) {
			Iterator<Point> it = points1.iterator();
			while(it.hasNext()) {
				Point p = it.next();
				serie.add(p.getX(), p.getY());
			}
		}
		if (nb==Parametres.GRAPH_SIZE_2) {
			Iterator<Point> it = points2.iterator();
			while(it.hasNext()) {
				Point p = it.next();
				serie.add(p.getX(), p.getY());
			}
		}
		if (nb==Parametres.GRAPH_SIZE_3) {
			Iterator<Point> it = points3.iterator();
			while(it.hasNext()) {
				Point p = it.next();
				serie.add(p.getX(), p.getY());
			}
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie);

		JFreeChart chart = ChartFactory.createXYLineChart("", "Temps", "Prix en Euro", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		Color couleur = new Color(48, 48, 48);
		Color couleur2 = new Color(163, 163, 163);
		XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(couleur);
		plot.setRangeGridlinePaint(couleur2);
		plot.setDomainGridlinePaint(couleur2);
		
		return chart;
	}
}
