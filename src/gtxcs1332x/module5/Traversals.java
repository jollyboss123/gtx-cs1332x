package gtxcs1332x.module5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(BSTNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> res = new ArrayList<>();
        preorderTraverse(root, res);
        return res;
    }

    private void preorderTraverse(BSTNode<T> root, List<T> res) {
        if (root != null) {
            res.add(root.getData());
            preorderTraverse(root.getLeft(), res);
            preorderTraverse(root.getRight(), res);
        }
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(BSTNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> res = new ArrayList<>();
        inorderTraverse(root, res);
        return res;
    }

    private void inorderTraverse(BSTNode<T> root, List<T> res) {
        if (root != null) {
            inorderTraverse(root.getLeft(), res);
            res.add(root.getData());
            inorderTraverse(root.getRight(), res);
        }
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(BSTNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> res = new ArrayList<>();
        postorderTraverse(root, res);
        return res;
    }

    private void postorderTraverse(BSTNode<T> root, List<T> res) {
        if (root != null) {
            postorderTraverse(root.getLeft(), res);
            postorderTraverse(root.getRight(), res);
            res.add(root.getData());
        }
    }

    public List<T> levelorder(BSTNode<T> root) {
        Queue<BSTNode<T>> q = new LinkedList<>();
        q.offer(root);
        List<T> res = new ArrayList<>();
        while (!q.isEmpty()) {
            BSTNode<T> curr = q.poll();
            if (curr != null) {
                res.add(curr.getData());

                q.offer(curr.getLeft());
                q.offer(curr.getRight());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // Create a sample binary search tree
        BSTNode<Integer> root = new BSTNode<>(5);
        root.setLeft(new BSTNode<>(3));
        root.getLeft().setLeft(new BSTNode<>(2));
        root.getLeft().setRight(new BSTNode<>(4));
        root.setRight(new BSTNode<>(7));
        root.getRight().setLeft(new BSTNode<>(6));
        root.getRight().setRight(new BSTNode<>(8));

        Traversals<Integer> traversals = new Traversals<>();

        // Test preorder traversal
        List<Integer> preorderResult = traversals.preorder(root);
        System.out.println("Preorder traversal: " + preorderResult);
        if (!preorderResult.equals(List.of(5,3,2,4,7,6,8))) {
            throw new RuntimeException();
        }
        // Expected output: Preorder traversal: [5, 3, 2, 4, 7, 6, 8]

        // Test inorder traversal
        List<Integer> inorderResult = traversals.inorder(root);
        System.out.println("Inorder traversal: " + inorderResult);
        if (!inorderResult.equals(List.of(2,3,4,5,6,7,8))) {
            throw new RuntimeException();
        }
        // Expected output: Inorder traversal: [2, 3, 4, 5, 6, 7, 8]

        // Test postorder traversal
        List<Integer> postorderResult = traversals.postorder(root);
        System.out.println("Postorder traversal: " + postorderResult);
        if (!postorderResult.equals(List.of(2,4,3,6,8,7,5))) {
            throw new RuntimeException();
        }
        // Expected output: Postorder traversal: [2, 4, 3, 6, 8, 7, 5]

        // Test level-order traversal
        List<Integer> levelorderResult = traversals.levelorder(root);
        System.out.println("Level-order traversal: " + levelorderResult);
        if (!levelorderResult.equals(List.of(5,3,7,2,4,6,8))) {
            throw new RuntimeException();
        }
        // Expected output: Level-order traversal: [5, 3, 7, 2, 4, 6, 8]
    }
}
