import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by can on 03.05.2015.
 */
public class TestMathematicalOperations {


    @Test
    public void testBinaryPlus() throws Exception {
        assertEquals(1, MathematicalOperations.binaryPlus(3, -2));
        assertEquals(0, MathematicalOperations.binaryPlus(3, -3));
        assertEquals(1355, MathematicalOperations.binaryPlus(1333, 22));
    }
}