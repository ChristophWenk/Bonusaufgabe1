import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * This class offers all methods which are needed to perform an encryption with a substitution-permutation-network (SPN)
 */
public class SPN {

    int r = 4;
    int n = 4;
    int m = 4;
    int s = 32;
    long k = 0b00111010100101001101011000111111;
    long chiffretext;
    byte[] chiffre;

    //K(k,i) bestehe aus 16 aufeinanderfolgenden Bits von k beginned bei Position 4i
    long k0 = 0b0011_1010_1001_0100;
    long k1 = 0b1010_1001_0100_1101;
    long k2 = 0b1001_0100_1101_0110;
    long k3 = 0b0100_1101_0110_0011;

    private int[] bitPermutation = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};

    private BidiMap sBox = new DualHashBidiMap();

    public SPN() {
        initializeSBox();
        encipher("");
    }

    public void readChiffre(String file) {
        try {
            chiffre = Files.readAllBytes(Paths.get(file));
            int i = 2;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @param plainText the plain text to encipher
     */
    public void encipher(String plainText) {

    }

    public void decipher() {
    }

    /*
     * Execute the S-Box. Send a String to the S-Box and transform it.
     *
     * @param input 16-bit input to lookup in the SBox
     * @return the 16-bit value given by the SBox
     */
    public String executeSBox(String input) {
        String output = "";

       for (int i = 0; i < m; i++ ) {
           String sBoxInput = input.substring(i*m, i*m + m);
           output += sBox.get(sBoxInput).toString();
       }
        return output;
    }

    /*
     * Execute the inverse S-Box. Send a String to the inverse S-Box and transform it.
     *
     * @param input 16-bit input to lookup in the inverse SBox
     * @return the 16-bit value given by the inverse SBox
     */
    public String executeInverseSBox(String input) {
        String output = "";

        for (int i = 0; i < m; i++ ) {
            String sBoxInput = input.substring(i*m, i*m + m);
            output += sBox.getKey(sBoxInput).toString();
        }
        return output;
    }

    /*
     * Execute the bit permutation. Scramble an input String according to the permutation definition.
     *
     * @param input 16-bit input to permute
     * @return the value given by the permutation
     */
    public String executeBitpermutation(String input) {
        char[] charList = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            int newBitPosition = bitPermutation[i];
            charList[newBitPosition] = input.charAt(i);
        }
        String output = String.copyValueOf(charList);
        return output;
    }

    /*
     * Initialize S-Box values
     */
    public void initializeSBox() {
        sBox.put("0000", "1110");    // 0 => E
        sBox.put("0001", "0100");    // 1 => 4
        sBox.put("0010", "1101");    // 2 => D
        sBox.put("0011", "0001");    // 3 => 1
        sBox.put("0100", "0010");    // 4 => 2
        sBox.put("0101", "1111");    // 5 => F
        sBox.put("0110", "1011");    // 6 => B
        sBox.put("0111", "1000");    // 7 => 8
        sBox.put("1000", "0011");    // 8 => 3
        sBox.put("1001", "1010");    // 9 => A
        sBox.put("1010", "0110");    // A => 6
        sBox.put("1011", "1100");    // B => C
        sBox.put("1100", "0101");    // C => 5
        sBox.put("1101", "1001");    // D => 9
        sBox.put("1110", "0000");    // E => 0
        sBox.put("1111", "0111");    // F => 7
    }
}
