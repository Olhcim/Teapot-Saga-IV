package teapot_saga_iv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.io.File;
import javax.imageio.ImageIO;


public class Render {
    
    public static final byte HEIGHT = 16;
    public static final byte WIDTH = 9;
    public static final byte WIDTHINCHARS = 80;
    public static final byte HEIGHTINCHARS = 24;
    private static final String FILENAME = "cp437crop.png";
    
    public static BufferedImage glyphSprite;
    public static BufferedImage[] glyphs = new BufferedImage[256];
    
    public static BufferedImage rendered = new BufferedImage(WIDTH*WIDTHINCHARS, HEIGHT*HEIGHTINCHARS, BufferedImage.TYPE_INT_RGB);
    //public static BufferedImage offscreenBuffer = new BufferedImage(80*9, 24*16, BufferedImage.TYPE_INT_RGB);
    //public static Graphics offscreenGraphics;
    
    public static char[][] chars;
    private static char[][] oldChars;
    
/*
 * Loads all the Characters from the Files
 */
    public static void LoadGlyphs() {
        
        try {
            glyphSprite = ImageIO.read(new File(Files.FILESPATH + FILENAME));
        } catch (Exception e) {}
        
        for (int i = 0; i < 256; i++) {     //functions the same as two for loops, one inside the other, but more efficient.
            int sx = (i % 32) * WIDTH;
            int sy = (i / 32) * HEIGHT;

            glyphs[i] = new BufferedImage(9, 16, BufferedImage.TYPE_INT_ARGB);
            glyphs[i].getGraphics().drawImage(glyphSprite, 0, 0, WIDTH, HEIGHT, sx, sy, sx + WIDTH, sy + HEIGHT, null);
        }
    }

/*
 * Paints over the specified char. Full X and Full Y coords needed
 */
    private static void paint(char a, int x, int y) {
        
        rendered.getGraphics().drawImage(glyphs[a], x*9, y*16, null);
        
    }
    
    /*
     * Paints the player, Map X and Map Y coords needed.
     */
    private static void paintPlayer() {
        
        paint('@', Player.x + getMidX(), Player.y + getMidY());
        
        Window.picLabel.repaint();
    }
    
    /*
     * Clears the BufferedImage and then paints the current map.
     */
    private static void paintMap() {
        
        int midX = getMidX();
        int midY = getMidY();
        
        clear();
        
        for (int y = 0; y < Files.map.length; y++)
        {
            for (int x = 0; x < Files.map[0].length; x++)
            {
                paint(Files.disMap[y][x], x + midX, y + midY);
            }
        }
    }
    
    /*
     * Paints spaces over the whole image.
     */
    public static void clear()
    {
        for (int y = 0; y < HEIGHTINCHARS; y++)
        {
            for (int x = 0; x < WIDTHINCHARS; x++)
            {
                paint(' ', x, y);
            }
        }
    }
    
    /*
     * Performs the PaintMap and then the paintPlayer Function.
     */
    public static void render()
    {
        paintMap();
        paintPlayer();
    }
    
    /*
     * Prints a line of text the the second line of the display
     */
    public static void print(String a)
    {
        if(a.length() <= 78)
        {}
        
        int l = a.length();
        
        for (int i = 0; i < l; i++)
        {
            paint(a.charAt(i), l + getMidX(l), 2);
        }
    }
    
    /*
     * Aproximates the starting x coord that is needed to display the map in the center of the screen.
     */
    private static int getMidX()
    {
        int a = (WIDTHINCHARS - Files.map[0].length) / 2;
        return a;
    }   
    
    /*
     * Aproximates the starting x coord that is needed to display a String in the center of the screen.
     */
    private static int getMidX(int a)
    {
        int b = (WIDTHINCHARS - a) / 2;
        return b;
    }       
    
    /*
     * Aproximates the starting y coord that is needed to display the map in the center of the screen.
     */
    private static int getMidY()
    {
        int a = (HEIGHTINCHARS - Files.map.length) / 2;
        return a;
    }
}