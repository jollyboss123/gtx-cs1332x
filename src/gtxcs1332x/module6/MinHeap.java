package gtxcs1332x.module6;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        size++;
        if (size == backingArray.length) {
            extendCapacity();
        }
        backingArray[size] = data;
        upHeap(size);
    }

    private void upHeap(int index) {
        int parent = index / 2;
        if (index == 1 || parent == 0) {
            return;
        }
        if (backingArray[parent].compareTo(backingArray[index]) > 0) {
            T p = backingArray[parent];
            T c = backingArray[index];
            backingArray[parent] = c;
            backingArray[index] = p;
        }
        upHeap(parent);
    }

    private void extendCapacity() {
        int c = backingArray.length * 2;
        T[] arr = (T[]) new Comparable[c];
        for (int i = 0; i < size; i++) {
            arr[i] = backingArray[i];
        }
        backingArray = arr;
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T temp = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return temp;
    }

    private void downHeap(int index) {
        int l = 2 * index;
        int r = l + 1;
        if (l > size) {
            return;
        }
        T curr = backingArray[index];
        T left = backingArray[l];
        T right = backingArray[r];

        // two children case
        if (left != null && right != null) {
            if (curr.compareTo(left) < 0 && curr.compareTo(right) < 0) {
                return;
            }
            if (left.compareTo(right) < 0) {
                backingArray[index] = left;
                backingArray[l] = curr;
                downHeap(l);
            } else {
                backingArray[index] = right;
                backingArray[r] = curr;
                downHeap(r);
            }
        }

        // one child case
        if (left != null && right == null) {
            if (curr.compareTo(left) < 0) {
                return;
            }
            backingArray[index] = left;
            backingArray[l] = curr;
            downHeap(l);
        }
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>();
        minHeap.add(7);
        minHeap.add(1);
        minHeap.add(4);
        minHeap.add(8);
        minHeap.add(5);
        minHeap.add(2);
        minHeap.add(6);
        minHeap.add(3);
        minHeap.add(9);
        if (!Arrays.equals(minHeap.getBackingArray(), new Integer[]{null, 1, 3, 2, 5, 7, 4, 6, 8, 9, null, null, null})) {
            System.out.println(Arrays.toString(minHeap.getBackingArray()));
            throw new RuntimeException();
        }
        Integer r = minHeap.remove();
        if (!r.equals(1)) {
            System.out.println(r);
            throw new RuntimeException();
        }
        if (!Arrays.equals(minHeap.getBackingArray(), new Integer[] {null, 2, 3, 4, 5, 7, 9, 6, 8, null, null, null, null})) {
            System.out.println(Arrays.toString(minHeap.getBackingArray()));
            throw new RuntimeException();
        }
        r = minHeap.remove();
        if (!r.equals(2)) {
            System.out.println(r);
            throw new RuntimeException();
        }
        if (!Arrays.equals(minHeap.getBackingArray(), new Integer[]{null, 3, 5, 4, 8, 7, 9, 6, null, null, null, null, null})) {
            System.out.println(Arrays.toString(minHeap.getBackingArray()));
            throw new RuntimeException();
        }
//        minHeap.remove();
//        minHeap.remove();
//        minHeap.remove();
//        minHeap.remove();
//        minHeap.remove();
//        minHeap.remove();
//        minHeap.remove();
//        System.out.println(Arrays.toString(minHeap.getBackingArray()));
        minHeap.add(1);
        minHeap.add(2);
        minHeap.add(10);
        minHeap.add(11);
        minHeap.add(13);
        minHeap.add(12);
        System.out.println(Arrays.toString(minHeap.getBackingArray()));
    }
}
