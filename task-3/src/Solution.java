import java.util.List;
import java.util.ArrayList;

public class Solution {

    public static int BOARD_LEN = 8;
    public static boolean visited[] = new boolean[BOARD_LEN * BOARD_LEN];

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
        visited[src] = true;
        visited[dest] = true;
        // List<Integer> srcN = new ArrayList<>(List.of(src));
        List<Integer> destN = new ArrayList<>(List.of(dest));
        if (src % 2 != dest % 2) {
            destN = getUnvisitedNeighbours(destN);
        }

        return -1;
    }

    public static List<Integer> getUnvisitedNeighbours(List<Integer> lstN) {
        List<Integer> unvisitedNeighbours = new ArrayList<>();
        for (int oldSquare : lstN) {
            for (Move m : Move.getLegalMoves(oldSquare)) {
                int newSquare = oldSquare + m.getCoordinateDiff();
                if (visited[newSquare] == true) continue;
                visited[newSquare] = true;
                unvisitedNeighbours.add(newSquare);
            }
        }
        return unvisitedNeighbours;
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
