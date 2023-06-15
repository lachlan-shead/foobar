import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestComplexSolutions {
    
    @Test
    public void givenTest2() {
        assertEquals(3, Solution.solution(0,1),
            "Given test 2 (0-1) failed");
    }

    @Test
    public void testOtherFirstRowCombinations() {
        assertEquals(2, Solution.solution(0, 2), "0-2 failed");
        assertEquals(3, Solution.solution(0, 3), "0-3 failed");
        assertEquals(2, Solution.solution(0, 4), "0-4 failed");
        assertEquals(3, Solution.solution(0, 5), "0-5 failed");
    }

    @Test
    public void testFinalRowCombinations() {
        assertEquals(3, Solution.solution(63, 62), "63-62 failed");
        assertEquals(2, Solution.solution(63, 61), "63-61 failed");
        assertEquals(3, Solution.solution(63, 60), "63-60 failed");
        assertEquals(2, Solution.solution(63, 59), "63-59 failed");
        assertEquals(3, Solution.solution(63, 58), "63-58 failed");
    }
}
