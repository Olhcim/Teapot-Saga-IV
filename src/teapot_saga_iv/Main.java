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
    
    public static void main(String[] args) throws InterruptedException
            
    {
        
        do
        {
            if(!frameActive)
            {
                Maps.loadCurrentMap();
                Maps.loadCurrentMapData();
                Window window = new Window();
            }
            
            if(Player.x == MapExit.x && Player.y == MapExit.y)
            {
                Window.destroy();
            }
            
            
            Render render = new Render();

            Thread.sleep(100);
        } while (gameActive);
        
        
    }
}
