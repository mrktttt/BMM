package process;

import java.io.Serializable;

/**
 * This class is necessary for the position of the point in the graph
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class Point implements Serializable{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
