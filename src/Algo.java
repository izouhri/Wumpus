public class Algo {

    public static void main(String[] args) {
        State initState = new State();
        System.out.print("Position des éléments :  Aventurier "+initState.getAventurier().getXY()+" Or "+initState.getOr().getXY()+" Wumpus "+initState.getWompus().getXY()+" 1er trou "+initState.getTrous().get(0).getXY()+" 2eme trou "+initState.getTrous().get(1).getXY());
    }
}
