package deque;

public class LinkedListDeque<T> {
    private class StuffNode {
        public T item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(T i, StuffNode n, StuffNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private StuffNode sentinel;
    private int size;

    /** Creates an empty deque.LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        size = 0;
    }
    public LinkedListDeque(T x) {
        sentinel = new StuffNode(null, null, null);
        StuffNode node = new StuffNode(x, null, null);
        sentinel.next = node;
        sentinel.prev = node;
        size = 1;
    }

    /** addFirst: Adds an item of type T to the front of the deque,
     * assuming that item is never null.*/
    public void addFirst(T item) {
        sentinel.next = new StuffNode(item, sentinel.next, sentinel);
        size = size + 1;
    }
    /** addLast: Adds an item of type T to the back of the deque,
     * assuming that item is never null.*/
    public void addLast(T item){
        sentinel.prev = new StuffNode(item, sentinel, sentinel.prev);
        size = size + 1;
    }

    /** isEmpty: Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty(){
        if (size == 0) {
            return true;
        } return false;
    }

    /** size: return the number of items in the deque*/
    public int size() {
        return size;
    }

    /** printDeque: Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        StuffNode p = sentinel;
        while(p.next != null){
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** removeFirst: Removes and returns the item at the front of the deque.
    If no such item exists, returns null.*/
    public T removeFirst(){
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size = size - 1;
        return first;
    }

    /** removeLast: Removes and returns the item at the back of the deque.
     If no such item exists, returns null.*/
    public T removeLast() {
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size = size - 1;
        return last;
    }

    /** get(int index): Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists, returns null.
     * Must not alter the deque!*/
    public T get(int index){
        int i;
        StuffNode p = sentinel;
        for (i = 0; i < size; i = i +1){
            if (index == i) {
                T result = p.next.item;
                return result;
            } p = p.next;
        }
        return null;
    }

    /** Iterator: The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator.*/
    //public Iterator<T> iterator(){
    //}


    /** equals: Returns whether or not the parameter o is equal to the Deque
     * o is considered equal if it is a Deque and if it contains the same contents
     * (as goverened by the generic T’s equals method) in the same order.*/
    public boolean equals(Object o){
        if (o instanceof LinkedListDeque){

        }
    }

}
