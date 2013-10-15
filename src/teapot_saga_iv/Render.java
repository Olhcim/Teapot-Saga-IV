package teapot_saga_iv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import teapot_saga_iv.characters.Entity;
import teapot_saga_iv.characters.Monster;
import teapot_saga_iv.maps.Staircase;


public class Render {
    
    public static final byte CHAR_WIDTH     = 15;
    public static final byte CHAR_HEIGHT    = 15;
    
    public static final byte DIALOG_HEIGHT  = 10;
    public static final byte WIDTH          = 80;
    public static final byte MAP_HEIGHT     = 50;
    public static final byte STATS_HEIGHT   = 4;
    
    public static final int WIDTH_PIXELS            = WIDTH * CHAR_WIDTH;
    
    public static final int DIALOG_HEIGHT_PIXELS    = DIALOG_HEIGHT * CHAR_HEIGHT;
    public static final int MAP_HEIGHT_PIXELS       = MAP_HEIGHT * CHAR_HEIGHT;
    public static final int STATS_HEIGHT_PIXELS     = STATS_HEIGHT * CHAR_HEIGHT;
    public static final int TOTAL_HEIGHT_PIXELS     = DIALOG_HEIGHT_PIXELS + MAP_HEIGHT_PIXELS + STATS_HEIGHT_PIXELS + 22;
    
    private static final String FILENAME = "mycharset.png";
    
    private static BufferedImage glyphSprite;
    private static BufferedImage[] glyphs = new BufferedImage[256];
    
