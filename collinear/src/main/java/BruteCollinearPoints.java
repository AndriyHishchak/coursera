import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments;
    private Point[] originPoints;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        checkPoint(points);
        checkNullPoint(points);

        originPoints = points.clone();
        Arrays.sort(originPoints);
        checkRepeatedPoint(originPoints);

        lineSegments = new ArrayList<LineSegment>();

        for (int i = 0; i < originPoints.length - 3; i++) {
            for (int j = i + 1; j < originPoints.length - 2; j++) {
                for (int k = j + 1; k < originPoints.length - 1; k++) {
                    for (int l = k + 1; l < originPoints.length; l++) {
                        if (originPoints[i].slopeTo(originPoints[j]) == originPoints[i].slopeTo(originPoints[k])
                                && originPoints[i].slopeTo(originPoints[k]) == originPoints[i]
                                .slopeTo(originPoints[l])) {
                            lineSegments.add(new LineSegment(originPoints[i], originPoints[l]));
                        }
                    }
                }
            }
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
     File file = new File("equidistant.txt");

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
     BruteCollinearPoints collinear = new BruteCollinearPoints(points);
     StdOut.println(collinear.numberOfSegments());

     for (LineSegment segment : collinear.segments()) {
     StdOut.println(segment);
     segment.draw();
     }
     StdDraw.show();
     }
}