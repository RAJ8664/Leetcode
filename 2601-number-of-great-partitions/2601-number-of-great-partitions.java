import java.util.*;

class Solution {
    private int mod = (int) 1e9 + 7;
    private long dp[];
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        long totalSum = 0;
        for (int num : nums)
            totalSum += num;

        if (totalSum < 2L * k)
            return 0;

        dp = new long[k];
        dp[0] = 1;
        for (int num : nums) {
            if (num >= k)
                continue;
            for (int j = k - 1; j >= num; j--)
                dp[j] = (dp[j] + dp[j - num]) % mod;
        }

        long bad = 0;
        for (long x : dp)
            bad = (bad + x) % mod;

        long totalWays = 1;
        for (int i = 0; i < n; i++)
            totalWays = (totalWays * 2) % mod;
        long res = (totalWays - 2 * bad  % mod + mod) % mod;
        return (int)(res);
    }
}