package gtxcs1332x.module10;

import java.util.Arrays;
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

    private void swap(T[] arr, int i1, int i2) {
        if (arr.length == 0) {
            throw new NoSuchElementException();
        }
        T temp = arr[i1];
        T temp2 = arr[i2];
        arr[i1] = temp2;
        arr[i2] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{2,3,4,5,1};
        IterativeSort<Integer> sort = new IterativeSort<>();
        sort.bubble(arr);
        if (!Arrays.equals(arr, new Integer[]{1, 2, 3, 4, 5})) {
            System.out.println(Arrays.toString(arr));
            throw new RuntimeException();
        }

        arr = new Integer[]{5,4,3,2,1};
        sort.bubble(arr);
        if (!Arrays.equals(arr, new Integer[]{1, 2, 3, 4, 5})) {
            System.out.println(Arrays.toString(arr));
            throw new RuntimeException();
        }
    }
}
