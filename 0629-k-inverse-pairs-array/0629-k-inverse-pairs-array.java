class Solution {
    private int mod = (int)(1e9 + 7);
    private int dp[][];
    public int kInversePairs(int n, int k) {
        dp = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++)
            dp[i][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = (dp[i - 1][j] + (j > 0 ? dp[i][j - 1] : 0)) % mod;
                dp[i][j] = (dp[i][j] + mod - (j >= i ? dp[i - 1][j - i] : 0)) % mod;
            }
        }
        return dp[n][k];
    }
}