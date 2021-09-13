import edu.princeton.cs.algs4.*;

public class PercolationStats {

    private Percolation perc;           // used to decide when grid percolates
    private int numberTestsPerformed;   // the number of runs performed
    private final double[] fractionOpenSites; // stores the fraction of sites needed for percolation
    // for each run.

    /**
     * Runs the percolation experiment T times on a grid of square
     * size N by N. For each run stores the fraction of sites in the
     * grid that had to be opened before the grid percolates.
     *
     * @param gridSize     the size of the grid (gridSize-by-gridSize)
     * @param numberOfRuns the number of times to run the experiment
     */
    public PercolationStats(int gridSize, int numberOfRuns) {
        fractionOpenSites = new double[numberOfRuns];

        // Run numberOfRuns experiments
        for (int i = 0; i < numberOfRuns; ++i) {
            perc = new Percolation(gridSize);
            experiment();

            numberTestsPerformed = numberTestsPerformed + 1;
            fractionOpenSites[i] = (perc.numberOpenSites / (double) (perc.size * perc.size));
        }

//        System.out.println("mean - " + mean());
//        System.out.println("stddev - " + stddev());

    }

    // Perform a single percolation experiment. Returns nothing.
    private void experiment() {

        // Keep running until percolation happens.
        while (!perc.percolates()) {
            // Pick a random site.
            int[] randomSite;
            randomSite = chooseRandomSite();

            // Open that site.
            perc.open(randomSite[0], randomSite[1]);
        }
    }

    // Pick a random site between (row, column) in {(a,b): 1<=a,b<=N}
    private int[] chooseRandomSite() {

        // Pick a random row and column.
        int[] randomSite;
        randomSite = new int[2];

        int size = perc.size;

        randomSite[0] = 1 + StdRandom.uniform(size);
        randomSite[1] = 1 + StdRandom.uniform(size);

        return randomSite;
    }

    public double mean() {
        return StdStats.mean(fractionOpenSites);
    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractionOpenSites);
    }

    /**
     * Given the mean and standard deviation, return the lower
     * part of the 95% confidence interval.
     *
     * @param mean   the mean for all the runs
     * @param stdDev the standard deviation for all runs.
     * @return the high end of the 95% confidence interval
     */
    public double confidenceHi(double mean, double stdDev) {
        return mean + ((1.96 * stdDev) / Math.sqrt(numberTestsPerformed));
    }

    /**
     * Given the mean and standard deviation, return the lower
     * part of the 95% confidence interval.
     *
     * @param mean   the mean for all the runs
     * @param stdDev the standard deviation for all runs.
     * @return the low end of the 95% confidence interval
     */
    public double confidenceLo(double mean, double stdDev) {
        return mean - ((1.96 * stdDev) / Math.sqrt(numberTestsPerformed));
    }

    public static void main(String[] args) {
//        int gridSize = Integer.parseInt(args[0]);
//        int numberOfTests = Integer.parseInt(args[1]);
        System.out.print("GridSize - ");
        int gridSize = StdIn.readInt();
        System.out.print("numberOfTests - ");
        int numberOfTests = StdIn.readInt();

        PercolationStats perStat = new PercolationStats(gridSize, numberOfTests);

        double mean = perStat.mean();
        double stdDev = perStat.stddev();
        double confHi = perStat.confidenceHi(mean, stdDev);
        double confLo = perStat.confidenceLo(mean, stdDev);

        System.out.printf("%-24s= %f%n", "mean", mean);
        System.out.printf("%-24s= %f%n", "stddev", stdDev);
        System.out.printf("%-24s= %f, %f%n", "95% confidence interval", confLo, confHi);
    }
}