public class CTR {
    private int r;
    private int n;
    private int m;
    private int s;
    private String totalKey;
    private String chiffreText;
    private int rounds;
    private int bitLength;
    private SPN spn;
    private Tools tools = new Tools();
    private int amountOfChiffreChunks;

    public CTR(int r, int n, int m, int s, String totalKey, String chiffreText) {
        this.r = r;
        this.n = n;
        this.m = m;
        this.s = s;
        this.totalKey = totalKey;
        this.chiffreText = new String(chiffreText);
        bitLength = m*n;
        rounds = chiffreText.length()/bitLength;
        spn = new SPN(r,n,m,s,totalKey);
    }

    public String[] getyArray() {
        int chunkLength = n * m;
        amountOfChiffreChunks = chiffreText.length() / chunkLength;
        String[] yArray = new String[rounds];

        for (int i = 0; i < amountOfChiffreChunks; i++ ) {
            yArray[i] = chiffreText.substring(i*chunkLength, i*chunkLength + chunkLength);
        }

        return yArray;
    }

    public String decipher() {
        String[] yArray = getyArray();
        String yMinus1 = yArray[0];
        // SPN output needs 1 less space because y-1 does not needed to be xor'd
        String[] storeSPNChunks = new String[yArray.length - 1];

        // Send all the chunks (y-1 + i) to the SPN to encrypt them
        String yn = yMinus1;
        for (int i = 0; i < (yArray.length-1); i++) {
            // Do not add anything for the first "real" round (y-1 + 0)
            if (i > 0) {
                yn = tools.add1ToY(yn);
            }
            storeSPNChunks[i] = spn.encipher(yn);
        }

        // Xor SPN output with chiffre text y0...yn-1
        String[] plainText = new String[yArray.length - 1];
        for (int i = 0; i < (yArray.length-1); i++) {
            plainText[i] = tools.xorStrings(storeSPNChunks[i],yArray[i+1]);
        }

        String plainTextString = tools.convertArrayToStringMethod(plainText);

        // Remove padding
        plainTextString = plainTextString.substring(0, plainTextString.lastIndexOf('1'));

        return plainTextString;
    }
}
