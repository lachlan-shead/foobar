import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestSolution {
    
    @Test
    public void testGivenCases() {
        String case1 = "Braille";
        String solution1 = "000001110000111010100000010100111000111000100010";
        String case2 = "The quick brown fox jumps over the lazy dog";
        String solution2 = "0000010111101100101000100000001111101010010101" +
            "001001001010000000001100001110101010100101111011100000001101001010" +
            "101011010000000101101010011011001111000111000000001010101110011000" +
            "101110100000000111101100101000100000001110001000001010111011110000" +
            "00100110101010110110";
        String case3 = "code";
        String solution3 = "100100101010100110100010";

        assertEquals(solution1, Solution.solution(case1));
        assertEquals(solution2, Solution.solution(case2));
        assertEquals(solution3, Solution.solution(case3));
    }
}
