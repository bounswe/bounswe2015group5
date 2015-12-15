package com.bounswe2015group5.nmf;

import Jama.Matrix;

/**
 *
 * @author burak
 */
public class Main {

    public static void main(String[] args) {
        double[][] numbers = {
            {30, 2, 0, 12},
            {37, 2, 1, 0},
            {41, 3, 1, 20},
            {42, 1, 0, 16},
            {45, 3, 2, 0},
            {49, 1, 2, 27},
            {51, 0, 1, 30},
            {55, 3, 2, 33},
            {58, 0, 2, 19},
            {60, 2, 0, 24}};
        Matrix R = new Matrix(numbers);
        int N = R.getRowDimension();
        int M = R.getColumnDimension();
        int K = 3;

        Matrix P = Matrix.random(N, K);
        Matrix Q = Matrix.random(K, M);
        NMF.factorize(R, P, Q, K, 5000);

        Matrix R_head = P.times(Q);
        System.out.println("Estimated R");
        R_head.print(4, 2);
        System.out.println("Estimated P");
        P.print(4, 2);
        System.out.println("Estimated Q");
        Q.print(4, 2);
    }
}
