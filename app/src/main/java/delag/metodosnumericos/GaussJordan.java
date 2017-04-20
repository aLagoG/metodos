package delag.metodosnumericos;

import android.support.annotation.NonNull;

public class GaussJordan {

    double[][] matrix;
    int col;
    int row;
    int height;
    int length;
    boolean hasToSwitchRows;
    boolean isGaussDone;

    public GaussJordan(@NonNull double[][] matrix) {
        this.matrix = matrix;
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix must be at least 1x1");
        }
        height = matrix.length;
        length = matrix[0].length;
    }

    public boolean isReduced() {
        int last = 0;
        for (int j = 0; j < length; j++) {
            int act = 0;
            for (int i = 0; i < height; i++) {
                if (matrix[i][j] != 0) {
                    act = i;
                }
            }
            if (act != last) {
                if (act != last + 1) {
                    return false;
                }
                last++;
            }
        }
        return true;
    }

    public Pair<Integer, Integer> getNextPivot() {
        int j, i = -1;
        boolean done = false;
        for (j = col; j < length; j++) {
            for (i = row; i < height; i++) {
                if (matrix[i][j] != 0) {
                    done = true;
                    break;
                }
            }
            if (done) {
                break;
            }
        }
        if (!done) {
            return null;
        }
        if (i != row) {
            hasToSwitchRows = true;
        }
        return new Pair<>(i, j);
    }

    public void switchRows(int i) {
        double[] goal = matrix[row];
        matrix[row] = matrix[i];
        matrix[i] = goal;
    }

    public double[] rowMultiply(double[] row, double n) {
        double[] res = new double[length];
        for (int i = 0; i < length; i++) {
            res[i] = row[i] * n;
        }
        return res;
    }

    public double[] rowSum(double[] row1, double[] row2) {
        double[] res = new double[length];
        for (int i = 0; i < length; i++) {
            res[i] = row1[i] + row2[i];
        }
        return res;
    }

    public double[][] doGaussStep() {
        if(isGaussDone){
            return null;
        }
        Pair<Integer, Integer> pivot = getNextPivot();
        if (pivot == null) {
            row = 1;
            col = 0;
            isGaussDone = true;
            return null;
        }
        if (pivot.getFirst() != row) {
            switchRows(pivot.getFirst());
            return matrix;
        }
        for (int i = pivot.getFirst() + 1; i < height; i++) {
            if (matrix[i][pivot.getSecond()]==0){
                continue;
            }
            matrix[i] = rowSum(
                    rowMultiply(matrix[pivot.getFirst()],
                            (matrix[i][pivot.getSecond()] * matrix[pivot.getFirst()][pivot.getSecond()] < 0 ? 1 : -1) *
                                    matrix[i][pivot.getSecond()]),
                    rowMultiply(matrix[i], matrix[pivot.getFirst()][pivot.getSecond()]));
        }
        row++;
        col++;
        return matrix;
    }
}
