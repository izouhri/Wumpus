import java.util.ArrayList;

public class State {
    private Coordonnees aventurier;
    private Coordonnees wumpus;
    private ArrayList<Coordonnees> trous;
    private Coordonnees or;

    public State(Coordonnees aventurier, Coordonnees wumpus, Coordonnees trous1, Coordonnees trou2, Coordonnees or) {
        this.trous = new ArrayList<Coordonnees>();
        this.trous.add(trous1);
        this.trous.add(trou2);
        this.wumpus = wumpus;
        this.or = or;
        this.aventurier = aventurier;
    }

    public State () {
        this.trous = new ArrayList<Coordonnees>();
        this.trous.add(new Coordonnees(2,2));
        this.trous.add(new Coordonnees(3,4));
        this.wumpus = new Coordonnees(1,3);
        this.or = new Coordonnees(3,3);
        this.aventurier = new Coordonnees(0,0);
    }

    public Coordonnees getAventurier() {
        return aventurier;
    }

    public ArrayList<Coordonnees> getTrous() {
        return trous;
    }

    public Coordonnees getOr() {
        return or;
    }

    public Coordonnees getwumpus() {
        return wumpus;
    }

    public void setAventurier(Coordonnees aventurier) {
        this.aventurier = aventurier;
    }

    public void setOr(Coordonnees or) {
        this.or = or;
    }

    public void setTrous(ArrayList<Coordonnees> trous) {
        this.trous = trous;
    }

    public void setwumpus(Coordonnees wumpus) {
        this.wumpus = wumpus;
    }
}