    public static BufferedImage dialog  = new BufferedImage(CHAR_WIDTH*WIDTH, CHAR_HEIGHT*DIALOG_HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static BufferedImage map     = new BufferedImage(CHAR_WIDTH*WIDTH, CHAR_HEIGHT*MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static BufferedImage stats   = new BufferedImage(CHAR_WIDTH*WIDTH, CHAR_HEIGHT*STATS_HEIGHT, BufferedImage.TYPE_INT_RGB);
    
    public static char[][] disMap;
    
    
    public static List<String> dialogQueue = new ArrayList<String>();
    public static List<Entity> extra = new ArrayList<Entity>();
    
////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////
    
    
////////////////////////////////////////////////////////////////////////////////
// Load Glyphs
////////////////////////////////////////////////////////////////////////////////
    
/**
 * Loads all the Characters from the Files
 */
    public static void LoadGlyphs() {
        
        try {
            glyphSprite = ImageIO.read(new File(Files.FILESPATH + FILENAME));
        } catch (Exception e) {}
        
        for (int i = 0; i < 256; i++) {     //functions the same as two for loops, one inside the other, but more efficient.
            int sx = (i % 16) * CHAR_WIDTH;
            int sy = (i / 16) * CHAR_HEIGHT;
            
            //System.out.println("sx: " + sx + " sy: " + sy);

            glyphs[i] = new BufferedImage(CHAR_WIDTH, CHAR_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            glyphs[i].getGraphics().drawImage(glyphSprite, 0, 0, CHAR_WIDTH, CHAR_HEIGHT, sx, sy, sx + CHAR_WIDTH, sy + CHAR_HEIGHT, null);
        }
    }
    
////////////////////////////////////////////////////////////////////////////////
// Paint
////////////////////////////////////////////////////////////////////////////////
    
    public static void queueDialog(String a)
    {
        dialogQueue.add(a);
    }
    
    public static void paintDialogInQueue()
    {
        try {
            String current = dialogQueue.get(0);
            print(current);
            
            if (dialogQueue.size() > 1)
            {
                printSpaceToContinue();
            }
            
            dialogQueue.remove(current);
        }
        catch (Exception e)
        {
//            clearDialog();
            System.out.println("No dialog in queue.");
        }
    }
    
    /**
    * Paints over the specified char. Full X and Full Y coords needed.
    * 
    * Does not allow the printing of return. (key code 13)
    */
    private static void paintToDialog(char a, int x, int y)
    {
        dialog.getGraphics().drawImage(glyphs[a], x*CHAR_WIDTH, y*CHAR_HEIGHT, null);
    }

    /**
    * Paints over the specified char. Full X and Full Y coords needed.
    * 
    * Does not allow the printing of return. (key code 13)
    */
    private static void paintToMap(char a, int x, int y) {
        map.getGraphics().drawImage(glyphs[a], x*CHAR_WIDTH, y*CHAR_HEIGHT, null);
    }
    
    /**
    * Paints over the specified char. Full X and Full Y coords needed.
    * 
    * Does not allow the printing of return. (key code 13)
    */
    private static void paintToStats(char a, int x, int y) {
        stats.getGraphics().drawImage(glyphs[a], x*CHAR_WIDTH, y*CHAR_HEIGHT, null);
    }
    
////////////////////////////////////////////////////////////////////////////////
// Print - Send text to dialog box
////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Prints a line of text the the second line of the display
     */
    private static void print(String a)
    {
        
        clearDialog();
        
        a = wrap(a, WIDTH - 8);
        
        int height = a.split("\n").length;
        int y = 0;
        int x = 0;
        
        System.out.println(a);
        
        
        for (int i = 0; i < a.length(); i++)
        {
            if (a.charAt(i) == '\n')
            { y++; x = 0; }
            else
            {
                paintToDialog(a.charAt(i), x + 4 , y + getMidY(DIALOG_HEIGHT, height));
                x++;
            }
        }
        
//        for (int i = 0; i < WIDTH; i++)
//        {
//            paintToDialog((char) 196, i, DIALOG_HEIGHT-1);
//        }
        
        Window.repaintAll();
    }
    
    /**
     * Prints a line of text the the second line of the display
     */
    private static void printSpaceToContinue()
    {
        String a = " > Press the Spacebar < ";
        
        for (int x = 0; x < a.length(); x++)
        {
                paintToDialog(a.charAt(x), getMidX(a.length()) + x , DIALOG_HEIGHT - 1);
        }
    }
    
    /**
     * Wraps the specified string to fit the specified width in characters.
     * - inserts "\n" where nessesary.
     * 
     * Method from http://ramblingsrobert.wordpress.com/2011/04/13/java-word-wrap-algorithm/
     */
    private static String wrap(String in,int len)
    {
        
        in=in.trim();
    
        if(in.length()<len)
        {
            return in;
        }
        
        if(in.substring(0, len).contains("\n"))
        {
            return in.substring(0, in.indexOf("\n")).trim() + "\n\n" + wrap(in.substring(in.indexOf("\n") + 1), len);
        }
        
        int place=Math.max(Math.max(in.lastIndexOf(" ",len),in.lastIndexOf("\t",len)),in.lastIndexOf("-",len));
        
        return in.substring(0,place).trim()+"\n"+wrap(in.substring(place),len);
    }
    
////////////////////////////////////////////////////////////////////////////////
// Update Status box
////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Updates the status box with the required text.
     */
    private static void paintStats()
    {        

        String health = "Health: " + Main.getPlayer().getHealth();
        String pots = "   Health Potions: " + Main.getPlayer().getHealthPotions();
        String numMoves = "   Turns: " + Main.getPlayer().getMoves();
        
        String all = health + pots + numMoves;

        for (int i = 0; i < WIDTH; i++)
        {
            paintToStats(' ', i , STATS_HEIGHT-2);
        }
        
        for (int i = 0; i < all.length(); i++)
        {
            paintToStats(all.charAt(i), i+5 , STATS_HEIGHT-2);
        }
    }
    
////////////////////////////////////////////////////////////////////////////////
// Clearing
    // >>>> ADD SELECTIVE CLEARING <<<<
////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Paints spaces over the whole image.
     */
    private static void clearDialog()
    {
        for (int y = 0; y < DIALOG_HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                paintToDialog(' ', x, y);
            }
        }
        
        for (int i = 0; i < WIDTH; i++)
        {
            paintToDialog((char) 196, i, DIALOG_HEIGHT - 1);
        }
    }
    
    private static void clearMap()
    {
        for (int y = 0; y < MAP_HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                paintToMap((char) 176, x, y);
            }
        }
    }
    
    private static void clearStats()
    {
        for (int y = 0; y < STATS_HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                paintToStats(' ', x, y);
            }
        }
        
        for (int i = 0; i < WIDTH; i++)
        {
            paintToStats((char) 196, i, 0);
        }
    }
    
