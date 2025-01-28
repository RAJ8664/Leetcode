class Solution {
    int m, n;
    int[][] raj = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int findMaxFish(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int maxFish = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    maxFish = Math.max(maxFish, dfs(grid, i, j));
                }
            }
        }
        return maxFish;
    }
    private int dfs(int[][] grid, int i, int j) {
        int fish = grid[i][j];
        grid[i][j] = 0;
        for (int[] dir : raj) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] > 0) fish += dfs(grid, ni, nj);
        }
        return fish;
    }
}