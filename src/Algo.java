import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Algo {

    public static void main(String[] args) {
        State initState = new State();
        System.out.println(initState.toString());
        
        Observation o = initState.getAventurier().getObservation(initState.getAventurier().getPosition());
        System.out.println(o.toString());
        State copy = new Problem().transition(initState, Action.ALLERBAS);
        System.out.println(initState.toString());
        System.out.println(copy.toString());
    }
    
    public static void solution(Problem p, State s) {
    	
    }


    public Action nextMove(State s, Problem p) {
        ArrayList<Action> actionsPrio = new ArrayList<Action>();
        ArrayList<Action> actionsNonPrio = new ArrayList<Action>();
        if (!s.getAventurier().getWhereIsWumpus().contains(p.transition(s, Action.ALLERHAUT).getAventurier().getPosition())
                && !s.getAventurier().getWhereIsPuitU().contains(p.transition(s, Action.ALLERHAUT).getAventurier().getPosition())
                && !s.getAventurier().getWhereIsPuitD().contains(p.transition(s, Action.ALLERHAUT).getAventurier().getPosition())
                && !s.getAventurier().getPosition().isEqual(p.transition(s, Action.ALLERHAUT).getAventurier().getPosition())) {
            if (s.getAventurier().getObservation((p.transition(s, Action.ALLERHAUT).getAventurier().getPosition())) == null) {
                actionsPrio.add(Action.ALLERHAUT);
            } else actionsNonPrio.add(Action.ALLERHAUT);
        } else if (s.getAventurier().hasArrow()
                && s.getAventurier().getWhereIsWumpus().contains(p.transition(s, Action.ALLERHAUT).getAventurier().getPosition())) {
            actionsPrio.add(Action.TIRERHAUT);
        }
        if (!s.getAventurier().getWhereIsWumpus().contains(p.transition(s, Action.ALLERBAS).getAventurier().getPosition())
                && !s.getAventurier().getWhereIsPuitU().contains(p.transition(s, Action.ALLERBAS).getAventurier().getPosition())
                && !s.getAventurier().getWhereIsPuitD().contains(p.transition(s, Action.ALLERBAS).getAventurier().getPosition())
                && !s.getAventurier().getPosition().isEqual(p.transition(s, Action.ALLERBAS).getAventurier().getPosition())) {
            if (s.getAventurier().getObservation((p.transition(s, Action.ALLERBAS).getAventurier().getPosition())) == null) {
                actionsPrio.add(Action.ALLERBAS);
            } else actionsNonPrio.add(Action.ALLERBAS);
        } else if (s.getAventurier().hasArrow()
                && s.getAventurier().getWhereIsWumpus().contains(p.transition(s, Action.ALLERBAS).getAventurier().getPosition())) {
            actionsPrio.add(Action.TIRERBAS);
        }
        if (!s.getAventurier().getWhereIsWumpus().contains(p.transition(s, Action.ALLERDROITE).getAventurier().getPosition())
                && !s.getAventurier().getWhereIsPuitU().contains(p.transition(s, Action.ALLERDROITE).getAventurier().getPosition())
                && !s.getAventurier().getWhereIsPuitD().contains(p.transition(s, Action.ALLERDROITE).getAventurier().getPosition())
                && !s.getAventurier().getPosition().isEqual(p.transition(s, Action.ALLERDROITE).getAventurier().getPosition())) {
            if (s.getAventurier().getObservation((p.transition(s, Action.ALLERDROITE).getAventurier().getPosition())) == null) {
                actionsPrio.add(Action.ALLERDROITE);
            } else actionsNonPrio.add(Action.ALLERDROITE);
        } else if (s.getAventurier().hasArrow()
                && s.getAventurier().getWhereIsWumpus().contains(p.transition(s, Action.ALLERDROITE).getAventurier().getPosition())) {
            actionsPrio.add(Action.TIRERDROITE);
        }
        if (!s.getAventurier().getWhereIsWumpus().contains(p.transition(s, Action.ALLERGAUCHE).getAventurier().getPosition())
                && !s.getAventurier().getWhereIsPuitU().contains(p.transition(s, Action.ALLERGAUCHE).getAventurier().getPosition())
                && !s.getAventurier().getWhereIsPuitD().contains(p.transition(s, Action.ALLERGAUCHE).getAventurier().getPosition())
                && !s.getAventurier().getPosition().isEqual(p.transition(s, Action.ALLERGAUCHE).getAventurier().getPosition())) {
            if (s.getAventurier().getObservation((p.transition(s, Action.ALLERGAUCHE).getAventurier().getPosition())) == null) {
                actionsPrio.add(Action.ALLERGAUCHE);
            } else actionsNonPrio.add(Action.ALLERGAUCHE);
        } else if (s.getAventurier().hasArrow()
                && s.getAventurier().getWhereIsWumpus().contains(p.transition(s, Action.ALLERGAUCHE).getAventurier().getPosition())) {
            actionsPrio.add(Action.TIRERGAUCHE);
        }
    }




    
    private static Action agentModel(Observation o, Agent a) {
    	a.setObservation(o);
    	return null;
    }
}
