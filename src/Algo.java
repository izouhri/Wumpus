import java.util.ArrayList;

import com.sun.xml.internal.ws.addressing.model.ActionNotSupportedException;

public class Algo {

	public Problem problem;
	
    public Algo(Problem p) {
		this.problem = p;
	}

	public static void main(String[] args) {
    	Algo a = new Algo(new Problem());
        //State initState = new State(new Agent(2, 0), new Wumpus(1, 0), new Coordonnees(3, 3), new Coordonnees(0, 2), new Coordonnees(3, 0));
    	State initState = new State();
        while (!a.problem.isResolvable(initState))
        	initState = new State();
        System.out.println(initState.toString());
        Observation o = Observation.newObservation(initState.getAventurier().getPosition(), initState);
        System.out.println(o.toString());
        initState.getAventurier().setObservation(o);
        Action action = a.nextAction(initState);
        System.out.println(action);
        State state = a.problem.transition(initState, action);
        System.out.println(state.toString());
        int i = 0;
        boolean gameOver = a.gameOver(state);
        boolean win = state.getOr().equals(state.getAventurier().getPosition());
        while (!win && !gameOver && i < 100) {
        	o = a.problem.observation(state, action);
        	System.out.println(o.toString());
        	state.getAventurier().setObservation(o);
        	a.actualiserMemoireCoups(action);
        	action = a.nextAction(state);
        	System.out.println(action);
            state = a.problem.transition(state, action);
            System.out.println(state.toString());
            win = state.getOr().equals(state.getAventurier().getPosition());
            gameOver = a.gameOver(state);
            i ++;
        }
        if (gameOver) {
            System.out.print("Game Over");
        }
        else if (win) {
            System.out.print("Bravo ! Problème résolu en " + i + " coups.");
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

	Action lastAction = null;
	Action actionTwoTimesAgo = null;
	Action actionThreeTimesAgo = null;
	Action actionFourTimesAgo = null;
	public void actualiserMemoireCoups(Action action) {
		actionFourTimesAgo = actionThreeTimesAgo;
		actionThreeTimesAgo = actionTwoTimesAgo;
		actionTwoTimesAgo = lastAction;
		lastAction = action;
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
        ArrayList<Action> actionsDanger = new ArrayList<Action>();
        if (positionAgent.getY() < 3)
        {
            if (agent.getObservation(enhaut) == null && !wumpusEnHaut && !puitHaut)
                actionsPrio.add(Action.ALLERHAUT);
            else if (wumpusEnHaut || puitHaut)
            	actionsDanger.add(Action.ALLERHAUT);
            else
            	actionsNonPrio.add(Action.ALLERHAUT);
        }
        if (positionAgent.getX() > 0)
        {
            if (agent.getObservation(agauche) == null && !wumpusAgauche && !puisGauche)
                actionsPrio.add(Action.ALLERGAUCHE);
            else if (wumpusAgauche || puisGauche)
            	actionsDanger.add(Action.ALLERGAUCHE);
            else
            	actionsNonPrio.add(Action.ALLERGAUCHE);
        }
        if (positionAgent.getY() > 0)
        {
            if (agent.getObservation(enbas) == null && !wumpusEnBas && !puitBas)
                actionsPrio.add(Action.ALLERBAS);
            else if (wumpusEnBas || puitBas)
            	actionsDanger.add(Action.ALLERBAS);
            else
            	actionsNonPrio.add(Action.ALLERBAS);
        }
        if (positionAgent.getX() < 3)
        {
            if (agent.getObservation(adroite) == null && !wumpusAdroite && !puisDroite)
                actionsPrio.add(Action.ALLERDROITE);
            else if (wumpusAdroite || puisDroite)
            	actionsDanger.add(Action.ALLERDROITE);
            else
            	actionsNonPrio.add(Action.ALLERDROITE);
        }
        if (s.getAventurier().hasArrow()) { // lorsque peut etre preferable de tirer la fleche
        	if (actionsPrio.isEmpty() && actionsNonPrio.isEmpty()) {
        		if (estEntoureWumpus(s, actionsDanger))
                {
                    if (actionsDanger.contains(Action.ALLERHAUT))
                    	actionsPrio.add(Action.TIRERHAUT);
                    if (actionsDanger.contains(Action.ALLERBAS))
                    	actionsPrio.add(Action.TIRERBAS);
                    if (actionsDanger.contains(Action.ALLERDROITE))
                    	actionsPrio.add(Action.TIRERDROITE);
                    if (actionsDanger.contains(Action.ALLERGAUCHE))
                    	actionsPrio.add(Action.TIRERGAUCHE);
                }
        	}
	        else if (actionsPrio.isEmpty() && agent.getWhereIsWumpus().size() <= 2)
	        {
	        	int i = 0;
	        	while (actionsPrio.isEmpty() && i < agent.getWhereIsWumpus().size())
	        	{
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
        if (actionsPrio.isEmpty()) // aucune autre solution que aller vers deja vu ou danger
        	if (agent.wumpusPosition != null
	    		&& agent.puitUPosition != null
	    		&& agent.puitDPosition != null)
		    {
		    	return trouverOr(s, actionsNonPrio);
		    }
        	else if (actionsNonPrio.isEmpty()
    			|| (actionsNonPrio.get(0) == actionFourTimesAgo && !actionsDanger.isEmpty()))
        	{
        		return actionsDanger.get(0);
        	}
        	else {
        		if (lastAction == Action.ALLERHAUT && actionsNonPrio.contains(Action.ALLERHAUT))
        			return Action.ALLERHAUT;
        		else if (lastAction == Action.ALLERHAUT && actionsNonPrio.contains(Action.ALLERGAUCHE))
        			return Action.ALLERGAUCHE;
        		else if (lastAction == Action.ALLERHAUT && actionsNonPrio.contains(Action.ALLERDROITE))
        			return Action.ALLERDROITE;
        		if (lastAction == Action.ALLERGAUCHE && actionsNonPrio.contains(Action.ALLERGAUCHE))
        			return Action.ALLERGAUCHE;
        		else if (lastAction == Action.ALLERGAUCHE && actionsNonPrio.contains(Action.ALLERBAS))
        			return Action.ALLERBAS;
        		else if (lastAction == Action.ALLERGAUCHE && actionsNonPrio.contains(Action.ALLERHAUT))
        			return Action.ALLERHAUT;
        		if (lastAction == Action.ALLERBAS && actionsNonPrio.contains(Action.ALLERBAS))
        			return Action.ALLERBAS;
        		else if (lastAction == Action.ALLERBAS && actionsNonPrio.contains(Action.ALLERDROITE))
        			return Action.ALLERDROITE;
        		else if (lastAction == Action.ALLERBAS && actionsNonPrio.contains(Action.ALLERGAUCHE))
        			return Action.ALLERGAUCHE;
        		if (lastAction == Action.ALLERDROITE && actionsNonPrio.contains(Action.ALLERDROITE))
        			return Action.ALLERDROITE;
        		else if (lastAction == Action.ALLERDROITE && actionsNonPrio.contains(Action.ALLERHAUT))
        			return Action.ALLERHAUT;
        		else if (lastAction == Action.ALLERDROITE && actionsNonPrio.contains(Action.ALLERBAS))
        			return Action.ALLERBAS;
        		
    			return actionsNonPrio.get(0);
        	}
        
        if (actionsPrio.contains(lastAction))
        	return lastAction;
        return actionsPrio.get(0);
    }

    private Action trouverOr(State s, ArrayList<Action> possibleMoves) {
		Agent a = s.getAventurier();
		Coordonnees unexplored = null;
		int x = 0;
		int y = 0;
		while (unexplored == null)
		{
			Observation o = a.getObservation(x, y);
			if (o == null)
				unexplored = new Coordonnees (x, y);
			x++;
			if (x == 4) {
				x = 0;
				y++;
			}
		}
		Coordonnees bestGoal = a.getPosition();
		Action bestMove = possibleMoves.get(possibleMoves.size() - 1);
		for (Action move : possibleMoves)
    	{
			Coordonnees goal = problem.transition(s, move).getAventurier().getPosition();
			if (goal.closer(bestGoal, unexplored)) {
				bestMove = move;
				bestGoal = goal;
			}
    	}
		return bestMove;
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
}
