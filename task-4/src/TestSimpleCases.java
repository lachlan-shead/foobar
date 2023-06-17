import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestSimpleCases {

    static BiConsumer<int[][], int[]> testLambda = (in, exp) -> {
        int[] out = Solution.solution(in);
        List<Integer> outList = IntStream.of(out).boxed().collect(Collectors.toList());
        List<Integer> expList = IntStream.of(exp).boxed().collect(Collectors.toList());
        assertEquals(expList, outList,
            "Real and expected mismatch");
    };

    @Test
    public void testCaseWithOneState() {
        testLambda.accept(new int[][]{{0}}, new int[]{1, 1});
    }

    @Test
    public void testSimpleTwoStateCases() {
        testLambda.accept(new int[][]{{0, 0}, {0, 0}}, new int[]{1, 0, 1});
        testLambda.accept(new int[][]{{0, 0}, {2, 5}}, new int[]{1, 1});
        testLambda.accept(new int[][]{{0, 1}, {0, 0}}, new int[]{1, 1});
    }
}