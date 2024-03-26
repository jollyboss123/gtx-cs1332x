package gtxcs1332x.module2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author jolly
 */
public class CircularlyLinkedList<T> implements Iterable<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;

        private Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        private Node(T data) {
            this(data, null);
        }

        private Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new CLLIterator();
    }

    private class CLLIterator implements Iterator<T> {
        private Node<T> curr;
        private boolean firstIteration;

        CLLIterator() {
            curr = head;
            firstIteration = true;
        }

        @Override
        public boolean hasNext() {
            if (firstIteration) {
                return curr != null;
            }
            return curr != head;
        }

        @Override
        public T next() {
            if (hasNext()) {
                firstIteration = false;
                T temp = curr.data;
                curr = curr.next;
                return temp;
            }
            throw new NoSuchElementException();
        }
    }

    private Node<T> head;
    private int size;

    // O(1) T
    public void addToFront(T data) {
        if (size < 2) {
            if (size == 0) {
                Node<T> newNode = new Node<>(data);
                newNode.next = newNode;
                head = newNode;
            } else {
                T temp = head.data;
                head.next = new Node<>(temp, head);
                head.data = data;
            }
            size++;
            return;
        }
        // add new node to index 1
        // copy head data to new node
        // assign new data to head
        T temp = head.data;
        Node<T> next = head.next;
        head.next = new Node<>(temp, next);
        head.data = data;
        size++;
    }

    // O(1) T
    public void addToBack(T data) {
        if (size < 2) {
            if (size == 0) {
                Node<T> newNode = new Node<>(data);
                newNode.next = newNode;
                head = newNode;
            } else {
                T temp = head.data;
                Node<T> newNode = new Node<>(temp, head);
                head.next = newNode;
                head.data = data;
                head = newNode;
            }
            size++;
            return;
        }
        // add new node to index 1
        // copy head data to new node
        // assign new data to head
        // assign new node as head
        T temp = head.data;
        Node<T> next = head.next;
        Node<T> newNode = new Node<>(temp, next);
        head.next = newNode;
        head.data = data;
        head = newNode;
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
        Node<T> curr = head;
        int count = 0;
        while (curr.next != head && count < index - 1) {
            curr = curr.next;
            count++;
        }
        curr.next = new Node<>(data, curr.next);
        size++;
    }

    public void removeFromFront() {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            head = null;
            size--;
            return;
        }
        head.data = head.next.data;
        if (size == 2) {
            head.next = head;
        } else {
            head.next = head.next.next;
        }
        size--;
    }

    public void removeFromBack() {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            head = null;
            size--;
            return;
        }
        Node<T> curr = head;
        int count = 0;
        // stop at 1 short of the last index
        while (curr.next != head && count < size - 2) {
            curr = curr.next;
            count++;
        }
        curr.next = head;
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
        Node<T> curr = head;
        int count = 0;
        while (curr.next != head && count < index - 1) {
            curr = curr.next;
            count++;
        }
        curr.next = curr.next.next;
        size--;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Node<T> curr = head;
        int count = 0;

        while (curr != null && count < size) {
            if (curr == head) {
                sb.append(curr).append("(head)");
            } else {
                sb.append(curr);
            }
            if (curr.next == head) {
                sb.append("->").append(head).append("(head)");
                break;
            } else {
                sb.append("->");
            }
            curr = curr.next;
            count++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
//        cll.addToFront(1);
//        cll.addToFront(2);
//        cll.addToFront(3);
//        cll.addToFront(4);
        cll.addToBack(1);
        cll.addToBack(2);
        cll.addToBack(3);
        cll.addToBack(4);
//        System.out.println(cll);
//        cll.removeFromBack();
//        System.out.println(cll);
//        cll.removeFromBack();
//        System.out.println(cll);
//        cll.removeAt(3);
//        cll.removeAt(3);
//        System.out.println(cll);
//        cll.addAt(3, 1);
//        cll.addAt(3, 2);
        System.out.println(cll);
        for (var i : cll) {
            System.out.println(i);
        }
    }
}
