public class Wumpus {

    private Coordonnees position;
    private boolean mort;

    public Wumpus(Coordonnees position){
        this.position = position;
        this.mort = false;
    }
    
    public Wumpus(Wumpus wumpus) {
		this.position = new Coordonnees(wumpus.getPosition());
		this.mort = wumpus.isMort();
	}

    public Coordonnees getPosition() {
        return position;
    }

    public void setPosition(Coordonnees position) {
        this.position = position;
    }

    public boolean isMort() {
        return mort;
    }

    public void setMort(boolean mort) {
        this.mort = mort;
    }
}


