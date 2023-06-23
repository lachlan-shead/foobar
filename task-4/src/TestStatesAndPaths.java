import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TestStatesAndPaths {

    static BiConsumer<Solution.Fraction, Solution.Fraction> testComp = (exp, out) -> {
        assertEquals(exp.getNumer(), out.getNumer(), "numerators mismatch");
        assertEquals(exp.getDenom(), out.getDenom(), "denominators mismatch");
    };

    @Test
    public void testStates() {
        int[][] m = new int[][] {
            {1, 5, 0},
            {0, 0, 1},
            {0, 0, 0}
        };
        Solution.State s = new Solution.State(0, m[0]);
        Solution.Fraction f0 = new Solution.Fraction(1, 6);
        Solution.Fraction f1 = new Solution.Fraction(5, 6);
        assertEquals(0, s.getID());
        assertFalse(s.isTerminal());
        testComp.accept(f0, s.getTransitionChance(0));
        testComp.accept(f1, s.getTransitionChance(1));
    }

    @Test
    public void testPaths() {
        int[][] m = new int[][] {
            {0, 4, 3},
            {6, 5, 1},
            {0, 0, 0}
        };
        Solution.Path p1 = new Solution.Path(List.of(
            new Solution.State(0, m[0]),
            new Solution.State(1, m[1])
        ));
        Solution.Fraction f1 = new Solution.Fraction(4, 7);
        assertEquals(2, p1.getNumStates());
        assertEquals(0, p1.getStart().getID());
        assertEquals(1, p1.getEnd().getID());
        assertFalse(p1.isCyclic());
        assertEquals(List.of(0, 1), p1.getStates().stream().map(x -> x.getID()).collect(Collectors.toList()));
        testComp.accept(f1, p1.getProbability());

        p1.add(new Solution.State(2, m[2]));
        f1.multiply(new Solution.Fraction(1, 12));
        assertEquals(3, p1.getNumStates());
        assertEquals(0, p1.getStart().getID());
        assertEquals(2, p1.getEnd().getID());
        assertFalse(p1.isCyclic());
        assertEquals(List.of(0, 1, 2), p1.getStates().stream().map(x -> x.getID()).collect(Collectors.toList()));
        testComp.accept(f1, p1.getProbability());
    }
}

