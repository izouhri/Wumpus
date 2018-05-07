import java.util.Random;

public class State {
    private Agent aventurier;
    private Wumpus wumpus;
    private Coordonnees[] puits = new Coordonnees[2];
    private Coordonnees or;

    // constructeur pour debug
    public State(Agent aventurier, Wumpus wumpus, Coordonnees puitU, Coordonnees puitD, Coordonnees or) {
        this.puits[0] = puitU;
        this.puits[1] = puitD;
        this.wumpus = wumpus;
        this.or = or;
        this.aventurier = aventurier;
        
        boolean odeur = this.aventurier.getPosition().isVoisin(this.wumpus.getPosition());
    	boolean air = this.aventurier.getPosition().isVoisin(puits[0])
    					|| this.aventurier.getPosition().isVoisin(puits[1]);
    	this.aventurier.setObservation(new Observation(this.aventurier.getPosition(), false, air, false, odeur, false));
    }

    // constructeur pour generer un etat initial aleatoire
    public State() {
    	Random r = new Random();
    	this.wumpus = new Wumpus(new Coordonnees(r.nextInt(4), r.nextInt(4)));
    	do
    	{
    		this.puits[0] = new Coordonnees(r.nextInt(4), r.nextInt(4));
    	} while (this.puits[0].isEqual(this.wumpus.getPosition()));
    	do
    	{
    		this.puits[1] = new Coordonnees(r.nextInt(4), r.nextInt(4));
    	} while (this.puits[1].isEqual(this.wumpus.getPosition())
    			|| this.puits[1].isEqual(this.puits[0]));
    	do
    	{
    		this.or = new Coordonnees(r.nextInt(4), r.nextInt(4));
    	} while (this.or.isEqual(this.wumpus.getPosition())
    			|| this.or.isEqual(this.puits[0])
    			|| this.or.isEqual(this.puits[1]));
    	do
    	{
    		this.aventurier = new Agent(new Coordonnees(r.nextInt(4), r.nextInt(4)));
    	} while (this.puits[0].isEqual(this.aventurier.getPosition())
    			|| this.puits[1].isEqual(this.aventurier.getPosition())
    			|| this.or.isEqual(this.aventurier.getPosition())
    			|| this.wumpus.getPosition().isEqual(this.aventurier.getPosition()));
    	
    	boolean odeur = this.aventurier.getPosition().isVoisin(this.wumpus.getPosition());
    	boolean air = this.aventurier.getPosition().isVoisin(puits[0])
    				|| this.aventurier.getPosition().isVoisin(puits[1]);
    	this.aventurier.setObservation(new Observation(this.aventurier.getPosition(), false, air, false, odeur, false));
    }

    //constructeur par copie pour fonction de transition
    public State(State s)
    {
        this.puits = (Coordonnees[])s.getPuits().clone();
        this.wumpus = s.getWumpus();
        this.or = new Coordonnees(s.getOr());
        this.aventurier = new Agent(s.getAventurier());
    }

    // setters et getters
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
    
    public String toString() {
    	return "Position des elements :  Aventurier "+this.aventurier.getPosition().toString()+" 1er trou "+this.puits[0].toString()+" 2eme trou "+this.puits[1].toString()+" Or "+this.or.toString()+" Wumpus "+this.wumpus.getPosition().toString();
    }
}
