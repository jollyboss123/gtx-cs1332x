package gtxcs1332x.module3;

/**
 * @author jolly
 */
public class ArrayQueue<T> {
    private T[] backingArray;
    private static final int INITIAL_CAPACITY = 7;
    private int size;
    private int front;
    private int back;

    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        back = 0;
    }

    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size == backingArray.length) {
            extendCapacity();
        }
        backingArray[back] = data;
        size++;
        back = ++back % backingArray.length;
    }

    public T dequeue() {
        T temp = backingArray[front];
        backingArray[front] = null;
        front = ++front % backingArray.length;
        size--;
        if (isEmpty()) {
            front = 0;
        }
        return temp;
    }

    public T peek() {
        return backingArray[front];
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public void clear() {
        size = 0;
        backingArray = (T[]) new Object[0];
    }

    public int size() {
        return size;
    }

    private int extendCapacity() {
        int cap = backingArray.length * 2;
        T[] arr = (T[]) new Object[cap];
        int curr = front;
        for (int i = 0; i < size; i++) {
            arr[i] = backingArray[curr];
            curr = ++curr % backingArray.length;
        }
        front = 0;
        back = size;
        backingArray = arr;
        return backingArray.length;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int curr = front;
        for (; ; ) {
            T e = backingArray[curr];
            sb.append(e == this ? "(this Collection)" : e);
            if (curr == back - 1) {
                return sb.append(']').toString();
            }
            curr = ++curr % backingArray.length;
            sb.append(',').append(' ');
        }
    }

    public static void main(String[] args) {
        var aq = new ArrayQueue<String>();
        aq.enqueue("r");
        aq.enqueue("a");
        aq.enqueue("m");
        aq.enqueue("b");
        aq.enqueue("l");
        aq.enqueue("i");
        aq.enqueue("n");
        if (aq.size != 7) {
            System.out.println(aq.size);
            throw new IllegalArgumentException();
        }
        if (aq.front != 0) {
            System.out.println(aq.front);
            throw new IllegalArgumentException();
        }
        if (aq.back != 0) {
            System.out.println(aq.back);
            throw new IllegalArgumentException();
        }
        aq.dequeue();
        aq.dequeue();
        aq.dequeue();
        aq.dequeue();
        if (!aq.peek().equals("l")) {
            System.out.println(aq.peek());
            throw new IllegalArgumentException();
        }
        if (aq.size != 3) {
            System.out.println(aq.size);
            throw new IllegalArgumentException();
        }
        if (aq.front != 4) {
            System.out.println(aq.front);
            throw new IllegalArgumentException();
        }
        if (aq.back != 0) {
            System.out.println(aq.back);
            throw new IllegalArgumentException();
        }
        aq.enqueue("w");
        aq.enqueue("r");
        if (aq.size != 5) {
            System.out.println(aq.size);
            throw new IllegalArgumentException();
        }
        if (aq.back != 2) {
            System.out.println(aq.back);
            throw new IllegalArgumentException();
        }
        System.out.println(aq);
        aq.enqueue("a");
        aq.enqueue("m");
        aq.enqueue("b");
        System.out.println(aq);
        if (aq.size != 8) {
            System.out.println(aq.size);
            throw new IllegalArgumentException();
        }
        if (aq.front != 0) {
            System.out.println(aq.front);
            throw new IllegalArgumentException();
        }
        if (aq.back != 8) {
            System.out.println(aq.back);
            throw new IllegalArgumentException();
        }
        if (!aq.peek().equals("l")) {
            System.out.println(aq.peek());
            throw new IllegalArgumentException();
        }
        aq.clear();
        if (!aq.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
