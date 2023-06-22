import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TestStatesAndPaths {

    @Test
    public void testStates() {
        int[][] m = new int[][] {
            {1, 5, 0},
            {0, 0, 1},
            {0, 0, 0}
        };
        Solution.State p0 = new Solution.State(0, 6, m);
        Solution.Fraction f0 = new Solution.Fraction(1, 6);
        Solution.Fraction f1 = new Solution.Fraction(5, 6);
        Solution.Fraction f2 = new Solution.Fraction(0, 1);
        assertEquals(0, p0.getID());
        assertFalse(p0.isTerminal());
        assertEquals(f0.getNumer(), p0.getTransitionChance(0).getNumer());
        assertEquals(f0.getDenom(), p0.getTransitionChance(0).getDenom());
        assertEquals(f1.getNumer(), p0.getTransitionChance(1).getNumer());
        assertEquals(f1.getDenom(), p0.getTransitionChance(1).getDenom());
        assertEquals(f2.getNumer(), p0.getTransitionChance(2).getNumer());
        assertEquals(f2.getDenom(), p0.getTransitionChance(2).getDenom());
    }

    @Test
    public void testPaths() {
        int[][] m = new int[][] {
            {0, 4, 3},
            {6, 5, 1},
            {0, 0, 0}
        };
        Solution.Path p1 = new Solution.Path(List.of(
            new Solution.State(0, 7, m),
            new Solution.State(1, 12, m)
        ));
        Solution.Fraction f1 = new Solution.Fraction(4, 7);
        assertEquals(2, p1.getNumStates());
        assertEquals(0, p1.getStart().getID());
        assertEquals(1, p1.getEnd().getID());
        assertFalse(p1.isCyclic());
        assertEquals(List.of(0, 1), p1.getStates().stream().map(x -> x.getID()).collect(Collectors.toList()));
        assertEquals(f1.getNumer(), p1.getProbability().getNumer());
        assertEquals(f1.getDenom(), p1.getProbability().getDenom());
        
        p1.add(new Solution.State(2, 0, m));
        f1.multiply(new Solution.Fraction(1, 12));
        assertEquals(3, p1.getNumStates());
        assertEquals(0, p1.getStart().getID());
        assertEquals(2, p1.getEnd().getID());
        assertFalse(p1.isCyclic());
        assertEquals(List.of(0, 1, 2), p1.getStates().stream().map(x -> x.getID()).collect(Collectors.toList()));
        assertEquals(f1.getNumer(), p1.getProbability().getNumer());
        assertEquals(f1.getDenom(), p1.getProbability().getDenom());
    }
}

