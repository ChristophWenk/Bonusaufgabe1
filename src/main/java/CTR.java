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


    public String[] decipher() {
        String[] yArray = getyArray();
        String yMinus1 = yArray[0];
        String[] storeSPNChunks = new String[8];

        // Send all the chunks to the SPN
        String yn = yMinus1;
        for (int i = 0; i < (8-1); i++) {
            if (i > 0) {
                yn = tools.add1ToY(yn);
            }
            storeSPNChunks[i] = spn.encipher(yn);
        }

        //Xor Chunks with y0...yn-1
        String[] plainText = new String[8];
        for (int i = 0; i < (8-1); i++) {
            plainText[i] = tools.xorStrings(storeSPNChunks[i],yArray[i+1]);
        }

        return plainText;
    }
}
