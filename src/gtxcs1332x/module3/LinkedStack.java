package gtxcs1332x.module3;

import gtxcs1332x.module2.SinglyLinkedList;

/**
 * @author jolly
 */
public class LinkedStack<T extends Comparable<T>> {
    // don't need a tail pointer in SinglyLinkedList for LinkedStack
    // because we don't need its optimization for addToBack()
    private SinglyLinkedList<T> backingLL;

    public LinkedStack() {
        backingLL = new SinglyLinkedList<>();
    }

    // O(1) T
    public void push(T data) {
        backingLL.addToFront(data);
    }

    // O(1) T
    public T pop() {
        return backingLL.removeFromFront();
    }

    // O(1) T
    public T peek() {
        return backingLL.head().data();
    }

    public boolean isEmpty() {
        return backingLL.head() == null;
    }

    // O(1) T
    public void clear() {
        backingLL.clear();
    }

    public int size() {
        return backingLL.size();
    }

    public static void main(String[] args) {
        var ls = new LinkedStack<Integer>();
        ls.push(1);
        ls.push(3);
        ls.push(3);
        ls.push(2);
        if (!ls.peek().equals(2)) {
            throw new IllegalArgumentException();
        }
        if (ls.size() != 4) {
            throw new IllegalArgumentException();
        }
        if (ls.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var p = ls.pop();
        if (!p.equals(2)) {
            throw new IllegalArgumentException();
        }
        ls.clear();
        if (!ls.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
