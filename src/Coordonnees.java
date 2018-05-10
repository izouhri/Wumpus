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
    
    @Override
    public boolean equals(Object coordonnees)
    {
    	Coordonnees c = (Coordonnees) coordonnees;
    	if (c == null)
    		return false;
    	return c.getX() == this.x && c.getY() == this.y;
    }
    
    public boolean isVoisin(Coordonnees c)
    {
    	if (c == null)
    		return false;
    	return c.getX() == this.x && (c.getY() - 1 == this.y || c.getY() + 1 == this.y)
    			|| c.getY() == this.y && (c.getX() - 1 == this.x || c.getX() + 1 == this.x);
    }
    
    public boolean closer(Coordonnees rival, Coordonnees goal) {
		int distanceX = this.x - goal.getX();
		if (distanceX < 0)
			distanceX *= -1;
		int rivalDistanceX = rival.getX() - goal.getX();
		if (rivalDistanceX < 0)
			rivalDistanceX *= -1;
		int distanceY = this.y - goal.getY();
		if (distanceY < 0)
			distanceY *= -1;
		int rivalDistanceY = rival.getY() - goal.getY();
		if (rivalDistanceY < 0)
			rivalDistanceY *= -1;    
		if ((distanceX == rivalDistanceX && distanceY < rivalDistanceY)
				|| (distanceY == rivalDistanceY && distanceX < rivalDistanceX))
		{
			return true;
		}
		else if (distanceX + distanceY < rivalDistanceX + rivalDistanceY)
			return true;

		return false;
	}

    public String toString(){
        return "("+this.x+";"+this.y+")";
    }
}