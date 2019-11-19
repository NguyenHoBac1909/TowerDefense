package Game;

public class Coordinate
{
    public double x;
    public double y;
    public boolean PlaceTowerStatus = true;
    public Coordinate()
    {

    }
    public Coordinate(double x,double y)
    {
        this.x = x;
        this.y = y;
    }
    public double getX()
    {
        return this.x;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public double getY()
    {
        return this.y;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public boolean checkPlaceTower()
    {
        return PlaceTowerStatus;
    }
}
