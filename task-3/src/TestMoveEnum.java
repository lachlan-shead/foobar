import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class TestMoveEnum {
    static BiConsumer<Integer, List<Solution.Move>> testLambda = (in, exp) -> {
            List<Solution.Move> out = Solution.Move.getLegalMoves(in);
            for (Solution.Move m : exp) {
                assertTrue(out.contains(m),
                    Integer.toString(in+m.getCoordinateDiff()) + " not recognised " +
                    "for coord " + Integer.toString(in)
                );
            }
            assertEquals(exp.size(), out.size(),
                "illegal move found for coord=" + Integer.toString(in));
        };
    
    @Test
    public void testEnumDiffValues() {
        assertEquals(-17, Solution.Move.ULU.getCoordinateDiff());
        assertEquals(10, Solution.Move.DRR.getCoordinateDiff());
    }

    @Test
    public void testCornerMoves() {
        testLambda.accept(0, Arrays.asList(Solution.Move.DRR, Solution.Move.DRD));
        testLambda.accept(7, Arrays.asList(Solution.Move.DLL, Solution.Move.DLD));
        testLambda.accept(56, Arrays.asList(Solution.Move.URR, Solution.Move.URU));
        testLambda.accept(63, Arrays.asList(Solution.Move.ULL, Solution.Move.ULU));
    }

    @Test
    public void testMovesOnOutermostLayers() {
        testLambda.accept(4, Arrays.asList(
            Solution.Move.DLL, Solution.Move.DLD,
            Solution.Move.DRD, Solution.Move.DRR));
        testLambda.accept(24, Arrays.asList(
            Solution.Move.URU, Solution.Move.URR,
            Solution.Move.DRR, Solution.Move.DRD));
        testLambda.accept(60, Arrays.asList(
            Solution.Move.ULL, Solution.Move.ULU,
            Solution.Move.URU, Solution.Move.URR));
        testLambda.accept(31, Arrays.asList(
            Solution.Move.ULU, Solution.Move.ULL,
            Solution.Move.DLL, Solution.Move.DLD));
    }

    @Test
    public void testMovesOnSecondmostOuterLayers() {
        testLambda.accept(12, Arrays.asList(
            Solution.Move.ULL, Solution.Move.DLL, Solution.Move.DLD,
            Solution.Move.DRD, Solution.Move.DRR, Solution.Move.URR));
        testLambda.accept(25, Arrays.asList(
            Solution.Move.ULU, Solution.Move.URU, Solution.Move.URR,
            Solution.Move.DRR, Solution.Move.DRD, Solution.Move.DLD));
        testLambda.accept(52, Arrays.asList(
            Solution.Move.DLL, Solution.Move.ULL, Solution.Move.ULU,
            Solution.Move.URU, Solution.Move.URR, Solution.Move.DRR));
        testLambda.accept(30, Arrays.asList(
            Solution.Move.URU, Solution.Move.ULU, Solution.Move.ULL,
            Solution.Move.DLL, Solution.Move.DLD, Solution.Move.DRD));
    }

    @Test
    public void testSquareWithAllLegalMoves() {
        testLambda.accept(19, Arrays.asList(Solution.Move.values()));
    }
}
