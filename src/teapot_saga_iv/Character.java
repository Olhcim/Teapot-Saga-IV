
package teapot_saga_iv;

import teapot_saga_iv.a_star.*;


public class Character extends Entity {
    
    int health = 100, damage = 2;
    
    boolean canMove = true;
    
    public Node path;
    
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
     * Fetches the health.
     * @return The current health of the character. 
     */
    public int getHealth()
    {
        return health;
    }
    
    public void damage(int d)
    {
        health -= (d > 0) ? d : 0;
    }
    
    public void heal(int d)
    {
        health += (d > 0) ? d : 0;
    }
    
    public boolean isDead()
    {
        if (health < 1)
        {
            return true;
        }
        return false;
    }
    
    
    /**
     * Sets whether this character can move or not.
     * @param a true or false.
     */
    public void setMoveAllowed(boolean a)
    {
        canMove = a;
    }
    
    /**
     * Returns true or false based on whether the character can move the the specified location.
     * @param a the x coordinate on the map.
     * @param b the y coordinate on the map.
     * @return true or false if the character can or cannot move.
     */
    public boolean canMove(int x, int y)
    {
        return Files.currentMap()[y][x] != '#' && Files.currentDisMap()[y][x] != '+' && canMove == true;
    }
    
    public boolean canMove(int dir)
    {        
        switch (dir)
            {
                // N:0  - E:2  - S:4  - W:6
                case 0: return canMove(x, y-1);
                case 2: return canMove(x+1, y);
                case 4: return canMove(x, y+1);
                case 6: return canMove(x-1, y);
                // NE:1 - SE:3 - SW:5 - NW:7
                case 1: return canMove(x+1, y-1);
                case 3: return canMove(x+1, y+1);
                case 5: return canMove(x-1, y+1);
                case 7: return canMove(x-1, y-1);
            }
        return false;
    }
    
    
    public boolean MoveAllowed()
    {
        return canMove;
    }
} 