public class Observation{
		public int[] position;
		public boolean[] attributsCaseObservable;
		
		public Observation(){
			
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