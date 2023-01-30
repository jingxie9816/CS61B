package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int resizeFactor = 2;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length-1;
        nextLast = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    private int findNextFirst(int index){
        if (size < items.length && index > 0){
            return index - 1;
        } else if (index == 0) {
            return items.length-1;
        }
        return index;
    }

    private int findNextLast(int index){
        if (size < items.length && index < items.length-1) {
            return index + 1;
        } else if (index == items.length -1){
            return 0;
        } return index;
    }

    /** Inserts item into the back of the list. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * resizeFactor);
            nextLast = size;
            nextFirst = size * resizeFactor - 1;
        }

        items[nextLast] = item;
        size += 1;
        nextLast = findNextLast(nextLast);
    }

    /** Inserts item into the front of the list. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * resizeFactor);
            nextLast = size;
            nextFirst = size * resizeFactor - 1;
        }

        items[nextFirst] = item;
        size += 1;
        nextFirst = findNextFirst(nextFirst);
    }


    /** isEmpty: Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /** Returns the number of items in the list. */
    public int size() {return size;}


    /** printDeque: Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        int startIndex;
        if (nextFirst == items.length -1){
            startIndex = 0;
        } else {startIndex = nextFirst+1;}
        for (int i = 0; i < size; i++) {
            System.out.print(items[startIndex] + " ");
            if (startIndex< items.length-1) {
                startIndex += 1;
            } else {startIndex = 0;}
        }
        System.out.println();
    }


    /** Gets the ith item in the list (0 is the front). */
    public T get(int index) {
        return items[index];
    }



    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if (size > 0){
            int removeIndex;
            if (nextLast == 0) {
                removeIndex =items.length-1;
            } else {removeIndex = nextLast-1;}
            T x = items[removeIndex];
            items[removeIndex] = null;
            size = size - 1;
            nextLast = removeIndex;
            return x;
        } else {return null;}
    }

    public T removeFirst() {
        if (size>0) {
            int removeIndex;
            if (nextFirst == items.length-1){
                removeIndex = 0;
            } else {removeIndex = nextFirst + 1;}
            T x = items[removeIndex];
            items[removeIndex] = null;
            size = size - 1;
            nextFirst = removeIndex;
            return x;
        } else {return null;}
    }

    /** Iterator: The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator.*/
    //public Iterator<T> iterator(){
    //}


    /** equals: Returns whether or not the parameter o is equal to the Deque
     * o is considered equal if it is a Deque and if it contains the same contents
     * (as goverened by the generic T’s equals method) in the same order.*/
    public boolean equals(Object o){
        if (!(o instanceof ArrayDeque)){
            return false;
        } else if(((ArrayDeque<?>) o).size != this.size) {
            return false;
        } else {
            for (int i = 0; i < items.length; i ++){
                if (((ArrayDeque<?>) o).get(i) == this.get(i)){
                    continue;
                } else {return false;}
            } return true;
        }
    }
}

