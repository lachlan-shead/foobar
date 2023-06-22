import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToIntFunction;

public class Solution {
    public static List<State> states = new ArrayList<>();

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
            int gcd = 0;
            for (int a : arr) gcd = GCD(gcd, a);
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

        /** Finds the GS value for a cycle with given probability a/b.
         * (No need to check whether |a/b| >= 1 by problem definition.)
         * Returns 1/(1-(a/b)) = b/(b-a). */
        public static Fraction findCycleGSValue(final Fraction prob) {
            return new Fraction(prob.getDenom(), prob.getNumer() - prob.getDenom());
        }
    }

    static class State {
        private int ID;
        private boolean isTerminal;
        private List<Fraction> neighbours;

        private static ToIntFunction<int[]> arrSum = (r) -> (Arrays.stream(r).sum());

        State(int ID, int[] mEntries) {
            this.ID = ID;
            int sum = arrSum.applyAsInt(mEntries);
            this.isTerminal = (sum == 0);
            this.neighbours = new ArrayList<>();
            if (sum == 0) sum = 1;
            for (int i = 0; i < mEntries.length; i++)
                neighbours.add(new Fraction(mEntries[i], sum));
        }

        public int getID() {
            return this.ID;
        }

        public boolean isTerminal() {
            return isTerminal;
        }

        public Fraction getTransitionChance(int nID) {
            return this.neighbours.get(nID);
        }
    }

    static class Path {
        private List<State> states;
        private int numStates;
        private Fraction probability;
        private State end;

        public Path(final List<State> states) {
            this.states = new ArrayList<>();
            this.numStates = 0;
            this.probability = new Fraction(1, 1);
            for (State s : states) this.add(s);
        }

        public void add(final State s) {
            if (numStates > 0)
                this.probability.multiply(this.end.getTransitionChance(s.getID()));
            this.states.add(s);
            this.end = s;
            this.numStates++;
        }

        public List<State> getStates() {
            return this.states;
        }

        public State getStart() {
            return this.states.get(0);
        }

        public State getEnd() {
            return this.end;
        }

        public int getNumStates() {
            return this.numStates;
        }

        public Fraction getProbability() {
            return this.probability;
        }

        public boolean isCyclic() {
            return (this.numStates > 0) && (this.getStart() == this.getEnd());
        }
    }

    public static int[] solution(int[][] m) {
        for (int i = 0; i < m.length; i++)
            states.add(new State(i, m[i]));
        return null;
    }
}