public class Problem {

    private State transition(State s, Action a) {
        State s2 = new State(s);

        if (a == Action.ALLERHAUT) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() + 1));
        }
        if (a == Action.ALLERBAS) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() - 1));
        }
        if (a == Action.ALLERDROITE) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() + 1, s.getAventurier().getPosition().getY()));
        }
        if (a == Action.ALLERGAUCHE) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() - 1, s.getAventurier().getPosition().getY()));
        }
        if (a == Action.TIRERHAUT) {
            s2.getAventurier().setHasArrow(false);
            if (s.getWumpus().getPosition().getY() > s.getAventurier().getPosition().getY()) {
                s2.setMortWumpus(true);
            }
        }
        if (a == Action.TIRERBAS) {
            s2.getAventurier().setHasArrow(false);
            if (s.getWumpus().getPosition().getY() < s.getAventurier().getPosition().getY()) {
                s2.setMortWumpus(true);
            }
        }
        if (a == Action.TIRERDROITE) {
            s2.getAventurier().setHasArrow(false);
            if (s.getWumpus().getPosition().getX() > s.getAventurier().getPosition().getX()) {
                s2.setMortWumpus(true);
            }
        }
        if (a == Action.TIRERGAUCHE) {
            s2.getAventurier().setHasArrow(false);
            if (s.getWumpus().getPosition().getX() < s.getAventurier().getPosition().getX()) {
                s2.setMortWumpus(true);
            }
        }
        return s2;
    }
}
    //To do

