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
    
    public static void main(String[] args)
            
    {
        System.out.println("Loading Map");
        Maps map = new Maps();
        Player player = new Player();
        System.out.println("Loading Map Complete");
        System.out.println("Creating Frame");
        Window window = new Window();
        System.out.println("Frame Creation Complete");
        gameActive = false;
    }
}
