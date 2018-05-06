import java.util.ArrayList;

public class State {
    private Agent aventurier;
    private final Coordonnees wumpus;
    private final Coordonnees[] puits = new Cordonnees[2];
    private final Coordonnees or;

    public State(Agent aventurier, Coordonnees wumpus, Coordonnees puitU, Coordonnees puitD, Coordonnees or) {
        this.puits[0] = puitU;
        this.puits[1] = puitD;
        this.wumpus = wumpus;
        this.or = or;
        this.aventurier = aventurier;
    }

    public State () {
        this.trous = new ArrayList<Coordonnees>();
        this.puits[0] = new Coordonnees(2,2);
        this.puits[1] = new Coordonnees(3,4);
        this.wumpus = new Coordonnees(1,3);
        this.or = new Coordonnees(3,3);
        this.aventurier = new Agent(0,0);
    }

    public Coordonnees getAventurier() {
        return aventurier;
    }

    public ArrayList<Coordonnees> getPuits() {
        return puits;
    }

    public Coordonnees getOr() {
        return or;
    }

    public Coordonnees getWumpus() {
        return wumpus;
    }

    public void setAventurier(Coordonnees aventurier) {
        this.aventurier = aventurier;
    }

    public void setOr(Coordonnees or) {
        this.or = or;
    }

    public void setPuits(Coordonnees> puits) {
        this.puits = puits;
    }

    public void setWumpus(Coordonnees wumpus) {
        this.wumpus = wumpus;
    }
}
