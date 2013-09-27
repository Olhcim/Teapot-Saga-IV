package teapot_saga_iv;


public class Player extends Character{
    
    public int moves = 0;
    
    public int getMoves()
    {
        return moves;
    }
    
    /**
     * Moves the player to the start but does not update the GUI.
     */
    public void goToStart()
    {
        setPos(Files.startX, Files.startY);
    }
    
    
    /**
     * Moves the player to the exit but does not update the GUI.
     */
    public void goToExit()
    {
        setPos(Files.exitX, Files.exitY);
    }
    
    /**
     * Checks for and uses a staircase, does not allow the use of a staircase if the player is currently on one.
     */
    public void useStaircase()
    {
        if(isOnStaircase())
        {
            checkForStairCase();
        }
    }
    
    /**
     * Checks weather the current position is over an entrance or exit and follows the appropriate actions..
     */
     private boolean isOnStaircase()
    {
        return isAtEntrance() || isAtExit();
    }
    
    /**
     * Checks weather the current position is over an entrance or exit and follows the appropriate actions..
     */
    private void checkForStairCase()
    {
        if(isAtExit())
        {
            Main.NextMap();
        }
        else if (isAtEntrance())
        {
            Main.PrevMap();
        }
    }
    
    /**
     * Checks weather the current position is over an entrance.
     */
    private boolean isAtEntrance()
    {
        return Files.map[y][x] == '<';
    }
    
    /**
     * Checks weather the current position is over an exit.
     */
    private boolean isAtExit()
    {
        return Files.map[y][x] == '>';
    }
    
    /**
     * recieves the keycodes of keys pressed from Window.class
     */
    public void move(int a)
    {
        switch (a)
        {
            case 65:
            case 37:
                if(canMove(x-1, y)) { Main.doPreGameTick(); x--; moves++; Main.doAfterGameTick();}
                break;
            case 87:
            case 38:
                if(canMove(x, y-1)) { Main.doPreGameTick(); y--; moves++; Main.doAfterGameTick();}
                break;
            case 68:
            case 39:
                if(canMove(x+1, y)) { Main.doPreGameTick(); x++; moves++; Main.doAfterGameTick();}
                break;
            case 83:
            case 40:
                if(canMove(x, y+1)) { Main.doPreGameTick(); y++; moves++; Main.doAfterGameTick();}
                break;
            case 79:
                useDoor();
                break;
        }
    }
    
    /**
     * checks for a door and switches the state of the door if it exists.
     */
    private void useDoor()
    {
        if(doorAt(0,1))
        {
            openDoor(0,1);
        } else if (doorAt(0,-1))
        {
            openDoor(0,-1);
        } else if (doorAt(1,0))
        {
            openDoor(1,0);
        } else if (doorAt(-1,0))
        {
            openDoor(-1,0);
        }
    }
    
    /**
     * Switches the state of a door between open and closed.
     * 
     * @param x The x position of the door
     * @param y The y position of the door
     */
    private void openDoor(int x, int y)
    {
        if      (Files.disMap[this.y+y][this.x+x] == '+')
        {
            Files.disMap[this.y+y][this.x+x] = 'X';
        }
        else if (Files.disMap[this.y+y][this.x+x] == 'X')
        {
            Files.disMap[this.y+y][this.x+x] = '+';
        }
        
        Main.doPreGameTick();
        moves++;
        Main.doAfterGameTick();
    }
    
    /**
     * 
     * @param x The x position of the door
     * @param y The y position of the door
     * @return True or False if there is a door at the specified coordinates.
     */
    public boolean doorAt(int x, int y)
    {
        if(Files.disMap[this.y+y][this.x+x] == '+' || Files.disMap[this.y+y][this.x+x] == 'X')
        {
            return true;
        }
        return false;
    }
} 