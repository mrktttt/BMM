package data;

import java.io.Serializable;

/**
 * The event that can generate in the simulation.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class Evenement implements Serializable{
	private static final long serialVersionUID = 1L;
	private String text;
	private int type;
	private boolean influence;
	
	public Evenement(String text, int type, boolean influence) {
		this.text = text;
		this.type = type;
		this.influence = influence;
	}

	public boolean isInfluence() {
		return influence;
	}

	public void setInfluence(boolean influence) {
		this.influence = influence;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
