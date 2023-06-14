import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestCapitalProcessing {
    public static String empty = "";
    public static String allSpaces = "     ";
    public static String maxLenLower = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx";
    public static String maxLenUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX";
    
    @Test
    public void testAllLower() {
        assertEquals("abcdefg", Solution.processCapitals("abcdefg"));
        assertEquals(maxLenLower, Solution.processCapitals(maxLenLower));
    }

    @Test
    public void testEmptyAndSpacedOnly() {
        assertEquals(empty, Solution.processCapitals(empty));
        assertEquals(allSpaces, Solution.processCapitals(allSpaces));
    }

    @Test
    public void testMaxLenUpper() {
        StringBuilder processedMaxLenUpperBld = new StringBuilder(100);
        processedMaxLenUpperBld.append(maxLenLower);
        for (int i = 0; i < 100; i += 2)
            processedMaxLenUpperBld.insert(i, '!');
        String processedMaxLenUpper = processedMaxLenUpperBld.toString();

        assertEquals(processedMaxLenUpper, Solution.processCapitals(maxLenUpper),
           "processed max length all upper case was different");
    }

    @Test
    public void testGivenCases() {
        String preProcess1 = "Braille";
        String postProcess1 = "!braille";
        assertEquals(postProcess1, Solution.processCapitals(preProcess1));


        String preProcess2 = "The quick brown fox jumps over the lazy dog";
        String postProcess2 = "!the quick brown fox jumps over the lazy dog";
        assertEquals(postProcess2, Solution.processCapitals(preProcess2));


        String preProcess3 = "code";
        String postProcess3 = "code";
        assertEquals(postProcess3, Solution.processCapitals(preProcess3));
    }

}
