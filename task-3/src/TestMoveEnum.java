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
    public void testMovesOnCorners() {
        testLambda.accept(0, List.of(Solution.Move.DRR, Solution.Move.DRD));
        testLambda.accept(7, List.of(Solution.Move.DLL, Solution.Move.DLD));
        testLambda.accept(56, List.of(Solution.Move.URR, Solution.Move.URU));
        testLambda.accept(63, List.of(Solution.Move.ULL, Solution.Move.ULU));
    }

    @Test
    public void testMovesOnOutermostLayers() {
        testLambda.accept(4, List.of(
            Solution.Move.DLL, Solution.Move.DLD,
            Solution.Move.DRD, Solution.Move.DRR));
        testLambda.accept(24, List.of(
            Solution.Move.URU, Solution.Move.URR,
            Solution.Move.DRR, Solution.Move.DRD));
        testLambda.accept(60, List.of(
            Solution.Move.ULL, Solution.Move.ULU,
            Solution.Move.URU, Solution.Move.URR));
        testLambda.accept(31, List.of(
            Solution.Move.ULU, Solution.Move.ULL,
            Solution.Move.DLL, Solution.Move.DLD));
    }

    @Test
    public void testMovesOnSecondmostOuterLayers() {
        testLambda.accept(12, List.of(
            Solution.Move.ULL, Solution.Move.DLL, Solution.Move.DLD,
            Solution.Move.DRD, Solution.Move.DRR, Solution.Move.URR));
        testLambda.accept(25, List.of(
            Solution.Move.ULU, Solution.Move.URU, Solution.Move.URR,
            Solution.Move.DRR, Solution.Move.DRD, Solution.Move.DLD));
        testLambda.accept(52, List.of(
            Solution.Move.DLL, Solution.Move.ULL, Solution.Move.ULU,
            Solution.Move.URU, Solution.Move.URR, Solution.Move.DRR));
        testLambda.accept(30, List.of(
            Solution.Move.URU, Solution.Move.ULU, Solution.Move.ULL,
            Solution.Move.DLL, Solution.Move.DLD, Solution.Move.DRD));
    }

    @Test
    public void testSquareWithAllLegalMoves() {
        testLambda.accept(19, List.of(Solution.Move.values()));
    }
}
