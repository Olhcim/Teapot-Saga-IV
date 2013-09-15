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
        Files.load();
        Player.setPos(Files.startX, Files.startY);
        
        Render.print("Hello person, My name is Geff and i will be your guide. Listen to my every word");
        Window window = new Window();
        Render.render();
        Files.renderDispMap();
    }
    
    
    /*
     * Called if the player is moved, performs all nessesary functions and checks.
     */
    public static void doGameTick()
    {
        
        if(Files.map[Player.y][Player.x] == '>')
        {
            NextMap();
        } else if (Files.map[Player.y][Player.x] == '<')
        {
            PreviousMap();
        } else
        {
            Render.render();
        }
        
        
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
            Player.goToStart();
        }
        Render.render();
    }
    
    /*
     * Loads the previous Map, moves the player and updates the GUI.
     */
    public static void PreviousMap()
    {
        if (Files.MapExists(-1)) {
            Files.mapNum--;
            Files.load();
            Player.setPos(Files.exitX, Files.exitY);
        }
        Render.render();
    }
}
