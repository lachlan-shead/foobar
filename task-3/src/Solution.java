import java.util.List;
import java.util.ArrayList;

public class Solution {

    private static int BOARD_LEN = 8;
    
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

        public int getDiff() {
            return BOARD_LEN * this.rowDiff + this.colDiff;
        }

        public static List<Move> getLegalMoves(int coord) {
            List<Move> legalMoves = new ArrayList<>();
            legalMoves.addAll(List.of(Move.values()));
            int row = coord / BOARD_LEN;
            int col = coord % BOARD_LEN;
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
}
