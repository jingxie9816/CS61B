package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V > {
    private BSTNode root;
    private class BSTNode {
        private K key;          // sorted by key
        private V val;         // associated data
        private BSTNode left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public BSTNode(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public BSTMap() {
    }

    @Override
    public void clear() {
        root.size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key)!=null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }
    /** helper get function **/
    public V get(BSTNode t, K key) {
        if (key == null) {
            return null;
        }
        int cmp = key.compareTo(t.key);
        if (cmp<0) return get(t.left, key);
        else if (cmp>0) return get(t.right, key);
        else return t.val;
    }

    @Override
    public int size() {
        return root.size;
    }
    // return number of key-value pairs in BST rooted at x
    private int size(BSTNode x) {
        if (x == null) return 0;
        else return x.size;
    }

    @Override
    public void put(K key, V value) {
       root = put(root, key, value);}


    /** helper put function **/
    private BSTNode put(BSTNode t, K key, V value){
        if (t == null)
            return new BSTNode(key, value, 1);
        int cmp = key.compareTo(t.key);
        if(cmp < 0)
            t.left = put(t.left, key, value);
        else if (cmp>0)
            t.right = put(t.right, key, value);
        else t.val = value;
        t.size = 1 + size(t.left) + size(t.right);
        return t;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("It's a unsupported operation!");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("It's a unsupported operation!");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("It's a unsupported operation!");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("It's a unsupported operation!");
    }
}