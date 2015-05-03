/**
 * Created by Can Guler on 03.05.2015.
 */

public class MathematicalOperations {

    /**
     * Performs addition of x and y.
     * Added by Can Guler.
     * @param x First operand
     * @param y Second operand
     * @return Sum of x and y
     */
    public static int binaryPlus(int x, int y) {
        return x+y;
    }

    /**
     * Performs subtraction of y from x.
     * Added by Hakan Şahin.
     * @param x First operand
     * @param y Second operand
     * @return x minus y
     */
    public static int binaryMinus(int x, int y) {
        return x - y;
    }
    
    /**
     * Calculates distance to the 0.
     * Added by Mehmet Burak Kurutmaz.
     * @param x Operand
     * @return Absolute value of x
     */
    public static double abs(double x) {
        return (x<0) ? (-x) : x ;
    }
}
