import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestFinishCheck {
    
    @Test
    public void testSuccess() {
        List<Integer> srcN = new ArrayList<>(Arrays.asList(10, 17));
        List<Integer> destN = new ArrayList<>(Arrays.asList(10, 12, 17, 21, 33, 37, 42, 44));
        assertTrue(Solution.finished(srcN, destN), "(0,0) and (3,3) not recognised as 2-neighbours");
    }

    @Test
    public void testFailure() {
        List<Integer> srcN = new ArrayList<>(Arrays.asList(10, 17));
        List<Integer> destN = new ArrayList<>(Arrays.asList(11, 13, 18, 22, 34, 38, 43, 45));
        assertFalse(Solution.finished(srcN, destN), "(0,0) and (3,4) recognised as 2-neighbours");
    }

    @Test
    public void testContentsNotChanged() {
        List<Integer> srcN = new ArrayList<>(Arrays.asList(10, 17));
        List<Integer> destN = new ArrayList<>(Arrays.asList(11, 13, 18, 22, 34, 38, 43, 45));
        Solution.finished(srcN, destN);
        assertEquals(srcN.size(), 2, "Solution.finished changed srcN");
        assertEquals(destN.size(), 8, "Solution.finished changed destN");
    }
}
