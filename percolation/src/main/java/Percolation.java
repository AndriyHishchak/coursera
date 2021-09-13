import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // This is the N x N grid
    public int[][] grid;
    public int size;
    public int numberOpenSites;

    private WeightedQuickUnionUF uf;
    private int topVirtualSite;
    private int bottomVirtualSite;

    // There are two values that a grid element can have: "open" or "blocked"
    public static final int FULL = 0;
    public static final int OPEN = 1;

    /**
     * The Percolation class.
     *
     * @param gridSize the size of the two-dimensional grid.
     */
    public Percolation(int gridSize) {
        size = gridSize;

        // We want to initialize all entries to be full.
        grid = new int[size][size];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                grid[row][col] = FULL;
            }
        }

        // No sites are open.
        numberOpenSites = 0;

        // Create a UF object for all the points in the gridSize-by-gridSize grid 
        // PLUS two "virtual" sites (the top and bottom).
        // The "top" virtual site will have index 0.
        // The "bottom" virtual site will have index 1.
        uf = new WeightedQuickUnionUF(gridSize * gridSize + 2);

        // Set the top and bottom virtual sites.
        topVirtualSite = gridSize * gridSize;
        bottomVirtualSite = gridSize * gridSize + 1;

    }

    /**
     * Convert a grid coordinate (i,j) to a a single integer
     *
     * @param row the row coordinate for the site
     * @param col the column coordinate for the site
     * @return the id in the UnionFind array corresponding to site (i, j)
     */
    private int rowColToSite(int row, int col) {
        return (row - 1) + ((col - 1) * size);
    }

    /**
     * Return true if the site at (row, col) is blocked, false otherwise.
     *
     * @param row the row of the site
     * @param col the column of the site
     * @return true if blocked, false otherwise
     */
    public boolean isFull(int row, int col) {
        return (grid[row - 1][col - 1] == FULL);
    }

    /**
     * Return true if the site at (row, col) is OPEN, false otherwise.
     *
     * @param row the row of the site
     * @param col the column of the site
     * @return true if open, false otherwise
     */
    public boolean isOpen(int row, int col) {
        return (!isFull(row, col));
    }

    /**
     * Change the site at (row, col) to OPEN if not already open. After opening,
     * add this site to the connected components of its open neighbors (north,
     * east, south, and west).
     *
     * @param row the row of the site to be opened
     * @param col the column of the site to be opened
     */
    public void open(int row, int col) {
        if (isFull(row, col)) {
            grid[row - 1][col - 1] = OPEN;
            numberOpenSites = numberOpenSites + 1;

            // We need to connect this site to the open sites
            // to the RIGHT, LEFT, DOWN, and UP.
            int mySite = rowColToSite(row, col);

            // UP
            if (row == 1) {
                // This is the top row, so connect to the top virtual site
                uf.union(topVirtualSite, mySite);
            } else {
                // This is NOT the top row, so connect to the above element IF 
                // the above element is open.
                if (isOpen(row - 1, col)) {
                    uf.union(rowColToSite(row - 1, col), mySite);
                }
            }

            // DOWN
            if (row == size) {
                // This is the bottom row, so connect to the bottom virtual site
                uf.union(bottomVirtualSite, mySite);
            } else {
                // This is NOT the bottom row, so connect to the below element IF 
                // the above element is open.
                if (isOpen(row + 1, col)) {
                    uf.union(rowColToSite(row + 1, col), mySite);
                }
            }

            // LEFT
            if (col != 1) {
                // This is not on the left edge, so connect to the 
                // left neighbor.
                if (isOpen(row, col - 1)) {
                    uf.union(rowColToSite(row, col - 1), mySite);
                }
            }

            // RIGHT
            if (col != size) {
                // This is not on the RIGHT edge, so connect to the 
                // right neighbor.
                if (isOpen(row, col + 1)) {
                    uf.union(rowColToSite(row, col + 1), mySite);
                }
            }

        }
    }

    /**
     * @return Returns true if the system percolates, false otherwise.
     */
    public boolean percolates() {
        return uf.connected(topVirtualSite, bottomVirtualSite);
    }
}