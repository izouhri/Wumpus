import java.util.ArrayList;

public class Agent {
	private Coordonnees position;
	private boolean arrow;
	private Observation[][] map = {{null, null, null, null},
									{null, null, null, null},
									{null, null, null, null},
									{null, null, null, null}};
	private ArrayList<Coordonnees> whereIsWumpus = new ArrayList<Coordonnees>();
	private Coordonnees wumpusPosition = null;
	private ArrayList<Coordonnees> whereIsPuitU = new ArrayList<Coordonnees>();
	private Coordonnees puitUPosition = null;
	private ArrayList<Coordonnees> whereIsPuitD = new ArrayList<Coordonnees>();
	private Coordonnees puitDPosition = null;

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
		if (o.hasCourantAir())
			trouverPuits(o.position);
		else {
			for (Coordonnees c : whereIsPuitU)
				if (c.isVoisin(o.position))
					whereIsPuitU.remove(c);
			for (Coordonnees c : whereIsPuitD)
				if (c.isVoisin(o.position))
					whereIsPuitD.remove(c);
		}
		if (o.hasOdeur()) {
			trouverWumpus(o.position);
		}
		else {
			for (Coordonnees c : whereIsWumpus)
				if (c.isVoisin(o.position))
					whereIsWumpus.remove(c);
		}
	}
	
	public Observation getObservation(Coordonnees c) {
		return map[c.getX()][c.getY()];
	}

	public void trouverWumpus(Coordonnees odeur) {
		if (wumpusPosition == null) {
			trouverAttribut(odeur, whereIsWumpus);
			
			for (Coordonnees c : whereIsWumpus)
				if (!c.isVoisin(odeur))
					whereIsWumpus.remove(c);
	
			for (Coordonnees c : whereIsWumpus)
			{
				Observation o = getObservation(c);
				if ((o != null && !o.hasWumpus()) || c.isEqual(puitUPosition) || c.isEqual(puitDPosition))
					whereIsWumpus.remove(c);
			}
			if (whereIsWumpus.size() == 1)
				wumpusPosition = whereIsWumpus.get(0);
		}
	}
	
	public void trouverPuits(Coordonnees air) {
		if (puitUPosition == null && puitDPosition == null) {
			trouverAttribut(air, whereIsPuitU);
	
			for (Coordonnees c : whereIsPuitU)
				if (!c.isVoisin(air))
				{
					whereIsPuitD.add(c);
					whereIsPuitU.remove(c);
				}
			
			for (Coordonnees c : whereIsPuitU)
			{
				Observation o = getObservation(c);
				if ((o != null && !o.hasPuit()) || c.isEqual(wumpusPosition) || c.isEqual(puitDPosition))
					whereIsPuitU.remove(c);
			}
			for (Coordonnees c : whereIsPuitD)
			{
				Observation o = getObservation(c);
				if ((o != null && !o.hasPuit()) || c.isEqual(wumpusPosition) || c.isEqual(puitUPosition))
					whereIsPuitD.remove(c);
			}
			if (whereIsPuitU.size() == 1)
				puitUPosition = whereIsPuitU.get(0);
			if (whereIsPuitD.size() == 1)
				puitDPosition = whereIsPuitD.get(0);
		}
	}

	public ArrayList<Coordonnees> getWhereIsPuitD() {
		return whereIsPuitD;
	}

	public ArrayList<Coordonnees> getWhereIsPuitU() {
		return whereIsPuitU;
	}

	public ArrayList<Coordonnees> getWhereIsWumpus() {
		return whereIsWumpus;
	}

	// ajoute des positions possibles en fonction de l'indice
	private void trouverAttribut(Coordonnees indice, ArrayList<Coordonnees> suppositions) {
		if (!suppositions.isEmpty()) {
			if (indice.getX() < 3)
				suppositions.add(new Coordonnees(indice.getX() + 1, indice.getY()));
			if (indice.getX() > 0)
				suppositions.add(new Coordonnees(indice.getX() - 1, indice.getY()));
			if (indice.getY() < 3)
				suppositions.add(new Coordonnees(indice.getX(), indice.getY() + 1));
			if (indice.getY() > 0)
				suppositions.add(new Coordonnees(indice.getX(), indice.getY() - 1));
		}
	}
}
