package deque;

public class LinkedListDeque<T> implements Deque<T> {
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
        if (size == 0){
            StuffNode s = new StuffNode(item, sentinel, sentinel);
            sentinel.next = s;
            sentinel.prev = s;
            size = size + 1;
        } else {
            sentinel.next = new StuffNode(item, sentinel.next, sentinel);
            size = size + 1;
        }
    }
    /** addLast: Adds an item of type T to the back of the deque,
     * assuming that item is never null.*/
    public void addLast(T item){
        if (size == 0) {
            StuffNode s = new StuffNode(item, sentinel, sentinel);
            sentinel.next = s;
            sentinel.prev = s;
            size = size + 1;
        } else {
            StuffNode s = new StuffNode(item, sentinel, sentinel.prev);
            sentinel.prev.next = s;
            sentinel.prev = s;
            size = size + 1;
        }
    }


    /** size: return the number of items in the deque*/
    public int size() {
        return size;
    }

    /** printDeque: Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        StuffNode p = sentinel;
        while(p.next != sentinel){
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** removeFirst: Removes and returns the item at the front of the deque.
    If no such item exists, returns null.*/
    public T removeFirst(){
        if(size > 0) {
            T first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            size = size - 1;
            return first;
        } else {return null;}

    }

    /** removeLast: Removes and returns the item at the back of the deque.
     If no such item exists, returns null.*/
    public T removeLast() {
        if (size > 0) {
            T last = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            size = size - 1;
            return last;
        } else {return null;}
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
    /** get uses recursion */
    public T getRecursive(int index){
        if (index < 0 || index > size()) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
        }

    private T getRecursiveHelper(int index, StuffNode node){
        if (index == 0){
            return node.item;
        } else{
            return getRecursiveHelper(index-1, node.next);
        }
    }

    /** Iterator: The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator.*/
    //public Iterator<T> iterator(){
    //}


    /** equals: Returns whether or not the parameter o is equal to the Deque
     * o is considered equal if it is a Deque and if it contains the same contents
     * (as goverened by the generic T’s equals method) in the same order.*/
    public boolean equals(Object o){
        if (!(o instanceof LinkedListDeque)){
            return false;
        } else if(((LinkedListDeque<?>) o).size != this.size) {
            return false;
        } else {
            StuffNode p = this.sentinel;
            StuffNode op = (StuffNode) ((LinkedListDeque<?>) o).sentinel;
            for (int i=0; i< this.size; i+=1){
                if (op.next.item.equals(p.next.item)){
                    p = p.next;
                    op = op.next;
                } else {return false;}
            } return true;
        }
    }


}
