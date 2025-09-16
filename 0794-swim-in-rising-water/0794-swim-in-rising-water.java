class Solution {
    static class Tuple {
        int row, col, val;
        public Tuple(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + ")";
        }
    }

    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.val, second.val);
        }
    } 

    public int swimInWater(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        int dist[][] = new int[n][m];
        for (int current[] : dist)
            Arrays.fill(current, (int)(1e9));

        dist[0][0] = grid[0][0]; 
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new customSort());
        pq.offer(new Tuple(0, 0, grid[0][0]));

        int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (pq.size() > 0) {
            int currRow = pq.peek().row, currCol = pq.peek().col, currVal = pq.peek().val;
            pq.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow < n && newCol < m && newRow >= 0 && newCol >= 0) {
                    if (dist[newRow][newCol] > Math.max(grid[newRow][newCol], currVal)) {
                        dist[newRow][newCol] = Math.max(grid[newRow][newCol], currVal);
                        pq.offer(new Tuple(newRow, newCol, dist[newRow][newCol]));
                    }
                }
            }
        }
        return dist[n - 1][m - 1];
    }
}