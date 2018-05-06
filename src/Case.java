public class Case
	{
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