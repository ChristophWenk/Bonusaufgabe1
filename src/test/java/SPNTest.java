import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SPNTest {

    private SPN spn;

    @Before
    public void initializeTest() {
        spn = new SPN();
    }

    @Test
    public void testExecuteSBox() {
        assertEquals("0101000111100111",spn.executeSBox("1100001100001111"));
    }

    @Test
    public void testinverseSBox() {
        assertEquals("1100001100001111",spn.executeInverseSBox("0101000111100111"));
    }

    @Test
    public void testExecuteBitpermutation() {
        assertEquals("1001100101010101",spn.executeBitpermutation("1100001100001111"));
    }


}
