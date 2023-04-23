package hashmap;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Jing Xie
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets ;
    // You should probably define some more!
    private static final Double INT_FACTOR = 0.75; /** default value*/
    private static final int INT_SIZE = 16; /** default value*/
    public int arraySize = INT_SIZE; /** Number of buckets(bins) currently has */
    public Double loadFactor;
    private int size;  /** Number of items currently stored */
    /** Constructors */

    public MyHashMap() {
        this(INT_SIZE, INT_FACTOR);
    }
    public MyHashMap(int initialSize) {
        this(initialSize, INT_FACTOR);
    }
    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.loadFactor = maxLoad;
        buckets = (Collection<Node>[]) new Collection[initialSize];
        for (int i=0; i<arraySize; i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    private void resize(int arraySize) {
        MyHashMap<K,V> temp = new MyHashMap<>(arraySize);
        Set<K> set = keySet();
        for (K key:set) {
            temp.put(key, this.get(key));
        }
        this.arraySize  = temp.arraySize;
        this.size  = temp.size;
        this.buckets = temp.buckets;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    /** Removes all of the mappings from this map. */
    public void clear(){
        size = 0;
        for (int i=0; i < this.arraySize; i++){
            buckets[i] = null;
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        int i = hash(key);
        return get(key) != null;
    }
    /** Return a value in the range 0 .. buckets.size ()-1, based on
     * the hash code of KEY. */
    private int hash (Object key) {
        return (key == null) ? 0: (0x7fffffff & key.hashCode ()) % this.arraySize;
    }
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        Node node = find(key);
        if (node != null){return node.value;}
        return null;
    }
    public Node find(K key){
        int i = hash(key);
        Collection<Node> bucket = buckets[i];
        if(bucket==null){return null;}
        for (Node w: bucket){
            if (w.key.equals(key)){
                return w;
            }
        } return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value){
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        // double table size if loadFactor > set max
        int i = hash(key);
        Collection<Node> bucket = buckets[i];
        if(!containsKey(key)) {
            bucket.add(createNode(key, value));
            size += 1;
            return;
        }
        for (Node w:bucket){
            if (w.key == key){w.value=value; return;}
        }
        if (size/arraySize > loadFactor) resize(2*arraySize);
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        Set<K> set = new HashSet<>();
        for (int i=0; i<arraySize; i++){
            for (Node w: buckets[i]){
                set.add(w.key);
            }
        } return set;
    }

    public Iterator iterator(){
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<Node> {
        private int index;
        private Iterator<Node> nodeIterator;

        public MyHashMapIterator() {
            index = 0;
            nodeIterator = null;
        }

        public boolean hasNext() {
            if (nodeIterator != null && nodeIterator.hasNext()) {
                return true;
            }
            while (index < buckets.length && (nodeIterator == null || !nodeIterator.hasNext())) {
                Collection<Node> bucket = buckets[index];
                if (bucket != null) {
                    nodeIterator = bucket.iterator();
                }
                index++;
            }
            return nodeIterator != null && nodeIterator.hasNext();
        }

        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node node = nodeIterator.next();
            return new Node(node.key, node.value);
        }
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
        throw new UnsupportedOperationException("It's a unsupported operation!");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
        throw new UnsupportedOperationException("It's a unsupported operation!");
    }
}
