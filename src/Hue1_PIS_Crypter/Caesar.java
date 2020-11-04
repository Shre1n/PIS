package Hue1_PIS_Crypter;


import javax.swing.*;

/**
 * Created by robin at 15:16, 23.10.2019, 2019
 */


public class Caesar implements Codec {

    private String verschiebung = CodecGUI.getLoesungswort(); //gibt die verschiebung der Buchstaben an

    @Override
    public String kodiere(String klartext) {
        char[] k = klartext.toCharArray();
        char[] res = new char[k.length];
        for (char i = 0; i < klartext.length(); i++) {
            res[i] = (char) (k[i] + Integer.parseInt(verschiebung) % 26);
        }
        return new String(res);
    }



    @Override
    public String dekodiere(String geheimtext) {
        char[] g = geheimtext.toCharArray();
        char[] res = new char[g.length];
        for (int i = 0; i < geheimtext.length(); i++) {
            res[i] = (char) (g[i] - Integer.parseInt(verschiebung) % 26);
            if((g[i] >= '\u0065' && g[i] <= '\u0090') || (g[i] >= '\u0097' && g[i] <= '\u0122')){
               continue;
            }
        }
        return new String(res);
    }

    @Override
    public String gibLoesung() {return null;}

    @Override
    public void setzeLoesung(String schluessel) throws IllegalArgumentException {

    }
}
