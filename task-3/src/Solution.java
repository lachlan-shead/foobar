import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

public class Solution {

    public static int BOARD_LEN = 8;

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

        /** Find the moves from a given square that do not leave the board. */
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
        boolean srcVisited[] = new boolean[BOARD_LEN * BOARD_LEN];
        boolean destVisited[] = new boolean[BOARD_LEN * BOARD_LEN];
        srcVisited[src] = true;
        destVisited[dest] = true;
        List<Integer> srcN = new ArrayList<>(List.of(src));
        List<Integer> destN = new ArrayList<>(List.of(dest));

        /** High-level idea: always check the same colour, expanding outwards */
        int moves = (oppositeColour(src, dest)) ? 1 : 0;
        if (moves == 1) destN = getUnvisitedNeighbours(destN, destVisited);

        while (!finished(srcN, destN)) {
            moves += 2;
            srcN = getUnvisitedNeighbours(srcN, srcVisited);
            destN = getUnvisitedNeighbours(destN, destVisited);
        }
        return moves;
    }

    /** Determine whether the src and dest have the same or opposite colour. */
    public static boolean oppositeColour(int src, int dest) {
        int srcRow = src / BOARD_LEN;
        int srcCol = src % BOARD_LEN;
        int destRow = dest / BOARD_LEN;
        int destCol = dest % BOARD_LEN;
        return  ((srcRow % 2 == destRow % 2) && (srcCol % 2 != destCol % 2)) ||
                ((srcRow % 2 != destRow % 2) && (srcCol % 2 == destCol % 2));
    }

    /** Return the list of all unvisited nodes reachable from the current one. */
    public static List<Integer> getUnvisitedNeighbours(List<Integer> lstN, boolean[] visited) {
        return lstN.stream().flatMap(x -> visitNeighbours(x, visited).stream()).collect(toList());
    }

    /** Visit and return the list of unvisited nodes from one node. */
    public static List<Integer> visitNeighbours(int coord, boolean visited[]) {
        List<Integer> neighbours = new ArrayList<>();
        for (Move m : Move.getLegalMoves(coord)) {
                int neighbour = coord + m.getCoordinateDiff();
                if (visited[neighbour] == true) continue;
                visited[neighbour] = true;
                neighbours.add(neighbour);
            }
        return neighbours;
    }

    /** Return whether the search for solutions is finished. */
    public static boolean finished(List<Integer> srcN, List<Integer> destN) {
        return srcN.stream().filter(destN::contains).count() > 0;
    }
}
