import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.stream.Collectors;

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

        public boolean equals(final Fraction f) {
            return (f.numer == this.numer) && (f.denom == this.denom);
        }

        public void set(final int numer, final int denom) {
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
        public Fraction multiply(final Fraction product) {
            this.set(this.numer * product.getNumer(), this.denom * product.getDenom());
            this.simplify();
            return this;
        }

        /** Add the current fraction by another (leaves input unchanged). */
        public Fraction add(final Fraction product) {
            this.set(this.numer * product.getDenom() + this.denom * product.getNumer(),
                this.denom * product.getDenom());
            this.simplify();
            return this;
        }

        /** Finds the GS value for a cycle with given probability a/b.
         * (No need to check whether |a/b| >= 1 by problem definition.)
         * Returns 1/(1-(a/b)) = b/(b-a). */
        public Fraction findCycleGSValue() {
            this.set(this.getDenom(), this.getDenom() - this.getNumer());
            return this;
        }

        /** Finds an int array with a common denominator for any number of fractions. */
        public static int[] consolidate(final List<Fraction> probabilities) {
            int denomLCM = 1;
            for (Fraction f : probabilities)
                denomLCM *= f.denom / GCD(denomLCM, f.denom);

            List<Integer> consolidated = new ArrayList<>();
            for (Fraction f : probabilities)
                consolidated.add(f.numer * denomLCM / f.denom);
            consolidated.add(denomLCM);
            return consolidated.stream().mapToInt(i -> i).toArray();
        }
    }

    static class State {
        private int ID;
        private Map<Integer, Fraction> neighbours;
        private Fraction visitProbability;

        State(int ID, int[] outbound) {
            this.ID = ID;
            this.neighbours = new HashMap<>();
            this.visitProbability = new Fraction(ID == 0 ? 1 : 0, 1);
            int sum = Arrays.stream(outbound).sum();
            if (sum == 0) return;
            for (int i = 0; i < outbound.length; i++) {
                if (outbound[i] > 0)
                    neighbours.put(i, new Fraction(outbound[i], sum));
            }
        }

        public int getID() {
            return this.ID;
        }

        public Set<Integer> getNeighbours() {
            return this.neighbours.keySet();
        }

        public Fraction getVisitProbability() {
            return this.visitProbability;
        }

        public void addToVisitProb(final Fraction f) {
            this.visitProbability.add(f);
        }

        /** Gets the chance of going from current state to another. */
        public Fraction getTransitionChance(final int nID) {
            return this.neighbours.get(nID);
        }
    }

    static class Path {
        private List<State> states;
        private int numStates;
        private Fraction probability;
        private State end;

        private static List<Path> cycles = new ArrayList<>();

        public Path(final State s) {
            this.states = new ArrayList<>(List.of(s));
            this.numStates = 1;
            this.probability = new Fraction(1, 1);
            this.end = s;
        }

        public Path(final List<State> states) {
            this(states.get(0));
            for (State s : states.subList(1, states.size()))
                this.add(s);
        }

        /** Add one more state to the path, disallowing finishing cycles. */
        public void add(final State s) {
            this.states.add(s);
            this.numStates++;
            this.probability.multiply(this.end.getTransitionChance(s.getID()));
            s.addToVisitProb(this.probability);
            this.end = s;
        }

        public List<State> getStates() {
            return this.states;
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

        public void branch(final Map<Integer, State> nonTerminalStates, Deque<Path> toExpand) {
            for (int i : this.end.getNeighbours()) {
                State s = nonTerminalStates.get(i);
                if (s == null) continue;
                if (this.states.contains(s)) {
                    Path p = this.copyFromState(s);
                    p.probability.multiply(this.end.getTransitionChance(s.getID()));
                    for (Path c : Path.cycles)
                        if (p.equals(c)) continue;
                    Path.cycles.add(p);
                }
                else {
                    Path p = this.deepCopy();
                    p.add(s);
                    toExpand.add(p);
                }
            }
        }

        public boolean equals(final Path p) {
            if (p.numStates != this.numStates) return false;
            for (int i = 0; i < p.numStates; i++) {
                if (p.states.get(i).getID() != this.states.get(i).getID()) return false;
            }
            return true;
        }

        public Path deepCopy() {
            Path p = new Path(this.states);
            p.probability = new Fraction(this.probability.getNumer(), this.probability.getDenom());
            return p;
        }

        public Path copyFromState(State s) {
            Path p = new Path(s);
            p.end = s;
            for (State t : this.states.subList(this.states.indexOf(s) + 1, this.numStates)) {
                p.probability.multiply(p.end.getTransitionChance(t.getID()));
                p.states.add(t);
                p.end = t;
                p.numStates++;
            }
            return p;
        }

        public static List<Path> getCycles() {
            return Path.cycles;
        }

        public static void mergeCycles() {
            for (int i = 0; i + 1 < Path.cycles.size(); i++) {
                Path p = Path.cycles.get(i);
                for (Path t : Path.cycles.subList(i + 1, Path.cycles.size())) {
                    List<State> nonOverlap = t.states.stream()
                        .filter(s -> !p.states.contains(s))
                        .collect(Collectors.toList());
                    if (nonOverlap.size() == t.states.size()) continue;
                    Path.cycles.remove(t);
                    p.states.addAll(nonOverlap);
                    p.probability.add(t.probability);
                }
            }
        }

        public static void findTrueProbabilities() {
            for (Path p : Path.cycles) {
                p.probability.findCycleGSValue();
                for (State s : p.getStates())
                    s.getVisitProbability().multiply(p.probability);
            }
        }
    }

    public static int[] solution(int[][] m) {
        /** Encode m as a list of states with fractional neighbour weightings */
        for (int i = 0; i < m.length; i++) {
            (Arrays.stream(m[i]).sum() == 0 ? terminalStates : nonTerminalStates)
                .put(i, new State(i, m[i]));
        }
        if (terminalStates.containsKey(0)) {
            List<Integer> terminalProbabilities = new ArrayList<>();
            terminalProbabilities.add(1);
            for (int i = 1; i < terminalStates.size(); i++) terminalProbabilities.add(0);
            terminalProbabilities.add(1);
            return terminalProbabilities.stream().mapToInt(i -> i).toArray();
        }

        /** Branch out from s0 in every way possible; store paths incoming
         * to each state by instance and cycles statically in Path. */
        Deque<Path> toExpand = new ArrayDeque<>();
        toExpand.add(new Path(nonTerminalStates.get(0)));
        while (!toExpand.isEmpty())
            toExpand.pop().branch(nonTerminalStates, toExpand);

        /** Deal with cycles... */
        Path.mergeCycles();
        Path.findTrueProbabilities();

        /** Find the weighted probability of reaching each terminal state,
         * then return them in requested format. */
        List<Fraction> terminalProbabilities = getTerminalProbabilities();
        return Fraction.consolidate(terminalProbabilities);
    }

    public static List<Fraction> getTerminalProbabilities() {
        List<Fraction> terminalProbabilities = new ArrayList<>();
        List<Integer> sortedTerminalStates = terminalStates.keySet().stream()
            .sorted().collect(Collectors.toList());
        for (int j : sortedTerminalStates) {
            State t = terminalStates.get(j);
            for (int i : nonTerminalStates.keySet()) {
                State s = nonTerminalStates.get(i);
                if (!s.getNeighbours().contains(j)) continue;
                t.getVisitProbability().add(
                    s.getTransitionChance(j).multiply(s.getVisitProbability()));
            }
            terminalProbabilities.add(t.getVisitProbability());
        }
        return terminalProbabilities;
    }

    public static void main(String[] a) {
        Solution.solution(new int[][] {
            {0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 3},
            {0, 0, 0, 0, 0}
        });
    }
}
