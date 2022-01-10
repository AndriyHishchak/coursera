import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {

    private final SET<Point2D> points;

    public PointSET() {
        points = new SET<>();
    }

    private void checkNullToObject(Object object) {
        if (object == null)
            throw new IllegalArgumentException();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        checkNullToObject(p);
        points.add(p);
    }

    public boolean contains(Point2D p) {
        checkNullToObject(p);
        return points.contains(p);
    }

    public void draw() {
        for (Point2D p : points)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        checkNullToObject(rect);
        Bag<Point2D> bags = new Bag<>();
        for (Point2D point2D : points)
            if (point2D.x() >= rect.xmin() && point2D.x() <= rect.xmax() && point2D.y() >= rect.ymin() && point2D.y() <= rect.ymax())
                bags.add(point2D);
        return bags;
    }

    public Point2D nearest(Point2D p) {
        checkNullToObject(p);
        double minDist = Double.POSITIVE_INFINITY;
        Point2D point = null;
        for (Point2D point2D : points) {
            double currDist = p.distanceSquaredTo(point2D);
            if (currDist < minDist) {
                minDist = currDist;
                point = point2D;
            }
        }
        return point;
    }
}
