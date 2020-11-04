import java.util.Arrays;

/**
 * Created by robin at 16:51, 17.11.2019, 2019
 */

public class Array2d {

    public static void main(String[] args) {
        String[][] f = new String[3][3];
        String klar = "BAB";
        String lw = "THM";
        int counter = 0;

        for (int i = 0; i < klar.length(); i++) {
            for (int j = 0; j < lw.length(); j++) {
                f[i][j] = "1";
                System.out.println(Arrays.toString(f[i]));
            }
        }
    }


}
