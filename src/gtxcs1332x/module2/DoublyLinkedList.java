package gtxcs1332x.module2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author jolly
 */
public class DoublyLinkedList<T extends Comparable<T>> implements Iterable<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        private Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        private Node(T data) {
            this(data, null, null);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DLLIterator();
    }

    private class DLLIterator implements Iterator<T> {
        private Node<T> curr;

        DLLIterator() {
            curr = head;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T temp = curr.data;
                curr = curr.next;
                return temp;
            }
            throw new NoSuchElementException();
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void addToFront(T data) {
        Node<T> newNode = new Node<>(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            // always connect new node to linked list first
            // to avoid risk losing O(1) access to the next node
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addToBack(T data) {
        Node<T> newNode = new Node<>(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            // always connect new node to linked list first
            // to avoid risk losing O(1) access to the next node
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addAt(int index, T data) {
        if (index > size) {
            throw new NoSuchElementException();
        }
        if (index == 0) {
            addToFront(data);
            return;
        }
        if (index == size) {
            addToBack(data);
            return;
        }
        // can optimize this to O(n/2) by splitting the list in half
        // and traverse from head if index is smaller than list size / 2
        // else traverse from tail
        Node<T> curr = head;
        Node<T> next = null;
        Node<T> newNode = new Node<>(data);
        int i = 0;
        while (curr.next != null && i < index - 1) {
            next = curr.next.next;
            curr = curr.next;
            i++;
        }
        newNode.prev = curr;
        newNode.next = next;
        curr.next = newNode;
        if (next != null) {
            next.prev = newNode;
        }
        size++;
    }

    public void removeFromFront() {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<T> newHead = head.next;
            newHead.prev = null;
            head = newHead;
        }
        size--;
    }

    // O(1) T
    public void removeFromBack() {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<T> newTail = tail.prev;
            newTail.next = null;
            tail = newTail;
        }
        size--;
    }

    public void removeAt(int index) {
        if (index >= size) {
            throw new NoSuchElementException();
        }
        if (index == 0) {
            removeFromFront();
            return;
        }
        if (index == size - 1) {
            removeFromBack();
            return;
        }

        // can optimize this to O(n/2) by splitting the list in half
        // and traverse from head if index is smaller than list size / 2
        // else traverse from tail
        Node<T> curr = head;
        int i = 0;
        while (curr.next != null && i < index - 1) {
            curr = curr.next;
            i++;
        }
        assert curr.next != null;
        Node<T> next = curr.next.next;
        curr.next = next;
        next.prev = curr;
        size--;
    }

    public void removeDuplicates() {
        head = rRemove(head);
    }

    private Node<T> rRemove(Node<T> curr) {
        if (curr == null) {
            return null;
        }
        curr.next = rRemove(curr.next);
        if (curr.next != null && curr.data.compareTo(curr.next.data) == 0) {
            size--;
            return curr.next;
        }
        return curr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> curr = head;
        while (curr != null) {
            sb.append(curr);
            if (curr.next != null) {
                sb.append("->");
            }
            curr = curr.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.addToFront(2);
        dll.addToFront(1);
        System.out.println(dll);
        dll.addToBack(3);
        dll.addToBack(4);
        dll.addToBack(4);
        dll.addToFront(1);
        dll.addToFront(1);
        dll.addToBack(4);
        System.out.println(dll);
//        dll.removeFromFront();
//        System.out.println(dll);
//        dll.removeFromFront();
//        System.out.println(dll);
//        dll.removeFromFront();
//        System.out.println(dll);
//        dll.removeFromFront();
//        System.out.println(dll);
//        dll.removeFromBack();
//        System.out.println(dll);
//        for (var d : dll) {
//            System.out.println(d);
//        }
        dll.removeDuplicates();
        System.out.println(dll);
        System.out.println(dll.size);
        dll.addAt(2, 3);
        dll.addAt(2, 3);
        System.out.println(dll);
        dll.removeAt(2);
        dll.removeAt(3);
        System.out.println(dll);
    }
}
