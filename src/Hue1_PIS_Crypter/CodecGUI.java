package Hue1_PIS_Crypter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by robin at 15:39, 23.10.2019, 2019
 */

public class CodecGUI extends JFrame implements ActionListener {

    private JButton start = new JButton("Start");
    private static JTextArea klartext = new JTextArea();
    private static JTextArea geheimtext = new JTextArea();
    private static JTextField loesungswort = new JTextField();
    private JRadioButton caesar = new JRadioButton("Caesar", false);
    private JRadioButton wuerfel = new JRadioButton("Wuerfel", false);
    private JRadioButton crypt = new JRadioButton("Crypt", false);
    private JRadioButton decrypt = new JRadioButton("Decrypt", false);


    public CodecGUI() {

        //Container für die 'Aufbewahrung' von Objekten
        Container ct = this.getContentPane();
        ct.setLayout(new BorderLayout());
        this.setTitle("The Crypter!");
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setAlwaysOnTop(false);
        this.setVisible(true);
        this.setSize(700, 300);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Attrtibute werden der CodecGUI hinzugefuegt
        ct.add(start,BorderLayout.PAGE_START);
        ct.add(klartext,BorderLayout.CENTER);
        ct.add(geheimtext,BorderLayout.PAGE_END);
        ct.add(loesungswort,BorderLayout.LINE_START);
        ct.add(caesar,BorderLayout.LINE_END);
        ct.add(wuerfel);
        ct.add(decrypt);
        ct.add(crypt);

        //Jedes Attribut erhaelt eine Position und eine Hoehe, sowie eine Breite (x,y,w,h)

        //(J)RadioButton kann nur einzeln ausgewaehlt werden, nicht beide gleichzeitig
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(caesar);
        buttonGroup.add(wuerfel);
        caesar.setBackground(Color.LIGHT_GRAY);
        wuerfel.setBackground(Color.LIGHT_GRAY);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(crypt);
        buttonGroup1.add(decrypt);
        crypt.setBackground(Color.LIGHT_GRAY);
        decrypt.setBackground(Color.LIGHT_GRAY);

        //start,caesar und wuerfel erhalten zusaetzlich noch einen Aufruf der ActionListener Methode, damit die Attribute auch als Interaktives Objekt gesehen wird
        start.addActionListener(this);
        caesar.addActionListener(this);
        wuerfel.addActionListener(this);
        crypt.addActionListener(this);
        decrypt.addActionListener(this);

        //Für schoeneres Aussehen
        JTextArea klartextinfo = new JTextArea("Klartext");
        klartextinfo.setFont(new Font("Dialog", 3, 10));
        klartextinfo.setBounds(50, 10, 60, 20);
        klartextinfo.setOpaque(false);
        klartextinfo.setHighlighter(null);
        klartextinfo.setEditable(false);
        ct.add(klartextinfo);

        JTextArea geheimtextinfo = new JTextArea("Geheimtext");
        geheimtextinfo.setFont(new Font("Dialog", 3, 10));
        geheimtextinfo.setBounds(50, 85, 60, 20);
        geheimtextinfo.setOpaque(false);
        geheimtextinfo.setHighlighter(null);
        geheimtextinfo.setEditable(false);
        ct.add(geheimtextinfo);

        JTextArea loesungswortinfo = new JTextArea("Loesungswort");
        loesungswortinfo.setFont(new Font("Dialog", 3, 10));
        loesungswortinfo.setBounds(50, 160, 70, 20);
        loesungswortinfo.setOpaque(false);
        loesungswortinfo.setHighlighter(null);
        loesungswortinfo.setEditable(false);
        ct.add(loesungswortinfo);

        start.setBackground(Color.DARK_GRAY);
        start.setForeground(Color.WHITE);

        klartext.setBackground(Color.BLACK);
        klartext.setForeground(Color.WHITE);

        geheimtext.setBackground(Color.BLACK);
        geheimtext.setForeground(Color.WHITE);

        loesungswort.setBackground(Color.BLACK);
        loesungswort.setForeground(Color.WHITE);

    }

    public static String getLoesungswort() {
        return loesungswort.getText();
    }

    public static String getKlarText() {
        return klartext.getText();
    }

    public static String getGeheimText() {
        return geheimtext.getText();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Caesar c1 = new Caesar();
        Wuefel w1 = new Wuefel(loesungswort.getText());

        if (e.getSource() == this.start) {
            if (crypt.isSelected()) {
                if (caesar.isSelected()) {
                    if (getKlarText().isEmpty() || getKlarText().equalsIgnoreCase(" ")) {
                        JOptionPane.showMessageDialog(null, "Type in some Text in 'Klartext'.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else this.geheimtext.setText(c1.kodiere(this.klartext.getText()));
                } else if (wuerfel.isSelected()) {
                    if (getKlarText().isEmpty() || getKlarText().equalsIgnoreCase(" ")) {
                        JOptionPane.showMessageDialog(null, "Type in some Text in 'Klartext'.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else this.geheimtext.setText(w1.kodiere(this.klartext.getText()));
                }
            }
            if (decrypt.isSelected()) {
                if (caesar.isSelected()) {
                    if (getGeheimText().isEmpty() || getGeheimText().equalsIgnoreCase(" ")) {
                        JOptionPane.showMessageDialog(null, "Type in some Text in 'Geheimtext'.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else this.klartext.setText(c1.dekodiere(this.geheimtext.getText()));
                } else if (wuerfel.isSelected()) {
                    if (getGeheimText().isEmpty() || getGeheimText().equalsIgnoreCase(" ")) {
                        JOptionPane.showMessageDialog(null, "Type in some Text in 'Geheimtext'.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else this.klartext.setText(w1.dekodiere(this.geheimtext.getText()));
                }
            }
        }
        if(e.getSource() == this.start && (getLoesungswort().isEmpty() || getLoesungswort().equalsIgnoreCase("")) && (getKlarText().isEmpty() || getKlarText().equalsIgnoreCase("")) && (getGeheimText().isEmpty() || getGeheimText().equalsIgnoreCase("")))
            JOptionPane.showMessageDialog(null, "Type in some Text.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
