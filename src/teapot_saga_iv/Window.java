package teapot_saga_iv;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Window extends JFrame implements KeyListener
{   
    private static JFrame jFrame = new JFrame("Teavup Saga IV: The Teapocolypse");
    private static JPanel jPanel = new JPanel();
    private static GroupLayout layout = new GroupLayout(jPanel);

    public static JLabel dialogLabel;
    public static JLabel mapLabel;
    public static JLabel statsLabel;
    
    /*
     * Creats the JFrame and preformes all the necessary actions such as the layout.
     */
    public Window()
    {   
        
        Render.LoadGlyphs();
        dialogLabel = new JLabel(new ImageIcon(Render.dialog));
        mapLabel = new JLabel(new ImageIcon(Render.map));
        statsLabel = new JLabel(new ImageIcon(Render.stats));
        
        setLayout(); 
        jPanel.setLayout(layout);
        
        jFrame.setSize(Render.WIDTH_PIXELS, Render.TOTAL_HEIGHT_PIXELS);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        jFrame.addKeyListener(this);
        
    }
    
    private static void setLayout()
    {
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dialogLabel, Render.WIDTH_PIXELS, Render.WIDTH_PIXELS, Render.WIDTH_PIXELS)
                    .addComponent(mapLabel)
                    .addComponent(statsLabel) )));
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dialogLabel, Render.DIALOG_HEIGHT_PIXELS, Render.DIALOG_HEIGHT_PIXELS, Render.DIALOG_HEIGHT_PIXELS)
                .addComponent(mapLabel, Render.MAP_HEIGHT_PIXELS, Render.MAP_HEIGHT_PIXELS, Render.MAP_HEIGHT_PIXELS)
                .addComponent(statsLabel, Render.STATS_HEIGHT_PIXELS, Render.STATS_HEIGHT_PIXELS, Render.STATS_HEIGHT_PIXELS) ));
    }
    
    public static void repaintAll()
    {
        jPanel.repaint();
    }
    
    public static void repaintMap()
    {
        mapLabel.repaint();
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
        
        System.out.println(ke.getKeyChar() + ": " +ke.getKeyCode());
        Player.move(ke.getKeyCode());
    }

    /*
     * Checks for a key that has been Released.
     */
    @Override
    public void keyReleased(KeyEvent ke) {}

} 