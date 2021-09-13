import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue <Item> implements Iterable<Item> {

    private Item[] arrItem;
    private int elementCount;
    private int indexToInsert;

    // construct an empty randomized queue
    public RandomizedQueue(){
        arrItem = (Item[]) new Object[1];
        elementCount = 0;
        indexToInsert = 0;
    }

    // is the queue empty?
    public boolean isEmpty(){
        return elementCount == 0;
    }

    // return the number of items on the queue
    public int size(){
        return elementCount;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (arrItem.length == indexToInsert) {
            resize(2*elementCount);
        }
        arrItem[indexToInsert++] = item;
        elementCount++;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        int k = 0;
        for (int i = 0; i < arrItem.length; i++) {
            if (arrItem[i] != null) {
                temp[k++] = arrItem[i];
            }
        }
        arrItem = temp;

        // alternative implementation
        // a = java.util.Arrays.copyOf(a, capacity);
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = null;
        int itemIndex;
        do {
            itemIndex = StdRandom.uniform(indexToInsert);
            item = arrItem[itemIndex];
        } while (item == null);
        arrItem[itemIndex] = null;
        elementCount--;
        if (elementCount == arrItem.length/4){
            resize(arrItem.length/2);
            indexToInsert = elementCount;
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = null;
        do {
            item = arrItem[StdRandom.uniform(indexToInsert)];
        } while (item == null);
        return item;
    }

    @Override
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private Item[] randomizedElements;
        private int currentIndex;

        public RandomizedQueueIterator() {
            randomizedElements = (Item[]) new Object[elementCount];
            int k = 0;
            for (int i = 0; i < arrItem.length; i++) {
                if (arrItem[i] != null){
                    randomizedElements[k++] = (Item) arrItem[i];
                }
            }
            StdRandom.shuffle(randomizedElements);
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex != randomizedElements.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return randomizedElements[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("aaa");
        randomizedQueue.enqueue("bbb");
        randomizedQueue.enqueue("ccc");
        randomizedQueue.enqueue("dddd");
        randomizedQueue.enqueue("eeee");
        randomizedQueue.enqueue("ffff");
        for (String item : randomizedQueue) {
            StdOut.println(item);
        }
        StdOut.println("Samples");
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());
        StdOut.println("Removing");
        StdOut.println("Removed " + randomizedQueue.dequeue());
        StdOut.println("Removed " + randomizedQueue.dequeue());
        StdOut.println("Removed " + randomizedQueue.dequeue());
        StdOut.println("Removed " + randomizedQueue.dequeue());

        for (String item : randomizedQueue) {
            StdOut.println(item);
        }

        StdOut.println("Removed " + randomizedQueue.dequeue());
        StdOut.println("Removed " + randomizedQueue.dequeue());

        randomizedQueue.enqueue("xxx");
        randomizedQueue.enqueue("yyy");

        for (String item : randomizedQueue) {
            StdOut.println(item);
        }

        StdOut.println("Removed " + randomizedQueue.dequeue());
        StdOut.println("Removed " + randomizedQueue.dequeue());

    }
}