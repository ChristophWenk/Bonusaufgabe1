/*
 * This class generates all round keys needed by an SPN
 */
public class RoundKeyGenerator2 {

    private int r;
    private int n;
    private int m;
    private int s;
    private String totalKey;

    /*
     * Construct the RoundKeyGenerator
     */
    public RoundKeyGenerator2(int r, int n, int m, int s, String totalKey) {
        this.r = r;
        this.n = n;
        this.m = m;
        this.s = s;
        this.totalKey = totalKey;
    }

    /*
     * Generate n round keys for the generators total key
     *
     * @return List with all the round keys
     */
    public String[] getRoundKey() {
        // instance length of the roundkeys
        int bitLength = r * m;
        // instance length of totalkey for iteration
        int totalKeyLength = totalKey.length();
        // instance helper Arrays
        char[] totalKeyArray = new char[totalKeyLength];
        char[] roundkeyBitArray = new char[bitLength];
        // instance Array for all roundkeys
        // +1 for the amount of roundkeys
        String[] roundkeyArray = new String[m+1];

        // filling "totalkey" into helper Array "totalkeyArray"
        for (int i = 0; i < totalKeyArray.length; i++) {
            totalKeyArray[i] = totalKey.charAt(i);
        }

        int q = 0;
        // Count rounds +1 for amount of roundkeys
        while (q <= r) {
            // generate m*n char-Array with each bit
            for (int j = 0; j < bitLength; j++) {
                roundkeyBitArray[j] = totalKeyArray[(q * r) + j];
            }
            // Take each bit and merge it to one String and put it into roundkeyArray
            String roundkeyPicker = new String(roundkeyBitArray);
            roundkeyArray[q] = roundkeyPicker;

            q++;
        }

        return roundkeyArray;
    }


}

