import java.util.ArrayList;

public class State {
    private Agent aventurier;

    private Wumpus wumpus;
    private Coordonnees[] puits = new Coordonnees[2];
    private Coordonnees or;

    public State(Agent aventurier, Wumpus wumpus, Coordonnees puitU, Coordonnees puitD, Coordonnees or) {
        this.puits[0] = puitU;
        this.puits[1] = puitD;
        this.wumpus = wumpus;
        this.or = or;
        this.aventurier = aventurier;
    }

    public State () {
        this.puits[0] = new Coordonnees(2,2);
        this.puits[1] = new Coordonnees(3,4);
        this.wumpus = new Wumpus(new Coordonnees(1,1));
        this.or = new Coordonnees(3,3);
        this.aventurier = new Agent(new Coordonnees(0,0));
    }

    public State(State s)
    {
        this.puits = (Coordonnees[])s.getPuits().clone();
        this.wumpus = s.getWumpus();
        this.or = new Coordonnees(s.getOr());
        this.aventurier = s.getAventurier();
    }

    public void setPositionAgent(Coordonnees new_coordonnees) {
        this.aventurier.setPosition(new_coordonnees);
    }

    public Agent getAventurier() {
        return aventurier;
    }

    public void setAventurier(Agent aventurier) {
        this.aventurier = aventurier;
    }

    public Wumpus getWumpus() {
        return wumpus;
    }

    public void setWumpus(Wumpus wumpus) {
        this.wumpus = wumpus;
    }

    public void setMortWumpus(boolean etat){
        this.wumpus.setMort(etat);
    }

    public Coordonnees[] getPuits() {
        return puits;
    }

    public Coordonnees getOr() {
        return or;
    }

    public void setOr(Coordonnees or) {
        this.or = or;
    }
}
