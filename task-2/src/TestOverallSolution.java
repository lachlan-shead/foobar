import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class TestOverallSolution {
    
    @Before
    public void setup() {
        Solution.setupSubtreeSizes();
    }

    @Test
    public void testGivenExample1() {
        int h = 5;
        int[] q = { 19, 14, 28 };
        int[] out = Solution.solution(h, q);
        int[] expected = { 21, 15, 29 };

        for (int i = 0; i < q.length; i++) {
            assertEquals(expected[i], out[i], "test 1 failed");
        }
    }

    @Test
    public void testGivenExample2() {
        int h = 3;
        int[] q = { 7, 3, 5, 1 };

        int[] out = Solution.solution(h, q);
        int[] expected = { -1, 7, 6, 3 };

        for (int i = 0; i < q.length; i++) {
            assertEquals(expected[i], out[i], "test 2 failed");
        }
    }

}


