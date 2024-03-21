package gtxcs1332x.module1;

/**
 * @author jolly
 */
public class Recursion {
    private static final int[] a = new int[]{1,7,3,5,2,8,10,24,-1,-5,4};

    private static int isValidSubsequence(int[] arr, int curr, int lastIdx, int aCurr) {
        if (curr < arr.length) {
            for (int i = aCurr; i < a.length; i++) {
                if (a[i] == arr[curr]) {
                    if (i < lastIdx) {
                        return curr;
                    } else {
                        System.out.printf("current: %d main current: %d%n", arr[curr], a[i]);
                        return isValidSubsequence(arr, curr + 1, i, i);
                    }
                }
            }
        }
        return curr;
    }

    public static void main(String[] args) {
        int[] first = new int[]{3,2,-1,-5,4};
        int count = isValidSubsequence(first, 0, 0, 0);
        System.out.println(count == first.length);
        int[] sec = new int[]{1,7,10,8};
        count = isValidSubsequence(sec, 0, 0, 0);
        System.out.println(count == sec.length);
    }
}
