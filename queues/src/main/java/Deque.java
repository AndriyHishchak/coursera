import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implements a double ended queue an array.
 *
 * @param <Item> A place holder the clients type or object being stored.
 * @author Andriy Hishchak
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int numOfnodes;

    public Deque() {                              // construct an empty deque
        first = null;
        last = null;
        numOfnodes = 0;
    }

    private static class Node<Item> {
        Item item = null;
        Node<Item> next = null;
        Node<Item> previous = null;
    }

    public boolean isEmpty() {                    // is the deque empty?
        return numOfnodes == 0;
    }

    public int size() {                            // return the number of items on the deque
        return numOfnodes;
    }

    public void addFirst(Item item) {              // add the item to the front
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (numOfnodes == 0) {
            Node<Item> tempNode = new Node<>();
            tempNode.item = item;
            first = tempNode;
            last = tempNode;
            numOfnodes++;
            return;
        }

        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        oldFirst.previous = first;
        numOfnodes++;

    }

    public void addLast(Item item) {               // add the item to the end
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (numOfnodes == 0) {
            Node<Item> tempNode = new Node<>();
            tempNode.item = item;
            first = tempNode;
            last = tempNode;
            numOfnodes++;
            return;
        }

        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        oldLast.next = last;
        last.previous = oldLast;
        numOfnodes++;
    }

    public Item removeFirst() {                    // remove and return the item from the front
        if (numOfnodes == 0) {
            throw new NoSuchElementException();
        } else {
            Item value = first.item;
            if (numOfnodes == 1) {
                first = null;
                last = null;
                numOfnodes--;
                return value;
            } else {
                first = first.next;
                first.previous = null;
                numOfnodes--;
                return value;
            }
        }
    }

    public Item removeLast() {                      // remove and return the item from the end
        if (numOfnodes == 0) {
            throw new NoSuchElementException();
        } else {
            Item value = last.item;
            if (numOfnodes == 1) {
                first = null;
                last = null;
                numOfnodes--;
                return value;
            } else {
                last = last.previous;
                last.next = null;
                numOfnodes--;
                return value;
            }

        }

    }

    public Iterator<Item> iterator() {              // return an iterator over items in order from front to end
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Deque.Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {        // unit testing (optional)
        Deque<Integer> deque = new Deque<Integer>();

        System.out.println("initial no of nodes: " + deque.numOfnodes);       //  ==> true

        deque.addLast(1);
        System.out.println("After addlast(1)--first item: " + deque.first.item + " last item: " + deque.last.item);
        System.out.println("nodes: " + deque.numOfnodes);

        deque.addLast(2);
        System.out.println("After addlast(2)--first item: " + deque.first.item + " last item: " + deque.last.item);
        System.out.println("nodes: " + deque.numOfnodes);

        System.out.println(deque.removeFirst());
        System.out.println("After removeFirst()--first item: " + deque.first.item + " last item: " + deque.last.item);
        System.out.println("nodes: " + deque.numOfnodes);

        System.out.println(deque.removeLast());
        System.out.println("nodes: " + deque.numOfnodes);
    }
}