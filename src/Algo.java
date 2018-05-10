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
    
    private static Action agentModel(Observation o, Agent a) {
    	a.setObservation(o);
    	return null;
    }
    
    
}
