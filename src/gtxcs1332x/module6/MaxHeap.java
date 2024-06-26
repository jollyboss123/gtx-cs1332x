package gtxcs1332x.module6;

/**
 * Your implementation of a MaxHeap.
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
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
     * This is the constructor that constructs a new MaxHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MaxHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    public void add(T data) {
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
        if (backingArray[parent].compareTo(backingArray[index]) < 0) {
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
     * Removes and returns the max item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * You may assume that the heap is not empty.
     *
     * @return The data that was removed.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
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
            if (curr.compareTo(left) > 0 && curr.compareTo(right) > 0) {
                return;
            }
            if (left.compareTo(right) > 0) {
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
            if (curr.compareTo(left) > 0) {
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
}
