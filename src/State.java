import java.util.ArrayList;

public class State {
    private Agent aventurier;
    private Coordonnees wumpus;
    private ArrayList<Coordonnees> trous;
    private Coordonnees or;

    public State(Coordonnees coordonnees_aventurier, Coordonnees coordonnees_wumpus, Coordonnees coordonnees_trous1, Coordonnees coordonnees_trou2, Coordonnees coordonnees_or) {
        this.trous = new ArrayList<Coordonnees>();
        this.trous.add(coordonnees_trous1);
        this.trous.add(coordonnees_trou2);
        this.wumpus = coordonnees_wumpus;
        this.or = coordonnees_or;
        this.aventurier = new Agent(coordonnees_aventurier);
    }

    public State () {
        this.trous = new ArrayList<Coordonnees>();
        this.trous.add(new Coordonnees(2,2));
        this.trous.add(new Coordonnees(3,4));
        this.wumpus = new Coordonnees(1,3);
        this.or = new Coordonnees(3,3);
        this.aventurier = new Agent(new Coordonnees(0,0));
    }

    public State(State s)
    {
        this.trous = s.trous;
        this.wumpus = s.wumpus;
        this.or = s.or;
        this.aventurier = s.aventurier;
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

    public void setTrous(ArrayList<Coordonnees> trous) {
        this.trous = trous;
    }

}
