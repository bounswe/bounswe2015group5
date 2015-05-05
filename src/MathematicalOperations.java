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
     * Performs the identity operation
     * Added by Arda Akdemir.
     * @param x Operand
     * @return identity of x
     */
    public static int unaryPlus(int x) {
        return x;
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

    /**
     * Performs the times operation
     * Added by Murat Can Karacabey.
     * @param x First operand
     * @param x Second operand
     * @return x times y
     */
    public static int times(int x,int y) {
        return x * y;
    }

    /**
     * Performs division of x by y.
     * Added by Ömer Ulusal.
     * @param x First operand
     * @param y Second operand
     * @return division of x by y.
     */
    public static int divide(int x, int y) {
        return (y!=0) ? x/y : void;
    }

}
