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
        assertEquals("1010",spn.executeSBox("1001"));
    }

    @Test
    public void testExecuteBitpermutation() {
        spn.executeBitpermutation("1");
        //assertEquals("");
    }

}
