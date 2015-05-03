import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by can on 03.05.2015.
 */
public class TestMathematicalOperations {
	
	private static final double epsilon = 1e-15; //epsilon for double operations

    @Test
    public void testBinaryPlus() throws Exception {
        assertEquals(1, MathematicalOperations.binaryPlus(3, -2));
        assertEquals(0, MathematicalOperations.binaryPlus(3, -3));
        assertEquals(1355, MathematicalOperations.binaryPlus(1333, 22));
    }
    
    @Test
    public void testAbs() throws Exception {
        assertEquals(8, MathematicalOperations.abs(-8),epsilon); //test when operand is negative
        assertEquals(23, MathematicalOperations.abs(23),epsilon); //test when operand is positive
        assertEquals(0, MathematicalOperations.abs(0),epsilon); //test when operand is 0
    }
}
