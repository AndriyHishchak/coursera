import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private static TreeNode root;
    private int size;


    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
    }

    public boolean contains(TreeNode root, Point2D p) { // does the set contain point p?
        checkNullToObject(p);
        return contains(root, p);
    }

    public void draw() {
    }

    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    public Point2D nearest(Point2D p) {
        return null;
    }

    private void checkNullToObject(Object object) {
        if (object == null)
            throw new IllegalArgumentException();
    }
}