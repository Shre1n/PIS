import Hue1_PIS_Crypter.Codec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by robin at 15:16, 23.10.2019, 2019
 */

public class Wuerfel1 implements Codec {

    private String loesungswort = "";
    private ArrayList<Integer> numb = new ArrayList<>();
    private String losungswortlow = "";

    Wuerfel1(String loesungswort) {
        setzeLoesung(loesungswort);
    }


    @Override
    public String kodiere(String klartext) {
        if(isInput(loesungswort)){
            getZahlen();
            StringBuilder verschluesselterText = new StringBuilder();
            for (int j : numb) {
                while (j < klartext.length()) {
                    verschluesselterText.append(klartext.charAt(j));
                    j += this.losungswortlow.length();
                }
            }
            return verschluesselterText.toString();
        }
        throw new IllegalArgumentException("Loesungswort oder Text ist nicht vorhanden!");
    }


    public void getZahlen() {
        numb.clear();
        for (char c = 'a'; c <= 'z'; c++) {
            if (losungswortlow.indexOf(c) != -1) {
                int next = losungswortlow.indexOf(c);
                numb.add(losungswortlow.indexOf(c));
                while (losungswortlow.indexOf(c, next + 1) != -1) {
                    numb.add(losungswortlow.indexOf(c, next + 1));
                    next = losungswortlow.indexOf(c, next + 1);
                }
            }
        }

    }

    @Override
    public String dekodiere(String geheimtext){
        getZahlen();
        if (this.isInput(geheimtext)) {
            StringBuilder entschluesselterText = new StringBuilder();
            char[] machklar = new char[geheimtext.length()];
            int counter = 0;
            int tiefe = (geheimtext.length() % losungswortlow.length());
            if (tiefe == 0) {
                for (int j : numb) {
                    for (int count = 0; count < (geheimtext.length() / this.losungswortlow.length()); count++) {
                        machklar[j] = geheimtext.charAt(counter);
                        j += this.losungswortlow.length();
                        counter++;
                    }
                }
            } else
                for (int j : numb) {
                    if (j < tiefe) {
                        for (int count = 0; count < (geheimtext.length() / this.losungswortlow.length()) + 1; count++) {
                            machklar[j] = geheimtext.charAt(counter);
                            j += this.losungswortlow.length();
                            counter++;
                        }
                    } else
                        for (int count = 0; count < (geheimtext.length() / this.losungswortlow.length()); count++) {
                            machklar[j] = geheimtext.charAt(counter);
                            j += this.losungswortlow.length();
                            counter++;
                        }
                }

            for (char c : machklar) {
                entschluesselterText.append(c);
            }
            return entschluesselterText.toString();
        }
        throw new IllegalArgumentException("Loesungswort oder Text ist nicht vorhanden!");

    }

    @Override
    public String gibLoesung() {
        return loesungswort;
    }

    public boolean isInput(String input) {
        if(input.isEmpty() || input.equalsIgnoreCase("")){
            return false;
        }
        return true;
    }

    @Override
    public void setzeLoesung(String schluessel) throws IllegalArgumentException {
        if(isInput(schluessel)) {
            loesungswort = schluessel;
            return;
        }
        throw  new IllegalArgumentException("Loesungswort nicht vorhanden!");

    }
}
