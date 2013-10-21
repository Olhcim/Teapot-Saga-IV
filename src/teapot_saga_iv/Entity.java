
package teapot_saga_iv;


public class Entity {

    int x, y;
    
    String name;
    char symbol;
    
    public void update() {}
    
    /**
     * Sets the position of the character.
     * @param x the x position on the map
     * @param y the y position on the map.
     */
    public void setPos(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Fetches the x coordinate.
     * @return The current x coordinate of the character.
     */
    
    public int getX()
    {
        return x;
    }
    
    /**
     * Fetches the y coordinate.
     * @return The current y coordinate of the character. 
     */
    public int getY()
    {
        return y;
    }
    
    
    public String getType()
    {
        return name;
    }
    
    public char getSymbol()
    {
        return symbol;
    }
    
    
    public boolean isOverPlayer(int x, int y)
    {
        if (x == Main.getPlayer().getX() && y == Main.getPlayer().getY())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    static boolean isEntity(int x, int y)
    {
        for (Entity e : Files.currentMapData().getEntities())
        {
            if (e.x == x && e.y == y)
            {
                return true;
            }
        }
        return false;
    }
} 