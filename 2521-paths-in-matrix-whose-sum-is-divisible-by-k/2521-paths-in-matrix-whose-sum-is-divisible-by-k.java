class Solution {
    private int mod = (int)(1e9 + 7);
    private int dp[][][];
    public int numberOfPaths(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        dp = new int[n + 1][m + 1][k + 1];
        for (int current[][] : dp)
            for (int current1[] : current)
                Arrays.fill(current1, -1);
        return solve(0, 0, 0, k, grid);
    }
    private int solve(int row, int col, int sum, int k, int grid[][]) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) 
            return 0;
        if (dp[row][col][sum] != -1) 
            return dp[row][col][sum];
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            if ((sum % k + grid[row][col] % k) % k == 0)
                return 1;
            return 0;
        }
        int op1 = solve(row, col + 1, (sum  % k + grid[row][col] % k) % k, k, grid);
        int op2 = solve(row + 1, col, (sum % k + grid[row][col] % k) % k, k, grid);
        return dp[row][col][sum] = (op1 + op2) % mod;
    }
}