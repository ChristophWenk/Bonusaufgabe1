import java.util.ArrayList;
import java.util.Arrays;

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
        char[] roundkeyBitArray = new char[bitLength];
        String[] roundkeyArray = new String[m];

        for (int i = 0; i < totalKeyArray.length; i++) {
                totalKeyArray[i] = totalKey.charAt(i);
            }

            int q= 0;
            // Count rounds
            while(q<r){
                // generate m*n char-Array with each bit
                for (int j=0; j<bitLength; j++){ ;
                        roundkeyBitArray[j] = totalKeyArray[(q*r)+j];
                    }
                    // Take each bit and merge it to one String and put it into roundkeyArray
                String roundkeyPicker = new String(roundkeyBitArray);
                roundkeyArray[q]= roundkeyPicker;

                q++;
                }
       System.out.println(Arrays.toString(roundkeyArray));


    }

}

