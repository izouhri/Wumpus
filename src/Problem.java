import java.util.ArrayList;

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
    
    public Observation observation(State s, Action a) {
    	Coordonnees positionAgent = s.getAventurier().getPosition();
    	ArrayList<Coordonnees> whereIsWumpus = s.getAventurier().getWhereIsWumpus();
    	if (a == Action.TIRERHAUT || a == Action.TIRERBAS) {
    		int i = 0;
    		while (i < whereIsWumpus.size()) {// mise a jour des connaissance de l'agent sur la position du wumpus (dans le cas ou le tire de fleche permet une deduction)
    			Coordonnees supposition = whereIsWumpus.get(i);
    			if (supposition.getX() == positionAgent.getX())
    				whereIsWumpus.remove(supposition);
    			else
    				i++;
    		}
            return Observation.newObservation(positionAgent, s);
        }
        else if (a == Action.TIRERDROITE || a == Action.TIRERGAUCHE) {
        	int i = 0;
    		while (i < whereIsWumpus.size()) {
    			Coordonnees supposition = whereIsWumpus.get(i);
    			if (supposition.getY() == positionAgent.getY())
    				whereIsWumpus.remove(supposition);
    			else
    				i++;
    		}
        	return Observation.newObservation(positionAgent, s);
        }
        else
        	return Observation.newObservation(positionAgent, s);
    }
    
    public boolean isResolvable(State initState) {
    	Coordonnees positionOr = initState.getOr();
    	Coordonnees[] positionsPuits = initState.getPuits();
    	if (positionOr.getX() == 0 || positionOr.getX() == 3)
			if (positionOr.getY() == 0 || positionOr.getY() == 3)
				if (positionOr.isVoisin(positionsPuits[0]) && positionOr.isVoisin(positionsPuits[1]))
					return false;
    	Coordonnees positionAgent = initState.getAventurier().getPosition();
    	if (positionAgent.getX() == 0 || positionAgent.getX() == 3)
			if (positionAgent.getY() == 0 || positionAgent.getY() == 3)
				if (positionAgent.isVoisin(positionsPuits[0]) && positionAgent.isVoisin(positionsPuits[1]))
					return false;
    	return true;
    }
}
