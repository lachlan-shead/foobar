import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class TestE2ESmallAcyclicCases {

    @Test
    public void testAcyclicCaseWithOneState() {
        assertArrayEquals(new int[]{1, 1}, Solution.solution(new int[][]{{0}}));
    }


    @Test
    public void isolate() {
        assertArrayEquals(new int[]{1, 1}, Solution.solution(new int[][]{{0, 1}, {0, 0}}));
    }

    @Test
    public void testSimpleTwoStateCases() {
        assertArrayEquals(new int[]{1, 1}, Solution.solution(new int[][]{{0, 0}, {2, 0}}));
    }

    @Test
    public void test2() {
        assertArrayEquals(new int[]{1, 0, 1}, Solution.solution(new int[][]{{0, 0}, {0, 0}}));
    }

    @Test
    public void test3() {
        assertArrayEquals(new int[]{1, 1, 2}, Solution.solution(new int[][]{{0, 1, 1}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void test4() {
        assertArrayEquals(new int[]{2, 1, 3}, Solution.solution(new int[][]{{0, 2, 1}, {0, 0, 0}, {0, 0, 0}}));
    }
}
