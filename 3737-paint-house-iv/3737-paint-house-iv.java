class Solution {
    private long dp[][][][];
    public long minCost(int n, int[][] cost) {
        dp = new long[n + 1][4][4][3];
        for (long current[][][] : dp) for (long current1[][] : current) for (long current2[] : current1) Arrays.fill(current2, -1);
        return solve(n, 0, cost, 3, 3, 0);   
    }
    private long solve(int n, int idx, int cost[][], int prev1, int prev2, int start) {
        if (idx >= n / 2) return 0;
        long ans = Long.MAX_VALUE;
        if (dp[idx][prev1][prev2][start] != - 1) return dp[idx][prev1][prev2][start];
        if (start == 0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i != j) {
                        long ans1 = (long) cost[idx][i] + (long) cost[n - 1 - idx][j] + solve(n, idx + 1, cost, i, j, 1);
                        ans = Math.min(ans, ans1);
                    }
                }
            }
        } 
        else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (prev1 != i && prev2 != j && i != j) {
                        long ans1 = (long) cost[idx][i] + (long) cost[n-1-idx][j] + solve(n, idx + 1, cost, i, j, start);
                        ans = Math.min(ans, ans1);
                    }
                }
            }
        }
        return dp[idx][prev1][prev2][start] = ans;
    }
}