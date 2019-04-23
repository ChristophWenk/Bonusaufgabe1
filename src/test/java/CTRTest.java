import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CTRTest {
    Tools tools = new Tools();
    String chiffreText = tools.readChiffreText("src/main/resources/chiffre.txt");
    CTR ctr = new CTR(4,4,4,32,"00111010100101001101011000111111", chiffreText);

    @Test
    public void testDecipher() {
        String plainText = ctr.decipher();

        String[] asciiPackages = new String[plainText.length()/8];

        for (int i = 0; i < asciiPackages.length; i++ ) {
            asciiPackages[i] = plainText.substring(i*8, i*8 + 8);
        }

        String output = "";
        for (String text : asciiPackages) {
            output += tools.convertBinaryToASCII(text);
        }

        assertEquals("Gut gemacht!",output);
    }


}
