import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestFractions {

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
        assertTrue(f.equals(new Solution.Fraction(5, 2)));
        f = new Solution.Fraction(36, 18);
        assertTrue(f.equals(new Solution.Fraction(2, 1)));
        f = new Solution.Fraction(0, 18);
        assertTrue(f.equals(new Solution.Fraction(0, 1)));
        f = new Solution.Fraction(6, 4);
        assertTrue(f.equals(new Solution.Fraction(3, 2)));
    }

    @Test
    public void testGCD() {
        assertEquals(1, Solution.Fraction.GCD(1, 1));
        assertEquals(1, Solution.Fraction.GCD(17, 10));
        assertEquals(2, Solution.Fraction.GCD(10, 16));
        assertEquals(20, Solution.Fraction.GCD(20, 340));
        assertEquals(1, Solution.Fraction.GCD(340, 7));
        assertEquals(20, Solution.Fraction.GCD(20, 0));
        assertEquals(5, Solution.Fraction.GCD(5, 10));
    }

    @Test
    public void testMultiplyingFractions() {
        Solution.Fraction f1 = new Solution.Fraction(1, 1);
        Solution.Fraction f2 = new Solution.Fraction(1, 1);
        assertTrue(f1.multiply(f2).equals(f2));

        f1.set(5, 1);
        f2.set(1, 2);
        assertTrue(f1.multiply(f2).equals(new Solution.Fraction(5, 2)));

        f1.set(340, 7);
        f2.set(1, 20);
        assertTrue(f1.multiply(f2).equals(new Solution.Fraction(17, 7)));
    }

    @Test
    public void testAddingFractions() {
        Solution.Fraction f1 = new Solution.Fraction(4, 5);
        Solution.Fraction f2 = new Solution.Fraction(3, 10);
        assertTrue(f1.add(f2).equals(new Solution.Fraction(11, 10)));

        f1.set(1, 5);
        f2.set(5, 1);
        assertTrue(f1.add(f2).equals(new Solution.Fraction(26, 5)));

        f1.set(1, 2);
        f2.set(1, 2);
        assertTrue(f1.add(f2).equals(new Solution.Fraction(1, 1)));

        f1.set(2, 3);
        f2.set(1, 12);
        assertTrue(f1.add(f2).equals(new Solution.Fraction(3, 4)));
    }

    @Test
    public void testCycleGSProbs() {
        Solution.Fraction f = new Solution.Fraction(2, 3);
        assertTrue(new Solution.Fraction(3, 1).equals(f.findCycleGSValue()));
        f.multiply(new Solution.Fraction(1, 5));
        assertTrue(new Solution.Fraction(5, 2).equals(f.findCycleGSValue()));
    }

    @Test
    public void testConsolidate() {
        List<Solution.Fraction> fracs1 = new ArrayList<>(List.of(
            new Solution.Fraction(2, 5),
            new Solution.Fraction(3, 10),
            new Solution.Fraction(1, 5),
            new Solution.Fraction(1, 10)
        ));
        int[] out1 = Solution.Fraction.consolidate(fracs1);
        int[] exp1 = new int[] {4, 3, 2, 1, 10};
        assertArrayEquals(exp1, out1);

        List<Solution.Fraction> fracs2 = new ArrayList<>(List.of(
            new Solution.Fraction(0, 1),
            new Solution.Fraction(1, 1),
            new Solution.Fraction(0, 1)
        ));
        int[] out2 = Solution.Fraction.consolidate(fracs2);
        int[] exp2 = new int[] {0, 1, 0, 1};
        assertArrayEquals(exp2, out2);

        List<Solution.Fraction> fracs3 = new ArrayList<>(List.of(new Solution.Fraction(1, 1)));
        int[] out3 = Solution.Fraction.consolidate(fracs3);
        int[] exp3 = new int[] {1, 1};
        assertArrayEquals(exp3, out3);
    }
}
