import java.util.Map;
import java.util.TreeMap;
import java.lang.StringBuilder;

public class Solution {
    public static int BRAILLE_LEN = 6;
    public static int MAX_PRE_BRAILLE_LEN = 100;
    public static Map<Character, Character> conversions;

    public static void main(String[] args) {
        System.out.println(solution(args[0]));
    }

    public static String solution(String s) {
        buildConversions();
        return findBraille(convertToBrailleInput(s));
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

    /** Returns the given string but lowercase, with ! before each capital.
     * 
     * @param   s   the string (containing uppercase, lowercase and/or spaces)
     * @return      s with ! before each original uppercase, now lowercase
     */
    public static String convertToBrailleInput(String s) {
        StringBuilder builder = new StringBuilder(MAX_PRE_BRAILLE_LEN);
        builder.append(s.toLowerCase());
        int numCaps = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                builder.insert(i + numCaps++, '!');
            }
        }
        return builder.toString();
    }

    /** Returns the Braille dots corresponding to the given string.
     * 
     * @param   s   a string whose characters come from the conversions keyset
     * @return      the string of 0's and 1's corresponding to each char of s
     */
    public static String findBraille(String s) {
        StringBuilder builder = new StringBuilder(s.length()*BRAILLE_LEN);
        char b;
        char braille[] = new char[BRAILLE_LEN];
        for (char c : s.toCharArray()) {
            b = conversions.get(c);
            for (int i = 0; i < BRAILLE_LEN; i++) {
                braille[i] = (char) ('0' + getDotValue(b, i + 1));
            }
            builder.append(braille);
        }
        return builder.toString();
    }

    /** Returns the value of a dot in a Braille character by bit shifting.
     * 
     * @param   b   a braille character (encoded in the last 6 digits of a byte)
     * @param   i   a dot number (from 1 to 6, left to right, inclusive)
     * @return      the value of b's i-th dot
     */
    public static int getDotValue(char b, int i) {
        int r = BRAILLE_LEN + 1 - i;
        return (((b >> r) << 1) < (b >> (r-1))) ? 1 : 0;
    }
}
