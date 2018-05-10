import java.util.ArrayList;

public class Agent {
	private Coordonnees position;
	private boolean arrow;
	public Observation[][] map = {{null, null, null, null},
									{null, null, null, null},
									{null, null, null, null},
									{null, null, null, null}};
	private ArrayList<Coordonnees> whereIsWumpus = new ArrayList<Coordonnees>();
	public Coordonnees wumpusPosition = null;
	public boolean isWumpusDead = false;
	private ArrayList<Coordonnees> whereIsPuitU = new ArrayList<Coordonnees>();
	public Coordonnees puitUPosition = null;
	private ArrayList<Coordonnees> whereIsPuitD = new ArrayList<Coordonnees>();
	public Coordonnees puitDPosition = null;

	public Agent(Coordonnees position){
		this.position = position;
		this.arrow = true;
	}
	
	public Agent(int x, int y){
		this.position = new Coordonnees(x, y);
		this.arrow = true;
	}
	
	//constructeur par copie
	public Agent(Agent agent) {
		this.position = new Coordonnees(agent.getPosition());
		this.arrow = agent.hasArrow();
		this.map = (Observation[][])agent.map.clone();
		this.whereIsWumpus = (ArrayList<Coordonnees>)agent.getWhereIsWumpus().clone();
		this.wumpusPosition = agent.wumpusPosition;
		this.whereIsPuitU = (ArrayList<Coordonnees>)agent.getWhereIsPuitU().clone();
		this.puitUPosition = agent.puitUPosition;
		this.whereIsPuitD = (ArrayList<Coordonnees>)agent.getWhereIsPuitD().clone();
		this.puitDPosition = agent.puitDPosition;
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
	
	public ArrayList<Coordonnees> getWhereIsPuitD() {
		return whereIsPuitD;
	}

	public ArrayList<Coordonnees> getWhereIsPuitU() {
		return whereIsPuitU;
	}

	public ArrayList<Coordonnees> getWhereIsWumpus() {
		return whereIsWumpus;
	}
	
	public Observation getObservation(Coordonnees c) {
		return map[c.getX()][c.getY()];
	}
	
	public Observation getObservation(int x, int y) {
		return map[x][y];
	}
	
	public void setObservation(Observation o) {
		// comparer avec une observation anterieure de la meme case
		Observation oldObservation = map[o.position.getX()][o.position.getY()];
		if (oldObservation != null)
			if (oldObservation.hasOdeur() && !o.hasOdeur())
			{
				whereIsWumpus.clear();
			}
		map[o.position.getX()][o.position.getY()] = o;
		// actualiser connaissances du monde
		if (o.hasCourantAir())
			trouverPuitU(o.position);
		else {
			actualiserSuppositions(o, whereIsPuitU);
			actualiserSuppositions(o, whereIsPuitD);
		}
		if (o.hasOdeur())
			trouverWumpus(o.position);
		else
			actualiserSuppositions(o, whereIsWumpus);
		
		comparerPositionsAttributs();
	}

	private void actualiserSuppositions(Observation o, ArrayList<Coordonnees> suppositions) {
		int i = 0;
		while (i < suppositions.size())
		{
			Coordonnees supposition = suppositions.get(i);
			if (supposition.isVoisin(o.position) || supposition.equals(o.position))
				suppositions.remove(supposition);
			else
				i++;
		}
	}

	private void comparerPositionsAttributs() {
		if (whereIsWumpus.contains(puitUPosition))
			whereIsWumpus.remove(puitUPosition);
		if (whereIsPuitD.contains(puitUPosition))
			whereIsPuitD.remove(puitUPosition);
		if (whereIsPuitD.contains(wumpusPosition))
			whereIsPuitD.remove(wumpusPosition);
		if (whereIsPuitU.contains(wumpusPosition))
			whereIsPuitU.remove(wumpusPosition);
		if (whereIsWumpus.contains(puitDPosition))
			whereIsWumpus.remove(puitDPosition);
		if (whereIsPuitU.contains(puitDPosition))
			whereIsPuitU.remove(puitDPosition);
		
		if (whereIsWumpus.size() == 1 && wumpusPosition == null) {
			wumpusPosition = whereIsWumpus.get(0);
			comparerPositionsAttributs();
		}
		if (whereIsPuitD.size() == 1 && puitDPosition == null) {
			puitDPosition = whereIsPuitD.get(0);
			comparerPositionsAttributs();
		}
		if (whereIsPuitU.size() == 1 && puitUPosition == null) {
			puitUPosition = whereIsPuitU.get(0);
			comparerPositionsAttributs();
		}
	}

	public void trouverWumpus(Coordonnees odeur) {
		if (wumpusPosition == null && whereIsWumpus.size() != 1) { // verif nb suppositions pour le cas ou la position est trouvee sur un tir de fleche
			trouverAttribut(odeur, whereIsWumpus);
			int i = 0;
			while (i < whereIsWumpus.size())
			{
				Coordonnees supposition = whereIsWumpus.get(i);
				Observation o = this.getObservation(supposition);
				if (o != null)
					whereIsWumpus.remove(supposition);
				else if (!verifOdeursVoisines(supposition))
					i++;
			}
		}
	}
	
	private boolean verifOdeursVoisines(Coordonnees supposition) {
		if (supposition.getX() < 3) {
			Observation o = this.getObservation(supposition.getX() + 1, supposition.getY());
			if (o != null && !o.hasOdeur()) {
				whereIsWumpus.remove(supposition);
				return true;
			}
		}
		if (supposition.getX() > 0) {
			Observation o = this.getObservation(supposition.getX() - 1, supposition.getY());
			if (o != null && !o.hasOdeur()) {
				whereIsWumpus.remove(supposition);
				return true;
			}
		}
		if (supposition.getY() < 3) {
			Observation o = this.getObservation(supposition.getX(), supposition.getY() + 1);
			if (o != null && !o.hasOdeur()) {
				whereIsWumpus.remove(supposition);
				return true;
			}
		}
		if (supposition.getY() > 0) {
			Observation o = this.getObservation(supposition.getX(), supposition.getY() - 1);
			if (o != null && !o.hasOdeur()) {
				whereIsWumpus.remove(supposition);
				return true;
			}
		}
		return false;
	}
	
	public void trouverPuitU(Coordonnees air) {
		boolean puitD = false;
		if (!whereIsPuitU.isEmpty()) {
			puitD = true;
			for (Coordonnees c : whereIsPuitU) { // Est-on bien à cote de puitU ?
				if (c.isVoisin(air)) {
					puitD = false;
					break;
				}
			}
		}
		if (puitD) {
			trouverPuitD(air);
		}
		else if (puitUPosition == null) {
			trouverAttribut(air, whereIsPuitU);
			int i = 0;
			while (i < whereIsPuitU.size())
			{
				Coordonnees supposition = whereIsPuitU.get(i);
				Observation o = this.getObservation(supposition);
				if (o != null)
					whereIsPuitU.remove(supposition);
				else if(!verifCourantsAirVoisins(supposition, whereIsPuitU))
					i++;
			}
		}
	}
	
	public void trouverPuitD(Coordonnees air) {
		if (puitDPosition == null) {
			trouverAttribut(air, whereIsPuitD);
			int i = 0;
			while (i < whereIsPuitD.size())
			{
				Coordonnees supposition = whereIsPuitD.get(i);
				Observation o = this.getObservation(supposition);
				if (o != null)
					whereIsPuitD.remove(supposition);
				else if (!verifCourantsAirVoisins(supposition, whereIsPuitD))
					i++;
			}
		}
	}
	
	private boolean verifCourantsAirVoisins(Coordonnees supposition, ArrayList<Coordonnees> suppositions) {
		if (supposition.getX() < 3) {
			Observation o = this.getObservation(supposition.getX() + 1, supposition.getY());
			if (o != null && !o.hasCourantAir()) {
				suppositions.remove(supposition);
				return true;
			}
		}
		if (supposition.getX() > 0) {
			Observation o = this.getObservation(supposition.getX() - 1, supposition.getY());
			if (o != null && !o.hasCourantAir()) {
				suppositions.remove(supposition);
				return true;
			}
		}
		if (supposition.getY() < 3) {
			Observation o = this.getObservation(supposition.getX(), supposition.getY() + 1);
			if (o != null && !o.hasCourantAir()) {
				suppositions.remove(supposition);
				return true;
			}
		}
		if (supposition.getY() > 0) {
			Observation o = this.getObservation(supposition.getX(), supposition.getY() - 1);
			if (o != null && !o.hasCourantAir()) {
				suppositions.remove(supposition);
				return true;
			}
		}
		return false;
	}

	// ajoute des positions possibles en fonction de l'indice
	private void trouverAttribut(Coordonnees indice, ArrayList<Coordonnees> suppositions) {
		if (suppositions.isEmpty()) {
			if (indice.getX() < 3)
				suppositions.add(new Coordonnees(indice.getX() + 1, indice.getY()));
			if (indice.getX() > 0)
				suppositions.add(new Coordonnees(indice.getX() - 1, indice.getY()));
			if (indice.getY() < 3)
				suppositions.add(new Coordonnees(indice.getX(), indice.getY() + 1));
			if (indice.getY() > 0)
				suppositions.add(new Coordonnees(indice.getX(), indice.getY() - 1));
		}
		int i = 0;
		while (i < suppositions.size())
		{
			Coordonnees supposition = suppositions.get(i);
			if (!supposition.isVoisin(indice))
				suppositions.remove(supposition);
			else
				i++;
		}
	}
}
