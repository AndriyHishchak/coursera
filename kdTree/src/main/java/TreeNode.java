import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class TreeNode {
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
