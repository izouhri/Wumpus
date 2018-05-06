public class Case
{
	private Coordonnees position;
	private boolean[] attributs;
	//private Case[] voisines;

	public Case(int x, int y) {
		position = new Cordonnees(x, y);
		attributsCase = new boolean[6];
		//voisines = new Case[4];
	}

	public Case(int x, int x, boolean Puit, boolean Odeur, boolean Tresor, boolean CourantAir, boolean Wumpus, boolean Agent) {
		position = new Coordonnees(x, y);
		attributs[0]= Puit;
		attributs[1]= Odeur;
		attributs[2]= Tresor;
		attributs[3]= CourantAir;
		attributs[4]= Wumpus;
		attributs[5]= Agent;
	}
	
	public boolean hasPuit() {
		return attributs[0];
	}
	
	public boolean hasOdeur() {
		return attributs[1];
	}
	
	public boolean hasTresor() {
		return attributs[2];
	}
	
	public boolean hasCourantAir() {
		return attributs[3];
	}
	
	public boolean hasWumpus() {
		return attributs[4];
	}
	
	public boolean hasAgent() {
		return attributs[5];
	}
}