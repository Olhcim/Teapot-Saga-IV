
package teapot_saga_iv.files;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.jdesktop.layout.GroupLayout;
import teapot_saga_iv.Main;

public class OptionMenu extends JFrame{
    
    private JButton exitButton;
    private JButton restartButton;
    private JButton continueButton;
    private JTextArea textArea;
    
    public OptionMenu(String text)
    {
        
        
        
        addComponents();
        
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        
        textArea.setText(text);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
    
    private void addComponents() {

        exitButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        continueButton = new javax.swing.JButton();
        textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { exitButtonActionPerformed(evt); }
        });

        restartButton.setText("Restart");
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { restartButtonActionPerformed(evt); }
        });

        continueButton.setText("Continue");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { continueButtonActionPerformed(evt); }
        });

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);


        GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(exitButton,        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(restartButton,     org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(continueButton,    org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(textArea)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(textArea)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(exitButton)
                    .add(restartButton)
                    .add(continueButton)))
        );

        pack();
    }
    
    
    private void exitButtonActionPerformed(ActionEvent evt) {
        System.exit(0);
    }
    
    private void restartButtonActionPerformed(ActionEvent evt) {
        Main.setupGame();
    }
    
    private void continueButtonActionPerformed(ActionEvent evt) {
        
    }
    
    
    
    public static void main(String[] args)
    {
        JFrame frame = new OptionMenu("Hello, my name is nathen and i think this line of text is too long.");
    }
    
}
