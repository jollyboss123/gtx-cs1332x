package gtxcs1332x.module10;

import java.util.*;

/**
 * @author jolly
 */
public class IterativeSort<T extends Comparable<T>> {
    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int stop = arr.length - 1;

        while (stop > 0) {
            int last = 0;
            int i = 0;
            while (i < stop) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    last = i;
                }
                i++;
            }
            stop = last;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int i = arr.length - 1; i >= 1; i--) {
            int maxIdx = 0;
            for (int n = 1; n <= i; n++) {
                if (comparator.compare(arr[n], arr[maxIdx]) > 0) {
                    maxIdx = n;
                }
            }
            swap(arr, i, maxIdx);
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int i = 1; i < arr.length; i++) {
            int n = i;
            while (n != 0 && (comparator.compare(arr[n], arr[n-1]) < 0)) {
                swap(arr, n, n-1);
                n--;
            }
        }
    }

    /**
     * Bubbles the maximum item to the end of the subarray. The subarray's search space decreases based on
     * where the last swap occurred.
     *
     * @param arr The array to be sorted.
     */
    public void bubble(T[] arr) {
        int stop = arr.length - 1;

        while (stop > 0) {
            int last = 0;
            int i = 0;
            while (i < stop) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    last = i;
                }
                i++;
            }
            stop = last;
        }
    }

    /**
     * Maintains two parts, the left part is relatively sorted, and the right part has not been processed. The border
     * between the two sections moves rightward in each iteration, and the item in iteration will be inserted into
     * its correct relative position in the sorted part.
     *
     * @param arr The array to be sorted.
     */
    public void insertion(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int n = i;
            while (n != 0 && (arr[n].compareTo(arr[n-1]) < 0)) {
                swap(arr, n, n-1);
                n--;
            }
        }
    }

    /**
     * Looks for the maximum item in the subarray and swaps it into its correct position. The search space
     * decreases by one each time.
     *
     * @param arr The array to be sorted.
     */
    public void selection(T[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            int maxIdx = 0;
            for (int n = 1; n <= i; n++) {
                if (arr[n].compareTo(arr[maxIdx]) > 0) {
                    maxIdx = n;
                }
            }
            swap(arr, i, maxIdx);
        }
    }

    /**
     * A modified bubble sort, where each iteration is composed of two bubble sort iterations in opposite
     * directions. The first iteration bubbles the maximum to the end, and the second does the opposite. The search
     * space's bounded are modified based on where the last swap occurred in each half iteration.
     *
     * @param arr The array to be sorted.
     */
    public void cocktailShaker(T[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int lastStart = 0;
            int lastEnd = 0;
            for (int i = start; i < end; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    lastEnd = i;
                }
            }
            end = lastEnd;
            for (int i = end; i > start; i--) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i - 1, i);
                    lastStart = i;
                }
            }
            start = lastStart;
        }
    }

    private static <T> void swap(T[] arr, int i1, int i2) {
        if (arr.length == 0) {
            throw new NoSuchElementException();
        }
        if (i1 >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (i2 >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T temp = arr[i1];
        T temp2 = arr[i2];
        arr[i1] = temp2;
        arr[i2] = temp;
    }

    public static void main(String[] args) {
        Map<Integer[], Integer[]> cases = new HashMap<>();
        cases.put(new Integer[]{2, 3, 4, 5, 1}, new Integer[]{1, 2, 3, 4, 5});
        cases.put(new Integer[]{5, 4, 3, 2, 1}, new Integer[]{1, 2, 3, 4, 5});
        cases.put(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4, 5});
        cases.put(new Integer[]{2, 3, 4, 6, 7, 8, 9, 6, 5, 10}, new Integer[]{2, 3, 4, 5, 6, 6, 7, 8, 9, 10});
        cases.put(new Integer[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 1}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        IterativeSort<Integer> sort = new IterativeSort<>();
        for (Map.Entry<Integer[], Integer[]> c : cases.entrySet()) {
            Integer[] input = Arrays.copyOf(c.getKey(), c.getKey().length);
            sort.bubble(input);
            verify(c, input);
            System.out.println("bubble passed: " + Arrays.toString(c.getKey()));

            input = Arrays.copyOf(c.getKey(), c.getKey().length);
            sort.insertion(input);
            verify(c, input);
            System.out.println("insertion passed: " + Arrays.toString(c.getKey()));

            input = Arrays.copyOf(c.getKey(), c.getKey().length);
            sort.selection(input);
            verify(c, input);
            System.out.println("selection passed: " + Arrays.toString(c.getKey()));

            input = Arrays.copyOf(c.getKey(), c.getKey().length);
            sort.cocktailShaker(input);
            verify(c, input);
            System.out.println("cocktail shaker passed: " + Arrays.toString(c.getKey()));
        }
    }

    private static void verify(Map.Entry<Integer[], Integer[]> c, Integer[] input) {
        if (!Arrays.equals(input, c.getValue())) {
            System.out.println("original: " + Arrays.toString(c.getKey()) + "\noutput: " + Arrays.toString(input));
            throw new RuntimeException();
        }
    }
}
