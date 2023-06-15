public class Solution {
    
    enum Move {
        BACK_FAR_LEFT(-17),
        BACK_FAR_RIGHT(-15),
        BACK_LEFT(-10),
        BACK_RIGHT(-6),
        FORW_LEFT(6),
        FORW_RIGHT(10),
        FORW_FAR_LEFT(15),
        FORW_FAR_RIGHT(17);

        private int diff;

        Move(int diff) {
            this.diff = diff;
        }

        public int getDiff() {
            return this.diff;
        }
    }

    public static int solution(int src, int dest) {
        if (src == dest) {
            return 0;
        }

        return -1;
    }
}
