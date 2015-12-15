package com.bounswe2015group5.nmf;

import Jama.Matrix;
/**
 *
 * @author Mehmet Burak Kurutmaz
 */
public class NMF {
    public static final double ALPHA = 0.0002;
    public static final double BETA = 0.02;
    
    public static void factorize(Matrix R, Matrix P, Matrix Q, int K, int steps, double alpha, double beta) {
        for (int step = 0; step < steps; step++) {
            int[] row = {0};
            int[] col = {0};
            for (int i = 0; i < R.getRowDimension(); i++) {
                row[0] = i;
                for (int j = 0; j < R.getColumnDimension(); j++) {
                    if (R.get(i, j) > 0) {
                        col[0] = j;
                        double eij = R.get(i, j) - P.getMatrix(row, 0, K - 1).times(Q.getMatrix(0, K - 1, col)).det();
                        for (int k = 0; k < K; k++) {
                            P.set(i, k, P.get(i, k) + alpha * (2 * eij * Q.get(k, j) - beta * P.get(i, k)));
                            Q.set(k, j, Q.get(k, j) + alpha * (2 * eij * P.get(i, k) - beta * Q.get(k, j)));
                        }
                    }
                }
            }

            double e = 0;
            for (int i = 0; i < R.getRowDimension(); i++) {
                row[0] = i;
                for (int j = 0; j < R.getColumnDimension(); j++) {
                    if (R.get(i, j) > 0) {
                        col[0] = j;
                        e = e + Math.pow(R.get(i, j) - P.getMatrix(row, 0, K - 1).times(Q.getMatrix(0, K - 1, col)).det(), 2);
                        for (int k = 0; k < K - 1; k++) {
                            e = e + (beta / 2) * (Math.pow(P.get(i, k), 2) + Math.pow(Q.get(k, j), 2));
                        }
                    }
                }
                if (e < 0.001) {
                    break;
                }
            }
        }
    }
    
    public static void factorize(Matrix R, Matrix P, Matrix Q, int K, int steps){
        factorize(R, P, Q, K, steps, ALPHA, BETA);
    }
}
