import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestRowData {

    @BeforeEach
    public void setup() {
        Solution.terminalStates.clear();
        Solution.denominators.clear();
    }

    @Test
    public void test1Case() {
        Solution.getRowData(new int[][] {{0}});
        assertEquals(List.of(true), Solution.terminalStates);
        assertEquals(List.of(1), Solution.denominators);
    }

    @Test
    public void test2Case1() {
        Solution.getRowData(new int[][] {{0, 3}, {0, 0}});
        assertEquals(List.of(false, true), Solution.terminalStates);
        assertEquals(List.of(3, 1), Solution.denominators);
    }

    @Test
    public void test2Case2() {
        Solution.getRowData(new int[][] {{0, 0}, {2, 5}});
        assertEquals(List.of(true, false), Solution.terminalStates);
        assertEquals(List.of(1, 7), Solution.denominators);
    }

    @Test
    public void test2Case3() {
        Solution.getRowData(new int[][] {{0, 0}, {0, 0}});
        assertEquals(List.of(true, true), Solution.terminalStates);
        assertEquals(List.of(1, 1), Solution.denominators);
    }

    @Test
    public void testGivenCase1() {
        Solution.getRowData(new int[][]{
            {0, 2, 1, 0, 0},
            {0, 0, 0, 3, 4},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        });
        assertEquals(List.of(false, false, true, true, true), Solution.terminalStates);
        assertEquals(List.of(3, 7, 1, 1, 1), Solution.denominators);
    }

    @Test
    public void testGivenCase2() {
        Solution.getRowData(new int[][] {
            {0, 1, 0, 0, 0, 1},
            {4, 0, 0, 3, 2, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        });
        assertEquals(List.of(false, false, true, true, true, true), Solution.terminalStates);
        assertEquals(List.of(2, 9, 1, 1, 1, 1), Solution.denominators);
    }

}
