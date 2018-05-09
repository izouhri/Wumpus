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
    
    public Observation observation(State s, Action a, Agent agent) {

    	Coordonnees positionAgent = agent.getPosition();
    	if (a == Action.ALLERHAUT) {
    		Coordonnees newPositionAgent = new Coordonnees(positionAgent.getX(), positionAgent.getY() + 1);
            return Observation.newObservation(newPositionAgent, s);
        }
        else if (a == Action.ALLERBAS) {
        	Coordonnees newPositionAgent = new Coordonnees(positionAgent.getX(), positionAgent.getY() - 1);
            return Observation.newObservation(newPositionAgent, s);
        }
        else if (a == Action.ALLERDROITE) {
        	Coordonnees newPositionAgent = new Coordonnees(positionAgent.getX()+ 1, positionAgent.getY() );
            return Observation.newObservation(newPositionAgent, s);
        }
        else if (a == Action.ALLERGAUCHE) {
        	Coordonnees newPositionAgent = new Coordonnees(positionAgent.getX() -1, positionAgent.getY() );
            return Observation.newObservation(newPositionAgent, s);
        }
        else {
        	State s2 = new State(s);
        	Coordonnees positionWumpus = s.getWumpus().getPosition();
        	ArrayList<Coordonnees> whereIsWumpus = agent.getWhereIsWumpus();
        	if (a == Action.TIRERHAUT) {
        		if (positionWumpus.getY() > positionAgent.getY()) {
                    s2.setMortWumpus(true);
                }
        		for (Coordonnees c : whereIsWumpus) {
        			if (c.getY() == agent.getPosition().getY())
        				whereIsWumpus.remove(c);
        		}
                return Observation.newObservation(positionAgent, s2);
	        }

	        else if (a == Action.TIRERBAS) {
	        	if (positionWumpus.getY() < positionAgent.getY()) {
                    s2.setMortWumpus(true);
                }
	        	for (Coordonnees c : whereIsWumpus) {
        			if (c.getY() == agent.getPosition().getY())
        				whereIsWumpus.remove(c);
        		}
	        	return Observation.newObservation(positionAgent, s2);

	        }
	        else if (a == Action.TIRERDROITE) {
	        	if (positionWumpus.getX() > positionAgent.getX()) {
                    s2.setMortWumpus(true);
                }
	        	for (Coordonnees c : whereIsWumpus) {
        			if (c.getX() == agent.getPosition().getX())
        				whereIsWumpus.remove(c);
        		}
	        	return Observation.newObservation(positionAgent, s2);

	        }
	        else if (a == Action.TIRERGAUCHE) {
	        	if (positionWumpus.getX() < positionAgent.getX()) {
                    s2.setMortWumpus(true);
                }
	        	for (Coordonnees c : whereIsWumpus) {
        			if (c.getX() == agent.getPosition().getX())
        				whereIsWumpus.remove(c);
        		}
	        	return Observation.newObservation(positionAgent, s2);
	        }
        }
    	
    	return null;
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
