import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestE2ELoopingCases {

    static BiConsumer<int[][], int[]> testLambda = (in, exp) -> {
        int[] out = Solution.solution(in);
        List<Integer> outList = IntStream.of(out).boxed().collect(Collectors.toList());
        List<Integer> expList = IntStream.of(exp).boxed().collect(Collectors.toList());
        assertEquals(expList, outList,
            "Real and expected mismatch");
    };


    @Test
    public void testOneLoopOneTerminalState() {
        testLambda.accept(new int[][] {
            {0, 1, 1},
            {1, 0, 0},
            {0, 0, 0}
        }, new int[] {1, 1});

        testLambda.accept(new int[][] {
            {0, 1, 0, 0},
            {1, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        }, new int[] {1, 1});

        testLambda.accept(new int[][] {
            {0, 2, 0, 1},
            {1, 0, 2, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        }, new int[] {2, 3, 5});
    }

    @Test
    public void testMultipleAndSelfLoops() {
        testLambda.accept(new int[][] {
            {1, 4, 1},
            {1, 0, 0},
            {0, 0, 0}
        }, new int[] {1, 1});

        testLambda.accept(new int[][] {
            {0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 3},
            {0, 0, 0, 0, 0}
        }, new int[] {1, 3, 4});
    }

}
