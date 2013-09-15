package teapot_saga_iv;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Window extends JFrame implements KeyListener
{
    
    private static JFrame jFrame = new JFrame("Teavup Saga IV: The Teapocolypse");
    private static JPanel jPanel = new JPanel();

    public static JLabel picLabel;
    
    /*
     * Creats the JFrame and preformes all the necessary actions such as the layout.
     */
    public Window()
    {   
        
        Render.LoadGlyphs();
        Render.update();
        picLabel = new JLabel(new ImageIcon(Render.rendered));
        
        picLabel.addKeyListener(this);
        
        jFrame.setSize(80*9, 24*16);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(picLabel);
        jFrame.setVisible(true);
        jFrame.addKeyListener(this);
        
    }
    
    /*
     * Checks for a key that has been typed.
     */
    @Override
    public void keyTyped(KeyEvent ke) {}

    /*
     * Checks for a key that has been pressed.
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        
        //System.out.println(ke.getKeyChar() + ": " +ke.getKeyCode());
        Player.move(ke.getKeyCode());
    }

    /*
     * Checks for a key that has been Released.
     */
    @Override
    public void keyReleased(KeyEvent ke) {}

} 