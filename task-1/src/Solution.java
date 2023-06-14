import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static int BRAILLE_LEN = 6;
    public static Map<Character, Character> conversions;

    public static void main(String[] args) {
        System.out.println(solution(args[0]));
    }

    public static String solution(String s) {
        return s;
    }

    /** Stores char-to-braille conversions in a static map. */
    public static void buildConversions() {
        conversions = new TreeMap<Character, Character>();
        conversions.put(' ', (char) 0b00000000);
        conversions.put('!', (char) 0b00000001);
        conversions.put('a', (char) 0b00100000);
        conversions.put('b', (char) 0b00110000);
        conversions.put('c', (char) 0b00100100);
        conversions.put('d', (char) 0b00100110);
        conversions.put('e', (char) 0b00100010);
        conversions.put('f', (char) 0b00110100);
        conversions.put('g', (char) 0b00110110);
        conversions.put('h', (char) 0b00110010);
        conversions.put('i', (char) 0b00010100);
        conversions.put('j', (char) 0b00010110);
        conversions.put('k', (char) 0b00101000);
        conversions.put('l', (char) 0b00111000);
        conversions.put('m', (char) 0b00101100);
        conversions.put('n', (char) 0b00101110);
        conversions.put('o', (char) 0b00101010);
        conversions.put('p', (char) 0b00111100);
        conversions.put('q', (char) 0b00111110);
        conversions.put('r', (char) 0b00111010);
        conversions.put('s', (char) 0b00011100);
        conversions.put('t', (char) 0b00011110);
        conversions.put('u', (char) 0b00101001);
        conversions.put('v', (char) 0b00111001);
        conversions.put('w', (char) 0b00010111);
        conversions.put('x', (char) 0b00101101);
        conversions.put('y', (char) 0b00101111);
        conversions.put('z', (char) 0b00101011);
    }

    /** Returns the value of a dot in a Braille character by bit shifting.
     * @param   b   a braille character (encoded in 6 digits of a byte)
     * @param   i   a dot number (from 1 to 6, left to right, inclusive)
     * @return      the value of b's i-th dot
     */
    public static int getDotValue(char b, int i) {
        int r = BRAILLE_LEN + 1 - i;
        return (((b >> r) << 1) < (b >> (r-1))) ? 1 : 0;
    }
}