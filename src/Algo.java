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
        Queue<Action> actions = new PriorityQueue<Action>();
        if (s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERHAUT).getAventurier().getPosition())){
            actions.add(Action.ALLERHAUT);
        }
        if (s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERBAS).getAventurier().getPosition())){
            actions.add(Action.ALLERBAS);
        }
        if (s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERDROITE).getAventurier().getPosition())){
            actions.add(Action.ALLERDROITE);
        }
        if (s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERGAUCHE).getAventurier().getPosition())){
            actions.add(Action.ALLERGAUCHE);
        }
    }


    
    private static Action agentModel(Observation o, Agent a) {
    	a.setObservation(o);
    	return null;
    }
}
