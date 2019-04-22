import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SPNTest {

    private SPN spn;
    private SPN spn2;

    @Before
    public void initializeTest() {
        spn = new SPN(4,4,4,32, "00111010100101001101011000111111");
        // Example test provided by A. Vogt
        spn2 = new SPN(4,4,4,32, "00010001001010001000110000000000");
    }

    @Test
    public void testKrysi() {
        // Example test provided by A. Vogt
        assertEquals("1010111010110100",spn2.encipher("0001001010001111"));
    }

    @Test
    public void testExecuteSBox() {
        assertEquals("0101000111100111",spn.executeSBox("1100001100001111"));
    }

    @Test
    public void testExecuteInverseSBox() {
        assertEquals("1100001100001111",spn.executeInverseSBox("0101000111100111"));
    }

    @Test
    public void testExecuteBitpermutation() {
        assertEquals("1001100101010101",spn.executeBitpermutation("1100001100001111"));
    }

    @Test
    public void encipher() {
        assertEquals("1111011010100100",spn.encipher("1100001100001111"));
    }

    @Test
    public void decipher() {
        assertEquals("1100001100001111",spn.decipher("1111011010100100"));
    }

    @Test
    public void compareCiphers() {
        String chiffreText = spn.encipher("1100001100001111");
        String plainText = spn.decipher(chiffreText);

        assertEquals("1100001100001111",plainText);
    }

}
