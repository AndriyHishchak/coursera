import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private TreeNode root;
    private int size;

    private static class TreeNode {
        private final Point2D point;
        private TreeNode left, right;
        private final RectHV rect;
        private final boolean isSeparatedByX;

        TreeNode(Point2D p, TreeNode left, TreeNode right, RectHV rect, boolean isSeparatedByX) {
            this.point = p;
            this.left = left;
            this.right = right;
            this.rect = rect;
            this.isSeparatedByX = isSeparatedByX;
        }
    }

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
        checkNullToObject(p);
        if (isEmpty()) {
            root = new TreeNode(p, null, null,
                    new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                            Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY), true);
            size = 1;
            return;
        }
        TreeNode currNode = root, pre;
        do {
            if (currNode.point.equals(p))
                return;
            pre = currNode;
            if ((currNode.isSeparatedByX && p.x() <= currNode.point.x()) ||
                    (!currNode.isSeparatedByX && p.y() <= currNode.point.y()))
                currNode = currNode.left;
            else
                currNode = currNode.right;

        } while (currNode != null);
        if (pre.isSeparatedByX) {
            if (p.x() <= pre.point.x())
                pre.left = new TreeNode(p, null, null,
                        new RectHV(pre.rect.xmin(), pre.rect.ymin(), pre.point.x(), pre.rect.ymax()), false);
            else
                pre.right = new TreeNode(p, null, null,
                        new RectHV(pre.point.x(), pre.rect.ymin(), pre.rect.xmax(), pre.rect.ymax()), false);
        } else {
            if (p.y() <= pre.point.y())
                pre.left = new TreeNode(p, null, null,
                        new RectHV(pre.rect.xmin(), pre.rect.ymin(), pre.rect.xmax(), pre.point.y()), true);
            else
                pre.right = new TreeNode(p, null, null,
                        new RectHV(pre.rect.xmin(), pre.point.y(), pre.rect.xmax(), pre.rect.ymax()), true);
        }
        ++size;
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        return false;
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