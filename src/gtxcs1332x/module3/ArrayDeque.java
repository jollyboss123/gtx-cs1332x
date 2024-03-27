package gtxcs1332x.module3;

import java.util.NoSuchElementException;

/**
 * @author jolly
 */
public class ArrayDeque<T> {
    private T[] backingArray;
    private int front;
    private int back;
    private int size;
    private static final int INITIAL_CAPACITY = 7;

    public ArrayDeque() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        back = 0;
        size = 0;
    }

    public void addFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            addLast(data);
            return;
        }
        if (size == backingArray.length) {
            extendCapacity();
        }
        front--;
        if (front == -1) {
            front = backingArray.length - 1;
        }
        backingArray[front] = data;
        size++;
    }

    public void addLast(T data) {
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

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T temp = backingArray[front];
        backingArray[front] = null;
        front = ++front % backingArray.length;
        size--;
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        back--;
        if (back == -1) {
            back = backingArray.length - 1;
        }
        T temp = backingArray[back];
        backingArray[back] = null;
        size--;
        return temp;
    }

    public boolean isEmpty() {
        return size <= 0;
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
        var ad = new ArrayDeque<String>();
        ad.addLast("r");
        ad.addLast("a");
        ad.addLast("m");
        ad.addLast("b");
        ad.addLast("l");
        ad.addLast("i");
        ad.addLast("n");
        if (ad.isEmpty()) {
            throw new RuntimeException();
        }
        if (ad.size() != 7) {
            throw new RuntimeException();
        }
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        if (ad.size() != 3) {
            throw new RuntimeException();
        }
        if (ad.front != 4) {
            System.out.println(ad.front);
            throw new RuntimeException();
        }
        if (ad.back != 0) {
            System.out.println(ad.back);
            throw new RuntimeException();
        }
        var r1 = ad.removeLast();
        if (!r1.equals("n")) {
            System.out.println(r1);
            throw new RuntimeException();
        }
        if (ad.back != 6) {
            throw new RuntimeException();
        }
    }
}
