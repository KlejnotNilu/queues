package princeton.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] Queue; // queue implemented on array due to randomized iterator
    private int N; // size of array, not really necessary

    public RandomizedQueue() {
        Queue = (Item[]) new Object[1]; // generic array
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private boolean[] ifReturned;

        {
            ifReturned = new boolean[N];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return checkArray();
        }

        public Item next() {
            if (hasNext() == false)
                throw new UnsupportedOperationException();
            int index;
            do {
                index = StdRandom.uniform(ifReturned.length);
                // System.out.println(index);
                if (ifReturned[index] == false) { // random search for item to return
                    ifReturned[index] = true;
                    break;
                }
            } while (true);
            return Queue[index];
        }

        private boolean checkArray() { // check if there are items to return in iterator. one item in array can be returned only once
            for (boolean c : ifReturned) {
                if (c == false) {
                    return true;
                }
            }
            return false;
        }

    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (N == Queue.length) // resize array when it is full
            resize(2 * Queue.length);
        Queue[N++] = item;
    }

    public Item dequeue() {
        if (N == 0)
            throw new NoSuchElementException();
        Item toReturn = Queue[--N];
        Queue[N] = null;
        if (N > 0 && N == Queue.length / 4) //reduce array when items take 25% of array's size
            resize(Queue.length / 2);
        return toReturn;
    }

    public Item sample() {
        return Queue[StdRandom.uniform(N)]; // return random item from array
    }

    private void resize(int length) {
        Item[] copy = (Item[]) new Object[length];
        for (int i = 0; i < N; i++) {
            copy[i] = Queue[i];
        }
        Queue = copy;
    }

}
