import javax.swing.*;
import java.awt.*;

/**
 * Created by robin at 18:12, 19.11.2019, 2019
 */

public class Borderlayout extends JFrame {
    private static void createAndShowGUI(){
        // JFrame erzeugen
        JFrame frame = new JFrame("JFrame mit BorderLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Buttons erzeugen
        JButton pageStart = new JButton("PAGE_START");
        JButton pageEnd = new JButton("PAGE_END");
        JButton center = new JButton("CENTER");
        JButton lineStart = new JButton("LINE_START");
        JButton lineEnd = new JButton("LINE_END");

        // ContentPane haelt standardmaeßig ein BorderLayout
        // Hinzufuegen der Buttons zum Content Pane des JFrames
        frame.getContentPane().add(pageStart, BorderLayout.PAGE_START);
        frame.getContentPane().add(center, BorderLayout.CENTER);
        frame.getContentPane().add(pageEnd, BorderLayout.PAGE_END);
        frame.getContentPane().add(lineStart, BorderLayout.LINE_START);
        frame.getContentPane().add(lineEnd, BorderLayout.LINE_END);

        // Framegroeße anpassen
        // Frame sichtbar machen
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] bla){
        // Diese Operation muss aufgrund Swings Threading-policy
        // durchgeführt werden
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();
            }
        });
    }

}
