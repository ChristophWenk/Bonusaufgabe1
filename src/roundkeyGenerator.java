import java.sql.Array;
import java.util.ArrayList;

public class roundkeyGenerator {

    public static void main(String[] args) {

        //Runden angeben
        int r =4;
        int m= 4;

        int bitLength = r*m;
        // Gesamtschluessel "totalKey" instanzieren und in einem Array abfuellen
        String totalKey = "00111010100101001101011000111111";
        int totalKeyLength = totalKey.length();
        char[] totalKeyArray = new char[totalKeyLength];

        ArrayList<Character> roundkeys = new ArrayList<Character>();
        char[] roundkeyArray = new char[bitLength];

            for (int i = 0; i < totalKeyArray.length; i++) {
                totalKeyArray[i] = totalKey.charAt(i);
            }

            int i= 0;
            // Runden zaehlen
            while(i<r){
                // Rundenschluessel abfuellen
                for (int j=0; j<bitLength; j++){ ;
                    // Einzelner Schlüssel erstellen
                        roundkeyArray[j] = totalKeyArray[(i*r)+j];
                        System.out.print(roundkeyArray[j]);
                    }
                i++;
                }

            String key = String.copyValueOf(roundkeyArray);
            String[] roundkeys2 = key.split("(?<=\\G\\d{16})");
            System.out.println(roundkeys2[0]);



    }

}

