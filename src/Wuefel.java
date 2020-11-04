import Hue1_PIS_Crypter.Codec;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Eine Klasse, die es ermoeglicht nach dem Wuerfel1-Prinzip zu verschluesseln
 * @author Robin Hahn
 */

public class Wuefel implements Codec {

    private String loesungswort;
    private String loesungswortlow = loesungswort.toLowerCase();

    Wuefel(String loesungswort){
        this.loesungswort = loesungswort;
    }

    public boolean isInput(String input) {
        return (input.isEmpty() || input.equalsIgnoreCase(" ")) ? false : true;
    }

    @Override
    public String kodiere(String klartext) {
        if(isInput(loesungswort)) {
            StringBuilder cryptText = new StringBuilder();
            int[] sortnumb = getZahlen();
            Arrays.sort(sortnumb);
            for (int j : sortnumb) {
                while (j < klartext.length()){
                    cryptText.append(klartext.charAt(j));
                    j += loesungswort.length();
                }
            }
            System.out.println(cryptText);


        }
        throw new IllegalArgumentException("Kein Wort mit den gewollten Anforderungen vorhanden");
    }

    @Override
    public String dekodiere(String geheimtext) {
        getZahlen();
        if(isInput(geheimtext)){
            StringBuilder entcryptText = new StringBuilder();
            char[] klartext = new char[geheimtext.length()];
            int col = geheimtext.length() % loesungswort.length();
            int[] sortnumb = getZahlen();
            int counter = 0;
            if(col == 0){
                for (int r : sortnumb) {
                    for (int i = 0; i < (geheimtext.length() / loesungswort.length()); i++) {
                        klartext[r] = geheimtext.charAt(counter);
                        r += loesungswort.length();
                        counter++;
                    }
                }
            }else for (int r : sortnumb) {
                if(r < col) {
                    for (int i = 0; i < (geheimtext.length() / loesungswort.length()) + 1; i++) {
                        klartext[r] = geheimtext.charAt(counter);
                        r += loesungswort.length();
                        counter++;
                    }
                }else
                    for (int i = 0; i < (geheimtext.length() / loesungswort.length()); i++) {
                        klartext[r] = geheimtext.charAt(counter);
                        r += loesungswort.length();
                        counter++;
                    }
            }
            for (char c : klartext) {
                entcryptText.append(c);
            }
            return entcryptText.toString();
        }
        throw new IllegalArgumentException("Loesungswort oder Text ist nicht vorhanden!");
    }

    public int[] getZahlen() {
        ArrayList<Character> wortList = new ArrayList<>();
        int[] num = new int[loesungswort.length()];
        int numbers = 1;
        loesungswortlow = loesungswort.toLowerCase();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();       //Alphabet wird in einem Array hinzugefuegt


        for(char c : loesungswortlow.toCharArray()){
            wortList.add(c);                                                //jeder charArray wird in Arraylist hinzugefuegt
        }

        for(int i=0;i<alphabet.length;i++){
            while(wortList.contains(alphabet[i])){                          //aktueller Buchstabe noch enthalten?
                for(int j=0;j<wortList.size();j++){
                    if(wortList.get(j) == alphabet[i]){
                        num[wortList.indexOf(alphabet[i])] = numbers;
                        numbers++;
                        wortList.set(j,' ');                                //kommt buchstabe vor? wenn ja, wird er 'geloescht', damit keine Annomalien entstehen
                    }
                }
            }
        }
        return num;
    }

    @Override
    public String gibLoesung() {
        return loesungswort;
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