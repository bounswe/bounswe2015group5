import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Can Guler on 03.05.2015.
 */
public class TestMathematicalOperations {

    /**
     * Epsilon for floating point operations.
     */
    private static final double epsilon = 1e-15;

    /**
     * Unit test for binary plus operation
     * Added by Can Guler
     *
     * @throws Exception
     */
    @Test
    public void testBinaryPlus() throws Exception {
        assertEquals(1, MathematicalOperations.binaryPlus(3, -2));
        assertEquals(0, MathematicalOperations.binaryPlus(3, -3));
        assertEquals(1355, MathematicalOperations.binaryPlus(1333, 22));
        assertEquals(2 * Integer.MAX_VALUE, MathematicalOperations.binaryPlus(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testBinaryMinus() throws Exception {
        assertEquals(3, MathematicalOperations.binaryMinus(15, 12));
        assertEquals(-119, MathematicalOperations.binaryMinus(-72, 47));
        assertEquals(64, MathematicalOperations.binaryMinus(-23, -87));
    }

    @Test
    public void testAbs() throws Exception {
        assertEquals(8, MathematicalOperations.abs(-8), epsilon); //test when operand is negative
        assertEquals(23, MathematicalOperations.abs(23), epsilon); //test when operand is positive
        assertEquals(0, MathematicalOperations.abs(0), epsilon); //test when operand is 0
    }

    @Test
    public void testTimes() throws Exception {
        assertEquals(0, MathematicalOperations.times(0, 50));
        assertEquals(2000, MathematicalOperations.times(100, 20));
        assertEquals(-5, MathematicalOperations.times(-5, 1));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivide() throws Exception {
        assertEquals(1, MathematicalOperations.divide(50, 50));
        assertEquals(-5, MathematicalOperations.divide(100, -20));
        assertEquals(1, MathematicalOperations.divide(130, 100));
        assertEquals(Integer.MAX_VALUE, MathematicalOperations.divide(7, 0));
    }

    @Test
    public void testRemainder() throws Exception {
        assertEquals(0, MathematicalOperations.remainder(20, 4));
        assertEquals(1, MathematicalOperations.remainder(121, 20));
        assertEquals(7, MathematicalOperations.remainder(107, 10));
    }

}
