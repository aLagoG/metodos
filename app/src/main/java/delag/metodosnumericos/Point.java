package delag.metodosnumericos;

import android.support.annotation.NonNull;

public class Point implements Comparable<Point> {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(@NonNull Point o) {
        int res = Double.compare(x, o.x);
        if (res == 0) {
            res = Double.compare(y, o.y);
        }
        return res;
    }
}
