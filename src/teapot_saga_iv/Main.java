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

import teapot_saga_iv.maps.Frame;
import java.io.IOException;



public class Main
{

    public static boolean gameActive = true;
    public static int lvl = 2;
    
    public static void main(String[] args) throws IOException
            
    {
        System.out.println("Map Starting");
        Maps map = new Maps();
        System.out.println("Maps Complete");
        System.out.println("Render Starting");
        Render render = new Render();
        System.out.println("Render Complete");
        System.out.println("Frame Starting");
        Window window = new Window();
        System.out.println("Frame Complete");
        gameActive = false;
    }
}
