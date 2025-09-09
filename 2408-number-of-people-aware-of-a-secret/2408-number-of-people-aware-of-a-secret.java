class Solution {
    private int dp[];
    private final int mod = (int)(1e9 + 7);
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        dp = new int[n + 1];
        dp[1] = 1;
        for (int day = 1; day <= n; day++) {
            for (int prevDay = day - forget + 1; prevDay <= day - delay; prevDay++) {
                if (prevDay >= 1) 
                    dp[day] = (dp[day] + dp[prevDay]) % mod;
            }
        }
        long res = 0;
        for (int day = n - forget + 1; day <= n; day++) {
            if (day >= 1) 
                res = (res + dp[day]) % mod;
        }
        return (int)(res);
    }
}