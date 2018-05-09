public class Problem {

    public State transition(State s, Action a) {
        State s2 = new State(s);
        Coordonnees positionAgent = s.getAventurier().getPosition();
        if (a == Action.ALLERHAUT && positionAgent.getY() < 3) {
            s2.setPositionAgent(new Coordonnees(positionAgent.getX(), positionAgent.getY() + 1));
        }
        else if (a == Action.ALLERBAS && positionAgent.getY() > 0) {
        	s2.setPositionAgent(new Coordonnees(positionAgent.getX(), positionAgent.getY() - 1));
        }
        else if (a == Action.ALLERDROITE && positionAgent.getX() < 3) {
        	s2.setPositionAgent(new Coordonnees(positionAgent.getX() + 1, positionAgent.getY()));
        }
        else if (a == Action.ALLERGAUCHE && positionAgent.getX() > 0) {
        	s2.setPositionAgent(new Coordonnees(positionAgent.getX() - 1, positionAgent.getY()));
        }
        else {
        	Coordonnees wumpusPosition = s.getWumpus().getPosition();
        	s2.getAventurier().setArrow(false); // on sait déjà que ce sera une action de tirer
        	if (wumpusPosition.getX() == positionAgent.getX())
        	{
	        	if (a == Action.TIRERHAUT) {
		            if (wumpusPosition.getY() > positionAgent.getY())
		                s2.setMortWumpus(true);
		        }
		        else if (a == Action.TIRERBAS) {
		            if (wumpusPosition.getY() < positionAgent.getY())
		                s2.setMortWumpus(true);
		        }
        	}
        	else if (wumpusPosition.getY() == positionAgent.getY()) {
		        if (a == Action.TIRERDROITE) {
		            if (wumpusPosition.getX() > positionAgent.getX())
		                s2.setMortWumpus(true);
		        }
		        else if (a == Action.TIRERGAUCHE) {
		            if (wumpusPosition.getX() < positionAgent.getX())
		                s2.setMortWumpus(true);
		        }
        	}
    	}
        return s2;
    }
    
    /*public static void etape() {//permet d'avoir touets les actions que va effectuer l'agent
    	try {
		System.out.println("Appuyez sur ENTRE pour continuer!");
		Scanner.scanner = new Scanner(System.in).useDelimiter("");//eviter pb et tockenize l'input du sccanner(en gros fonctionne comme "StringTokenize class"
		scanner.next();
		}
		catch(Exception error) {
			
		}
    }*/
    	
    	
    public Observation observation(State s, Action a) {
    	if (a == Action.ALLERHAUT) {
    		Coordonnees positionAgent = new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() + 1);
            return Observation.newObservation(positionAgent, s);
        }
        else if (a == Action.ALLERBAS) {
        	Coordonnees positionAgent = new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() - 1);
            return Observation.newObservation(positionAgent, s);
        }
        else if (a == Action.ALLERDROITE) {
        	Coordonnees positionAgent = new Coordonnees(s.getAventurier().getPosition().getX()+ 1, s.getAventurier().getPosition().getY() );
            return Observation.newObservation(positionAgent, s);
        }
        else if (a == Action.ALLERGAUCHE) {
        	Coordonnees positionAgent = new Coordonnees(s.getAventurier().getPosition().getX() -1, s.getAventurier().getPosition().getY() );
            return Observation.newObservation(positionAgent, s);
        }
        else {
        	State s2 = new State(s);
        	if (a == Action.TIRERHAUT) {
        		if (s.getWumpus().getPosition().getY() > s.getAventurier().getPosition().getY()) {
                    s2.setMortWumpus(true);
                }
                return Observation.newObservation(s2.getAventurier().getPosition(), s2);
	        }

	        else if (a == Action.TIRERBAS) {
	        	if (s.getWumpus().getPosition().getY() < s.getAventurier().getPosition().getY()) {
                    s2.setMortWumpus(true);
                }
	        	return Observation.newObservation(s2.getAventurier().getPosition(), s2);

	        }
	        else if (a == Action.TIRERDROITE) {
	        	if (s.getWumpus().getPosition().getX() > s.getAventurier().getPosition().getX()) {
                    s2.setMortWumpus(true);
                }
	        	return Observation.newObservation(s2.getAventurier().getPosition(), s2);

	        }
	        else if (a == Action.TIRERGAUCHE) {
	        	if (s.getWumpus().getPosition().getX() < s.getAventurier().getPosition().getX()) {
                    s2.setMortWumpus(true);
                }
	        	return Observation.newObservation(s2.getAventurier().getPosition(), s2);
	        }
        }
    	
    	return null;
    }
}
