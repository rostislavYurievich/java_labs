/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    public boolean equals(Location o){
        return (this.xCoord==o.xCoord)&&(this.yCoord==o.yCoord);
    }

    public int hashCode() {
        int result = 17; // Some prime value
        // Use another prime value to combine
        result = 37 * result + xCoord.hashCode();
        result = 37 * result + yCoord.hashCode();
        return result;
    }
}
