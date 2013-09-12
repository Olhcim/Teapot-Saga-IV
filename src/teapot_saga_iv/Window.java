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
    static Font font = new Font("Monospaced",Font.PLAIN,16);
    
    static Color brown = new Color(100,40,10);
    static Color lbrown = new Color(200,150,110);
    
    public Window()  //creates frame
    {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        Main.frameActive = true;
        
        //stats.setSize(500, 10);
        info.setEditable(false);
        info.setFont(font);
        info.setBackground(lbrown);
        info.setForeground(brown);
        info.addKeyListener(this);
        
        //textPane.setSize(1000, 890);
        textPane.setEditable(false);
        textPane.setFont(font);
        textPane.setBackground(brown);
        textPane.setForeground(Color.WHITE);
        textPane.addKeyListener(this);
        
        //stats.setSize(500, 100);
        stats.setEditable(false);
        stats.setFont(font);
        stats.setBackground(lbrown);
        stats.setForeground(brown);
        stats.addKeyListener(this);
        
        setLayout();
        jp.setLayout(layout);
        
        frame.setSize(1000, 1000);
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
                    .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                    .addComponent(textPane)
                    .addComponent(stats))
                .addContainerGap()));
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()//component - min, pref, max
                .addComponent(info, 100, 100, 100)
                .addComponent(textPane, 500, 800, 800)
                .addComponent(stats, 100, 100, 100)
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