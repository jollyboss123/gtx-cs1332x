package gtxcs1332x.module3;

import gtxcs1332x.module1.ArrayList;

/**
 * @author jolly
 */
public class ArrayStack<T> {
    private ArrayList<T> backingAL;

    public ArrayStack() {
        backingAL = new ArrayList<>();
    }

    public void push(T data) {
        backingAL.addToBack(data);
    }

    public T pop() {
        return backingAL.removeFromBack();
    }

    public T peek() {
        return backingAL.get(backingAL.size() - 1);
    }

    public boolean isEmpty() {
        return backingAL.size() <= 0;
    }

    public void clear() {
        backingAL.clear();
    }

    public int size() {
        return backingAL.size();
    }

    public static void main(String[] args) {
        var as = new ArrayStack<Integer>();
        as.push(1);
        as.push(3);
        as.push(3);
        as.push(2);
        var pe = as.peek();
        if (!pe.equals(2)) {
            throw new IllegalArgumentException();
        }
        if (as.size() != 4) {
            throw new IllegalArgumentException();
        }
        if (as.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var p = as.pop();
        if (!p.equals(2)) {
            throw new IllegalArgumentException();
        }
        as.clear();
        if (!as.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
