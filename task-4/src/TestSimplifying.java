import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestSimplifying {

    @Test
    public void testGCD() {
        assertEquals(1, Solution.euclideanGCD(1, 1));
        assertEquals(1, Solution.euclideanGCD(10, 17));
        assertEquals(2, Solution.euclideanGCD(10, 6));
        assertEquals(10, Solution.euclideanGCD(10, 30));
        assertEquals(Integer.MAX_VALUE, Solution.euclideanGCD(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
    
}
