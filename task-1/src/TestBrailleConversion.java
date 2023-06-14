import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestBrailleConversion {
    
    @Test
    public static void testIndividualUpperAndLowerLetters() {
        String d = "d";
        String dInBraille = "100110";
        assertEquals(dInBraille, Solution.findBraille("d"));
    }


}
