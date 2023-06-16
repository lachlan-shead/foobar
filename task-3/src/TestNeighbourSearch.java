import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class TestNeighbourSearch {
        static BiConsumer<List<Integer>, List<Integer>> testLambda = (in, exp) -> {
            List<Integer> out = Solution.getUnvisitedNeighbours(in);
            String inStr = in.stream()
                        .map(String::valueOf).collect(Collectors.joining(""));
            for (int m : exp) {
                assertTrue(out.contains(m),
                    Integer.toString(m) + " not recognised for coords " + inStr);
                assertTrue(Solution.visited[m],
                    Integer.toString(m) + "not counted as visited");
            }
            assertEquals(exp.size(), out.size(),
                "illegal move found for coords" + inStr);
        };
    
    static boolean[] copyVisited() {
        boolean visitedCopy[] = new boolean[64];
        for (int i = 0; i < 64; i++) visitedCopy[i] = Solution.visited[i];
        return visitedCopy;
    }

    @Test
    public void testNeighboursOnCorners() {
        testLambda.accept(List.of(0), List.of(10, 17));
        testLambda.accept(List.of(7), List.of(13, 22));
        testLambda.accept(List.of(56), List.of(41, 50));
        testLambda.accept(List.of(63), List.of(46, 53));

        for (int i = 0; i < 64; i++) {
            Solution.visited[i] = false;
        }
        testLambda.accept(List.of(0, 7, 56, 63), List.of(
            10, 17, 13, 22,
            41, 50, 46, 53));
    }

    @Test
    public void testNeighboursOnOutermostLayers() {
        testLambda.accept(List.of(4), List.of(10, 19, 21, 14));
        testLambda.accept(List.of(24), List.of(9, 18, 34, 41));
        testLambda.accept(List.of(59), List.of(49, 42, 44, 53));
        testLambda.accept(List.of(39), List.of(22, 29, 45, 54));

        for (int i = 0; i < 64; i++) {
            Solution.visited[i] = false;
        }
        testLambda.accept(List.of(4, 24, 59, 39), List.of(
            10, 19, 21, 14,
            9, 18, 34, 41,
            49, 42, 44, 53,
            22, 29, 45, 54));
    }

    @Test
    public void testNeighboursOnSecondmostOuterLayersWithOverlap() {
        testLambda.accept(List.of(12), List.of(2, 18, 27, 29, 22, 6));
        testLambda.accept(List.of(25), List.of(8, 10, 19, 35, 40, 42));
        testLambda.accept(List.of(52), List.of(37, 46, 58, 62));
        testLambda.accept(List.of(30), List.of(15, 13, 20, 36, 45, 47));
    }

    @Test
    public void testCentre() {
        testLambda.accept(List.of(19), List.of(2, 4, 9, 13, 25, 29, 34, 36));
    }
}
