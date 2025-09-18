class Solution {
    private int vis[][];
    static class Pair {
        int row, col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + ")";
        }
    }
    public int findMaxFish(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        vis = new int[n][m];

        int maxi = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && grid[i][j] > 0) {
                    int currFish = BFS(i, j, grid);
                    maxi = Math.max(maxi, currFish);
                }
            }
        }
        return maxi;
    }

    private int BFS(int row, int col, int arr[][]) {
        int n = arr.length, m = arr[0].length;
        int totalFish = 0;
        vis[row][col] = 1;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(row, col));
        int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (q.size() > 0) {
            int currRow = q.peek().row, currCol = q.peek().col;
            q.poll();
            totalFish += arr[currRow][currCol];
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow < n && newCol < m && newRow >= 0 && newCol >= 0 && vis[newRow][newCol] == 0 && arr[newRow][newCol] > 0) {
                    vis[newRow][newCol] = 1;
                    q.offer(new Pair(newRow, newCol));
                }
            }
        }
        return totalFish;
    }
}