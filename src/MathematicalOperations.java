/**
 * Contains simple mathematical operations.
 * Created by Can Guler on 03.05.2015.
 */

public class MathematicalOperations {

    /**
     * Performs addition of x and y.
     * @author Can Guler.
     * @param x First operand
     * @param y Second operand
     * @return Sum of x and y
     */
    public static int binaryPlus(int x, int y) {
        return x + y;
    }

    /**
     * Performs subtraction of y from x.
     * @author Hakan Sahin.
     * @param x First operand
     * @param y Second operand
     * @return x minus y
     */
    public static int binaryMinus(int x, int y) {
        return x - y;
    }

    /**
     * Performs the identity operation
     * @author Arda Akdemir.
     * @param x Operand
     * @return identity of x
     */
    public static int unaryPlus(int x) {
        return x;
    }

    /**
     * Returns the Additive inverse of the operand
     * @author Abdullah Hanefi Onaldi
     * @param x Operand
     * @return the additive inverse of the Operand
     */
     public static int unaryMinus(int x) {
         return -x;
     }

    /**
     * Calculates distance to the 0.
     * @author Mehmet Burak Kurutmaz.
     * @param x Operand
     * @return Absolute value of x
     */
    public static double abs(double x) {
        return (x < 0) ? (-x) : x;
    }

    /**
     * Performs the times operation
     * @author Murat Can Karacabey.
     * @param x First operand
     * @param y Second operand
     * @return x times y
     */
    public static int times(int x, int y) {
        return x * y;
    }

    /**
     * Performs division of x by y.
     * @author Omer Ulusal.
     * @param x First operand
     * @param y Second operand
     * @return division of x by y.
     * @throws ArithmeticException
     */
    public static int divide(int x, int y) {
        return x / y;
    }

    /**
     * Returns the remainder from division
     * @author Mustafa Tugrul Ozsahin
     * @param x the number divided
     * @param y the divisor
     * @return the remainder, (or x mod y)
     * @throws ArithmeticException
     */
    public static int remainder(int x, int y) {
        return x % y;
    }
}
