import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestBrailleConversion {
    
    @Before
    public void setup() {
        Solution.buildConversions();
    }

    @Test
    public void testEmptyAndSpacedOnly() {
        int spaceCount = 3;
        String brailleForSpaces = Solution.findBraille("   ");

        assertEquals("", Solution.findBraille(""));
        assertEquals(spaceCount*6, brailleForSpaces.length());
        for (char c : brailleForSpaces.toCharArray()) {
            assertEquals(c, '0', "Braille for spaces only was not all 0's");
        }
    }

    @Test
    public void testIndividualUpperAndLowerLetters() {
        String d = "d";
        String dInBraille = "100110";
        String dUpperConverted = "!d";
        String dUpperConvertedInBraille = "000001100110";

        assertEquals(dInBraille, Solution.findBraille(d));
        assertEquals(dUpperConvertedInBraille, Solution.findBraille(dUpperConverted));
    }

    @Test
    public void testGivenCases() {
        String task1 = "!braille";
        String task1InBraille = "000001110000111010100000010100111000111000100010";
        String task2 = "!the quick brown fox jumps over the lazy dog";
        String task2InBraille = "0000010111101100101000100000001111101010010101" +
            "001001001010000000001100001110101010100101111011100000001101001010" +
            "101011010000000101101010011011001111000111000000001010101110011000" +
            "101110100000000111101100101000100000001110001000001010111011110000" +
            "00100110101010110110";
        String task3 = "code";
        String task3InBraille = "100100101010100110100010";

        assertEquals(task1InBraille, Solution.findBraille(task1));
        assertEquals(task2InBraille, Solution.findBraille(task2));
        assertEquals(task3InBraille, Solution.findBraille(task3));
    }

}
