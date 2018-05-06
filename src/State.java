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
        this.puits[0] = new Coordonnees(2,2);
        this.puits[1] = new Coordonnees(3,4);
        this.wumpus = new Coordonnees(1,3);
        this.or = new Coordonnees(3,3);
        this.aventurier = new Agent(0,0);
    }

    public State(State s)
    {
        this.puits = (Coordonnees[])s.getPuits().clone();
        this.wumpus = new Coordonnees(s.getWumpus());
        this.or = new Coordonnees(s.getOr());
        this.aventurier = new Coordonnees(s.getAventurier());
    }

    public ArrayList<Coordonnees> getPuits() {
        return puits;
    }
    
    public Agent getAventurier() {
        return aventurier;
    }

    public void setAventurier(Agent aventurier) {
        this.aventurier = aventurier;
    }

    public Coordonnees getWumpus() {
        return wumpus;
    }

    public void setWumpus(Coordonnees wumpus) {
        this.wumpus = wumpus;
    }


    public ArrayList<Coordonnees> getTrous() {
        return trous;
    }

    public Coordonnees getOr() {
        return or;
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
