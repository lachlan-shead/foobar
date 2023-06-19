import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToIntFunction;

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

}