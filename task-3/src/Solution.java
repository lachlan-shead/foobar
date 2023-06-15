import java.util.List;
import java.util.ArrayList;

public class Solution {

    private static int BOARD_LEN = 8;

    /** Describes the different knight moves by coordinate change. */
    enum Move {
        ULU(-2,-1),
        URU(-2,+1),
        ULL(-1,-2),
        URR(-1,+2),
        DLL(+1,-2),
        DRR(+1,+2),
        DLD(+2,-1),
        DRD(+2,+1);

        private int rowDiff;
        private int colDiff;

        Move(int rowDiff, int colDiff) {
            this.rowDiff = rowDiff;
            this.colDiff = colDiff;
        }

        /** Convert the move coordinate changes to the index difference. */
        public int getCoordinateDiff() {
            return BOARD_LEN * this.rowDiff + this.colDiff;
        }

        /** Find the moves from a given square that do not leave the board.
         * 
         * @param   coord   the current square's index.
         * @return          the list of legal moves from this square.
         */
        public static List<Move> getLegalMoves(int coord) {
            int row = coord / BOARD_LEN;
            int col = coord % BOARD_LEN;

            List<Move> legalMoves = new ArrayList<>(List.of(Move.values()));
            legalMoves.removeIf(m -> (
                   (m.rowDiff + row < 0) || (m.rowDiff + row >= BOARD_LEN)
                || (m.colDiff + col < 0) || (m.colDiff + col >= BOARD_LEN)
            ));
            return legalMoves;
        }
    }

    public static int solution(int src, int dest) {
        if (src == dest) {
            return 0;
        }

        return -1;
    }

    /** Return whether the search for solutions is finished.
     * 
     * @param srcN  the set of neighbours visited from the source.
     * @param destN the set of neighbours visited from the destination.
     * @return      whether the two sets have any overlap.
     */
    public static boolean finished(List<Integer> srcN, List<Integer> destN) {
        return srcN.stream().filter(destN::contains).count() > 0;
    }
}
