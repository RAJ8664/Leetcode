class Solution {
    private int dp[][][];
    public int maxPathScore(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        int[][][] dp = new int[n][m][k + 1];
        for (int[][] arr : dp)
            for (int[] a : arr)
                Arrays.fill(a, Integer.MIN_VALUE);
        int startCost = grid[0][0] == 2 ? 1 : grid[0][0];
        if (startCost > k)
            return -1;
        dp[0][0][startCost] = grid[0][0];
        int[][] dirs = {{1, 0}, {0, 1}};
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                for (int cost = 0; cost <= k; cost++) {
                    if (dp[row][col][cost] == Integer.MIN_VALUE)
                        continue;
                    for (int[] dir : dirs) {
                        int newRow = row + dir[0], newCol = col + dir[1];
                        if (newRow < n && newCol < m) {
                            int addCost = grid[newRow][newCol] == 2 ? 1 : grid[newRow][newCol];
                            int newCost = cost + addCost;
                            if (newCost <= k) {
                                int newScore = dp[row][col][cost] + grid[newRow][newCol];
                                if (dp[newRow][newCol][newCost] < newScore)
                                    dp[newRow][newCol][newCost] = newScore;
                            }
                        }
                    }
                }
            }
        }
        int maxScore = Integer.MIN_VALUE;
        for (int cost = 0; cost <= k; cost++)
            maxScore = Math.max(maxScore, dp[n - 1][m - 1][cost]);
        return maxScore == Integer.MIN_VALUE ? -1 : maxScore;
    }
}