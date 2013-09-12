package teapot_saga_iv;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
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
    static GroupLayout layout = new GroupLayout(jp);
    
    static JTextArea info = new JTextArea();
    static JTextPane textPane = new JTextPane();
    static JTextArea stats = new JTextArea();
    static Font font = new Font("Monospaced",Font.PLAIN,20);
    
    static Color brown = new Color(100,40,10);
    //static Color lbrown = new Color(200,150,110);
    
    public Window()  //creates frame
    {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        Main.frameActive = true;

        info.setEditable(false);
        info.setFont(font);
        info.setBackground(Color.WHITE);
        info.setForeground(brown);
        info.addKeyListener(this);
        
        info.setText("Default Info Text.");
        
        textPane.setEditable(false);
        textPane.setFont(font);
        textPane.setBackground(brown);
        textPane.setForeground(Color.WHITE);
        textPane.addKeyListener(this);
        
        stats.setEditable(false);
        stats.setFont(font);
        stats.setBackground(Color.WHITE);
        stats.setForeground(brown);
        stats.addKeyListener(this);
        
        stats.setText("Default Stats Text.");
        
        setLayout();
        jp.setLayout(layout);
        
        frame.setSize(400, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(jp);
        frame.setVisible(true);
        
    }
    
    private static void setLayout()
    {
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)//component - min, prefrence, max
                    .addComponent(textPane)
                    .addComponent(stats))
                .addContainerGap()));
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(info, 100, 100, 100)//component - min, prefrence, max
                .addComponent(textPane, 400, 800, Short.MAX_VALUE)//component - min, prefrence, max
                .addComponent(stats, 100, 100, 100)//component - min, prefrence, max
                .addContainerGap()));
    }
    
    public static void setText(String a)
    {textPane.setText(a);}
    public static void clear()
    {textPane.setText("");}
    
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        
        //System.out.println(ke.getKeyChar() + ": " +ke.getKeyCode());
        Player.move(ke.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent ke) {}

} 