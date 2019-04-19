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
}
