package teapot_saga_iv;


public class Character {
    
    public static int health = 100, x, y;
    private static boolean canMove = true;
    
    /**
     * Creates a character with 100 health.
     * x position is 0.
     * y position is 0.
     */
    public Character() {}
    
    /**
     * Creates a character with 100 health
     */
    public Character(int x, int y) {

    }

    /**
     * @param h the starting health of the character.
     * @param x the starting x coordinate of the character.
     * @param y the starting y coordinate of the character.
     */
    public Character(int h, int x, int y) {
        
        health = h;
        this.x = x;
        this.y = y;
    }
    
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
    
    /**
     * Fetches the health.
     * @return The current health of the character. 
     */
    public int getHealth()
    {
        return y;
    }
    
    /**
     * Sets whether this character can move or not.
     * @param a true or false.
     */
    public static void setMoveAllowed(boolean a)
    {
        canMove = a;
    }
    
    /**
     * Returns true or false based on whether the character can move the the specified location.
     * @param a the x coordinate on the map.
     * @param b the y coordinate on the map.
     * @return true or false if the character can or cannot move.
     */
    public static boolean canMove(int x, int y)
    {
        return Files.map[y][x] != '#' && Files.disMap[y][x] != '+' && canMove == true;
    }
    
    public static boolean MoveAllowed()
    {
        return canMove;
    }
    
    
} 