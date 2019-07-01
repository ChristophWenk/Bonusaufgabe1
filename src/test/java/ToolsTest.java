import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToolsTest {

    private Tools tools;

    @Before
    public void initializeTest() {
        tools = new Tools();
    }

    @Test
    public void testxorStrings() {
        assertEquals("0010", tools.xorStrings("1011","1001"));
    }

    @Test
    public void testReadChiffreText() {
        assertEquals("00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000",
            tools.readChiffreText("src/main/resources/chiffre.txt"));
    }

    @Test
    public void testYMinus1(){
        tools.readChiffreText("src/main/resources/chiffre.txt");
        assertEquals("0000010011010010",tools.yMinus1());
    }

    @Test
    public void testNormalizeText() {
        assertEquals(  "000001001101001000001011101110000000001010001\n" +
                                "111100011100111111101100000010100010100001110\n" +
                                "1000000001001101100111001010111011000010",
                tools.normalizeText(
                          "000001001101001000001011101110000000001010001\n" +
                                "111100011100111111101100000010100010100001110\n" +
                                "10000000010011011001110010101110110000", 16));
    }

    @Test
    public void testCalculateModulo() {
        assertEquals("1110",tools.calculateModuloToBase2("1110"));
        assertEquals("0000",tools.calculateModuloToBase2("0000"));
    }
}
