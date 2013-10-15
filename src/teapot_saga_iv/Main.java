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
    
    private static final String FILESPATH = System.getProperty("user.home") + "/Desktop/log.txt";
    public static PrintWriter writer;
    
    
    
    public static void main(String[] args)
    {   
        try {
            writer =  new PrintWriter(FILESPATH, "UTF-8");
        } catch (Exception e) {}
        
        writer.write(FILESPATH);
        writer.println(FILESPATH);
        
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

        Render.dialogQueue.clear();

        p.update();
        
        Files.currentMapData().update();
        
        Render.update();
        
        time = System.currentTimeMillis() - time;
        
        count++;
        totalTime += time;
        aveTime = totalTime / count;
        
        System.out.println("move: " + Main.p.getMoves() + " Time:" + time + " AveTime: " + aveTime);
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
            p.goToStart();
        }
        else if (!ahead)
        {
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

        for (Staircase s : Files.currentMapData().getStairs()) {
            if (s.getDestWorld() == Files.world) {
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
            Render.queueDialog(Files.currentMapData().getDialogStart());
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
            if (Files.currentMapData().getDialogExit() != null)
            {
                Render.queueDialog(Files.currentMapData().getDialogStart());
                
                do
                { 
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {}
                } while (Render.dialogQueue.size() > 0);
            }
            
            useMap(Files.world, Files.level - 1);

        }
        else
        {
            goToOverworld();
        }
    }
}
