/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
    
    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slopeWithP1 = slopeTo(p1);
            double slopeWithP2 = slopeTo(p2);
            if (slopeWithP1 < slopeWithP2)
                return -1;
            else if (slopeWithP1 == slopeWithP2)
                return 0;
            else
                return 1;

        }
    };


    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        double a = 1.0;
        double pZero = (a - a) / a;
        double nZero = (a - a) / -a;
        if (this.x == that.x && this.y == that.y) // Degenrate line segment
            return Double.NEGATIVE_INFINITY;
        else if (this.x == that.x && this.y != that.y) // Verical line segment 
            return Double.POSITIVE_INFINITY;
        else if (this.y == that.y && this.x != that.x) // Horizontal line segment
            return pZero;
        else
            return ((double) (that.y - this.y)) / ((double) (that.x - this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y < that.y || ((this.y == that.y) && (this.x < that.x)))
            return -1;
        else if (this.y == that.y && this.x == that.x)
            return 0;
        else
            return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p1 = new Point(1, 1);
        Point h1 = new Point(2, 1);
        Point v1 = new Point(1, 2);
        Point p2 = new Point(2, 2);
        StdOut.println("Slope of Horizontal : "+p1.slopeTo(h1));
        StdOut.println("Slope of Vertical : "+p1.slopeTo(v1));
        StdOut.println("Slope of Itself : "+p1.slopeTo(p1));
        StdOut.println("Slope of Normal : "+p1.slopeTo(p2));
    }
}
