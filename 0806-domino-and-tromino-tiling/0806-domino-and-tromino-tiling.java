class Solution {
    static int mod = (int)(1e9 + 7);
    public int numTilings(int n) {
        long dp[] = new long[n + 3];
        dp[0] = 1; dp[1] = 2; dp[2] = 5;
        for (int i = 3; i < n; i++) dp[i] = (dp[i - 1] * 2 + dp[i - 3]) % mod;
        return (int)dp[n - 1];
    }
}