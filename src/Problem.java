public class Problem {
    private String[] action_deplacement() {
        String deplacement[] = {"guauche", "droite", "bas", "haut"};
        return deplacement;
    }

    private String[] action_tirer_fleche() {
        String tirer_fleche[] = {"guauche", "droite", "bas", "haut"};
        return tirer_fleche;
    }

    private State transition(State s, String a) {
        State s2 = new State(s);

        if (a == Action.ALLERHAUT) {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() + 1));
        }
        if (a == Action.ALLERBAS") {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX(), s.getAventurier().getPosition().getY() - 1));
        }
        if (a == Action.ALLERDROITE") {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() + 1, s.getAventurier().getPosition().getY()));
        }
        if (a == Action.ALLERGAUCHE") {
            s2.setPositionAgent(new Coordonnees(s.getAventurier().getPosition().getX() - 1, s.getAventurier().getPosition().getY()));
        }
        if (a == Action.TIRERHAUT") {
            s2.getAventurier().setHasArrow(false);
            if (s.getWumpus().getY() == s.getAventurier().getPosition().getY()) {

            }
        }
        return s2;
    }
}
    //To do

