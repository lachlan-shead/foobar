import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestOneMoveSolutions {
    
    @Test
    public void trivial() {
        assertEquals(0, Solution.solution(25, 25),
            "Trivial case failed");
    }

    @Test
    public void givenTest1() {
        assertEquals(1, Solution.solution(19,36),
            "Given test 1 (19-36) failed");
    }

    @Test
    public void testAllOtherOneMoveSolutionsFrom19() {
        assertEquals(1, Solution.solution(19, 2), "Back2 left failed");
        assertEquals(1, Solution.solution(19, 4), "Back2 right failed");
        assertEquals(1, Solution.solution(19, 9), "Back1 left failed");
        assertEquals(1, Solution.solution(19, 13), "Back1 right failed");
        assertEquals(1, Solution.solution(19, 25), "For1 left failed");
        assertEquals(1, Solution.solution(19, 29), "For1 right failed");
        assertEquals(1, Solution.solution(19, 34), "For2 left failed");
    }

}
