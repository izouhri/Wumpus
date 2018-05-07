import java.util.ArrayList;

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

    //regles fonction qui prend etat en pararet et qui te dit la'action a efectuer

    public Action nextMove (State s, Problem p) {

        if (s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERHAUT).getAventurier().getPosition())){
            return Action.ALLERHAUT;
        }
        if (s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERBAS).getAventurier().getPosition())){
            return Action.ALLERBAS;
        }
        if (s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERDROITE).getAventurier().getPosition())){
            return Action.ALLERDROITE;
        }
        if (s.getAventurier().getWhereIsWumpus().contains(p.transition(s,Action.ALLERGAUCHE).getAventurier().getPosition())){
            return Action.ALLERGAUCHE;
        }
        else return Action.ALLERGAUCHE;
    }


    
    private static Action agentModel(Observation o, Agent a) {
    	a.setObservation(o);
    	return null;
    }
}
