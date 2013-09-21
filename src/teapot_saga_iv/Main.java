/*
 * Storyline by:
 * 
 * Samuel Sapire
 * 
 * 
 * Programming:
 * 
 * Nathan Michlo
 * Samuel Sapire 
 */


package teapot_saga_iv;

public class Main
{

    public static boolean gameActive = true;
    public static boolean frameActive = false;
    
    public static Player p = new Player();
    
    public static void main(String[] args)
    {   
        
        Window window = new Window();
        NextMap();
        
        for (int i = 0; i < 1000; i++)
        {
            p.move(37);
            p.move(39);
        }
    }
    
    /**
     * Returns the current player and its data.
     * @return The current player and its data.
     */
    public static Player getPlayer()
    {
        return p;
    }
    
    
    /**
     * Called if the player is moved, performs all necesary functions and checks.
     */
    public static void doGameTick()
    {
            p.useStaircase();
            Render.update();
    }
    
    
    /**
     * Loads the next Map, moves the player and updates the GUI.
     */
    public static void NextMap()
    {
        if (Files.MapExists(1))
        {
            Files.mapNum++;
            Files.load();
            Render.print(Files.dialogStart);
            p.goToStart();
            p.setPos(2, 2);
        }
        Render.clearAll();
        Render.update();
    }
    
    /**
     * Loads the previous Map, moves the player and updates the GUI.
     */
    public static void PrevMap()
    {
        if (Files.MapExists(-1)) {
            Files.mapNum--;
            Files.load();
            Render.print("You have already been to this place, head back to continue.");
            p.setPos(Files.exitX, Files.exitY);
        }
        Render.clearAll();
        Render.update();
    }
}
