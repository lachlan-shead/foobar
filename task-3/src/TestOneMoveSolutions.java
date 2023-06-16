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
        assertEquals(1, Solution.solution(19, 2), "ULU failed");
        assertEquals(1, Solution.solution(19, 4), "URU failed");
        assertEquals(1, Solution.solution(19, 9), "ULL failed");
        assertEquals(1, Solution.solution(19, 13), "URR failed");
        assertEquals(1, Solution.solution(19, 25), "DLL failed");
        assertEquals(1, Solution.solution(19, 29), "DRR failed");
        assertEquals(1, Solution.solution(19, 34), "DLD failed");
    }

}
