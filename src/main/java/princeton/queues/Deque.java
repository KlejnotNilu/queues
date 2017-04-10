package princeton.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;

    private Node last;

    private int size;

    private class Node {
        Item item;
        Node next;
        Node back;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {

        if (item == null)
            throw new NullPointerException();

        Node previousFirst = first;

        first = new Node();

        first.item = item;

        first.next = previousFirst;

        if (previousFirst != null)
            previousFirst.back = first;
        else
            last = first;

        size++;
    }

    public void addLast(Item item) {

        if (item == null)
            throw new NullPointerException();

        Node previousLast = last;

        last = new Node();

        last.item = item;

        last.back = previousLast;

        if (previousLast != null)
            previousLast.next = last;
        else
            first = last;

        size++;
    }

    public Item removeFirst() {

        if (this.size == 0)
            throw new NoSuchElementException();

        Item toReturn = first.item;

        first = first.next;

        if (first != null)
            first.back = null;
        else
            last = first; //Remove the only one element in array. must assign last variable to null after removing.

        size--;

        return toReturn;
    }

    public Item removeLast() {

        if (this.size == 0)
            throw new NoSuchElementException();

        Item toReturn = last.item;

        // Possible a run-time error

        last = last.back;

        if (last != null)
            last.next = null;
        else
            first = last;

        size--;

        return toReturn;
    }

    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {

            if (this.hasNext() == false) {
                throw new NoSuchElementException();
            }

            Item item = current.item;

            current = current.next;

            return item;
        }

    }

}