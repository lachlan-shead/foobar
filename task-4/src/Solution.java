import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToIntFunction;
import java.util.AbstractMap.SimpleEntry;

/** Store fractions using SimpleEntry<a, b> -> a/b. */

public class Solution {

    private static ToIntFunction<int[]> sum = (r) -> (Arrays.stream(r).sum());
    public static List<Boolean> terminalStates = new ArrayList<>();
    public static List<Integer> denominators = new ArrayList<>();

    public static int[] solution(int[][] m) {
        getRowData(m);
        return null;
    }

    /** Creates a list denoting which state indices are terminal. */
    public static void getRowData(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            int s = sum.applyAsInt(m[i]);
            denominators.add(s == 0 ? 1 : s);
            terminalStates.add(s == 0);
        }
    }

    /** Reduces the product of two fractions: (a/b) * (c/d) = (a/d) * (b/c),
     * where the original fractions were reduced. */
    public static SimpleEntry<Integer, Integer> fracTimes(SimpleEntry<Integer, Integer> p, SimpleEntry<Integer, Integer> q) {
            int frac = euclideanGCD(p.getKey(), q.getValue()) * euclideanGCD(q.getKey(), p.getValue());
            return new SimpleEntry<>((p.getKey() * q.getKey()) / frac, (p.getValue() * q.getValue()) / frac);
    }

    /** Finds the GCD of two integers. */
    public static int euclideanGCD(int a, int b) {
        while (b != 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }

}