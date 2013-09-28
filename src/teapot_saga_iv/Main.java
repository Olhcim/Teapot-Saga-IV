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

import java.util.ArrayList;
import java.util.List;

public class Main
{

    public static boolean gameActive = true;
    public static boolean frameActive = false;
    
    public static Player p = new Player();
    
    public static void main(String[] args)
    {   
        
        Window window = new Window();
        
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
        p.moves++;
        p.useStaircase();
        updateMonsters();
        Render.update();
    }
    
    private static void updateMonsters()
    {
        for (Monster m : Files.monsters)
        {
            m.moveTowardsPlayer();
        }
    }
    
    
    /**
     * Loads the next Map, moves the player and updates the GUI.
     */
    public static void NextMap()
    {
        if (Files.MapExists(1))
        {
            Render.clearAll();
            Files.mapNum++;
            Files.load();
            Render.print(Files.dialogStart);
            p.goToStart();
            Render.update();
        } else {
            Render.print("The following map does not exist.");
        }
    }
    
    /**
     * Loads the previous Map, moves the player and updates the GUI.
     */
    public static void PrevMap()
    {
        if (Files.MapExists(-1))
        {
            Render.clearAll();
            Files.mapNum--;
            Files.load();
            Render.print("You have already been to this place, head back to continue.");
            p.setPos(Files.exitX, Files.exitY);
            Render.update();
        } else {
            Render.print("The previous map does not exist.");
        }
    }
}
