public class Algo {

    public static void main(String[] args) {
        State initState = new State();
        System.out.print("Position des elements :  Aventurier "+initState.getAventurier().getPosition().toString()+" Or "+initState.getOr().toString()+" Wumpus "+initState.getWumpus().toString()+" 1er trou "+initState.getPuits()[0].toString()+" 2eme trou "+initState.getPuits()[1].toString());
    }
}
