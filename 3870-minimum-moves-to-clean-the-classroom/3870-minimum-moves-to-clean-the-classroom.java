class Solution {
    static class Pair {
        int row, col, energy, mask, moves;
        public Pair(int row, int col, int energy, int mask, int moves) {
            this.row = row;
            this.col = col;
            this.energy = energy;
            this.mask = mask;
            this.moves = moves;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + " " + energy + " " + mask + " " + moves + ")";
        }
    }
    public int minMoves(String[] classroom, int energy) {
        int n = classroom.length, m = classroom[0].length();
        char[][] grid = new char[n][m];
        int start_row = -1, start_col = -1, count = 0;
        for (int i = 0; i < n; i++) {
            String x = classroom[i];
            for (int j = 0; j < m; j++) {
                grid[i][j] = x.charAt(j);
                if (grid[i][j] == 'S') {
                    start_row = i;
                    start_col = j;
                }
                if (grid[i][j] == 'L') count++;
            }
        }
        if (count == 0) return 0;
        Map<Integer, Integer> pos = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'L') {
                    pos.put(i * m + j, idx++);
                }
            }
        }
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(start_row, start_col, energy, 0, 0));
        int[][][][] vis = new int[n][m][(int)(Math.pow(2, count)) + 1][energy + 1];
        vis[start_row][start_col][0][energy] = 1;
        int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!q.isEmpty()) {
            int curr_row = q.peek().row, curr_col = q.peek().col, curr_energy = q.peek().energy, curr_mask = q.peek().mask, curr_moves = q.peek().moves;
            q.poll();
            if (grid[curr_row][curr_col] == 'L') {
                int curr_idx = pos.get(curr_row * m + curr_col);
                curr_mask |= (1 << curr_idx);
                if (curr_mask == Math.pow(2, count) - 1) return curr_moves;
            }
            int new_energy = 0;
            if (grid[curr_row][curr_col] == 'R') new_energy = energy;
            else new_energy = curr_energy;
            for (int dire[] : dir) {
                int nrow = curr_row + dire[0];
                int ncol = curr_col + dire[1];
                if (nrow < n && nrow >= 0 && ncol < m && ncol >= 0 && grid[nrow][ncol] != 'X') {
                    if (new_energy - 1 < 0) continue;
                    if (vis[nrow][ncol][curr_mask][new_energy - 1] == 0) {
                        vis[nrow][ncol][curr_mask][new_energy - 1] = 1;
                        q.offer(new Pair(nrow, ncol, new_energy - 1, curr_mask, curr_moves + 1));
                    }
                }
            }
        }
        return -1;
    }
}