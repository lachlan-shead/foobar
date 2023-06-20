import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToIntFunction;

public class Solution {
    private static ToIntFunction<int[]> sum = (r) -> (Arrays.stream(r).sum());
    public static List<Boolean> terminalStates = new ArrayList<>();
    public static List<Integer> denominators = new ArrayList<>();

    static class Fraction {
        private int numer;
        private int denom;

        /** Reduce the fraction on construction if needed. */
        Fraction(int numer, int denom) {
            this.numer = numer;
            this.denom = denom;
            this.simplify();
        }

        public void simplify() {
            int gcd = GCD(this.numer, this.denom);
            this.numer /= gcd;
            this.denom /= gcd;
        }

        /** Return the GCD between two integers. */
        public static int GCD(int a, int b) {
            if (b == 0) return a;
            return GCD(b, a % b);
        }

        /** Return the GCD between any number of integers. */
        public static int GCD(int... arr) {
            int gcd = arr[0];
            for (int i = 1; i < arr.length; i++) gcd = GCD(gcd, arr[i]);
            return gcd;
        }

        public void set(int numer, int denom) {
            this.numer = numer;
            this.denom = denom;
        }

        public int getNumer() {
            return this.numer;
        }

        public int getDenom() {
            return this.denom;
        }

        /** Multiply the current fraction by another (leaves input unchanged). */
        public void multiply(final Fraction product) {
            this.set(this.numer * product.getNumer(), this.denom * product.getDenom());
            this.simplify();
        }

        /** Add the current fraction by another (leaves input unchanged). */
        public void add(final Fraction product) {
            this.set(this.numer * product.getDenom() + this.denom * product.getNumer(),
                this.denom * product.getDenom());
            this.simplify();
        }
    }

    public static int[] solution(int[][] m) {
        getRowData(m);
        return null;
    }

    /** Creates a list denoting which state indices are terminal. */
    public static void getRowData(final int[][] m) {
        for (int i = 0; i < m.length; i++) {
            int s = sum.applyAsInt(m[i]);
            denominators.add(s == 0 ? 1 : s);
            terminalStates.add(s == 0);
        }
    }

    /** Finds the geometric series value of a cycle with probability given by a fraction.
     *  
    */
    public static Fraction findCycleGSValue(Fraction prob) {
        return new Fraction(prob.getDenom(), prob.getNumer() - prob.getDenom());
    }

}