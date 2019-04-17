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

}
