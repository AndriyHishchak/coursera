import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rq.enqueue(item);
        }
        int x = Integer.parseInt(args[0]);
        for (int i = 0; i < x; i++) {
            StdOut.printf("%s\n", rq.dequeue());
        }
    }
}
