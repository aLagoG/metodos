package delag.metodosnumericos;

public class InterpolationPolynomials {

    public static String getInterpolationPolinomial(double[] xs, double[] ys) {
        if (xs.length != ys.length) {
            throw new IllegalArgumentException("Both arrays should be the same legth");
        }
        double[][] matrix = new double[xs.length][xs.length];
        for (int i = 0; i < xs.length; i++) {
            matrix[i][0] = 1;
            for (int j = 1; j < xs.length; j++) {
                matrix[i][j] = Math.pow(xs[i], j);
            }
        }
        matrix = MatrixInverse(matrix);
        double[][] mul = new double[ys.length][1];
        for (int i = 0; i < ys.length; i++) {
            mul[i][0] = ys[i];
        }
        matrix = MatrixMultiply(matrix, mul);
        String res = "f(x) = ";
        if (Math.abs(matrix[0][0]) > 0.0001) {
            res += matrix[0][0];
        }
        for (int i = 1; i < matrix.length; i++) {
            if (Math.abs(matrix[i][0]) < 0.0001) {
                continue;
            }
            res += (res.length() > 7 ? " " + (matrix[i][0] > 0 ? "+ " : "") : "") + matrix[i][0] + "x" + (i > 1 ? "^" + i : "");
        }
        return res;
    }

    public static double[][] MatrixMultiply(double[][] a, double[][] b) {
        double[][] res = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                double sum = 0;
                for (int k = 0; k < b.length; k++) {
                    sum += a[i][k] * b[k][j];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }

    public static double[][] MatrixInverse(double[][] matrix) {
        double[][] nn = new double[matrix.length][matrix.length << 1];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, nn[i], 0, matrix.length);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                nn[i][j + matrix.length] = i == j ? 1 : 0;
            }
        }
        GaussJordan gj = new GaussJordan(nn);
        nn = gj.doGaussJordan();
        double[][] res = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(nn[i], matrix.length, res[i], 0, matrix.length);
        }
        return res;
    }


}
