public class Program {
    public static void main(String[] args) {
        SPN spn = new SPN(4,4,4,32,"00111010100101001101011000111111");

        Tools tools = new Tools();


        String chiffreText = tools.readChiffreText("src/main/resources/chiffre.txt");
        CTR ctr = new CTR(4,4,4,32,"00111010100101001101011000111111", chiffreText);


        String[] klartext = ctr.decipher();
        String plainText = tools.convertArrayToStringMethod(klartext);

        String[] asciiPackages = new String[plainText.length()/8];

        for (int i = 0; i < asciiPackages.length; i++ ) {
            asciiPackages[i] = plainText.substring(i*8, i*8 + 8);
        }

        for (String text : asciiPackages) {
            System.out.print(tools.convertBinaryToASCII(text));
        }
    }
}
