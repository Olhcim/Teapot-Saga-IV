package teapot_saga_iv;


public class Player {
    
    public static int health = 100, moves = 0;
    public static int x=2, y=2;
    public static boolean canMove = true;
    
    
    /*
     * Moves the player to the specified position, but does not update the GUI.
     */
    public static void setPos(int a, int b)
    {
        x = a;
        y = b;
    }
    
    /*
     * Moves the player to the start but does not update the GUI.
     */
    public static void goToStart()
    {
        setPos(Files.startX, Files.startY);
    }
    
    
    /*
     * Moves the player to the exit but does not update the GUI.
     */
    public static void goToExit()
    {
        setPos(Files.exitX, Files.exitY);
    }
    
    /*
     * Freezes the player and stops it from moving.
     */
    public static void freeze()
    {
        canMove = false;
    }
    
    /*
     * unFreezes the player and allowes it to move again.
     */
    public static void unFreeze()
    {
        canMove = false;
    }
    
    /*
     * Checks weather the current possition is over an entrance or exit and follows the apropreate actions..
     */
    private static void checkForStairCase()
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
    
    /*
     * Checks weather the current possition is over an entrance.
     */
    private static boolean isAtEntrance()
    {
        return Files.map[Player.y][Player.x] == '<';
    }
    
    /*
     * Checks weather the current possition is over an exit.
     */
    private static boolean isAtExit()
    {
        return Files.map[Player.y][Player.x] == '>';
    }
    
    /*
     * recieves the keycodes of keys pressed from Window.class
     * 
     */
    public static void move(int a)
    {
        switch (a)
        {
            case 65:
            case 37:
                if(canMove(x-1, y)) { x--; moves++; Main.doGameTick();}
                break;
            case 87:
            case 38:
                if(canMove(x, y-1)) { y--; moves++; Main.doGameTick();}
                break;
            case 68:
            case 39:
                if(canMove(x+1, y)) { x++; moves++; Main.doGameTick();}
                break;
            case 83:
            case 40:
                if(canMove(x, y+1)) { y++; moves++; Main.doGameTick();}
                break;
            case 10:
                if(canMove == false && isAtExit()) {
                    canMove = true;
                    Main.NextMap();
                }
        }
        checkForStairCase();
    }
    
    
    /*
     * Checks weather the player can move to the specified location.
     */
    private static boolean canMove(int a, int b)
    {
        return Files.map[b][a] != '#' && canMove == true;
    }
} 