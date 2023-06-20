import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestFractions {
    static int expectedNumer;
    static int expectedDenom;

    static BiConsumer<Solution.Fraction, Solution.Fraction> testMultiply = (f1, f2) -> {
        f1.multiply(f2);
        assertEquals(expectedNumer, f1.getNumer(), "numerators mismatch");
        assertEquals(expectedDenom, f1.getDenom(), "denominators mismatch");
    };

    @Test
    public void testGetAndSet() {
        Solution.Fraction f = new Solution.Fraction(327, Integer.MAX_VALUE);
        assertEquals(f.getNumer(), 327);
        assertEquals(f.getDenom(), Integer.MAX_VALUE);
        f.set(0, 40);
        assertEquals(f.getNumer(), 0);
        assertEquals(f.getDenom(), 40);
    }

    @Test
    public void testSimplifyOnConstruction() {
        Solution.Fraction f = new Solution.Fraction(50, 20);
        assertEquals(5, f.getNumer());
        assertEquals(2, f.getDenom());
        f = new Solution.Fraction(36, 18);
        assertEquals(2, f.getNumer());
        assertEquals(1, f.getDenom());
        f = new Solution.Fraction(0, 18);
        assertEquals(0, f.getNumer());
        assertEquals(1, f.getDenom());
        f = new Solution.Fraction(6, 4);
        assertEquals(3, f.getNumer());
        assertEquals(2, f.getDenom());
    }

    @Test
    public void testGCD() {
        assertEquals(1, Solution.Fraction.GCD(1, 1));
        assertEquals(1, Solution.Fraction.GCD(17, 10));
        assertEquals(2, Solution.Fraction.GCD(10, 16));
        assertEquals(20, Solution.Fraction.GCD(20, 340));
        assertEquals(1, Solution.Fraction.GCD(340, 7));
        assertEquals(20, Solution.Fraction.GCD(20, 0));

        assertEquals(1, Solution.Fraction.GCD(1, 102, 204));
        assertEquals(102, Solution.Fraction.GCD(0, 102, 204));
        assertEquals(1, Solution.Fraction.GCD(15, 20, 24));
    }

    @Test
    public void testMultiplyingFractions() {
        expectedNumer = 1;
        expectedDenom = 1;
        Solution.Fraction f1 = new Solution.Fraction(1, 1);
        Solution.Fraction f2 = new Solution.Fraction(1, 1);
        testMultiply.accept(f1, f2);
        
        expectedNumer = 5;
        expectedDenom = 2;
        f1.set(5, 1);
        f2.set(1, 2);
        testMultiply.accept(f1, f2);

        expectedNumer = 17;
        expectedDenom = 7;
        f1.set(340, 7);
        f2.set(1, 20);
        testMultiply.accept(f1, f2);
    }
    
    @Test
    public void testAddingFractions() {
        Solution.Fraction f1 = new Solution.Fraction(4, 5);
        Solution.Fraction f2 = new Solution.Fraction(3, 10);
        f1.add(f2);
        assertEquals(11, f1.getNumer());
        assertEquals(10, f1.getDenom());

        f1.set(1, 5);
        f2.set(5, 1);
        f1.add(f2);
        assertEquals(26, f1.getNumer());
        assertEquals(5, f1.getDenom());

        f1.set(1, 2);
        f2.set(1, 2);
        f1.add(f2);
        assertEquals(1, f1.getNumer());
        assertEquals(1, f1.getDenom());
    }
}
