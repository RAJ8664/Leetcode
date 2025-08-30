class Solution {
    private int dp[][][];
    private int mod = (int)(1e9 + 7);
    public int uniquePaths(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        dp = new int[n + 1][m + 1][2];
        for (int current[][] : dp)
            for (int current1[] : current)
                Arrays.fill(current1, -1);
        int res = solve(0, 0, grid, 0);
        return res;
    }

    /* dir --> 1 --> I came here by moving down */
    /* dir --> 0 -- > I came here by moving right */
    private int solve(int row, int col, int grid[][], int dir) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
            return 0;
        if (dp[row][col][dir] != -1)
            return dp[row][col][dir];
        if (row == grid.length - 1 && col == grid[0].length - 1)
            return 1;
        if (grid[row][col] == 0) {
            int op1 = solve(row + 1, col, grid, 1);
            int op2 = solve(row, col + 1, grid, 0);
            return dp[row][col][dir] = (op1 + op2) % mod;
        }
        else {
            int op1 = 0, op2 = 0;
            if (dir == 0) {
                /* I came here by moving right */
                return dp[row][col][dir] = solve(row + 1, col, grid, 1);
            } 
            else if (dir == 1) {
                /* I came here by moving down */ 
                return dp[row][col][dir] = solve(row, col + 1, grid, 0);
            }
        }
        return 0;
    }
}