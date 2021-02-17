/*
 * Name: Diane Li
 * PID:  A15773774
 */

import java.util.*;

/**
 * Binary search tree implementation: In each node, there is a key to define the ordering of the
 * node and a linked list to store the relevant information corresponding to the key
 * 
 * @author Diane Li
 * @since  02/16/2021
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    /**
     * Node implementation: creates nodes for BSTs that hold a key, dataList, left Node, right Node
     *
     * @author Diane Li
     * @since  02/16/2021
     */
    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.key = key;
            this.dataList = dataList;
            this.left = left;
            this.right = right;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this(left, right, new LinkedList<>(), key);
        }

        /**
         * Return the key
         * @return The key
         */
        public T getKey() { return this.key; }

        /**
         * Return the left child of the node
         * @return The left child of the node
         */
        public BSTNode getLeft() { return this.left; }

        /**
         * Return the right child of the node
         * @return The right child of the node
         */
        public BSTNode getRight() { return this.right; }

        /**
         * Return the linked list of the node
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() { return this.dataList; }

        /**
         * Setter for left child of the node
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) { this.left = newleft; }

        /**
         * Setter for right child of the node
         * @param newright New right child
         */
        public void setright(BSTNode newright) { this.right = newright; }

        /**
         * Setter for the linked list of the node
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) { this.dataList = newData; }

        /**
         * Append new data to the end of the existing linked list of the node
         * @param data New data to be appended
         */
        public void addNewInfo(T data) { this.dataList.add(data); }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) { return this.dataList.remove(data); }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() { return this.root; }

    /**
     * Return the BST size
     * @return The BST size
     */
    public int getSize() { return this.nelems; }

    /**
     * Insert a key into BST
     * @param key
     * @return true if insertion is successful and false otherwise
     * @throws NullPointerException if key is null
     */
    public boolean insert(T key) {
        if (key == null) { throw new NullPointerException(); }

        // inserts node at root if tree is empty. otherwise, recurses to find position to insert
        if (this.root == null) {
            this.root = new BSTNode(null, null, key);
            this.nelems++;
            return true;
        } else {
            if (insertHelper(this.root, key)) {
                this.nelems++;
                return true;
            } else { return false; }
        }
    }

    /**
     * Helper method to insert a key into BST recursively
     * @param curr current node being checked for duplicate or null child
     * @param toInsert key to insert at appropriate position if it is not a duplicate
     * @return true if insertion is successful and false otherwise
     */
    private boolean insertHelper(BSTNode curr, T toInsert) {
        int compared = toInsert.compareTo(curr.getKey());
        // if key is duplicate, inserting is unsuccessful
        if (compared == 0) { return false; }
        // otherwise, searches appropriate side for internal or leaf node to insert new node
        else if (compared < 0) {
            if (curr.getLeft() == null) {
                curr.setleft(new BSTNode(null, null, toInsert));
            } else {
                insertHelper(curr.getLeft(), toInsert);
            }
        } else {
            if (curr.getRight() == null) {
                curr.setright(new BSTNode(null, null, toInsert));
            } else {
                insertHelper(curr.getRight(), toInsert);
            }
        }
        return true;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) { throw new NullPointerException(); }
        else { return findHelper(this.root, key); }
    }

    /**
     * Helper method to find a key in a BST recursively
     * @param curr current node being checked for given key, duplicate,or null child
     * @param toFind key to search for
     * @return true if key is found and false otherwise
     */
    private boolean findHelper(BSTNode curr, T toFind) {
        // if the root is null or a bottom layer null node is reach, the find was unsuccesstul
        if (curr == null) { return false; }

        // otherwise, compare key value and recurse on appropriate side of sub-tree
        int compared = toFind.compareTo(curr.getKey());
        if (compared == 0) {
            return true;
        } else if (compared < 0) {
            return findHelper(curr.getLeft(), toFind);
        } else {
            return findHelper(curr.getRight(), toFind);
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) { throw new NullPointerException(); }

        BSTNode toInsert = findNode(this.root, key);
        if (toInsert == null) { throw new IllegalArgumentException(); }
        else { toInsert.addNewInfo(data); }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) { throw new NullPointerException(); }

        BSTNode toInsert = findNode(this.root, key);
        if (toInsert == null) { throw new IllegalArgumentException(); }
        else { return toInsert.getDataList(); }
    }

    /**
     * Helper method to find a node in a BST recursively
     * @param curr current node being checked for given key
     * @param toFind key to search for in BST
     * @return BSTNode with the given key or null if not found
     */
    private BSTNode findNode(BSTNode curr, T toFind) {
        // if the root is null or a bottom layer null node is reach, the find was unsuccesstul
        if (curr == null) { return curr; }

        // otherwise, compare key value and recurse on appropriate side of sub-tree
        int compared = toFind.compareTo(curr.getKey());
        if (compared == 0) {
            return curr;
        } else if (compared < 0) {
            return findNode(curr.getLeft(), toFind);
        } else {
            return findNode(curr.getRight(), toFind);
        }
    }

    /**
     * Return the height of the tree
     * @return The height of the tree, -1 if BST is empty and 0 if the BST has only one node
     */
    public int findHeight() { return findHeightHelper(this.root); }

    /**
     * Helper for the findHeight method
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        // recursively computes left & right child subtree heights, returns 1 + (greater of the 2)
        if (root == null) { return -1; }
        else {
            int leftHeight = findHeightHelper(root.getLeft());
            int rightHeight = findHeightHelper(root.getRight());
            if (leftHeight >= rightHeight) { return 1 + leftHeight; }
            else { return 1 + rightHeight; }
        }
    }

    /* * * * * BST Iterator * * * * */

    /**
     * BST Iterator implementation: uses a Stack of BSTNode to track path from root to current node
     *
     * @author Diane Li
     * @since  02/16/2021
     */
    public class BSTree_Iterator implements Iterator<T> {

        /* * * * * BSTree_Iterator Instance Variables * * * * */
        private Stack<BSTNode> stack;

        /**
         * A constructor that initializes the Stack with the leftPath of the root
         */
        public BSTree_Iterator() {
            this.stack = new Stack();
            addLeftPath(getRoot());
        }

        /**
         * A method that returns whether the Stack still has elements
         * @return True if there are more nodes left to iterate, false otherwise
         */
        public boolean hasNext() { return !this.stack.isEmpty(); }

        /**
         * A method that returns the next item in the BST
         * @return T the next item in the BST ordering
         * @throws NoSuchElementException if there is no next element
         */
        public T next() {
            BSTNode popped;
            if (!hasNext()) { throw new NoSuchElementException(); }
            else {
                popped = this.stack.pop();
                addLeftPath(popped.getRight());
            }
            return popped.getKey();
        }

        /**
         * Helper method that pushes the left side of the given node into the stack
         * @param curr node to start pushing from
         */
        private void addLeftPath(BSTNode curr) {
            while (curr != null) {
                this.stack.push(curr);
                curr = curr.getLeft();
            }
        }
    }

    /**
     * An iterator that allows iteration over BST through calling this method
     * @return The iterative in-order traversal iterator
     */
    public Iterator<T> iterator() { return new BSTree_Iterator(); }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public int levelCount(int level) {
        /* TODO */
        return -1;
    }
}
