public class Agent {
	private Coordonnees position;
	private boolean arrow;
	private Observation[][] map = {{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null}};

	public Agent(Coordonnees position){
		this.position = position;
		this.arrow = true;
	}
	
	public Agent(Agent agent) {
		this.position = new Coordonnees(agent.getPosition());
		this.arrow = agent.hasArrow();
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
	
	public void setObservation(Observation o) {
		map[o.position.getX()][o.position.getY()] = o;
	}
	
	public Observation getObservation(Coordonnees c) {
		return map[c.getX()][c.getY()];
	}
}
