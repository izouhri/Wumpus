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
	
	public static Observation newObservation(Coordonnees c, State s)
	{
		boolean puit = c.isEqual(s.getPuits()[0]) || c.isEqual(s.getPuits()[1]);
		boolean courantAir = c.isVoisin(s.getPuits()[0]) || c.isVoisin(s.getPuits()[1]);
		boolean tresor = c.isEqual(s.getOr());
		boolean odeur = !s.getWumpus().isMort() && c.isVoisin(s.getWumpus().getPosition());
		boolean wumpus = !s.getWumpus().isMort() && c.isEqual(s.getWumpus().getPosition());
		
		return new Observation(c, puit, courantAir, tresor, odeur, wumpus);
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