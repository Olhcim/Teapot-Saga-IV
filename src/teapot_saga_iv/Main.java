/*
 * Storyline by:
 * 
 * Samuel Sapire
 * 
 * 
 * Programming:
 * 
 * Nathan Michlo
 * 
 * 
 * Samuel Sapire 
 */


package teapot_saga_iv;


import javax.swing.JOptionPane;


public class Main
{

    public static boolean gameActive;
    public static boolean frameActive;
    
    private static Player p;
    private static Window w;
    
    public static boolean isAtOverworld;
    
    public static void main(String[] args)
    {   

        setupGame();
    }
    
    private static void setupGame()
    {
        gameActive = true;
        frameActive = false;
    
        p = new Player();
        w = new Window();
    
       isAtOverworld = false;
       
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
        
        Render.dialogQueue.clear();
        
        p.update();

        Files.currentMapData().update();
        
        if (p.isDead())
        {
            endGame("You died, would you like to restart?");
        }
        
        Render.update();
    }
    
    public static void endGame(String a)
    {
        Render.queueDialog(a);
        Render.update();
        
            int option = JOptionPane.showConfirmDialog(null, a);

            if (option == 0)
            {
                setupGame();
            } else {
                System.exit(0);
            }
    }
    
    
    /**
     * Switches the map to the specified map corrisponding to the world and level passed to the method.
     * 
     * @param world The world to switch to.
     * @param level The level in the world to switch to.
     */
    public static void useMap(int world, int level)
    {
        
        Render.clearAll();
     

        boolean ahead = true;
        
        
        if (Files.world < world)
        {
        	ahead = true;
        }
        else if (Files.world > world)
        {
        	ahead = false;
		}
        else if (Files.world == world)
        {
            if (Files.level < level) {ahead = true;}
            else if (Files.level > level) {ahead = false;}
            else { ahead = true; }
        }
        
            
        Files.world = world;
        Files.level = level;
        
        if (isAtOverworld)
        {
            isAtOverworld = false;
            
           if(level == 1)
           {
                Files.currentMapData().setDefaultDialog("You have already visited this place.");
                p.goToStart();
           } else {
               Files.currentMapData().setDefaultDialog("You have already visited this place.");
                p.goToExit();
           }
        }
        else 
        {
            if (!ahead)
            {
                Files.currentMapData().setDefaultDialog("You have already visited this place.");
                p.goToExit();
            } else {
                Render.queueDialog(Files.currentMapData().getDialogStart());
                p.goToStart();
            }
        }

        Files.currentMapData().update();
        Render.update();
    }
    
    
    public static void goToOverworld()
    {
    	
        Render.clearAll();

        isAtOverworld = true;
        
        Files.currentMapData().update();

        for (Staircase s : Files.currentMapData().stairs)
        {
            if (s.getDestWorld() == Files.world)
            {
                p.setPos(s.getX(), s.getY());
            }
        }

        Render.update();
    }
    
    
    public static void NextMap()
    {
        if (Files.mapExists(Files.world, Files.level + 1))
        {
            useMap(Files.world, Files.level + 1);
        }
        else
        {
            if (p.allowedOverworldStaircase < Files.world + 1)
            {
                p.allowedOverworldStaircase++;
            }
            
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
            Render.queueDialog("You have already visited this map");
            
            useMap(Files.world, Files.level - 1);

        }
        else
        {
            goToOverworld();
        }
    }
}
