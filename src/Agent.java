import java.util.ArrayList;

public class Agent {
	private Coordonnees position;
	private boolean arrow;
	private Observation[][] map = {{null, null, null, null},
									{null, null, null, null},
									{null, null, null, null},
									{null, null, null, null}};
	private ArrayList<Coordonnees> whereIsWumpus = new ArrayList<Coordonnees>();

	public Agent(Coordonnees position){
		this.position = position;
		this.arrow = true;
	}
	
	//constructeur par copie
	public Agent(Agent agent) {
		this.position = new Coordonnees(agent.getPosition());
		this.arrow = agent.hasArrow();
	}

	//getters et setters
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

	public void findWumpus(Coordonnees odeur) {
		if (whereIsWumpus.isEmpty()) {
			whereIsWumpus.add(new Coordonnees(odeur.getX(), odeur.getY() + 1));
		}
	}
}
