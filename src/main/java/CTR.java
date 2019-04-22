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
    public CTR(int r, int n, int m, int s, String totalKey, String chiffreText) {
        this.r = r;
        this.n = n;
        this.m = m;
        this.s = s;
        this.totalKey = totalKey;
        this.chiffreText = new String(chiffreText);
        bitLength = m*n;
        rounds = chiffreText.length()/bitLength;
        SPN spn = new SPN(r,n,m,s,totalKey);
    }


    public String[] getyArray() {
        char[] chiffreTextCharArray = chiffreText.toCharArray();
        char[] chiffreBitArray = new char[bitLength];
        String[] yArray = new String[rounds];
        int q = 0;
        // Count rounds +1 for amount of roundkeys
        while (q < rounds) {
            // generate m*n char-Array with each bit
            for (int j = 0; j < bitLength; j++) {
                chiffreBitArray[j] = chiffreTextCharArray[(q * r) + j];
            }
            // Take each bit and merge it to one String and put it into roundkeyArray
            String chifffrePicker = new String(chiffreBitArray);
            yArray[q] = chifffrePicker;
            q++;
        }
        return yArray;
    }


    public String[] decipher() {
        String result;
        String[] yArray = getyArray();
        // get yMinusN
        String[] storeYminus1 = yMinusN(yArray);
        String[]x= new String[rounds-1];
        for (int i=1; i>rounds-1; i++){
            // run SPN
            result = spn.decipher(storeYminus1[i]);
            // xor
            String xElement = tools.xorStrings(yArray[i],result);
            // store result
            x[i] = xElement;
        }
        return x;
    }

    public String[] yMinusN(String[] yArray){
        String yMinus1 = yArray[0];
        //convert Integer
        // TODO: java.lang.NumberFormatException: For input string: "0000010011010010"

        int yMinusNInteger = Integer.valueOf(yMinus1);
        int[] yMinusNIntArray= new int[rounds];

        // count +1 for each round to y-1
        String[] yMinus1StringArray = new String [rounds];
        for(int i=0; i>rounds;i++) {
            int yMinus1Increment =(yMinusNInteger+i)% (1<< bitLength);
            yMinusNIntArray[i] = yMinus1Increment;
            yMinus1StringArray[i]= String.valueOf(yMinus1Increment);
        }
        return yMinus1StringArray;
    }

}
