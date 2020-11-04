package Hue1_PIS_Crypter;

/**
 * Created by robin at 15:15, 23.10.2019, 2019
 */

public interface Codec {

    /**
     *
     * @param klartext
     * @return new String
     */
    public String kodiere(String klartext);

    /**
     *
     * @param geheimtext
     * @return new String
     */
    public String dekodiere(String geheimtext);

    /**
     *
     * @return loesungswort
     */
    public String gibLoesung();

    /**
     *
     * @param schluessel wird zu loesungswort
     * @throws IllegalArgumentException
     */
    public void setzeLoesung(String schluessel) throws IllegalArgumentException;

}
