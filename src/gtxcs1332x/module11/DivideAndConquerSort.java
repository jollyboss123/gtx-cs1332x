package gtxcs1332x.module11;

import java.util.*;

/**
 * @author jolly
 */
public class DivideAndConquerSort {

    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // base case
        if (arr.length == 1) {
            return;
        }
        // optional base case
//        if (arr.length == 2) {
//            if (comparator.compare(arr[0], arr[1]) > 0) {
//                T temp = arr[0];
//                T temp2 = arr[1];
//                arr[0] = temp2;
//                arr[1] = temp;
//            }
//        }

        // partitioning
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

    private static final Random random = new Random();

    public static <T> void quickSort(T[] arr, int start, int end, Comparator<T> comparator) {
        // base case
        if (end - start < 1) {
            return;
        }

        // choose pivot
        int pivotIdx = random.nextInt(start, end + 1);
        T pivotVal = arr[pivotIdx];
        swap(arr, start, pivotIdx);

        // partitioning
        int i = start + 1;
        int j = end;

        do {
            while (j >= i && comparator.compare(arr[i], pivotVal) <= 0) {
                i++;
            }
            while (j >= i && comparator.compare(arr[j], pivotVal) >= 0) {
                j--;
            }
            if (j > i) {
                swap(arr, i, j);
                i++;
                j--;
            }
        } while (/*i and j have not crossed*/ j >= i);

        // place pivot
        swap(arr, start, j);
        quickSort(arr, start, j - 1, comparator);
        quickSort(arr, j + 1, end, comparator);
    }

    private static <T> void swap(T[] arr, int i1, int i2) {
        T temp = arr[i1];
        T temp2 = arr[i2];
        arr[i1] =  temp2;
        arr[i2] = temp;
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

            input = Arrays.copyOf(c.getKey(), c.getKey().length);
            quickSort(input, 0, input.length - 1, Integer::compare);
            if (!Arrays.equals(input, c.getValue())) {
                System.out.println("original: " + Arrays.toString(c.getKey()) + "\noutput: " + Arrays.toString(input));
                throw new RuntimeException();
            }
            System.out.println("quick passed: " + Arrays.toString(c.getKey()));
        }
    }
}
