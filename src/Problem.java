public class Problem {

    private State transition(State s, String a) {
        State s2 = new State(s);

        if (a == "ALLERHAUT") {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() + 1));
        }
        if (a == "ALLERBAS") {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() - 1));
        }
        if (a == "ALLERDROITE") {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() + 1, s.getAventurier().getPosition().getY()));
        }
        if (a == "ALLERGAUCHE") {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() - 1, s.getAventurier().getPosition().getY()));
        }
        if (a == "TIRERHAUT") {
            s2.getAventurier().setArrow(false);
        }
        return s2;
    }
}
    //To do

