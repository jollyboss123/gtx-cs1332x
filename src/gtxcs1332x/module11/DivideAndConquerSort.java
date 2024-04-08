package gtxcs1332x.module11;

import java.util.*;
import java.util.concurrent.BlockingQueue;

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

    public static void lsdRadixSort(Integer[] arr) {
        Queue<Integer>[] buckets = new Queue[19]; // for negative and positive ints
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int k = digitCount(max);

        int base = 10;
        for (int i = 0; i < k; i++) {
            for (int value : arr) {
                int n;
                if (i == 0) {
                    n = value % 10;
                } else {
                    int pow = (int) Math.pow(base, i);
                    n = (value / pow) % 10;
                }
                buckets[n].offer(value);
            }
            int idx = 0;
            for (Queue<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[idx++] = bucket.remove();
                }
            }
        }
    }

    /**
     * Gets the digit count from a number by dividing the number by powers of two.
     *
     * @param number input number.
     * @return length of number.
     */
    private static int digitCount(int number) {
        int len = 1;
        if (number >= 100_000_000) {
            len += 8;
            number /= 100_000_000;
        }
        if (number >= 10_000) {
            len += 4;
            number /= 10_000;
        }
        if (number >= 100) {
            len += 2;
            number /= 100;
        }
        if (number >= 10) {
            len += 1;
        }
        return len;
    }

    private static <T> void swap(T[] arr, int i1, int i2) {
        T temp = arr[i1];
        arr[i1] =  arr[i2];
        arr[i2] = temp;
    }

    public static void main(String[] args) {
        Map<Integer[], Integer[]> cases = new HashMap<>();
        cases.put(new Integer[]{5, 7, 8, 9, 3, 6, 4, 6, 2}, new Integer[]{2, 3, 4, 5, 6, 6, 7, 8, 9});
        cases.put(new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{1, 2, 3, 4, 5, 6});
        cases.put(new Integer[]{6, 5, 4, 3, 2, 1}, new Integer[]{1, 2, 3, 4, 5, 6});
        cases.put(new Integer[]{5268, 8362, 8010, 2229, 7651, 8555, 347, 4968, 7278, 9742}, new Integer[]{347, 2229, 4968, 5268, 7278, 7651, 8010, 8362, 8555, 9742});

        for (Map.Entry<Integer[], Integer[]> c : cases.entrySet()) {
            Integer[] input = Arrays.copyOf(c.getKey(), c.getKey().length);
            mergeSort(input, Integer::compare);
            verify(c, input);
            System.out.println("merge passed: " + Arrays.toString(c.getKey()));

            input = Arrays.copyOf(c.getKey(), c.getKey().length);
            quickSort(input, 0, input.length - 1, Integer::compare);
            verify(c, input);
            System.out.println("quick passed: " + Arrays.toString(c.getKey()));

            input = Arrays.copyOf(c.getKey(), c.getKey().length);
            lsdRadixSort(input);
            verify(c, input);
            System.out.println("lsd radix passed: " + Arrays.toString(c.getKey()));
        }
    }

    private static void verify(Map.Entry<Integer[], Integer[]> c, Integer[] input) {
        if (!Arrays.equals(input, c.getValue())) {
            System.out.println("original: " + Arrays.toString(c.getKey()) + "\noutput: " + Arrays.toString(input));
            throw new RuntimeException();
        }
    }
}
