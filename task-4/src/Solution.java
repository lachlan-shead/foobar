import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.function.ToIntFunction;

public class Solution {
    private static Map<Integer, State> terminalStates = new HashMap<>();
    private static Map<Integer, State> nonTerminalStates = new HashMap<>();

    static class Fraction {
        private int numer;
        private int denom;

        /** Reduce the fraction on construction if needed. */
        Fraction(int numer, int denom) {
            this.numer = numer;
            this.denom = denom;
            this.simplify();
        }

        /** Make this instance irreducible. */
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
        public static Fraction findCycleGSValue(final List<Fraction> cycleProbs) {
            Fraction f = new Fraction(0, 1);
            for (Fraction c : cycleProbs) f.add(c);
            f.set(f.getDenom(), f.getDenom() - f.getNumer());
            return f;
        }
    }

    static class State {
        private int ID;
        private boolean isTerminal;
        private Map<Integer, Fraction> neighbours;

        private static ToIntFunction<int[]> arrSum = (r) -> (Arrays.stream(r).sum());

        State(int ID, int[] mEntries) {
            this.ID = ID;
            this.neighbours = new HashMap<>();
            int sum = arrSum.applyAsInt(mEntries);
            this.isTerminal = (sum == 0);
            if (sum == 0) sum = 1;
            for (int i = 0; i < mEntries.length; i++)
                if (mEntries[i] > 0) neighbours.put(i, new Fraction(mEntries[i], sum));
        }

        public int getID() {
            return this.ID;
        }

        public boolean isTerminal() {
            return this.isTerminal;
        }

        /** Gets the chance of going from current state to another. */
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

        /** Add one more edge to the path. */
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

        /** Decide whether the path is a cycle. */
        public boolean isCyclic() {
            return (this.numStates > 0) && (this.getStart() == this.getEnd());
        }
    }

    public static int[] solution(int[][] m) {
        /** Encode m as a list of states with fractional neighbour weightings */
        for (int i = 0; i < m.length; i++) {
            State s = new State(i, m[i]);
            (s.isTerminal ? terminalStates : nonTerminalStates).put(i, s);
        }

        /** Find all paths to each node */

        return null;
    }
}