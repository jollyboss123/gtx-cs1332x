package gtxcs1332x.module5;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }

        root = rAdd(root, data);
    }

    private BSTNode<T> rAdd(BSTNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode<>(data);
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        return curr;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = rRemove(root, data, dummy);
        return dummy.getData();
    }

    private BSTNode<T> rRemove(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException();
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rRemove(curr.getRight(), data, dummy));
        } else {
            // data found
            dummy.setData(curr.getData());
            size--;
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getRight() != null && curr.getLeft() == null) {
                return curr.getRight();
            } else {
                BSTNode<T> dummy2 = new BSTNode<>(null);
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        return curr;
    }

    private BSTNode<T> removeSuccessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
            return curr;
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        // Test case 1: Adding elements to an empty tree
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        bst.add(6);
        bst.add(8);
        if (!bst.getRoot().getData().equals(5)) {
            System.out.println(bst.getRoot().getData());
            throw new RuntimeException();
        }
        if (bst.size() != 7) {
            System.out.println(bst.size());
            throw new RuntimeException();
        }
        Traversals<Integer> traversals = new Traversals<>();
        List<Integer> preorderResult = traversals.preorder(bst.getRoot());
        if (!preorderResult.equals(List.of(5, 3, 2, 4, 7, 6, 8))) {
            System.out.println("preorder traversal: " + preorderResult);
            throw new RuntimeException();
        }
        List<Integer> inorderResult = traversals.inorder(bst.getRoot());
        if (!inorderResult.equals(List.of(2, 3, 4, 5, 6, 7, 8))) {
            System.out.println("inorder traversal: " + inorderResult);
            throw new RuntimeException();
        }

        // Test case 2: Removing a leaf node (8)
        var r = bst.remove(8);
        if (!r.equals(8)) {
            System.out.println(r);
            throw new RuntimeException();
        }
        if (bst.size() != 6) {
            throw new RuntimeException();
        }
        preorderResult = traversals.preorder(bst.getRoot());
        if (!preorderResult.equals(List.of(5, 3, 2, 4, 7, 6))) {
            System.out.println("preorder traversal: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(bst.getRoot());
        if (!inorderResult.equals(List.of(2, 3, 4, 5, 6, 7))) {
            System.out.println("inorder traversal: " + inorderResult);
            throw new RuntimeException();
        }

        // Test case 3: Removing a node with one child (3)
        r = bst.remove(3);
        if (!r.equals(3)) {
            System.out.println(r);
            throw new RuntimeException();
        }
        if (bst.size() != 5) {
            throw new RuntimeException();
        }
        preorderResult = traversals.preorder(bst.getRoot());
        if (!preorderResult.equals(List.of(5, 4, 2, 7, 6))) {
            System.out.println("preorder traversal: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(bst.getRoot());
        if (!inorderResult.equals(List.of(2, 4, 5, 6, 7))) {
            System.out.println("inorder traversal: " + inorderResult);
            throw new RuntimeException();
        }

        // Test case 4: Removing a node with two children (5)
        r = bst.remove(5);
        if (!r.equals(5)) {
            System.out.println(r);
            throw new RuntimeException();
        }
        if (bst.size() != 4) {
            throw new RuntimeException();
        }
        preorderResult = traversals.preorder(bst.getRoot());
        if (!preorderResult.equals(List.of(6, 4, 2, 7))) {
            System.out.println("preorder traversal: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(bst.getRoot());
        if (!inorderResult.equals(List.of(2, 4, 6, 7))) {
            System.out.println("inorder traversal: " + inorderResult);
            throw new RuntimeException();
        }
    }
}
