package delag.metodosnumericos;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class InterpolationPolynomialsUnitTest {

    @Test
    public void matrixInverse() {
        double[][] res = InterpolationPolynomials.MatrixInverse(new double[][]{{1, 1, 1}, {1, 2, 4}, {1, 3, 9}});
        assertArrayEquals(new double[]{3, -3, 1}, res[0], 0.1);
        assertArrayEquals(new double[]{-(5d / 2), 4, -(3d / 2)}, res[1], 0.1);
        assertArrayEquals(new double[]{1d / 2, -1, 1d / 2}, res[2], 0.1);
    }

    @Test
    public void matrixMultiply() {
        double[][] res = InterpolationPolynomials.MatrixMultiply(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, new double[][]{{2}, {4}, {6}});
        assertArrayEquals(new double[]{28}, res[0], 0.1);
        assertArrayEquals(new double[]{64}, res[1], 0.1);
        assertArrayEquals(new double[]{100}, res[2], 0.1);
    }

    @Test
    public void interpolationPolynomials() {
        String res = InterpolationPolynomials.getInterpolationPolinomial(new double[]{1, 2, 3}, new double[]{3, 5, 2});
        assertEquals("f(x) = -4.0 + 9.5x -2.5x^2", res);
        res = InterpolationPolynomials.getInterpolationPolinomial(new double[]{1, 2}, new double[]{1, 2});
        assertEquals("f(x) = 1.0x", res);
    }
}
