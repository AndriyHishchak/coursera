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

    public Boolean isEmpty() {
        return points.isEmpty();
    }

    public Integer size() {
        return points.size();
    }

    public void insert(Point2D p) {
        checkNullToObject(p);
        points.add(p);
    }

    public Boolean contains(Point2D p) {
        checkNullToObject(p);
        return points.contains(p);
    }

    public void draw() {
        for (Point2D p : points)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    public Point2D nearest(Point2D p) {
        return null;
    }
}
