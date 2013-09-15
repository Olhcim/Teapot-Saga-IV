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
    
    public static BufferedImage glyphSprite;
    public static BufferedImage[] glyphs = new BufferedImage[256];
    
    public static BufferedImage rendered = new BufferedImage(WIDTH*WIDTHINCHARS, HEIGHT*HEIGHTINCHARS, BufferedImage.TYPE_INT_RGB);
    public static BufferedImage offscreenBuffer = new BufferedImage(80*9, 24*16, BufferedImage.TYPE_INT_RGB);
    public static Graphics offscreenGraphics;
    
    public static char[][] chars;
    private static char[][] oldChars = Files.map;
    

    public static void LoadGlyphs() {
        
        try {
            glyphSprite = ImageIO.read(new File(Files.FILESPATH + "cp437crop.png"));
        } catch (Exception e) {}
        
        for (int i = 0; i < 256; i++) {
            int sx = (i % 32) * WIDTH;
            int sy = (i / 32) * HEIGHT;

            glyphs[i] = new BufferedImage(9, 16, BufferedImage.TYPE_INT_ARGB);
            glyphs[i].getGraphics().drawImage(glyphSprite, 0, 0, WIDTH, HEIGHT, sx, sy, sx + WIDTH, sy + HEIGHT, null);
        }
    }
        
    
    
    



    
////////////////////////////////////////////////////////////////////////////////
//  Paint to Window
////////////////////////////////////////////////////////////////////////////////
    
    public static void update() {
        paintMap();
    } 


    public static void paint(char a, int x, int y) {
        
        rendered.getGraphics().drawImage(glyphs[a], x*9, y*16, null);
        
    }
    
    public static void paintPlayer() {
        
        paint('@', Player.x + getMidX(), Player.y + getMidY());
        
        Window.picLabel.repaint();
    }
    
    public static void paintMap() {
        
        int midX = getMidX();
        int midY = getMidY();
        
        for (int y = 0; y < Files.map.length; y++)
        {
            for (int x = 0; x < Files.map[0].length; x++)
            {
                paint(Files.map[y][x], x + midX, y + midY);
            }
        }
    }
    
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
    
    public static void render()
    {
        clear();
        paintMap();
        paintPlayer();
    }
    
    public static int getMidX()
    {
        int a = (WIDTHINCHARS - Files.map[0].length) / 2;
        return a;
    }
    
    public static int getMidY()
    {
        int a = (HEIGHTINCHARS - Files.map.length) / 2;
        return a;
    }
            
}