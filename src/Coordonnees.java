public class Coordonnees {

    private int x;
    private int y;

    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Coordonnees(Coordonnees c) {
    	this.x = c.getX();
    	this.y = c.getY();
    }

    public int getX(){
        return this.x;
    }

    public void setX(int newX){
        this.x = newX;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int newY){
        this.y = newY;
    }
    
    public boolean isEqual(Coordonnees c)
    {
    	return c.getX() == this.x && c.getY() == this.y;
    }

    public String toString(){
        return "("+this.x+";"+this.y+")";
    }
}