import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestMoveEnum {
    
    @Test
    public void testEnumValues() {
        assertEquals(Solution.Move.BACK_FAR_LEFT.getDiff(), -17);
        assertEquals(Solution.Move.FORW_RIGHT.getDiff(), 10);
    }
}
