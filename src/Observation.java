public class Observation
{
	public Coordonnees position;
	private boolean[] attributs = new boolean[2];

	public Observation(Coordonnees c) {
		position = c;
	}

	public Observation(Coordonnees c, boolean courantAir, boolean odeur) {
		position = c;
		attributs[0] = courantAir;
		attributs[1] = odeur;
	}
	
	public static Observation newObservation(Coordonnees c, State s)
	{
		boolean courantAir = c.isVoisin(s.getPuits()[0]) || c.isVoisin(s.getPuits()[1]);
		boolean odeur = !s.getWumpus().isMort() && c.isVoisin(s.getWumpus().getPosition());
		
		return new Observation(c, courantAir, odeur);
	}

	public boolean hasCourantAir() {
		return attributs[0];
	}
	
	public boolean hasOdeur() {
		return attributs[1];
	}
	
	public String toString() {
		return this.position.toString() + " Courant d'air : " + hasCourantAir() + ", Odeur : " + hasOdeur();
	}
}