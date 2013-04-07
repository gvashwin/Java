import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
public class Fast {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        ArrayList<Point> pointsList = new ArrayList<Point>();
        ArrayList<Point> points2Draw = new ArrayList<Point>();
        ArrayList<Point> startPoints = new ArrayList<Point>();
        ArrayList<Point> endPoints = new ArrayList<Point>();
        Point[] pointsBySlope = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            pointsBySlope[i] = p;
            pointsList.add(p);
            p.draw();
        }
        Collections.sort(pointsList);
        int i = 0;
        while (i <  pointsList.size()) {
            Point currPoint = pointsList.get(i);
            Arrays.sort(pointsBySlope, currPoint.SLOPE_ORDER);
            double slope2Match = 0.0;
            int j = 0;
            Point p1 = pointsBySlope[j];
            for (j = 1; j < N-2;) {
                int k = j; // 2nd point
                Point p2 = pointsBySlope[k];
                int l = k+1; // 3rd point
                Point p3 = pointsBySlope[l];
                int m = l+1; // 4th point
                Point p4 = pointsBySlope[m];
                slope2Match = p1.slopeTo(p2);
                if ((p1.slopeTo(p3) == slope2Match) 
                        && (p1.slopeTo(p4) == slope2Match)) {
                    points2Draw.add(p1);
                    points2Draw.add(p2);
                    points2Draw.add(p3);
                    points2Draw.add(p4);
                    int n = m + 1;
                    while (n < N
                            && (p1.slopeTo(pointsBySlope[n]) == slope2Match)) {
                        points2Draw.add(pointsBySlope[n]);
                        n = n + 1;
                    }
                    Collections.sort(points2Draw);
                    int len = points2Draw.size();
                    Point pn = (points2Draw.get(len-1));
                    Point ps = (points2Draw.get(0));
                    boolean lineDrawn = false;
                    assert (startPoints.size() == endPoints.size());
                    for (int s = 0; s < startPoints.size(); s++) {
                        if ((startPoints.get(s) == ps) && (endPoints.get(s) == pn)) {
                            lineDrawn = true;
                        }
                    } 
                    if (!lineDrawn) {
                        ps.drawTo(pn);
                        startPoints.add(ps);
                        endPoints.add(pn);
                        StdOut.println("");
                        int arrow = 0;
                        for (Point pointToPrint : points2Draw) {
                            if (arrow == 0) {
                                StdOut.print(""+pointToPrint.toString());
                                arrow++;
                            }
                            else
                                StdOut.print(" -> "+pointToPrint.toString());
                        }
                    }
                    points2Draw.clear();
                    j = n;
                } else {
                    j++;
                }
            }
            i++;
        }
        // display to screen all at once
        StdDraw.show(0);
    }
}

