package gtxcs1332x.module1;

import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 */
public class ArrayList<T> {

    /*
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayList.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the front of the list.
     *
     * This add may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size() == backingArray.length) {
            extendCapacity();
        }
        for (int i = size-1; i >= 0; i--) {
            T o = backingArray[i];
            backingArray[i+1] = o;
        }
        backingArray[0] = data;
        size++;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size() == backingArray.length) {
            extendCapacity();
        }
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Do not shrink the backing array.
     *
     * This remove may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T initial = null;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                initial = backingArray[i];
            }
            if (i == size-1) {
                backingArray[i] = null;
                continue;
            }
            T o = backingArray[i + 1];
            backingArray[i] = o;
        }
        size--;
        return initial;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Do not shrink the backing array.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T o = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return o;
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    public T get(int index) {
        if (index >= size) {
            throw new NoSuchElementException();
        }
        return backingArray[index];
    }

    public void clear() {
        size = 0;
        // to allow the gc to take care of clearing away
        // the old data in O(1) T
        backingArray = (T[]) new Object[0];
    }

    private int extendCapacity() {
        int cap = backingArray.length * 2;
        T[] arr = (T[]) new Object[cap];
//        System.arraycopy(backingArray, 0, arr, 0, backingArray.length);
//        backingArray = arr;
//        return backingArray.length;
        for (int i = 0; i < size; i++) {
            arr[i] = backingArray[i];
        }
        backingArray = arr;
        return backingArray.length;
    }

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for index size and O(n) for all other cases.
     *
     * ASSUMPTIONS:
     * - You may assume that the backingArray will not need to be resized.
     * - You may assume that the index is valid [0, size].
     * - You may assume that the data will never be null.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     */
    public void addAtIndex(int index, T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (index < size) {
            for (int i = size - 1; i >= index; i--) {
                T o = backingArray[i];
                backingArray[i+1] = o;
            }
        }
        backingArray[index] = data;

        size++;
    }
}
