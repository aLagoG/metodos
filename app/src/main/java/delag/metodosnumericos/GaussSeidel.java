package delag.metodosnumericos;

import android.support.annotation.NonNull;

public class GaussSeidel {
    double[][] A;
    double[] res;
    final double maxError;
    final int length;
    final int height;
    private final int maxCount = 1000;

    public GaussSeidel(@NonNull double[][] A, double[] res, double maxError) {
        this.A = A;
        this.maxError = maxError;
        if (A.length == 0 || A[0].length == 0) {
            throw new IllegalArgumentException("Matrix must be at least 1x1");
        }
        if (A.length + 1 != A[0].length) {
            throw new IllegalArgumentException("Matrix must be n x n+1");
        }
        this.res = res == null ? new double[A.length] : res;
        if (this.res.length != A.length) {
            throw new IllegalArgumentException("Guess doesn't match");
        }
        if (maxError <= 0) {
            throw new IllegalArgumentException("The maximum error should be greater than 0");
        }
        height = A.length;
        length = A[0].length;
    }

    public double[] doMethod() {
        double error;
        double[] prevs = new double[height];
        System.arraycopy(res, 0, prevs, 0, height);
        int count = 0;
        do {
            error = 0;
            for (int i = 0; i < height; i++) {
                double sum = 0;
                for (int j = 0; j < height; j++) {
                    if (j == i) {
                        continue;
                    }
                    sum += A[i][j] * res[j];
                }
                res[i] = (1d / A[i][i]) * (A[i][height] - sum);
                error += 100 * Math.abs((prevs[i] - res[i]) / res[i]);
            }
        } while (error > maxError && ++count < maxCount);
        return res;
    }
}
