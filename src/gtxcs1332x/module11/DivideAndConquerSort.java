package gtxcs1332x.module11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jolly
 */
public class DivideAndConquerSort {

    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // partitioning
        if (arr.length == 1) {
            return;
        }
        int len = arr.length;
        int mid = len / 2;
        T[] left = Arrays.copyOf(arr, mid);
        T[] right = Arrays.copyOfRange(arr, mid, len);
        mergeSort(left, comparator);
        mergeSort(right, comparator);

        // merging
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }

        // emptying subarrays
        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }
    }

    public static void main(String[] args) {
        Map<Integer[], Integer[]> cases = new HashMap<>();
        cases.put(new Integer[]{5, 7, 8, 9, 3, 6, 4, 6, 2}, new Integer[]{2, 3, 4, 5, 6, 6, 7, 8, 9});
        cases.put(new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{1, 2, 3, 4, 5, 6});
        cases.put(new Integer[]{6, 5, 4, 3, 2, 1}, new Integer[]{1, 2, 3, 4, 5, 6});

        for (Map.Entry<Integer[], Integer[]> c : cases.entrySet()) {
            Integer[] input = Arrays.copyOf(c.getKey(), c.getKey().length);
            mergeSort(input, Integer::compare);
            if (!Arrays.equals(input, c.getValue())) {
                System.out.println("original: " + Arrays.toString(c.getKey()) + "\noutput: " + Arrays.toString(input));
                throw new RuntimeException();
            }
            System.out.println("merge passed: " + Arrays.toString(c.getKey()));
        }
    }
}
