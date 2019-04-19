import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

import java.security.InvalidParameterException;

/*
 * This class offers all methods which are needed to perform an encryption with a substitution-permutation-network (SPN)
 */
public class SPN {

    private Tools tools = new Tools();

    private int r;
    private int n;
    private int m;
    private int s;

    //K(k,i) consisting of n concurrent bits of k starting at position 4i
    private String[] encipherRoundKeys;
    private String[] decipherRoundKeys;
    private int[] bitPermutation = {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};
    private BidiMap sBox = new DualHashBidiMap();

    public SPN(int r, int n, int m, int s) {
        this.r = r;
        this.n = n;
        this.m = m;
        this.s = s;
        encipherRoundKeys = new String[r];
        decipherRoundKeys = new String[r];
        initializeKeys();
        initializeSBox();
    }

    /*
     * Encipher a given plain text and return the chiffre text
     *
     * @param plainText the 16-bit plain text to encipher
     * @return the enciphered chiffre text
     */
    public String encipher(String plainText) {
        if (plainText.length() > n*m) {
            throw new InvalidParameterException("Plaintext-length " + plainText.length() +
                    " is greater than n * m = " + n*m);
        }
        // Initial Whitestep
        String sBoxInput = tools.xorStrings(plainText, encipherRoundKeys[0]);
        String sBoxOutput = "";
        String bitPermutOutput = "";

        for (int i = 1; i < r; i++) {
            sBoxOutput = executeSBox(sBoxInput);
            // Do not execute bit permutation in the last round
            if (i < r-1) {
                bitPermutOutput = executeBitpermutation(sBoxOutput);
                sBoxInput = tools.xorStrings(bitPermutOutput, encipherRoundKeys[i]);
            }
            else {
                sBoxInput = tools.xorStrings(sBoxOutput, encipherRoundKeys[i]);
            }
        }
        return sBoxInput;
    }

    /*
     * Decipher a given chiffre text and return the plain text
     *
     * @param chiffreText the 16-bit chiffre text to decipher
     * @return the deciphered plain text
     */
    public String decipher(String chiffreText) {
        if (chiffreText.length() > n*m) {
            throw new InvalidParameterException("Plaintext-length " + chiffreText.length() +
                    " is greater than n * m = " + n*m);
        }
        initializeDecipherKeys();

        // Initial Whitestep
        String sBoxInput = tools.xorStrings(chiffreText, decipherRoundKeys[0]);
        String sBoxOutput = "";
        String bitPermutOutput = "";

        for (int i = 1; i < r; i++) {
            sBoxOutput = executeInverseSBox(sBoxInput);
            // Do not execute bit permutation in the last round
            if (i < r - 1) {
                bitPermutOutput = executeBitpermutation(sBoxOutput);
                sBoxInput = tools.xorStrings(bitPermutOutput, decipherRoundKeys[i]);
            } else {
                sBoxInput = tools.xorStrings(sBoxOutput, decipherRoundKeys[i]);
            }
        }
        return sBoxInput;
    }

    /*
     * Execute the S-Box. Send a String to the S-Box and transform it.
     *
     * @param input input to lookup in the SBox
     * @return the value given by the SBox
     */
    public String executeSBox(String input) {
        if (input.length() > n*m) {
            throw new InvalidParameterException("Input-length " + input.length() +
                    " is greater than n * m = " + n*m);
        }
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
     * @param input input to lookup in the inverse SBox
     * @return the value given by the inverse SBox
     */
    public String executeInverseSBox(String input) {
        if (input.length() > n*m) {
            throw new InvalidParameterException("Input-length " + input.length() +
                    " is greater than n * m = " + n*m);
        }
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
     * @param input input to permute
     * @return the value given by the permutation
     */
    public String executeBitpermutation(String input) {
        if (input.length() > n*m) {
            throw new InvalidParameterException("Input-length " + input.length() +
                    " is greater than n * m = " + n*m);
        }
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
        sBox.put("0000", "1110");    // 0 <=> E
        sBox.put("0001", "0100");    // 1 <=> 4
        sBox.put("0010", "1101");    // 2 <=> D
        sBox.put("0011", "0001");    // 3 <=> 1
        sBox.put("0100", "0010");    // 4 <=> 2
        sBox.put("0101", "1111");    // 5 <=> F
        sBox.put("0110", "1011");    // 6 <=> B
        sBox.put("0111", "1000");    // 7 <=> 8
        sBox.put("1000", "0011");    // 8 <=> 3
        sBox.put("1001", "1010");    // 9 <=> A
        sBox.put("1010", "0110");    // A <=> 6
        sBox.put("1011", "1100");    // B <=> C
        sBox.put("1100", "0101");    // C <=> 5
        sBox.put("1101", "1001");    // D <=> 9
        sBox.put("1110", "0000");    // E <=> 0
        sBox.put("1111", "0111");    // F <=> 7
    }

    public void initializeKeys() {
        encipherRoundKeys[0] = "0011101010010100";
        encipherRoundKeys[1] = "1010100101001101";
        encipherRoundKeys[2] = "1001010011010110";
        encipherRoundKeys[3] = "0100110101100011";
    }

    public void initializeDecipherKeys() {
        for (int i = 0; i < r; i++) {
            if (i == 0) {
                decipherRoundKeys[r-1] = encipherRoundKeys[0];
            }
            if (i == r-1) {
                decipherRoundKeys[0] = encipherRoundKeys[r-1];
            }
            else {
                decipherRoundKeys[i] = executeBitpermutation(encipherRoundKeys[i]);
            }
        }
    }
}
