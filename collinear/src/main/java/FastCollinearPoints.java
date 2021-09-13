import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;
    private Point[] orderPoints;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        checkPoint(points);
        checkNullPoint(points);

        orderPoints = points.clone();
        Arrays.sort(orderPoints);
        checkRepeatedPoint(orderPoints);

        lineSegments = new ArrayList<LineSegment>();
        ArrayList<Point> collinear = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];
            Arrays.sort(orderPoints, origin.slopeOrder());
            int count = 0;
            double preSlope = 0.0;
            collinear.add(origin);

            for (int j = 1; j < orderPoints.length - 1; j++) {
                double slope1 = origin.slopeTo(orderPoints[j]);
                double slope2 = origin.slopeTo(orderPoints[j + 1]);

                if (preSlope == 0.0) {
                    preSlope = slope1;
                }

                if (slope1 == slope2 && slope2 == preSlope) {
                    count++;
                    collinear.add(orderPoints[j]);
                    collinear.add(orderPoints[j + 1]);
                } else {
                    if (count >= 2) {
                        Point[] p = new Point[collinear.size()];
                        collinear.toArray(p);
                        Arrays.sort(p);
                        if (p[0] == origin) {
                            lineSegments.add(new LineSegment(origin, p[p.length - 1]));
                        }
                    }
                    count = 0;
                    preSlope = slope2;
                    collinear.clear();
                    collinear.add(origin);
                }
            }
            if (count >= 2) {
                Point[] p = new Point[collinear.size()];
                collinear.toArray(p);
                Arrays.sort(p);
                if (p[0] == origin) {
                    lineSegments.add(new LineSegment(origin, p[p.length - 1]));
                }
            }
            collinear.clear();
        }
    }

    private void checkPoint(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points is null");
        }
    }

    private void checkNullPoint(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("point " + points[i] + " is null.");
            }
        }
    }

    private void checkRepeatedPoint(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("repeated point: " + points[i] + " " + points[i + 1] + ".");
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        // the line segments
        LineSegment[] segments = new LineSegment[numberOfSegments()];
        return lineSegments.toArray(segments);
    }

     public static void main(String[] args) {
     File file = new File("input20.txt");

     // read the n points from a file
     In in = new In(file);
     int n = in.readInt();
     StdOut.println(n);
     Point[] points = new Point[n];
     for (int i = 0; i < n; i++) {
     int x = in.readInt();
     int y = in.readInt();
     points[i] = new Point(x, y);
     }

     // draw the points
     StdDraw.enableDoubleBuffering();
     StdDraw.setXscale(0, 32768);
     StdDraw.setYscale(0, 32768);
     for (Point p : points) {
     p.draw();
     }
     StdDraw.show();

     // print and draw the line segments
     FastCollinearPoints collinear = new FastCollinearPoints(points);
     for (LineSegment segment : collinear.segments()) {
     StdOut.println(segment);
     segment.draw();
     }
     StdDraw.show();
     }
}