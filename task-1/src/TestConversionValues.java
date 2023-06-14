import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestConversionValues {

    @Before
    public void setup() {
        Solution.buildConversions();
    }

    @Test
    public void testLength() {
        int expectedKeySetSize = 28;
        assertEquals(Solution.conversions.entrySet().size(), expectedKeySetSize);
    }

    @Test
    public void testConversionValues() {
        assertEquals((char) 0b00000000, Solution.conversions.get(' '),
            "' ' had the wrong encoding");
        assertEquals((char) 0b00000001, Solution.conversions.get('!'),
            "'!' had the wrong encoding");
        assertEquals((char) 0b00111100, Solution.conversions.get('p'),
            "'p' had the wrong encoding");
    }

    @Test
    public void testDotValues() {
        int pExpected[] = { 1, 1, 1, 1, 0, 0 };
        char p = Solution.conversions.get('p');
        assertEquals(p, (char) 0b00111100);
        for (int i = 0; i < 6; i++) {
            assertEquals(pExpected[i], Solution.getDotValue(p, i+1),
                "char was not parsed correctly");
        }
    }
}