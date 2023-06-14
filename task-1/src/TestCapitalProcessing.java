import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestCapitalProcessing {
    public static String empty = "";
    public static String allSpaces = "     ";
    public static String maxLenLower = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx";
    public static String maxLenUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX";
    
    @Test
    public void testAllLower() {
        assertEquals("abcdefg", Solution.convertToBrailleInput("abcdefg"));
        assertEquals(maxLenLower, Solution.convertToBrailleInput(maxLenLower));
    }

    @Test
    public void testEmptyAndSpacedOnly() {
        assertEquals(empty, Solution.convertToBrailleInput(empty));
        assertEquals(allSpaces, Solution.convertToBrailleInput(allSpaces));
    }

    @Test
    public void testMaxLenUpper() {
        StringBuilder processedMaxLenUpperBld = new StringBuilder(100);
        processedMaxLenUpperBld.append(maxLenLower);
        for (int i = 0; i < 100; i += 2)
            processedMaxLenUpperBld.insert(i, '!');
        String processedMaxLenUpper = processedMaxLenUpperBld.toString();

        assertEquals(processedMaxLenUpper, Solution.convertToBrailleInput(maxLenUpper),
           "processed max length all upper case was different");
    }

    @Test
    public void testGivenCases() {
        String preProcess1 = "Braille";
        String postProcess1 = "!braille";
        String preProcess2 = "The quick brown fox jumps over the lazy dog";
        String postProcess2 = "!the quick brown fox jumps over the lazy dog";
        String preProcess3 = "code";
        String postProcess3 = "code";
        
        assertEquals(postProcess1, Solution.convertToBrailleInput(preProcess1));
        assertEquals(postProcess2, Solution.convertToBrailleInput(preProcess2));
        assertEquals(postProcess3, Solution.convertToBrailleInput(preProcess3));
    }

}
