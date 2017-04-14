package princeton.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue; // queue implemented on array due to randomized iterator
    private int n; /// / size of array, not really necessary

    public RandomizedQueue() {
        queue = (Item[]) new Object[1]; // generic array
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private boolean[] ifReturned;

        {
            ifReturned = new boolean[n];
        }

        public void remove() {

            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {

            return checkArray();
        }

        public Item next() {

            if (hasNext() == false)
                throw new NoSuchElementException();
            int index;

            do {
                index = StdRandom.uniform(ifReturned.length);

                if (!ifReturned[index]) { // random search for item to return
                    ifReturned[index] = true;
                    break;
                }

            } while (true);

            return queue[index];
        }

        private boolean checkArray() { // check if there are items to return in iterator. one item in array can be returned only once

            for (boolean c : ifReturned) {
                if (!c) {
                    return true;
                }
            }
            return false;
        }

    }

    public boolean isEmpty() {

        return n == 0;
    }

    public int size() {

        return n;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (n == queue.length) { // resize array when it is full
            resize(2 * queue.length);
        }
        queue[n++] = item;
    }

    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(n);

        Item toReturn = queue[index];

        queue[index] = queue[n - 1];

        queue[n - 1] = null;

        n--;

        if (n > 0 && n == queue.length / 4) //reduce array when items take 25% of array's size
            resize(queue.length / 2);
        return toReturn;
    }

    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        return queue[StdRandom.uniform(n)]; // return random item from array
    }

    private void resize(int length) {
        Item[] copy = (Item[]) new Object[length];
        System.arraycopy(queue, 0, copy, 0, n);
        queue = copy;
    }

}
