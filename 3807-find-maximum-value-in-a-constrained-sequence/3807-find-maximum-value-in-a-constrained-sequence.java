class Solution {
    private long dp[];
    public int findMaxVal(int n, int[][] restrictions, int[] diff) {
        dp = new long[n];
        Arrays.fill(dp, (long) 1e16);
        dp[0] = 0;
        for (int i = 0; i < restrictions.length; i++)
            dp[restrictions[i][0]] = restrictions[i][1] * 1L;
        for (int i = 1; i < n; i++)
            dp[i] = Math.min(dp[i], dp[i - 1] + diff[i - 1]);
        for (int i = n - 2; i >= 0; i--)
            dp[i] = Math.min(dp[i], dp[i + 1] + diff[i]);
        long res = 0;
        for (long v : dp)
            res = Math.max(res, v);
        return (int)res;
    }
}