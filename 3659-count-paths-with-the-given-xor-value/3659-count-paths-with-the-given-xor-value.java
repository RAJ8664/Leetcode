class Solution {
    private int mod = (int)(1e9 + 7);
    private int dp[][][];
    public int countPathsWithXorValue(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        dp = new int[n + 1][m + 1][20];
        for (int current[][] : dp) for (int current1[] : current) Arrays.fill(current1, -1);
        int res = solve(0, 0, 0, grid, k);
        return res;
    }
    private int solve(int row, int col, int xor, int grid[][], int k) {
        if (row >= grid.length || col >= grid[0].length) return 0;
        xor ^= grid[row][col];
        if (row == grid.length - 1 && col == grid[0].length - 1) return xor == k ? 1 : 0;
        if (dp[row][col][xor] != -1) return dp[row][col][xor];
        int op1 = solve(row + 1, col, xor, grid, k);
        int op2 = solve(row, col + 1, xor, grid, k);
        return dp[row][col][xor] = (op1 + op2) % mod;
    }
}