public class Problem {

    public State transition(State s, Action a) {
        State s2 = new State(s);

        if (a == Action.ALLERHAUT) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() + 1));
        }
        else if (a == Action.ALLERBAS) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() - 1));
        }
        else if (a == Action.ALLERDROITE) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() + 1, s.getAventurier().getPosition().getY()));
        }
        else if (a == Action.ALLERGAUCHE) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() - 1, s.getAventurier().getPosition().getY()));
        }
        else if (a == Action.TIRERHAUT) {
            s2.getAventurier().setArrow(false);
            if (s.getWumpus().getPosition().getY() > s.getAventurier().getPosition().getY()) {
                s2.setMortWumpus(true);
            }
        }
        else if (a == Action.TIRERBAS) {
            s2.getAventurier().setArrow(false);
            if (s.getWumpus().getPosition().getY() < s.getAventurier().getPosition().getY()) {
                s2.setMortWumpus(true);
            }
        }
        else if (a == Action.TIRERDROITE) {
            s2.getAventurier().setArrow(false);
            if (s.getWumpus().getPosition().getX() > s.getAventurier().getPosition().getX()) {
                s2.setMortWumpus(true);
            }
        }
        else if (a == Action.TIRERGAUCHE) {
            s2.getAventurier().setArrow(false);
            if (s.getWumpus().getPosition().getX() < s.getAventurier().getPosition().getX()) {
                s2.setMortWumpus(true);
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

                return s2.getAventurier.getPosition();
	        
                return Observation.newObservation(s2.getAventurier().getPosition(), s2);
	        }

	        else if (a == Action.TIRERBAS) {
	        	if (s.getWumpus().getPosition().getY() < s.getAventurier().getPosition().getY()) {
                    s2.setMortWumpus(true);
                }

	        	return s2.getAventurier.getPosition();

	        	return Observation.newObservation(s2.getAventurier().getPosition(), s2);

	        }
	        else if (a == Action.TIRERDROITE) {
	        	if (s.getWumpus().getPosition().getX() > s.getAventurier().getPosition().getX()) {
                    s2.setMortWumpus(true);
                }

	        	return s2.getAventurier.getPosition();

	        	return Observation.newObservation(s2.getAventurier().getPosition(), s2);

	        }
        	
        	
	        else if (a == Action.TIRERGAUCHE) {
	        	if (s.getWumpus().getPosition().getX() < s.getAventurier().getPosition().getX()) {
                    s2.setMortWumpus(true);
                }
	        	return s2.getAventurier().getPosition();
	        	
	        	return Observation.newObservation(s2.getAventurier().getPosition(), s2);
	        }
        	//condition qui l'empechera d'avancer s'il se retrouve a 3 ou 0 en fonction de l'axe
	        else if (a== Action.ALLERHAUT && s.getAventurier().getPosition().getY() < 3) {
                whereIsWumpus.add(new Coordonnees(getAventurier().getPosition().getX(), getAventurier().getPosition().getY()));
	        }
	        else if (a== Action.ALLERBAS && s.getAventurier().getPosition().getY()  > 0) {
                whereIsWumpus.add(new Coordonnees(s.getAventurier().getPosition().getX() - 1, s.getAventurier().getPosition().getY()));
	        }
            else if (a== Action.ALLERDROITE && s.getAventurier().getPosition().getX()  < 3) {
                whereIsWumpus.add(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() + 1));
            }
            else if (a== Action.ALLERGAUCHE && s.getAventurier().getPosition().getX() > 0) {
                whereIsWumpus.add(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() - 1));
            }
        }
    	
    	return null;
    }
}
