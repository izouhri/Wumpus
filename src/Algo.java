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
        if (!s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERHAUT).getAventurier().getPosition())){
            if (s.getAventurier().getObservation((p.transition(s,Action.ALLERHAUT).getAventurier().getPosition())) == null){
                actions.add(Action.ALLERHAUT); //mettre en priorité
            }
            // else Mettre allzeHaut pas priorité
        }
        if (!s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERBAS).getAventurier().getPosition())){
            if (s.getAventurier().getObservation((p.transition(s,Action.ALLERBAS).getAventurier().getPosition())) == null){
                actions.add(Action.ALLERBAS);
            }
            // else pas en priorité actions.add(Action.ALLERBAS);
        }
        if (!s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERDROITE).getAventurier().getPosition())){
            if (s.getAventurier().getObservation((p.transition(s,Action.ALLERDROITE).getAventurier().getPosition())) == null){
                actions.add(Action.ALLERDROITE);
            }
            //else pas priorité actions.add(Action.ALLERDROITE);
        }
        if (!s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERGAUCHE).getAventurier().getPosition())){
            if (s.getAventurier().getObservation((p.transition(s,Action.ALLERGAUCHE).getAventurier().getPosition())) == null){
                actions.add(Action.ALLERGAUCHE);
            }
            // pas en priorité actions.add(Action.ALLERGAUCHE);
        }
        // Si aucune priorité

    }


    
    private static Action agentModel(Observation o, Agent a) {
    	a.setObservation(o);
    	return null;
    }
}
