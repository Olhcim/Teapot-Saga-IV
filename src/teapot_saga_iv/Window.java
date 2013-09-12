package teapot_saga_iv;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class Window extends JFrame implements KeyListener
{
    
    static JFrame frame = new JFrame("Teavup Saga IV: The Teapocolypse");
    static JPanel jp = new JPanel();
    static JTextPane textPane = new JTextPane();
    static Font font = new Font("Monospaced",Font.PLAIN,16);
    static Color brown = new Color(100,40,10);
    
    public Window()  //creates frame
    {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        Main.frameActive = true;
        
        textPane.setEditable(false);
        textPane.setFont(font);
        textPane.setBackground(brown);
        textPane.setForeground(Color.WHITE);
        textPane.addKeyListener(this);

        frame.add(textPane);
        frame.setSize(260, 430);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    public static void setText(String a)
    {textPane.setText(a);}
    public static void clear()
    {textPane.setText("");}
    
    
    public static void resize()
    {
        //frame.setSize((int)(Math.round(Maps.map[0].length * 12.1)), (int)(Math.round(Maps.map.length * 22.7)) );
    }

    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        
        System.out.println(Player.x + " " + Player.y);
        Player.move(ke.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent ke) {}

} 