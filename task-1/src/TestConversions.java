import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestConversions {

    static Map<Character, Character> conversions = Solution.conversions;

    @Before
    void setup() {
        Solution.buildConversions();
    }

    @Test
    void testConversionValues() {
        assertEquals((char) 0b00000000, conversions.get(' '),
            "' ' had the wrong encoding");
        assertEquals((char) 0b00000001, conversions.get('!'),
            "'!' had the wrong encoding");
        assertEquals((char) 0b00111100, conversions.get('p'),
            "'p' had the wrong encoding");
    }

    @Test
    void testDotValues() {
        int p_expected[] = { -1, 1, 1, 1, 1, 0, 0 }; // ignore index 0
        char p = conversions.get('p');
        for (int i = 1; i <= Solution.BRAILLE_LEN; i++) {
            assertEquals(p_expected[i], Solution.getDotValue(p, i),
                "p was not parsed correctly");
        }
    }
}