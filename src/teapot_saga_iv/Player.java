package teapot_saga_iv;


public class Player {
    
    public int health = 10;
    public static int x=2, y=2;
    
    
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
     * recieves the keycodes of keys pressed from Window.class
     */
    public static void move(int a)
    {
        switch (a)
        {
            case 65:
            case 37:
                if(Files.map[y][x-1] != '#') { x--; }
                System.out.print("Dir: Left ");
                Main.doGameTick();
                break;
            case 87:
            case 38:
                if(Files.map[y-1][x] != '#') { y--; }
                System.out.print("Dir: Up ");
                Main.doGameTick();
                break;
            case 68:
            case 39:
                if(Files.map[y][x+1] != '#') { x++; }
                System.out.print("Dir: Right ");
                Main.doGameTick();
                break;
            case 83:
            case 40:
                if(Files.map[y+1][x] != '#') { y++; }
                System.out.print("Dir: Down ");
                Main.doGameTick();
                break;
        }
    }
} 