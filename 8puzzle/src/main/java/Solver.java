import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private SearchNode lastNode;
    private final boolean solvable;
    private final int minMoves;

    public static void main(String[] args) {

        Iterable<Board> solution;
        int [] numbers = { 0, 1, 3, 4, 2, 5, 7, 8, 6 };
        int[][] blocks = new int[3][3];
        int[][] block2 = new int [2][2];
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blocks[i][j] = numbers[idx++];
            }
        }

        int [] unsolvable2 = { 1, 0, 2, 3 };
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                block2[i][j] = unsolvable2[k++];
            }
        }

        Board board = new Board(blocks);

        Solver solver = new Solver(board);
        System.out.println("Is solvable? " + solver.isSolvable());
        solution = solver.solution();
        System.out.println("Minimum number of moves = " + solver.moves());
        for (Board b : solution)
            System.out.println(b);

    }
    public Solver(Board initial) {
        if (initial == null)
            throw new java.lang.IllegalArgumentException();
        int moves = 0;
        int twinMoves = 0;

        Queue<Board> neighbors = new Queue<>();
        Queue<Board> twinNeighbors = new Queue<>();

        MinPQ<SearchNode> searchNodes = new MinPQ<>();
        MinPQ<SearchNode> twinNodes = new MinPQ<>();

        SearchNode searchNode = new SearchNode(initial, moves, null);
        SearchNode twinSearchNode = new SearchNode(initial.twin(), twinMoves, null);
        twinNodes.insert(twinSearchNode);
        searchNodes.insert(searchNode);

        boolean solved = false;
        boolean twinSolved = false;
        //System.out.println("Get Board: " + searchNodes.delMin().getBoard());
        SearchNode current;

        while (!solved && !twinSolved) {
            current = searchNodes.delMin();
            SearchNode predecessor = current.getPredecessor();
            Board temp = current.getBoard();
            solved = temp.isGoal();

            SearchNode twinCurrent = twinNodes.delMin();
            SearchNode twinPredecessor = twinCurrent.getPredecessor();
            Board twinTemp = twinCurrent.getBoard();
            twinSolved = twinTemp.isGoal();

            for (Board b : temp.neighbors())
                neighbors.enqueue(b);

            for (Board b : twinTemp.neighbors())
                twinNeighbors.enqueue(b);

            while(neighbors.size() > 0) {
                Board board = neighbors.dequeue();
                int move = current.getMoves();
                move++;
                if (predecessor != null && predecessor.getBoard().equals(board))
                    continue;

                SearchNode neighborNode = new SearchNode(board, move, current);
                //System.out.println("Priorities " + neighborNode.getPriority());
                searchNodes.insert(neighborNode);
            }

            while(twinNeighbors.size() > 0) {
                Board board = twinNeighbors.dequeue();
                int twinMove = current.getMoves();
                twinMove++;
                if (twinPredecessor != null && twinPredecessor.getBoard().equals(board))
                    continue;

                SearchNode neighborNode = new SearchNode(board, twinMove, twinCurrent);
                twinNodes.insert(neighborNode);
            }

            moves = current.getMoves() + 1;
            lastNode = current;
        }

        solvable = !twinSolved;
        minMoves = moves - 1;
    }

    public boolean isSolvable() {
        return solvable;

    }

    public int moves() {
        if (!isSolvable())
            return -1;
        return minMoves;

    }

    public Iterable<Board> solution() {
        Stack<Board> boards = new Stack<>();
        SearchNode lastNode = this.lastNode;
        if (this.isSolvable()) {
            while (lastNode.getPredecessor() != null) {
                boards.push(lastNode.getBoard());
                lastNode = lastNode.getPredecessor();
            }
            boards.push(lastNode.getBoard());
            return boards;
        }
        return null;
    }

    private static class SearchNode implements Comparable<SearchNode> {

        private final SearchNode predecessor;
        private final Board current;
        private final int moves;
        private final int priority;

        public SearchNode(Board initial, int m, SearchNode pred) {
            predecessor = pred;
            moves = m;
            current = initial;

            priority = m + initial.manhattan();
        }

        public Board getBoard() {
            return current;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPredecessor() {
            return predecessor;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }
    }
}