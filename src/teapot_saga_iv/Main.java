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

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import teapot_saga_iv.characters.Player;
import teapot_saga_iv.maps.Staircase;


public class Main
{
    
    public static int totalTime = 0;
    public static int count = 0;
    public static double aveTime = 0;

    public static boolean gameActive = true;
    public static boolean frameActive = false;
    
    private static Player p = new Player();
    private static Window w = new Window();
    
    public static boolean isAtOverworld = true;
    
    public static void main(String[] args)
    {   

        Files.loadAllMapData();
        useMap(1,1);
        p.setPos(2,3);
        
//        long time = System.currentTimeMillis();
//        
//        for (int i = 0; i < 1000; i++)
//        {
//            p.x ++;
//            doGameTick();
//            p.x--;
//            doGameTick();
//        
//        }
//        
//        time = System.currentTimeMillis() - time;
//        System.out.println("move: " + Main.p.getMoves() + " Time:" + time);
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
        
//        long time = System.currentTimeMillis();

        p.update();

        Files.currentMapData().update();
        
        
        Render.update();
        
//        time = System.currentTimeMillis() - time;
        
//        count++;
//        totalTime += time;
//        aveTime = totalTime / count;
        
//        System.out.println("move: " + Main.p.getMoves() + " Time:" + time + " AveTime: " + aveTime);
    }
    
    
    
    public static void useMap(int world, int level)
    {
        
        Render.clearAll();
     
        isAtOverworld = false;
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
        
        if (ahead)
        {
            Render.setDefaultDialog(Files.currentMapData().getDialogStart());
            p.goToStart();
        }
        else if (!ahead)
        {
            Render.setDefaultDialog("You have already visited this place.");
            p.goToExit();
        }

        Files.currentMapData().update();
        Render.update();
    }
    
    
    public static void goToOverworld()
    {
    	
        Render.clearAll();

        isAtOverworld = true;
        
        Files.currentMapData().update();

        for (Staircase s : Files.currentMapData().getStairs())
        {
            if (s.getDestWorld() == Files.world)
            {
                if (s.getDestWorld() < Files.world)
                {
                    p.allowedOverworldStaircase++;
                }
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
