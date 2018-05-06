//carte de l'environnement, l'agent aura acces à l'environnement
// mais aprés

//import Action;
//import Agent;


public class Probleme {
	
	public class Carte{
		private int[] taille;
		/////private /////[] case;
		private int etatJeu = 0; //Mort ou Vivant(entrain de jouer)
		
		
		public Carte() {
		//	taille = new int
		}
	}
	
	public class Case{
		private int[] position;
		private boolean[] attributsCase;
		private Case[] voisines;
	
		public Case() {
			position = new int[2];
			attributsCase = new boolean[4]; // Pas sure de 4
			voisines = new Case[4];		
	}
	public Case(int i, int j, Boolean Trou, Boolean Odeur, Boolean Tresor, Boolean CourantAir, Boolean Wumpus, Boolean Agent) {
		this();
	
		position[0]=i;
		position[1]=j;
		attributsCase[0]= Trou;
		attributsCase[1]= Odeur;
		attributsCase[2]= Tresor;
		attributsCase[3]= CourantAir;
		attributsCase[4]= Wumpus;
		attributsCase[5]= Agent;
		attributsCase[6]= false;//toujours sur la supposition le wumpus est mort ou pas, ici pas mort
	}
	
	//CaseVoisine oui ou non?
	
		//public ObservationCase getObservation() {
		
	}
		
	// Permet a l'agent d'avoir un observation de l'environnement dans lequel il se trouve
	public class ObservationCase{
		public int[] position;
		public boolean[] attributsCaseObservable;
		
		public ObservationCase(){
			
		}
		public int geti() {
				return position[0];
		}

		public int getj() {
			return position[1];
		}
		public boolean getTrou() { //Trou= Puit de toute façon
			return attributsCaseObservable[0];
		}
		
		public boolean getOdeur() { //Odeur du wumpus
			return attributsCaseObservable[1];
		}
		
		public boolean getTresor() { //Le trésor
			return attributsCaseObservable[2];
		}
		
		public boolean getCourantAir() { //Le courant d'air
			return attributsCaseObservable[3];
		}
		public boolean getWumpus() { // Je me demande s'il ne faut pas ajouter le Wumpus mort??
			return attributsCaseObservable[4];
		}
		public boolean getAgent() {
			return attributsCaseObservable[5];
		}
		//Comment il va décrire la carte
		
		public void Description() {
			int i,j;
			System.out.println("La carte :{\n");
			String[] nbCases;
			//System.out.println("La carte est de taille :" +nbCases[0]"*" +nbCases[0]);
		}
	}
}


