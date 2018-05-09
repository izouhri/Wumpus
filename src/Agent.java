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
	private boolean isWumpusDead = false;
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
	
	public Observation getObservation(Coordonnees c) {
		return map[c.getX()][c.getY()];
	}
	
	public void setObservation(Observation o) {
		// comparer avec une observation anterieure de la meme case
		Observation oldObservation = map[o.position.getX()][o.position.getY()];
		if (oldObservation != null) {
			if (oldObservation.hasOdeur() && !o.hasOdeur()) {
				isWumpusDead = true;
			}
		}
		map[o.position.getX()][o.position.getY()] = o;
		// actualiser connaissances du monde
		if (o.hasCourantAir())
			trouverPuitU(o.position);
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

	public void trouverWumpus(Coordonnees odeur) {
		if (wumpusPosition == null) {
			trouverAttribut(odeur, whereIsWumpus);
			if (whereIsWumpus.contains(puitUPosition))
				whereIsWumpus.remove(puitUPosition);
			if (whereIsWumpus.contains(puitDPosition))
				whereIsWumpus.remove(puitDPosition);
			for (Observation[] raw : map) {
				for (Observation o : raw) {
					if (o != null && whereIsWumpus.contains(o.position))
						whereIsWumpus.remove(o.position);
					for (Coordonnees c : whereIsWumpus)
						if (o != null && c.isVoisin(o.position) && !o.hasOdeur())
							whereIsWumpus.remove(o.position);
				}
			}
			if (whereIsWumpus.size() == 1)
				wumpusPosition = whereIsWumpus.get(0);
		}
	}
	
	public void trouverPuitU(Coordonnees air) {
		if (puitUPosition == null) {
			if (!whereIsPuitU.isEmpty()) {
				boolean puitD = true;
				for (Coordonnees c : whereIsPuitU) {
					if (c.isVoisin(air)) {
						puitD = false;
						break;
					}
				}
				if (puitD)
					trouverPuitD(air);
				else
					trouverAttribut(air, whereIsPuitU);
			}
			if (whereIsPuitU.contains(wumpusPosition))
				whereIsPuitU.remove(wumpusPosition);
			if (whereIsPuitU.contains(puitDPosition))
				whereIsPuitU.remove(puitDPosition);
			for (Observation[] raw : map) {
				for (Observation o : raw)
				{
					if (o != null && whereIsPuitU.contains(o.position))
						whereIsPuitU.remove(o.position);
					for (Coordonnees c : whereIsPuitU)
						if (o != null && c.isVoisin(o.position) && !o.hasOdeur())
							whereIsPuitU.remove(o.position);
				}
			}
			if (whereIsPuitU.size() == 1)
				puitUPosition = whereIsPuitU.get(0);
		}
	}
	
	public void trouverPuitD(Coordonnees air) {
		if (puitDPosition == null) {
			trouverAttribut(air, whereIsPuitD);
			if (whereIsPuitD.contains(wumpusPosition))
				whereIsPuitD.remove(wumpusPosition);
			if (whereIsPuitD.contains(puitUPosition))
				whereIsPuitD.remove(puitUPosition);
			for (Observation[] raw : map) {
				for (Observation o : raw)
				{
					if (o != null && whereIsPuitD.contains(o.position))
						whereIsPuitD.remove(o.position);
					for (Coordonnees c : whereIsPuitD)
						if (o != null && c.isVoisin(o.position) && !o.hasOdeur())
							whereIsPuitD.remove(o.position);
				}
			}
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
		for (Coordonnees c : suppositions)
			if (!c.isVoisin(indice))
				suppositions.remove(c);
	}
}
