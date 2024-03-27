package gtxcs1332x.module3;

import gtxcs1332x.module2.SinglyLinkedList;

/**
 * @author jolly
 */
public class LinkedQueue<T extends Comparable<T>> {
    private SinglyLinkedList<T> backingSLL;

    public LinkedQueue() {
        backingSLL = new SinglyLinkedList<>();
    }

    public void enqueue(T data) {
        backingSLL.addToBack(data);
    }

    public T dequeue() {
        return backingSLL.removeFromFront();
    }

    public T peek() {
        return backingSLL.head().data();
    }

    public boolean isEmpty() {
        return backingSLL.head() == null;
    }

    public void clear() {
        backingSLL.clear();
    }

    public int size() {
        return backingSLL.size();
    }

    @Override
    public String toString() {
        return backingSLL.toString();
    }

    public static void main(String[] args) {
        var lq = new LinkedQueue<String>();
        lq.enqueue("r");
        lq.enqueue("a");
        lq.enqueue("m");
        lq.enqueue("b");
        lq.enqueue("l");
        lq.enqueue("i");
        lq.enqueue("n");
        if (lq.size() != 7) {
            System.out.println(lq.size());
            throw new IllegalArgumentException();
        }
        lq.dequeue();
        lq.dequeue();
        lq.dequeue();
        lq.dequeue();
        if (!lq.peek().equals("l")) {
            System.out.println(lq.peek());
            throw new IllegalArgumentException();
        }
        if (lq.size() != 3) {
            System.out.println(lq.size());
            throw new IllegalArgumentException();
        }
        lq.enqueue("w");
        lq.enqueue("r");
        if (lq.size() != 5) {
            System.out.println(lq.size());
            throw new IllegalArgumentException();
        }
        System.out.println(lq);
        lq.enqueue("a");
        lq.enqueue("m");
        lq.enqueue("b");
        System.out.println(lq);
        if (lq.size() != 8) {
            System.out.println(lq.size());
            throw new IllegalArgumentException();
        }
        if (!lq.peek().equals("l")) {
            System.out.println(lq.peek());
            throw new IllegalArgumentException();
        }
        lq.clear();
        if (!lq.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
