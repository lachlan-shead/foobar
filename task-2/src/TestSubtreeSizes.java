import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class TestSubtreeSizes {
    
    @Before
    public void setup() {
        Solution.setupSubtreeSizes();
    }

    @Test
    public void testHeightValues() {
        for (int i = 0; i <= 30; i++) {
            assertEquals(Math.pow(2,i)-1, Solution.SUBTREE_SIZE_BY_HEIGHT[i],
                "Subset height mismatch on entry " + Integer.toString(i));
        }
    }

}
