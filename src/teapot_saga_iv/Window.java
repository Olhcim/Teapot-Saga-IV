package teapot_saga_iv;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Window extends JFrame implements KeyListener
{

    public static boolean escPressed = false;
    
    static JFrame frame = new JFrame("Teavup Saga IV: The Teapocolypse");
    static JPanel jp = new JPanel();
    static JTextArea area = new JTextArea();
    static Font font = new Font("Monospaced",Font.PLAIN,20);
    static Color brown = new Color(110,70,30);
    
    public Window()  //creates frame
    {
        
        area.setEditable(false);
        area.setFont(font);
        area.setBackground(Color.WHITE);
        area.setForeground(brown);
        area.addKeyListener(this);

        frame.add(area);
        frame.setSize(400, 375);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        Render render = new Render();

    }
    
    public static void setText(String a)
    {area.setText(a);}
    
    public static void print(String a)
    {area.append(a);}
    public static void print(char a)
    {area.append(a+"");}
    public static void println(String a)
    {area.append(a + "\n");}
    public static void nl()
    {area.append("\n");}
    
    public static void clear()
    {area.setText("");}

    
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