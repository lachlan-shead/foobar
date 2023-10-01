import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Map;
import java.util.Deque;
import java.util.ArrayDeque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestStatesAndPaths {

    static BiConsumer<Solution.Fraction, Solution.Fraction> testFracComp = (exp, out) -> {
        assertEquals(exp.getNumer(), out.getNumer(), "numerators mismatch");
        assertEquals(exp.getDenom(), out.getDenom(), "denominators mismatch");
    };

    static BiConsumer<List<Solution.Path>, List<Solution.Path>> testPathListComp = (exp, out) -> {
        assertEquals(exp.size(), out.size());
        for (int i = 0; i < exp.size(); i++) assertTrue(exp.get(i).equals(out.get(i)));
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
        testFracComp.accept(f0, s.getTransitionChance(0));
        testFracComp.accept(f1, s.getTransitionChance(1));
        assertEquals(new HashSet<Integer>(List.of(0, 1)), s.getNeighbours());
        assertEquals(new HashSet<>(), new Solution.State(2, m[2]).getNeighbours());
    }

    @Test
    public void testPathConstructorsAndAdditions() {
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
        assertEquals(0, p1.getStates().get(0).getID());
        assertEquals(1, p1.getEnd().getID());
        assertEquals(List.of(0, 1), p1.getStates().stream().map(x -> x.getID()).collect(Collectors.toList()));
        testFracComp.accept(f1, p1.getProbability());
        assertEquals(new HashSet<Integer>(List.of(1, 2)), p1.getStates().get(0).getNeighbours());
        assertEquals(new HashSet<Integer>(List.of(0, 1, 2)), p1.getStates().get(1).getNeighbours());

        p1.add(new Solution.State(2, m[2]));
        f1.multiply(new Solution.Fraction(1, 12));
        assertEquals(3, p1.getNumStates());
        assertEquals(0, p1.getStates().get(0).getID());
        assertEquals(2, p1.getEnd().getID());
        assertEquals(List.of(0, 1, 2), p1.getStates().stream().map(x -> x.getID()).collect(Collectors.toList()));
        testFracComp.accept(f1, p1.getProbability());

        Solution.Path p2 = new Solution.Path(List.of(p1.getStates().get(0), p1.getStates().get(1), p1.getStates().get(2)));
        assertTrue(p1.equals(p2));
    }

    @Test
    public void testSubList() {
        int[][] m = new int[][] {
            {0, 1, 2, 0},
            {0, 3, 1, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 0}
        };
        Solution.State s0 = new Solution.State(0, m[0]);
        Solution.State s1 = new Solution.State(1, m[1]);
        Solution.State s2 = new Solution.State(2, m[2]);
        Solution.State s3 = new Solution.State(3, m[3]);

        Solution.Path p = new Solution.Path(s0);
        assertTrue(p.equals(p.copyFromState(s0)));
        p.add(s1);
        assertTrue(p.equals(p.copyFromState(s0)));
        assertTrue(new Solution.Path(s1).equals(p.copyFromState(s1)));
        p.add(s2);
        assertTrue(p.equals(p.copyFromState(s0)));
        assertTrue(new Solution.Path(List.of(s1, s2)).equals(p.copyFromState(s1)));
        assertTrue(new Solution.Path(s2).equals(p.copyFromState(s2)));
        p.add(s3);
        assertTrue(p.equals(p.copyFromState(s0)));
        assertTrue(new Solution.Path(List.of(s1, s2, s3)).equals(p.copyFromState(s1)));
        assertTrue(new Solution.Path(List.of(s2, s3)).equals(p.copyFromState(s2)));
        assertTrue(new Solution.Path(s3).equals(p.copyFromState(s3)));

        assertTrue(p.equals(new Solution.Path(List.of(s0, s1, s2, s3))));
    }

    // @Test <-- leave out for now...
    public void testFullSearchBehaviour() {
        int[][] m = new int[][] {
            {0, 1, 2, 0},
            {0, 3, 1, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 0}
        };
        Solution.State s0 = new Solution.State(0, m[0]);
        Solution.State s1 = new Solution.State(1, m[1]);
        Solution.State s2 = new Solution.State(2, m[2]);
        Solution.State s3 = new Solution.State(3, m[3]);

        Map<Integer, Solution.State> nonTerminalStates = Map.of(0, s0, 1, s1, 2, s2);

        Solution.Path p = new Solution.Path(s0);
        Deque<Solution.Path> toExpand = new ArrayDeque<>(List.of(p));

        toExpand.pop().branch(nonTerminalStates, toExpand);

        Solution.Path pi1 = (Solution.Path) s1.getIncomingPaths().toArray()[0];
        Solution.Path pi2 = (Solution.Path) s2.getIncomingPaths().toArray()[0];
        Solution.Path ei1 = new Solution.Path(List.of(s0, s1));
        Solution.Path ei2 = new Solution.Path(List.of(s0, s2));
        assertTrue(Solution.Path.getCycles().isEmpty());

        assertEquals(0, s0.getIncomingPaths().toArray().length);
        assertEquals(1, s1.getIncomingPaths().toArray().length);
        assertEquals(1, s2.getIncomingPaths().toArray().length);
        assertEquals(0, s3.getIncomingPaths().toArray().length);

        assertEquals(ei1.getNumStates(), pi1.getNumStates());
        assertEquals(ei1.getEnd(), pi1.getEnd());
        for (int j = 0; j < ei1.getNumStates(); j++)
            assertEquals(ei1.getStates().get(j), pi1.getStates().get(j));
        assertEquals(ei1.getEnd(), pi1.getEnd());
        assertEquals(ei2.getNumStates(), pi2.getNumStates());
        assertEquals(ei2.getEnd(), pi2.getEnd());
        for (int j = 0; j < ei2.getNumStates(); j++)
            assertEquals(ei2.getStates().get(j), pi2.getStates().get(j));
        assertEquals(ei2.getEnd(), pi2.getEnd());
        assertTrue(Solution.Path.getCycles().isEmpty());

        toExpand.pop().branch(nonTerminalStates, toExpand);

        assertEquals(0, s0.getIncomingPaths().toArray().length);
        assertEquals(1, s1.getIncomingPaths().toArray().length);
        assertEquals(2, s2.getIncomingPaths().toArray().length);
        assertEquals(0, s3.getIncomingPaths().toArray().length);

        Solution.Path pi3 = (Solution.Path) s2.getIncomingPaths().toArray()[0];
        Solution.Path ei3 = new Solution.Path(List.of(s0, s1, s2));
        Solution.Path pi4 = (Solution.Path) s2.getIncomingPaths().toArray()[1];
        Solution.Path ei4 = new Solution.Path(List.of(s0, s2));

        if (ei3.getNumStates() != pi3.getNumStates()) {
            Solution.Path tmp = ei3;
            ei3 = ei4;
            ei4 = tmp;
        }

        assertEquals(ei3.getNumStates(), pi3.getNumStates());
        assertEquals(ei3.getEnd(), pi3.getEnd());
        for (int j = 0; j < ei3.getNumStates(); j++)
            assertEquals(ei3.getStates().get(j), pi3.getStates().get(j));
        assertEquals(ei3.getEnd(), pi3.getEnd());

        assertEquals(ei4.getNumStates(), pi4.getNumStates());
        assertEquals(ei4.getEnd(), pi4.getEnd());
        for (int j = 0; j < ei4.getNumStates(); j++)
            assertEquals(ei4.getStates().get(j), pi4.getStates().get(j));
        assertEquals(ei4.getEnd(), pi4.getEnd());

        toExpand.pop().branch(nonTerminalStates, toExpand);
        assertEquals(2, Solution.Path.getCycles().size());
        assertEquals(1, Solution.Path.getCycles().get(1).getNumStates());
        assertEquals(s2, Solution.Path.getCycles().get(1).getStates().get(0));
        testPathListComp.accept(List.of(new Solution.Path(List.of(s0, s1, s2))), toExpand.stream().collect(Collectors.toList()));
        assertEquals(0, s0.getIncomingPaths().toArray().length);
        assertEquals(1, s1.getIncomingPaths().toArray().length);
        assertEquals(2, s2.getIncomingPaths().toArray().length);
        assertEquals(0, s3.getIncomingPaths().toArray().length);

        toExpand.pop().branch(nonTerminalStates, toExpand);
        assertTrue(toExpand.isEmpty());
        assertEquals(0, s0.getIncomingPaths().toArray().length);
        assertEquals(1, s1.getIncomingPaths().toArray().length);
        assertEquals(2, s2.getIncomingPaths().toArray().length);
        assertEquals(0, s3.getIncomingPaths().toArray().length);
    }
}
