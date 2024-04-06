package gtxcs1332x.module10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author jolly
 */
public class IterativeSort<T extends Comparable<T>> {
    public void bubble(T[] arr) {
        int stop = arr.length - 1;

        while (stop > 0) {
            int last = arr.length - 1;
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

    public void insertion(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int n = i;
            while (n != 0 && (arr[n].compareTo(arr[n-1]) < 0)) {
                swap(arr, n, n-1);
                n--;
            }
        }
    }

    public void selection(T[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            int maxIdx = 0;
            for (int n = 0; n <= i; n++) {
                if (arr[n].compareTo(arr[maxIdx]) > 0) {
                    maxIdx = n;
                }
            }
            swap(arr, i, maxIdx);
        }
    }

    private void swap(T[] arr, int i1, int i2) {
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
        cases.put(new Integer[]{2,3,4,5,1}, new Integer[]{1,2,3,4,5});
        cases.put(new Integer[]{5,4,3,2,1}, new Integer[]{1,2,3,4,5});

        IterativeSort<Integer> sort = new IterativeSort<>();
        for (Map.Entry<Integer[], Integer[]> c : cases.entrySet()) {
            Integer[] input = c.getKey();
            sort.bubble(input);
            if (!Arrays.equals(input, c.getValue())) {
                System.out.println("original: " + Arrays.toString(c.getKey()) + "\noutput: " + Arrays.toString(input));
                throw new RuntimeException();
            }
        }

        for (Map.Entry<Integer[], Integer[]> c : cases.entrySet()) {
            Integer[] input = c.getKey();
            sort.insertion(input);
            if (!Arrays.equals(input, c.getValue())) {
                System.out.println("original: " + Arrays.toString(c.getKey()) + "\noutput: " + Arrays.toString(input));
                throw new RuntimeException();
            }
        }

        for (Map.Entry<Integer[], Integer[]> c : cases.entrySet()) {
            Integer[] input = c.getKey();
            sort.selection(input);
            if (!Arrays.equals(input, c.getValue())) {
                System.out.println("original: " + Arrays.toString(c.getKey()) + "\noutput: " + Arrays.toString(input));
                throw new RuntimeException();
            }
        }
    }
}
