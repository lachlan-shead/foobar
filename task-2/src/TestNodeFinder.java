import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class TestNodeFinder {
    
    @Before
    public void setup() {
        Solution.setupSubtreeSizes();
    }

    @Test
    public void bruteForceSmallTrees() {
        int numTests = 11;

        Map<Integer, SimpleEntry<Integer, Integer>> testIn = new TreeMap<>();
        testIn.put(1, new SimpleEntry<>(1,1));
        testIn.put(2, new SimpleEntry<>(2,1));
        testIn.put(3, new SimpleEntry<>(2,2));
        testIn.put(4, new SimpleEntry<>(2,3));
        testIn.put(5, new SimpleEntry<>(3,1));
        testIn.put(6, new SimpleEntry<>(3,2));
        testIn.put(7, new SimpleEntry<>(3,3));
        testIn.put(8, new SimpleEntry<>(3,4));
        testIn.put(9, new SimpleEntry<>(3,5));
        testIn.put(10, new SimpleEntry<>(3,6));
        testIn.put(11, new SimpleEntry<>(3,7));
        
        Map<Integer, SimpleEntry<Integer, Solution.Move>> testOut = new TreeMap<>();
        testOut.put(1, new SimpleEntry<>(1, Solution.Move.NONE));
        testOut.put(2, new SimpleEntry<>(1, Solution.Move.LEFT));
        testOut.put(3, new SimpleEntry<>(1, Solution.Move.RIGHT));
        testOut.put(4, new SimpleEntry<>(2, Solution.Move.NONE));
        testOut.put(5, new SimpleEntry<>(1, Solution.Move.LEFT));
        testOut.put(6, new SimpleEntry<>(1, Solution.Move.RIGHT));
        testOut.put(7, new SimpleEntry<>(2, Solution.Move.LEFT));
        testOut.put(8, new SimpleEntry<>(1, Solution.Move.LEFT));
        testOut.put(9, new SimpleEntry<>(1, Solution.Move.RIGHT));
        testOut.put(10, new SimpleEntry<>(2, Solution.Move.RIGHT));
        testOut.put(11, new SimpleEntry<>(3, Solution.Move.NONE));

        for (int i = 1; i <= numTests; i++) {
            SimpleEntry<Integer, Integer> in = testIn.get(i);
            SimpleEntry<Integer, Solution.Move> out = testOut.get(i);
            assertEquals(Solution.findNode(in.getKey(), in.getValue()), out,
                "findNode failed with h=" + Integer.toString(in.getKey())
                + " and r=" + Integer.toString(in.getValue()));
        }
    }

}
