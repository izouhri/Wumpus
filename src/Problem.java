public class Problem {

    public State transition(State s, Action a) {
        State s2 = new State(s);

        if (a == Action.ALLERHAUT) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() + 1));
        }
        else if (a == Action.ALLERBAS) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() - 1));
        }
        else if (a == Action.ALLERDROITE) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() + 1, s.getAventurier().getPosition().getY()));
        }
        else if (a == Action.ALLERGAUCHE) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() - 1, s.getAventurier().getPosition().getY()));
        }
        else if (a == Action.TIRERHAUT) {
            s2.getAventurier().setArrow(false);
            if (s.getWumpus().getPosition().getY() > s.getAventurier().getPosition().getY()) {
                s2.setMortWumpus(true);
            }
        }
        else if (a == Action.TIRERBAS) {
            s2.getAventurier().setArrow(false);
            if (s.getWumpus().getPosition().getY() < s.getAventurier().getPosition().getY()) {
                s2.setMortWumpus(true);
            }
        }
        else if (a == Action.TIRERDROITE) {
            s2.getAventurier().setArrow(false);
            if (s.getWumpus().getPosition().getX() > s.getAventurier().getPosition().getX()) {
                s2.setMortWumpus(true);
            }
        }
        else if (a == Action.TIRERGAUCHE) {
            s2.getAventurier().setArrow(false);
            if (s.getWumpus().getPosition().getX() < s.getAventurier().getPosition().getX()) {
                s2.setMortWumpus(true);
            }
        }
        return s2;
    }
}
    //To do

