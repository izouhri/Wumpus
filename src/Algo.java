public class Algo {

    public static void main(String[] args) {
        State initState = new State(new Coordonnees(0,0), new Coordonnees(1,1), new Coordonnees(1,1), new Coordonnees(2,2), new Coordonnees(2,2) );
        System.out.print("Position des éléments :  Aventurier "+initState.getAventurier().getXY()+" Or "+initState.getOr().getXY()+" Wumpus "+initState.getwumpus().getXY()+" 1er trou "+initState.getTrous().get(0).getXY()+" 2eme trou "+initState.getTrous().get(1).getXY());
    }
}
