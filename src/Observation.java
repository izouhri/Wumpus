public class Observation
{
	public Coordonnees position;
	private boolean[] attributs = new boolean[5];

	public Observation(Coordonnees c) {
		position = c;
	}

	public Observation(Coordonnees c, boolean puit, boolean courantAir, boolean tresor, boolean odeur, boolean wumpus) {
		position = c;
		attributs[0] = puit;
		attributs[1] = courantAir;
		attributs[2] = tresor;
		attributs[3] = odeur;
		attributs[4] = wumpus;
	}
	
	public boolean hasPuit() {
		return attributs[0];
	}
	
	public boolean hasCourantAir() {
		return attributs[1];
	}
	
	public boolean hasTresor() {
		return attributs[2];
	}
	
	public boolean hasOdeur() {
		return attributs[3];
	}
	
	public boolean hasWumpus() {
		return attributs[4];
	}
	
	public String toString() {
		return hasPuit() + ", " + hasCourantAir() + ", " + hasTresor() + ", " + hasOdeur() + ", " + hasWumpus();
	}
}