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
    
    public static void main(String[] args)
            
    {   
        //Render.print("");
        Window window = new Window();
        NextMap();
    }
    
    
    /*
     * Called if the player is moved, performs all nessesary functions and checks.
     */
    public static void doGameTick()
    {
            Player.useStaircase();
            Render.update();
    }
    
    
    /*
     * Loads the next Map, moves the player and updates the GUI.
     */
    public static void NextMap()
    {
        if (Files.MapExists(1))
        {
            Files.mapNum++;
            Files.load();
            Render.print(Files.dialogStart);
            Player.goToStart();
        }
        Render.update();
    }
    
    /*
     * Loads the previous Map, moves the player and updates the GUI.
     */
    public static void PrevMap()
    {
        if (Files.MapExists(-1)) {
            Files.mapNum--;
            Files.load();
            Render.print("You have already been to this place, head back to continue.");
            Player.setPos(Files.exitX, Files.exitY);
        }
        Render.update();
    }
}
