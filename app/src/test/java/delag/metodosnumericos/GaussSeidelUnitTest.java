package delag.metodosnumericos;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class GaussSeidelUnitTest {

    @Test
    public void doMethod() throws Exception {
        GaussSeidel gs = new GaussSeidel(new double[][]{{2, 1, 2}, {1, 1, 1.5}}, new double[]{1, 1}, 1);
        assertArrayEquals(new double[]{0.5, 1}, gs.doMethod(), 0.1);
        gs = new GaussSeidel(new double[][]{{4, 1, -1, 3}, {2, 7, 1, 19}, {1, -3, 12, 31}}, new double[]{1, 1, 1}, 1);
        assertArrayEquals(new double[]{1, 2, 3}, gs.doMethod(), 0.1);

        gs = new GaussSeidel(new double[][]{{4, 1, -1, 3}, {2, 7, 1, 19}, {1, -3, 12, 31}}, null, 1);
        assertArrayEquals(new double[]{1, 2, 3}, gs.doMethod(), 0.1);
    }

}
