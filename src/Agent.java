//from Probleme import *


public class Agent {
	private Coordonnees position;
	private boolean hasArrow;

	public Agent(Coordonnees coordonnees){
		this.position = coordonnees;
		this. hasArrow = true;
	}

	public Coordonnees getPosition() {
		return position;
	}

	public void setPosition(Coordonnees position) {
		this.position = position;
	}

	public boolean isHasArrow() {
		return hasArrow;
	}

	public void setHasArrow(boolean hasArrow) {
		this.hasArrow = hasArrow;
	}
}
