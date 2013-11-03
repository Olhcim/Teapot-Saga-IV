
package teapot_saga_iv;

import teapot_saga_iv.a_star.*;


public class Character extends Entity {
    
    int health = 100, damage = 2;
    
    public Node path;
    

    
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
} 