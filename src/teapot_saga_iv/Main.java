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
            
    {       //game start
        
        Maps.loadCurrentMap();
        Maps.loadCurrentMapData();
        Render.loadMap();
        Window window = new Window();
        
        do  //game loop
        {
            if(Player.x == Maps.exitX && Player.y == Maps.exitY)
            {
                Maps.mapNum++;
                Maps.loadCurrentMap();
                Maps.loadCurrentMapData();
                Render.loadMap();
                Window.resize();
            }
            
            if(Maps.mapNum > 3)
            {
                Window.setText("Game Over");
                break;
            } else {
                Render.rend();
            }
            

            Thread.sleep(100);
        } while (gameActive);
        
        
    }
}
