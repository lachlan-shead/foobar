import java.util.AbstractMap.SimpleEntry;

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

    @Test
    public void testSimplifyingFractions() {
        assertEquals(new SimpleEntry<Integer, Integer>(1, 1),
            Solution.fracTimes(
                new SimpleEntry<Integer, Integer>(1, 5),
                new SimpleEntry<Integer, Integer>(5, 1))
        );
        assertEquals(new SimpleEntry<Integer, Integer>(1, 2),
            Solution.fracTimes(
                new SimpleEntry<Integer, Integer>(1, 5),
                new SimpleEntry<Integer, Integer>(5, 2))
        );
        assertEquals(new SimpleEntry<Integer, Integer>(2, 3),
            Solution.fracTimes(
                new SimpleEntry<Integer, Integer>(20, 15),
                new SimpleEntry<Integer, Integer>(5, 10))
        );
        assertEquals(new SimpleEntry<Integer, Integer>(187, 247),
            Solution.fracTimes(
                new SimpleEntry<Integer, Integer>(11, 13),
                new SimpleEntry<Integer, Integer>(17, 19))
        );
    }
    
}
