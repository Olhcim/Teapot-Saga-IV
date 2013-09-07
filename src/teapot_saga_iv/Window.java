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

    static JFrame frame = new JFrame("Teavup Saga IV: The Teapocolypse");
    static JPanel jp = new JPanel(),mp = new JPanel();
    static JTextArea
            dialog = new JTextArea(),
            area = new JTextArea(),
            info = new JTextArea();
    static Font font = new Font("Monospaced",Font.PLAIN,20);
    static Color brown = new Color(110,70,30);
    
    public Window()  //creates frame
    {
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        
        dialog.setEditable(false);
        dialog.setFont(font);
        dialog.setBackground(brown);
        dialog.setForeground(Color.WHITE);
        jp.add(dialog);
        
        area.setEditable(false);
        area.setFont(font);
        area.setBackground(Color.WHITE);
        area.setForeground(brown);
        area.addKeyListener(this);
        jp.add(area);
        
        info.setEditable(false);
        info.setFont(font);
        info.setBackground(brown);
        info.setForeground(Color.WHITE);
        jp.add(info);

        frame.addKeyListener(this);
        frame.add(jp);
        frame.setSize(400, 450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
    public static void setText(String a)
    {area.setText(a);}
    
    public static void println()
    {area.append("\n");}
    public static void println(String a)
    {area.append(a + "\n");}
    
    public static void clear()
    {area.setText("");}

    
    
    
    
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.append("hi");
    }

    @Override
    public void keyReleased(KeyEvent ke) {}

} 