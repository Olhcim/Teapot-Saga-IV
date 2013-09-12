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
        Window window = new Window();
        Files.load();
        Render.rend();
    }
    
    public static void doGameTick()
    {
        
        if(Files.map[Player.y][Player.x] == '>')
        {}
        
        Render.rend();
    }
    
    
    
    public static void NextMapAndUpdate()
    {
        Files.mapNum++;
        Files.load();
        Render.rend();
    }
}
