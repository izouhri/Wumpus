public class Wumpus {

    private Coordonnees position;
    private boolean mort;

    public Wumpus (Coordonnees coordonnees){
        this.position = coordonnees;
        this.mort = false;
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


