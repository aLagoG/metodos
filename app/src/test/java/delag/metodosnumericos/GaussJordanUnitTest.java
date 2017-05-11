package delag.metodosnumericos;

import org.junit.Test;

import static org.junit.Assert.*;

public class GaussJordanUnitTest {

    @Test
    public void constructor() throws Exception {
        boolean ex = false;
        try {
            new GaussJordan(new double[][]{});
        } catch (IllegalArgumentException e) {
            ex = true;
        }
        assertTrue(ex);
        ex = false;
        try {
            new GaussJordan(new double[][]{{}});
        } catch (IllegalArgumentException e) {
            ex = true;
        }
        assertTrue(ex);
        ex = false;
        try {
            new GaussJordan(new double[][]{{1}});
        } catch (IllegalArgumentException e) {
            ex = true;
        }
        assertFalse(ex);
    }

    @Test
    public void getNextPivot() throws Exception {
        GaussJordan gj = new GaussJordan(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Pair<Integer, Integer> p = gj.getNextPivot();
        assertEquals((Integer) 0, p.getFirst());
        assertEquals((Integer) 0, p.getSecond());

        gj = new GaussJordan(new double[][]{{0, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        p = gj.getNextPivot();
        assertEquals((Integer) 1, p.getFirst());
        assertEquals((Integer) 0, p.getSecond());

        gj = new GaussJordan(new double[][]{{0, 0, 3}, {0, 5, 6}, {0, 8, 9}});
        p = gj.getNextPivot();
        assertEquals((Integer) 1, p.getFirst());
        assertEquals((Integer) 1, p.getSecond());

        gj = new GaussJordan(new double[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assertEquals(null, gj.getNextPivot());

        gj = new GaussJordan(new double[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        gj.row = 1;
        gj.col = 1;
        assertEquals(null, gj.getNextPivot());
    }

    @Test
    public void isReduced() throws Exception {
        GaussJordan gj = new GaussJordan(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertFalse(gj.isReduced());

        gj = new GaussJordan(new double[][]{{1, 2, 3}, {0, 5, 6}, {0, 0, 9}});
        assertTrue(gj.isReduced());

        gj = new GaussJordan(new double[][]{{0, 2, 3}, {0, 0, 6}, {0, 0, 0}});
        assertTrue(gj.isReduced());

        gj = new GaussJordan(new double[][]{{0, 2, 0}, {0, 0, 6}, {0, 0, 0}});
        assertTrue(gj.isReduced());

        gj = new GaussJordan(new double[][]{{0, 2, 3}, {0, 0, 0}, {0, 0, 6}});
        assertFalse(gj.isReduced());

        gj = new GaussJordan(new double[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assertTrue(gj.isReduced());
    }

    @Test
    public void switchRows() throws Exception {
        GaussJordan gj = new GaussJordan(new double[][]{{0, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        gj.switchRows(1);
        assertArrayEquals(new double[]{4, 5, 6}, gj.matrix[0], 0.1);
        assertArrayEquals(new double[]{0, 2, 3}, gj.matrix[1], 0.1);

        gj = new GaussJordan(new double[][]{{0, 0, 3}, {0, 5, 6}, {0, 8, 9}});
        gj.switchRows(1);
        assertArrayEquals(new double[]{0, 5, 6}, gj.matrix[0], 0.1);
        assertArrayEquals(new double[]{0, 0, 3}, gj.matrix[1], 0.1);
    }

    @Test
    public void rowMultiply() throws Exception {
        GaussJordan gj = new GaussJordan(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertArrayEquals(gj.rowMultiply(gj.matrix[0], 2), new double[]{2, 4, 6}, 0.1);
        assertArrayEquals(gj.rowMultiply(gj.matrix[0], -2), new double[]{-2, -4, -6}, 0.1);
    }

    @Test
    public void rowSum() throws Exception {
        GaussJordan gj = new GaussJordan(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertArrayEquals(gj.rowSum(gj.matrix[0], gj.matrix[1]), new double[]{5, 7, 9}, 0.1);
    }

    @Test
    public void doGaussStep() throws Exception {
        GaussJordan gj = new GaussJordan(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        gj.doGaussStep();
        assertArrayEquals(gj.matrix[0], new double[]{1, 2, 3}, 0.1);
        assertArrayEquals(gj.matrix[1], new double[]{0, -3, -6}, 0.1);
        assertArrayEquals(gj.matrix[2], new double[]{0, -6, -12}, 0.1);
        gj.doGaussStep();
        assertArrayEquals(gj.matrix[0], new double[]{1, 2, 3}, 0.1);
        assertArrayEquals(gj.matrix[1], new double[]{0, -3, -6}, 0.1);
        assertArrayEquals(gj.matrix[2], new double[]{0, 0, 0}, 0.1);
        assertNull(gj.doGaussStep());
        gj = new GaussJordan(new double[][]{{0, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        gj.doGaussStep();
        assertArrayEquals(gj.matrix[0], new double[]{4, 5, 6}, 0.1);
        assertArrayEquals(gj.matrix[1], new double[]{0, 2, 3}, 0.1);
        assertArrayEquals(gj.matrix[2], new double[]{7, 8, 9}, 0.1);
    }

    @Test
    public void doGaussJordan() throws Exception {
        GaussJordan gj = new GaussJordan(new double[][]{{2, 1, 2}, {1, 1, 1.5}});
        gj.doGaussJordan();
        assertArrayEquals(new double[]{1,0,0.5}, gj.matrix[0], 0.1);
        assertArrayEquals(new double[]{0,1,1}, gj.matrix[1], 0.1);
    }
}
