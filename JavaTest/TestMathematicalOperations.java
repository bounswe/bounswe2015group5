import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Contains JUnit tests for MathematicalOperations class.
 * Created by Can Guler on 03.05.2015.
 */
public class TestMathematicalOperations {

    /**
     * Epsilon for floating point operations.
     */
    private static final double epsilon = 1e-15;

    /**
     * Unit test for binary plus operation
     *
     * @throws Exception
     * @author Can Guler
     */
    @Test
    public void testBinaryPlus() throws Exception {
        assertEquals(1, MathematicalOperations.binaryPlus(3, -2));
        assertEquals(0, MathematicalOperations.binaryPlus(3, -3));
        assertEquals(1355, MathematicalOperations.binaryPlus(1333, 22));
        assertEquals(2 * Integer.MAX_VALUE, MathematicalOperations.binaryPlus(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    /**
     * Unit test for binary minus operation
     *
     * @throws Exception
     * @author Hakan Sahin
     */
    @Test
    public void testBinaryMinus() throws Exception {
        assertEquals(3, MathematicalOperations.binaryMinus(15, 12));
        assertEquals(-119, MathematicalOperations.binaryMinus(-72, 47));
        assertEquals(64, MathematicalOperations.binaryMinus(-23, -87));
    }

    /**
     * Unit test for Unary Plus operation
     *
     * @throws Exception
     * @author Hakan Sahin
     */

    @Test
    public void testUnaryPlus() throws Exception {
        assertEquals(3, MathematicalOperations.unaryPlus(3));
        assertEquals(-119, MathematicalOperations.unaryPlus(-119));
        assertEquals(0, MathematicalOperations.unaryPlus(0));
    }

    /**
     * Unit test for Unary Minus operation
     *
     * @throws Exception
     * @author Abdullah Hanefi Onaldi
     */

    @Test
    public void testUnaryMinus() throws Exception {
        assertEquals(3, MathematicalOperations.unaryMinus(-3));
        assertEquals(-119, MathematicalOperations.unaryMinus(119));
        assertEquals(64, MathematicalOperations.unaryMinus(-64));
    }

    /**
     * Unit test for absolute value operation
     *
     * @author Mehmet Burak Kurutmaz
     * @throws Exception
     */
    @Test
    public void testAbs() throws Exception {
        assertEquals(8, MathematicalOperations.abs(-8), epsilon); //test when operand is negative
        assertEquals(23, MathematicalOperations.abs(23), epsilon); //test when operand is positive
        assertEquals(0, MathematicalOperations.abs(0), epsilon); //test when operand is 0
    }

    /**
     * Unit test for times operation
     *
     * @author Murat Can Karacabey
     * @throws Exception
     */
    @Test
    public void testTimes() throws Exception {
        assertEquals(0, MathematicalOperations.times(0, 50));
        assertEquals(2000, MathematicalOperations.times(100, 20));
        assertEquals(-5, MathematicalOperations.times(-5, 1));
    }

    /**
     * Unit test for divide operation
     *
     * @throws Exception
     * @author Omer Ulusal
     */

    @Test(expected = ArithmeticException.class)
    public void testDivide() throws Exception {
        assertEquals(-5, MathematicalOperations.divide(100, -20));
        assertEquals(1, MathematicalOperations.divide(130, 100));
        assertEquals(Integer.MAX_VALUE, MathematicalOperations.divide(7, 0));
    }
    
    /**
     * Unit test for inverse divide operation
     * @throws Exception
     * @author Melih Demiroren
     */

    @Test(expected = ArithmeticException.class)
    public void testInverseDivide() throws Exception {
        assertEquals(-2, MathematicalOperations.inverseDivide(10, -20));
        assertEquals(2, MathematicalOperations.inverseDivide(100, 220));
        assertEquals(Integer.MAX_VALUE, MathematicalOperations.inverseDivide(0, 9));
    }

    /**
     * Unit test for remainder operation
     *
     * @throws Exception
     * @author Mustafa Tugrul Ozsahin
     */

    @Test
    public void testRemainder() throws Exception {
        assertEquals(0, MathematicalOperations.remainder(20, 4));
        assertEquals(1, MathematicalOperations.remainder(121, 20));
        assertEquals(7, MathematicalOperations.remainder(107, 10));
    }

    /**
     * Unit Test of power method.
     *
     * @throws java.lang.Exception
     * @author Buket Yilmazel
     */

    @Test
    public void testPower() throws Exception{
        assertEquals(9.0, MathematicalOperations.power(3.0,2.0),epsilon);
        assertEquals(3.0, MathematicalOperations.power(3.0,1.0 ),epsilon);
        assertEquals(0.11,MathematicalOperations.power(3.0,-2),epsilon);

    }

}
