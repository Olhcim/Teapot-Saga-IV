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
    public static Window w = new Window();
    
    public static void main(String[] args)
    {   
        Files.loadMaps();
        NextMap();
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
     * Called if the player is moved, performs all nessecery functions and checks.
     */
    public static void doGameTick()
    {
        
        long time = System.currentTimeMillis();

        p.moves++;
        p.useStaircase();
        updateMonsters();
        Render.update();
        
        time = System.currentTimeMillis() - time;
        System.out.println("move: " + Main.p.moves + "time:" + time);
    }
    
    private static void updateMonsters()
    {
        for (Monster m : Files.currentMapData().monsters)
        {
            m.moveTowardsPlayer();
        }
    }
    
    
    /**
     * Loads the next Map, moves the player and updates the GUI.
     */
    public static void NextMap()
    {
        if (Files.mapExists(Files.world, Files.level+1))
        {
            Render.clearAll();
            Files.level++;
            Render.print(Files.currentMapData().dialogStart);
            p.goToStart();
            Render.update();
        }
        else if (Files.mapExists(Files.world+1, 1))
        {
            Render.clearAll();
            Files.level = 1;
            Files.world++;
            Render.print(Files.currentMapData().dialogStart);
            p.goToStart();
            Render.update();
        }
        else
        {
            Render.print("The following map does not exist.");
        }
    }
    
    /**
     * Loads the previous Map, moves the player and updates the GUI.
     */
    public static void PrevMap()
    {
        if (Files.mapExists(Files.world, Files.level-1))
        {
            Render.clearAll();
            Files.level--;
            Render.print("You have already been to this place, head back to continue.");
            p.goToExit();
            Render.update();
        }
        else if (Files.mapExists(Files.world-1, 1))
        {
            int highest = 0;
            do
            {
                if (Files.mapExists(Files.world-1, highest+1))
                {
                    highest++;
                } else {
                    break;
                }
            } while(true);
            
            Render.clearAll();
            Files.level = highest;
            Files.world--;
            Render.print("You have already been to this place, head back to continue.");
            p.goToExit();
            Render.update();
        }
        else
        {
            Render.print("The previous map does not exist.");
        }
    }
}
