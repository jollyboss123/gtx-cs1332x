package gtxcs1332x.module2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author jolly
 */
public class SinglyLinkedList<T extends Comparable<T>> implements Iterable<T> {

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

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LLIterator();
    }

    private class LLIterator implements Iterator<T> {
        private Node<T> curr;

        LLIterator() {
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
    private Node<T> tail; // addToBack becomes O(1) T
    private int size; // for edge cases checking in O(1) T

    public void addToFront(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head; // point to current head
        head = newNode; // redirect the head reference to point to the new node
        size++;
        if (size == 1) {
            tail = newNode;
        }
    }

    public void addToBack(T data) {
        Node<T> newNode = new Node<>(data);
        if (size == 0) {
            head = newNode;
        } else {
//            Node curr = head;
//            while (curr.next != null) {
//                curr = curr.next;
//            }
//            curr.next = new Node(data);
            tail.next = newNode;
        }
        tail = newNode;
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

        var curr = head;
        Node<T> next = null;
        int i = 0;
        while (curr.next != null && i < index - 1) {
            next = curr.next.next;
            curr = curr.next;
            i++;
        }
        Node<T> newNode = new Node<>(data);
        curr.next = newNode;
        newNode.next = next;
        size++;
    }

    public void removeFromFront() {
        if (size != 0) {
            head = head.next;
            size--;
        }
    }

    public void removeFromBack() {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return;
        }

        Node<T> curr = head;
        // iterate to the 2nd last node
        while (curr.next.next != null) {
            curr = curr.next;
        }
        curr.next = null;
        tail = curr;
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
        int i = 0;
        while (curr.next != null && i < index - 1) {
            curr = curr.next;
            i++;
        }
        assert curr.next != null;
        curr.next = curr.next.next;
        size--;
    }

    // assuming linked list in sorted order
    public void removeDuplicates() {
        // iteration method
//        if (head == null) {
//            return;
//        }
//        Node<T> curr = head;
//        Node<T> prev = head;
//        while (curr.next != null) {
//            assert prev != null;
//            if (prev.data.compareTo(curr.data) == 0) {
//                assert prev.next != null;
//                prev.next = prev.next.next;
//            } else {
//                prev = prev.next;
//                continue;
//            }
//            if (prev.next != null) {
//                curr = prev.next;
//            }
//        }

        // recursion
//        Node<T> curr = head;
//        reRemove(curr, curr.next);
        head = rRemove(head);
    }

//    private void reRemove(Node<T> curr, Node<T> next) {
//        if (curr == null || next == null) {
//            return;
//        }
//        if (curr.data.compareTo(next.data) != 0) {
//            compare(curr.next, curr.next.next);
//        } else {
//            curr.next = curr.next.next;
//            compare(curr, curr.next);
//        }
//    }

    // recursion with pointer reinforcement
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
        StringBuilder answer = new StringBuilder();
        // to keep track of the node we are looking
        // new pointer is important because we cannot accidentally destroy
        // the head of the SinglyLinkedList
        // if the head pointer moves to the next node we lose all access
        // to the old head
        Node<T> curr = head;
        while (curr != null) {
            answer.append(curr);
            if (curr.next != null) {
                answer.append("->");
            }
            curr = curr.next;
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        var sll = new SinglyLinkedList<Integer>();
        sll.addToFront(1);
        sll.addToBack(2);
        sll.addToBack(4);
        sll.addToBack(5);
        sll.addAt(2, 3);
        sll.addAt(2, 3);
        sll.addAt(2, 3);
        sll.addToBack(5);
        sll.addToFront(1);
        System.out.println(sll);
        sll.removeDuplicates();
        System.out.println(sll);
//        sll.addAt(4, 6);
//        System.out.println(sll);
//        sll.removeAt(4);
//        System.out.println(sll);
//        sll.removeFromFront();
//        System.out.println(sll);
//        sll.removeFromBack();
//        System.out.println(sll);
//        sll.removeFromBack();
//        System.out.println(sll.size);
//        sll.removeFromFront();
//        System.out.println(sll.size);
//        for (var i : sll) {
//            System.out.println(i);
//        }
//
//        var iterator = sll.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
    }
}
