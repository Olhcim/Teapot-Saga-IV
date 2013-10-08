package teapot_saga_iv;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class Render {
    
    public static final byte CHAR_WIDTH     = 15;
    public static final byte CHAR_HEIGHT    = 15;
    
    public static final byte DIALOG_HEIGHT  = 10;
    public static final byte WIDTH          = 120;
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
    public static void print(String a)
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
        
        for (int i = 0; i < WIDTH; i++)
        {
            paintToDialog((char) 196, i, DIALOG_HEIGHT-1);
        }
        
        Window.repaintAll();
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
        
        int place=Math.max(Math.max(in.lastIndexOf(" ",len),in.lastIndexOf("\t",len)),in.lastIndexOf("-",len));
        
        return in.substring(0,place).trim()+"\n"+wrap(in.substring(place),len);
    }
    
    
////////////////////////////////////////////////////////////////////////////////
// Update Status box
////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Updates the status box with the required text.
     */
    private static void updateStats()
    {        

        String health = "Health: " + Main.getPlayer().health;
        String numMoves = "Turns: " + Main.getPlayer().getMoves();
        
        if      (Main.p.health > 99) {health += "   ";}
        else if (Main.p.health > 9)  {health += "    ";}
        else                         {health += "     ";}
        
        String all = health + numMoves;

        
        for (int i = 0; i < all.length(); i++)
        {
            paintToStats(all.charAt(i), i+5 , STATS_HEIGHT-2);
        }

        for (int i = 0; i < WIDTH; i++)
        {
            paintToStats((char) 196, i, 0);
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
    }
    
    private static void clearMap()
    {
        for (int y = 0; y < MAP_HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                paintToMap(' ', x, y);
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
        paintMap();
        paintPlayer();
        paintMonsters();
        updateStats();
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
                if (Files.currentMapData().getSeen()[y][x] == '1')
                {
                    paintToMap(Files.currentDisMap()[y][x], x + midX, y + midY);
                } else {
                    paintToMap((char) 176, x + midX, y + midY);
                }
                
            }
        }
    }
    
    private static void paintPlayer() {
        
        paintToMap('@', Main.p.x + getMidX(), Main.p.y + getMidY());
    }
    
    private static void paintMonsters() {
        
        for (Monster m : Files.currentMapData().monsters)
        {
            if (Files.currentMapData().getSeen()[m.y][m.x] == '1')
            {
            paintToMap(m.symbol, m.x + getMidX(),m.y + getMidY());
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
     * Aproximates the starting x coord that is needed to display a String in the center of the screen.
     */
    private static int getMidX(int a)
    {
        return (WIDTH - a) / 2;
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