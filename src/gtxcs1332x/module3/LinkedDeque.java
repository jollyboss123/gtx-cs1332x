package gtxcs1332x.module3;

import gtxcs1332x.module2.DoublyLinkedList;

/**
 * @author jolly
 */
public class LinkedDeque<T extends Comparable<T>> {
    private DoublyLinkedList<T> backingDLL;

    public void addFirst(T data) {
        backingDLL.addToFront(data);
    }

    public void addLast(T data) {
        backingDLL.addToBack(data);
    }

    public T removeFirst() {
        return backingDLL.removeFromFront();
    }

    public T removeLast() {
        return backingDLL.removeFromBack();
    }

    public boolean isEmpty() {
        return backingDLL.isEmpty();
    }

    public int size() {
        return backingDLL.size();
    }

    @Override
    public String toString() {
        return backingDLL.toString();
    }
}
