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
    }

    public Boolean contains(Point2D p) {
        return null;
    }

    public void draw() {
    }

    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    public Point2D nearest(Point2D p) {
        return null;
    }
}