    public static void clearAll()
    {
        clearDialog();
        clearMap();
        clearStats();
    }

////////////////////////////////////////////////////////////////////////////////
// Update
////////////////////////////////////////////////////////////////////////////////
    
    /*
     * Performs the PaintMap and then the paintPlayer Function.
     */
    public static void update()
    {
        paintDialogInQueue();
        
        paintMap();
        paintEntity();
        paintOverworldObjects();
        paintPlayer();
        
        paintStats();
        
        Window.repaintAll();
    }
    
    public static void updateDialog()
    {
        paintDialogInQueue();
        
        Window.repaintAll();
    }
    
    public static void updatMap()
    { 
        paintMap();
        paintEntity();
        paintOverworldObjects();
        paintPlayer();
        
        Window.repaintAll();
    }
    
    public static void updateStats()
    {
        paintStats();
        
        Window.repaintAll();
    }
    
////////////////////////////////////////////////////////////////////////////////
// Paint Whole Map
////////////////////////////////////////////////////////////////////////////////
    
    /*
     * Paints all of the current map.
     */
    public static void paintMap() {
        
        int midX = getMidX();
        int midY = getMidY();        
        
        for (int y = 0; y < Files.currentMap().length; y++)
        {
            for (int x = 0; x < Files.currentMap()[y].length; x++)
            {
                if (Main.isAtOverworld)
                {
                    paintToMap(Files.currentDisMap()[y][x], x + midX, y + midY);
                }
                else
                {
                    if (Files.currentMapData().getSeen()[y][x] == '1')
                    {
                        paintToMap(Files.currentDisMap()[y][x], x + midX, y + midY);
                    }
                    else
                    {
                        paintToMap((char) 176, x + midX, y + midY);
                    }
                }
                
                
            }
        }
    }
    
    private static void paintPlayer() {
        
        paintToMap('@', Main.getPlayer().getX() + getMidX(), Main.getPlayer().getY() + getMidY());
    }
    
    private static void paintEntity()
    {
        
        for (Entity e : Files.currentMapData().getEntities())
        {
            
            if (Files.currentMapData().getSeen()[e.getY()][e.getX()] == '1')
            {
            paintToMap(e.getSymbol(), e.getX() + getMidX(),e.getY() + getMidY());
            }
        }
    }
    
    private static void paintOverworldObjects()
    {
        if (Main.isAtOverworld)
        {
            for (Staircase s : Files.currentMapData().getStairs())
            {
                paintToMap(s.getSymbol(), s.getX() + getMidX(), s.getY() + getMidY());
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////
// Midpoints
////////////////////////////////////////////////////////////////////////////////
    
    /*
     * Aproximates the starting x coord that is needed to display the map in the center of the screen.
     */
    private static int getMidX()
    {
        return (WIDTH - Files.currentMap()[0].length) / 2;
    }
    
    /*
     * Aproximates the starting x coord that is needed to display the text in the center.
     */
    private static int getMidX(int textLength)
    {
        return (WIDTH - textLength) / 2;
    }
    
    /*
     * Aproximates the starting y coord that is needed to display the map in the center of the screen.
     */
    private static int getMidY()
    {
        return (MAP_HEIGHT - Files.currentMap().length) / 2;
    }
    
    
    /*
     * Aproximates the starting y coord that is needed to display the text in the center.
     */
    private static int getMidY(int length, int textLength)
    {
        return (length - textLength) / 2;
    }
}