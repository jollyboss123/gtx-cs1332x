package gtxcs1332x.module1.recursion;

/**
 * @author jolly
 */
public class ComputePower {

    // O(n) T
    private static int power(int num, int pow) {
        if (pow == 0) {
            return 1;
        }
        return num * power(num, pow - 1);
    }

    // O(log n) T
    // tail recursion
    private static int repeatSquares(int num, int pow) {
        if (pow == 0) {
            return 1;
        }
        if (pow % 2 == 0) {
            int y = repeatSquares(num, pow/2);
            return y * y;
        } else {
            int y = repeatSquares(num, (pow-1)/2);
            return num * y * y;
        }
    }

    public static void main(String[] args) {
        System.out.println(power(2, 3));
        System.out.println(power(2, 5));
        System.out.println(repeatSquares(2, 3));
        System.out.println(repeatSquares(2, 5));
    }
}
