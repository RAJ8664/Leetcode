class Solution {
    static class Tuple {
        int x, y, score;
        public Tuple(int x, int y, int score) {
            this.x = x;
            this.y = y;
            this.score = score;
        }
        @Override
        public String toString() {
            return "(" + x + ", " + y + " " + score + ")";
        }
    }

    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            int op1 = Integer.compare(second.score, first.score);
            if (op1 != 0)
                return op1;
            int op2 = Integer.compare(first.x, second.x);
            if (op2 != 0)
                return op2;
            return Integer.compare(first.y, second.y);
        }
    }

    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int n = towers.length;
        ArrayList<Tuple> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int dist = Math.abs(center[0] - towers[i][0]) + Math.abs(center[1] - towers[i][1]);
            if (dist <= radius)
                res.add(new Tuple(towers[i][0], towers[i][1], towers[i][2]));
        }
        Collections.sort(res, new customSort());
        if (res.size() == 0)
            return new int[] {-1, -1};
        return new int[] {res.get(0).x, res.get(0).y};
    }
}