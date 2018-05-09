import java.util.ArrayList;

public class Algo {

	public Problem problem;
	
    public Algo(Problem p) {
		this.problem = p;
	}

	public static void main(String[] args) {
    	Algo a = new Algo(new Problem());
        State initState = new State(new Agent(3, 2), new Wumpus(2, 2), new Coordonnees(0, 1), new Coordonnees(1, 3), new Coordonnees(1, 1));
        //while (!a.problem.isResolvable(initState))
        //	initState = new State();
        System.out.println(initState.toString());
        Observation o = Observation.newObservation(initState.getAventurier().getPosition(), initState);
        System.out.println(o.toString());
        initState.getAventurier().setObservation(o);
        Action action = a.nextAction(initState);
        System.out.println(action);
        State oldState = initState;
        State newState = a.problem.transition(initState, action);
        System.out.println(newState.toString());
        int i = 0;
        boolean gameOver = a.gameOver(newState);
        boolean win = newState.getOr().equals(newState.getAventurier().getPosition());
        while (!win && !gameOver && i < 100) {
        	o = a.problem.observation(oldState, action, newState.getAventurier());
        	System.out.println(o.toString());
        	newState.getAventurier().setObservation(o);
        	action = a.nextAction(newState);
        	System.out.println(action);
        	oldState = newState;
            newState = a.problem.transition(newState, action);
            System.out.println(newState.toString());
            win = newState.getOr().equals(newState.getAventurier().getPosition());
            gameOver = a.gameOver(newState);
            i ++;
        }
        if (gameOver) {
            System.out.print("Game Over");
        }
        else if (win) {
            System.out.print("Bravo !");
        }
        else System.out.print("Game Impossible");
    }
	
	public boolean gameOver(State s) {
		Agent aventurier = s.getAventurier();
		Wumpus wumpus = s.getWumpus();
		if (aventurier.getPosition().equals(wumpus.getPosition())
			&& !wumpus.isMort())
		{
			return true;
		}
		if (aventurier.getPosition().equals(s.getPuits()[0])
			|| aventurier.getPosition().equals(s.getPuits()[1]))
		{
			return true;
		}
		return false;
	}

    public Action nextAction(State s) {
        // definition variables utiles      
        Agent agent = s.getAventurier();
        Coordonnees positionAgent = s.getAventurier().getPosition(); 
        
        Coordonnees enhaut = new Coordonnees(positionAgent);
        enhaut.setY(positionAgent.getY() + 1);
        Coordonnees enbas = new Coordonnees(positionAgent);
        enbas.setY(positionAgent.getY() - 1);
        Coordonnees adroite = new Coordonnees(positionAgent);
        adroite.setX(positionAgent.getX() + 1);
        Coordonnees agauche = new Coordonnees(positionAgent);
        agauche.setX(positionAgent.getX() - 1);
        
        // suppositions de la position du wumpus
        boolean wumpusEnHaut = agent.getWhereIsWumpus().contains(enhaut);
        boolean wumpusEnBas = agent.getWhereIsWumpus().contains(enbas);
        boolean wumpusAdroite = agent.getWhereIsWumpus().contains(adroite);
        boolean wumpusAgauche = agent.getWhereIsWumpus().contains(agauche);
        
        // suppositions de la position des puits
        boolean puitHaut = agent.getWhereIsPuitU().contains(enhaut)
        					|| agent.getWhereIsPuitD().contains(enhaut);
        boolean puitBas = agent.getWhereIsPuitU().contains(enbas)
							|| agent.getWhereIsPuitD().contains(enbas);
        boolean puisDroite = agent.getWhereIsPuitU().contains(adroite)
							|| agent.getWhereIsPuitD().contains(adroite);
        boolean puisGauche = agent.getWhereIsPuitU().contains(agauche)
							|| agent.getWhereIsPuitD().contains(agauche);
        
        // Decision pour les actions
        ArrayList<Action> actionsPrio = new ArrayList<Action>();
        ArrayList<Action> actionsNonPrio = new ArrayList<Action>();
        if (positionAgent.getY() < 3)
        {
            if (agent.getObservation(enhaut) == null && !wumpusEnHaut && !puitHaut)
                actionsPrio.add(Action.ALLERHAUT);
            else
            	actionsNonPrio.add(Action.ALLERHAUT);
        }
        if (positionAgent.getY() > 0)
        {
            if (agent.getObservation(enbas) == null && !wumpusEnBas && !puitBas)
                actionsPrio.add(Action.ALLERBAS);
            else
            	actionsNonPrio.add(Action.ALLERBAS);
        }
        if (positionAgent.getX() < 3)
        {
            if (agent.getObservation(adroite) == null && !wumpusAdroite && !puisDroite)
                actionsPrio.add(Action.ALLERDROITE);
            else
            	actionsNonPrio.add(Action.ALLERDROITE);
        }
        if (positionAgent.getX() > 0)
        {
            if (agent.getObservation(agauche) == null && !wumpusAgauche && !puisGauche)
                actionsPrio.add(Action.ALLERGAUCHE);
            else
            	actionsNonPrio.add(Action.ALLERGAUCHE);
        }
        if (actionsPrio.isEmpty()) {// lorsque preferable de tirer la fleche
        	if (s.getAventurier().hasArrow()) {
        		if (estEntoureWumpus(s, actionsNonPrio))
                {
                    if (actionsNonPrio.contains(Action.ALLERHAUT))
                    	actionsPrio.add(Action.TIRERHAUT);
                    if (actionsNonPrio.contains(Action.ALLERBAS))
                    	actionsPrio.add(Action.TIRERBAS);
                    if (actionsNonPrio.contains(Action.ALLERDROITE))
                    	actionsPrio.add(Action.TIRERDROITE);
                    if (actionsNonPrio.contains(Action.ALLERGAUCHE))
                    	actionsPrio.add(Action.TIRERGAUCHE);
                }
                else if (agent.getWhereIsWumpus().size() == 2) {
                	int i = 0;
                	while (actionsPrio.isEmpty() && i < 2) {
                		Coordonnees wumpusPosition = agent.getWhereIsWumpus().get(i);
                		if (wumpusPosition.getX() == positionAgent.getX()) {
        		            if (wumpusPosition.getY() > positionAgent.getY())
        		            	actionsPrio.add(Action.TIRERHAUT);
            		        else
            		        	actionsPrio.add(Action.TIRERBAS);
            		    }
                		else if (wumpusPosition.getY() == positionAgent.getY()) {
                			if (wumpusPosition.getX() > positionAgent.getX())
                				actionsPrio.add(Action.TIRERDROITE);
            		        else
            		        	actionsPrio.add(Action.TIRERGAUCHE);
                		}
                	}
                }
        	}
        }
        if (actionsPrio.isEmpty()) // aucune autre solution que aller vers deja vu ou danger
        	return actionsNonPrio.get(0);
        return actionsPrio.get(0);
    }

	private boolean estEntoureWumpus(State s, ArrayList<Action> possibleMoves) {// Si les positions possibles du Wumpus (en fonction des connaissances de l'agent) entourent l'agent
    	for (Action move : possibleMoves)
    	{
    		boolean dangerousMove = s.getAventurier().getWhereIsWumpus().contains(problem.transition(s, move).getAventurier().getPosition());
    		if (!dangerousMove)
    			return false;
    	}
    	return true;
    }
    
    private Action agentModel(Observation o, Agent a) {
    	a.setObservation(o);
    	return null;
    }
}
