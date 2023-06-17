import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestGivenCases {

    static BiConsumer<int[][], int[]> testLambda = (in, exp) -> {
        int[] out = Solution.solution(in);
        List<Integer> outList = IntStream.of(out).boxed().collect(Collectors.toList());
        List<Integer> expList = IntStream.of(exp).boxed().collect(Collectors.toList());
        assertEquals(expList, outList,
            "Real and expected mismatch");
    };

    @Test
    public void givenTest1() {
        testLambda.accept(new int[][]{
            {0, 2, 1, 0, 0},
            {0, 0, 0, 3, 4},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        }, new int[]{7, 6, 8, 21});
    }

    @Test
    public void givenTest2() {
        testLambda.accept(new int[][] {
            {0, 1, 0, 0, 0, 1},
            {4, 0, 0, 3, 2, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        }, new int[] {0, 3, 2, 9, 14});
    }
}
