import static org.junit.Assert.*;

import org.junit.Test;


public class TestPercolation {

    @Test
    public void PercolationTestsBasic() {
        // Create a Percolation object.
        Percolation percolation = new Percolation(10);
        assertTrue(percolation instanceof Percolation);

        // Check that some of the elements in the initialized array are BLOCKED.
        assertEquals(percolation.grid[0][0], Percolation.FULL);
        assertEquals(percolation.grid[2][3], Percolation.FULL);

        // Check the is_blocked method
        assertTrue(percolation.isFull(1, 1));
        assertTrue(percolation.isFull(2, 3));
        assertTrue(percolation.isFull(10, 10));

        assertFalse(percolation.isOpen(1, 1));

        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1, 1));

    }


    @Test
    public void PercolationTestMainOne() {
        // Create a Percolation object (case N = 1).
        Percolation percolation = new Percolation(1);
        assertTrue(percolation instanceof Percolation);

        assertTrue(percolation.isFull(1, 1));
        assertFalse(percolation.percolates());

        percolation.open(1, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void PercolationTestMainTwo() {
        // Create a Percolation object (case N = 2).
        Percolation percolation = new Percolation(2);
        assertTrue(percolation instanceof Percolation);

        assertTrue(percolation.isFull(1, 1));
        assertFalse(percolation.percolates());

        percolation.open(1, 1);
        assertFalse(percolation.percolates());

        percolation.open(1, 2);
        assertFalse(percolation.percolates());

        percolation.open(2, 1);
        assertTrue(percolation.percolates());

        percolation.open(2, 2);
        assertTrue(percolation.percolates());
    }

    @Test
    public void PercolationTestMainFour() {
        // Create a Percolation object (case N = 4).
        Percolation percolation = new Percolation(4);
        assertTrue(percolation instanceof Percolation);

        assertTrue(percolation.isFull(1, 1));
        assertFalse(percolation.percolates());

        percolation.open(1, 1);
        assertFalse(percolation.percolates());

        percolation.open(1, 2);
        assertFalse(percolation.percolates());

        percolation.open(2, 1);
        assertFalse(percolation.percolates());

        percolation.open(2, 2);
        assertFalse(percolation.percolates());

        percolation.open(2, 3);
        assertFalse(percolation.percolates());

        percolation.open(3, 3);
        assertFalse(percolation.percolates());

        percolation.open(4, 4);
        assertFalse(percolation.percolates());

        percolation.open(3, 4);
        assertTrue(percolation.percolates());
    }

    @Test
    public void PercolationTestStats1() {
        PercolationStats perStat = new PercolationStats(200, 100);
    }

    // Test for exceptions.
    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void PercolationTestsException1() {
        Percolation percolation = new Percolation(10);
        assertTrue(percolation.isOpen(20, 1));
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void PercolationTestsException2() {
        Percolation percolation = new Percolation(10);
        assertTrue(percolation.isOpen(11, 1));
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void PercolationTestsException3() {
        Percolation percolation = new Percolation(10);
        assertTrue(percolation.isOpen(1, 20));
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void PercolationTestsException4() {
        Percolation percolation = new Percolation(10);
        assertTrue(percolation.isOpen(1, 11));
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void PercolationTestsException5() {
        Percolation percolation = new Percolation(10);
        assertTrue(percolation.isOpen(0, 6));
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void PercolationTestsException6() {
        Percolation percolation = new Percolation(10);
        assertTrue(percolation.isOpen(6, 0));
    }
}