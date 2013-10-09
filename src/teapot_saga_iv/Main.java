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
    
        public static int world = -1, level = 1;
        public static boolean isAtOverworld = true;
    
    
    public static void main(String[] args)
    {   
        Files.loadAllMapData();
        useMap(1,1);
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
        
        Files.currentMapData().update();
        Render.update();
        
        time = System.currentTimeMillis() - time;
        System.out.println("move: " + Main.p.moves + "time:" + time);
    }
    
    private static void updateMonsters()
    {
        for (Monster m : Files.currentMapData().getMonsters())
        {
            m.moveTowardsPlayer();
        }
    }
    
    
    public static void goToOverworld()
    {
        Render.clearAll();

        isAtOverworld = true;
        
        Files.currentMapData().update();
        p.setPos(5, 5);

        Render.update();
    }
    
    
    public static void useMap(int world, int level)
    {
        
        Render.clearAll();
     
        isAtOverworld = false;
        boolean ahead = true;
        
        
        if (Files.world < world) {ahead = true;}
        else if (Files.world > world) {ahead = false;}
        else if (Files.world == world)
        {
            if (Files.level < level) {ahead = true;}
            else if (Files.level > level) {ahead = false;}
            else { ahead = true; }
        }
        
            
        Files.world = world;
        Files.level = level;
        
        if (ahead)
        {
            Render.print(Files.currentMapData().getDialogStart());
            p.goToStart();
            Files.currentMapData().update();
        } else if (!ahead) {
            Render.print("You have already been to this place, head back to continue.");
            p.goToExit();
            Files.currentMapData().update();
        }

        Render.update();
    }
    
    public static void NextMap()
    {
        
        if (Files.mapExists(Files.world, Files.level + 1))
        {
            useMap(Files.world, Files.level + 1);
        }
        else if (Files.mapExists(Files.world + 1, 1))
        {
            useMap(Files.world + 1, 1);
        }
        else
        {
            goToOverworld();
        }
    }
    
    /**
     * Loads the previous Map, moves the player and updates the GUI.
     */
    public static void PrevMap()
    {
        if (Files.mapExists(Files.world, Files.level - 1))
        {
            useMap(Files.world, Files.level - 1);

        }
        else if (Files.mapExists(Files.world-1, 1))
        {
            int highest = 0;
            do {
                if (Files.mapExists(Files.world-1, highest+1))
                { highest++;
                } else { break;}
            } while(true);
            
            useMap(Files.world - 1, highest);
        }
        else
        {
            goToOverworld();
        }
    }
}
