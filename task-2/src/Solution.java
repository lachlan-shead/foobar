import java.util.AbstractMap.SimpleEntry;

public class Solution {
    public static int MAX_HEIGHT = 30;
    public static int SUBTREE_SIZE_BY_HEIGHT[] = new int[MAX_HEIGHT + 1];

    enum Move {
        LEFT,
        RIGHT,
        NONE
    }

    public static int[] solution(int h, int[] q) {
        setupSubtreeSizes();
        for (int i = 0; i < q.length; i++) {
            SimpleEntry<Integer, Move> treeInfo = findNode(h, q[i]);
            if (treeInfo.getKey() == h) {
                q[i] = -1;
            }
            else if (treeInfo.getValue() == Move.RIGHT) {
                q[i] += 1;
            }
            else {
                q[i] += 1 + SUBTREE_SIZE_BY_HEIGHT[treeInfo.getKey()];
            }
        }
        return q;
    }

    /** Initialise the perfect subtree size counts (indexed from 0 to h). */
    public static void setupSubtreeSizes() {
        SUBTREE_SIZE_BY_HEIGHT[0] = 0;
        for (int i = 1; i <= MAX_HEIGHT; i++) {
            SUBTREE_SIZE_BY_HEIGHT[i] = 2 * SUBTREE_SIZE_BY_HEIGHT[i - 1] + 1;
        }
    }

    /** Search the perfect binary tree of a given height for a specific node.
     * @param   h            the height of the tree being searched
     * @param   targetNode   the target node, labelled by post-order index
     * @return               target level in the tree, and the last move made to reach it
     */
    public static SimpleEntry<Integer, Move> findNode(int h, int targetNode) {
        int level = h;
        Move move = Move.NONE;
        int currentNode = SUBTREE_SIZE_BY_HEIGHT[h];
        if (targetNode == currentNode) {
            return new SimpleEntry<Integer, Move>(h, move);
        }
        while (currentNode != targetNode) {
            level--;
            currentNode -= (1 + SUBTREE_SIZE_BY_HEIGHT[level]);
            move = Move.LEFT;
            if (targetNode > currentNode) {
                currentNode += SUBTREE_SIZE_BY_HEIGHT[level];
                move = Move.RIGHT;
            }
        }
        return new SimpleEntry<Integer, Move>(level, move);
    }
}
