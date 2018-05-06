public class Agent {
	private Coordonnees position;
	private boolean arrow;

	public Agent(Coordonnees coordonnees){
		this.position = coordonnees;
		this.arrow = true;
	}

	public Coordonnees getPosition() {
		return position;
	}

	public void setPosition(Coordonnees position) {
		this.position = position;
	}

	public boolean hasArrow() {
		return arrow;
	}

	public void setArrow(boolean hasArrow) {
		this.arrow = hasArrow;
	}
}
