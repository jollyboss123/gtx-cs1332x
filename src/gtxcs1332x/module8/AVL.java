package gtxcs1332x.module8;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of the AVL tree rotations.
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        root = rAdd(root, data);
    }

    private AVLNode<T> rAdd(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new AVLNode<>(data);
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        return balance(curr);
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     *    replace the data, NOT predecessor. As a reminder, rotations can occur
     *    after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        AVLNode<T> dummy = new AVLNode<>(null);
        root = rRemove(root, data, dummy);
        return dummy.getData();
    }

    private AVLNode<T> rRemove(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException();
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rRemove(curr.getRight(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            size--;
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            }
            if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            }
            if (curr.getRight() != null && curr.getLeft() == null) {
                return curr.getRight();
            }
            AVLNode<T> dummy2 = new AVLNode<>(null);
            curr.setRight(removeSuccessor(curr.getRight(), dummy2));
            curr.setData(dummy2.getData());
        }
        return balance(curr);
    }

    private AVLNode<T> removeSuccessor(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            if (curr.getRight() != null) {
                return balance(curr.getRight());
            }
            return curr.getRight();
        } else {
            curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
            return balance(curr);
        }
    }

    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not
     * null, then the height of that node will be its height instance
     * data. The height of a node is the max of its left child's height
     * and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child
     * minus the height of its right child.
     *
     * This method should run in O(1).
     * You may assume that the passed in node is not null.
     *
     * This method should only be called in rotateLeft(), rotateRight(),
     * and balance().
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    public void updateHeightAndBF(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int leftHeight;
        int rightHeight;
        if (currentNode.getLeft() == null) {
            leftHeight = -1;
        } else {
            leftHeight = currentNode.getLeft().getHeight();
        }
        if (currentNode.getRight() == null) {
            rightHeight = -1;
        } else {
            rightHeight = currentNode.getRight().getHeight();
        }
        currentNode.setHeight(Math.max(leftHeight, rightHeight) + 1);
        currentNode.setBalanceFactor(leftHeight - rightHeight);
    }

    /**
     * Method that rotates a current node to the left. After saving the
     * current's right node to a variable, the right node's left subtree will
     * become the current node's right subtree. The current node will become
     * the right node's left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to
     * perform any preliminary checks, rather, you can immediately perform a
     * left rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    public AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        AVLNode<T> dummy = currentNode.getRight();
        currentNode.setRight(dummy.getLeft());
        dummy.setLeft(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(dummy);
        return dummy;
    }

    /**
     * Method that rotates a current node to the right. After saving the
     * current's left node to a variable, the left node's right subtree will
     * become the current node's left subtree. The current node will become
     * the left node's right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right
     * rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    public AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        AVLNode<T> dummy = currentNode.getLeft();
        currentNode.setLeft(dummy.getRight());
        dummy.setRight(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(dummy);
        return dummy;
    }

    /**
     * This is the overarching method that is used to balance a subtree
     * starting at the passed in node. This method will utilize
     * updateHeightAndBF(), rotateLeft(), and rotateRight() to balance
     * the subtree. In part 2 of this assignment, this balance() method
     * will be used in your add() and remove() methods.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a
     * double rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do
     * not need to perform any preliminary checks, rather, you can immediately
     * check to see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param currentNode The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    public AVLNode<T> balance(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        /* First, we update the height and balance factor of the current node. */
        updateHeightAndBF(currentNode);

        if ( /* Condition for a right heavy tree. */ currentNode.getBalanceFactor() < -1) {
            if ( /* Condition for a right-left rotation. */ currentNode.getRight().getBalanceFactor() >= 1) {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if ( /* Condition for a left heavy tree. */ currentNode.getBalanceFactor() > 1) {
            if ( /* Condition for a left-right rotation. */ currentNode.getLeft().getBalanceFactor() <= -1) {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }

        return currentNode;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public AVLNode<T> getRoot() {
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
        AVL<Integer> avl = new AVL<>();

        avl.add(6);
        avl.add(4);
        avl.add(9);
        avl.add(1);
        avl.add(5);
        avl.add(8);
        avl.add(2);
        avl.add(3);
        if (!avl.getRoot().getData().equals(6)) {
            System.out.println(avl.getRoot().getData());
            throw new RuntimeException();
        }
        if (avl.size() != 8) {
            System.out.println(avl.size());
            throw new RuntimeException();
        }
        Traversals<Integer> traversals = new Traversals<>();
        List<Integer> preorderResult = traversals.preorder(avl.getRoot());
        if (!preorderResult.equals(List.of(6, 4, 2, 1, 3, 5, 9, 8))) {
            System.out.println("preorder: " + preorderResult);
            throw new RuntimeException();
        }
        List<Integer> inorderResult = traversals.inorder(avl.getRoot());
        if (!inorderResult.equals(List.of(1, 2, 3, 4, 5, 6, 8, 9))) {
            System.out.println("inorder: " + inorderResult);
            throw new RuntimeException();
        }

        avl = new AVL<>();
        avl.add(6);
        avl.add(4);
        avl.add(9);
        avl.add(1);
        avl.add(5);
        avl.add(0);
        if (!avl.getRoot().getData().equals(4)) {
            System.out.println(avl.getRoot().getData());
            throw new RuntimeException();
        }
        preorderResult = traversals.preorder(avl.getRoot());
        if (!preorderResult.equals(List.of(4, 1, 0, 6, 5, 9))) {
            System.out.println("preorder: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(avl.getRoot());
        if (!inorderResult.equals(List.of(0, 1, 4, 5, 6, 9))) {
            System.out.println("inorder: " + inorderResult);
            throw new RuntimeException();
        }

        avl = new AVL<>();
        avl.add(2);
        avl.add(1);
        avl.add(0);
        preorderResult = traversals.preorder(avl.getRoot());
        if (!preorderResult.equals(List.of(1, 0, 2))) {
            System.out.println("preorder: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(avl.getRoot());
        if (!inorderResult.equals(List.of(0, 1, 2))) {
            System.out.println("inorder: " + inorderResult);
            throw new RuntimeException();
        }

        avl = new AVL<>();
        avl.add(1);
        avl.add(2);
        avl.add(3);
        preorderResult = traversals.preorder(avl.getRoot());
        if (!preorderResult.equals(List.of(2, 1, 3))) {
            System.out.println("preorder: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(avl.getRoot());
        if (!inorderResult.equals(List.of(1, 2, 3))) {
            System.out.println("inorder: " + inorderResult);
            throw new RuntimeException();
        }

        avl = new AVL<>();
        avl.add(1);
        avl.add(0);
        avl.add(2);
        avl.remove(1);
        preorderResult = traversals.preorder(avl.getRoot());
        if (!preorderResult.equals(List.of(2, 0))) {
            System.out.println("preorder: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(avl.getRoot());
        if (!inorderResult.equals(List.of(0, 2))) {
            System.out.println("inorder: " + inorderResult);
            throw new RuntimeException();
        }

        avl = new AVL<>();
        avl.add(6);
        avl.add(4);
        avl.add(12);
        avl.add(1);
        avl.add(5);
        avl.add(10);
        avl.add(13);
        avl.add(0);
        avl.add(9);
        avl.add(11);
        avl.add(14);
        avl.add(8);
        avl.remove(5);
        if (!avl.getRoot().getData().equals(10)) {
            System.out.println(avl.getRoot().getData());
            throw new RuntimeException();
        }
        preorderResult = traversals.preorder(avl.getRoot());
        if (!preorderResult.equals(List.of(10, 6, 1, 0, 4, 9, 8, 12, 11, 13, 14))) {
            System.out.println("preorder: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(avl.getRoot());
        if (!inorderResult.equals(List.of(0, 1, 4, 6, 8, 9, 10, 11, 12, 13, 14))) {
            System.out.println("inorder: " + inorderResult);
            throw new RuntimeException();
        }

        avl = new AVL<>();
        avl.add(4);
        avl.add(1);
        avl.add(7);
        avl.add(0);
        avl.add(3);
        avl.add(5);
        avl.add(8);
        avl.add(6);
        avl.remove(4);
        preorderResult = traversals.preorder(avl.getRoot());
        if (!preorderResult.equals(List.of(5, 1, 0, 3, 7, 6, 8))) {
            System.out.println("preorder: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(avl.getRoot());
        if (!inorderResult.equals(List.of(0, 1, 3, 5, 6, 7, 8))) {
            System.out.println("inorder: " + inorderResult);
            throw new RuntimeException();
        }

        avl = new AVL<>();
        avl.add(7);
        avl.add(4);
        avl.add(10);
        avl.add(2);
        avl.add(6);
        avl.add(8);
        avl.add(11);
        avl.add(0);
        avl.add(3);
        avl.add(5);
        avl.add(9);
        avl.add(1);
        avl.remove(4);
        preorderResult = traversals.preorder(avl.getRoot());
        if (!preorderResult.equals(List.of(7, 2, 0, 1, 5, 3, 6, 10, 8, 9, 11))) {
            System.out.println("preorder: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(avl.getRoot());
        if (!inorderResult.equals(List.of(0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11))) {
            System.out.println("inorder: " + inorderResult);
            throw new RuntimeException();
        }

        avl = new AVL<>();
        avl.add(7);
        avl.add(4);
        avl.add(15);
        avl.add(1);
        avl.add(6);
        avl.add(10);
        avl.add(18);
        avl.add(0);
        avl.add(3);
        avl.add(5);
        avl.add(8);
        avl.add(13);
        avl.add(16);
        avl.add(19);
        avl.add(2);
        avl.add(9);
        avl.add(11);
        avl.add(14);
        avl.add(17);
        avl.add(12);
        avl.remove(7);
        preorderResult = traversals.preorder(avl.getRoot());
        if (!preorderResult.equals(List.of(8, 4, 1, 0, 3, 2, 6, 5, 15, 11, 10, 9, 13, 12, 14, 18, 16, 17, 19))) {
            System.out.println("preorder: " + preorderResult);
            throw new RuntimeException();
        }
        inorderResult = traversals.inorder(avl.getRoot());
        if (!inorderResult.equals(List.of(0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19))) {
            System.out.println("inorder: " + inorderResult);
            throw new RuntimeException();
        }
    }
}
