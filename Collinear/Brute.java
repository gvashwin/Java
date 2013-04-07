import java.util.Arrays;
public class Brute {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            p.draw();
        }
        Arrays.sort(points);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    for (int l = k + 1; l < N; l++) {
                        Point p1 = points[i];
                        Point p2 = points[j];
                        Point p3 = points[k];
                        Point p4 = points[l];
                        if ((p1.slopeTo(p2) == p1.slopeTo(p3)) 
                                && (p1.slopeTo(p3) == p1.slopeTo(p4))) {
                            p1.drawTo(p4);
                            StdOut.println(""+p1.toString()+" -> " 
                                             +p2.toString()+" -> " 
                                             +p3.toString()+" -> " 
                                             +p4.toString());
                        }
                    }
                }
            }
        }

        // display to screen all at once
        StdDraw.show(0);
    }
}
