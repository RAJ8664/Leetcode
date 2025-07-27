import java.util.Arrays;

class Solution {
    private int dp[][];
    private int mod = (int)(1e9 + 7);
    public int numRollsToTarget(int n, int k, int target) {
        dp = new int[n + 1][target + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        return solve(n, 0, target, k) % mod;
    }
    private int solve(int n, int ind, int target, int k) {
        if (ind >= n) {
            if (target != 0)
                return 0;
            return 1;
        }

        if (dp[ind][target] != -1)
            return dp[ind][target] % mod;

        long ways = 0;
        for (int i = 1; i <= k; i++) {
            if (target >= i)
                ways = (ways + solve(n, ind + 1, target - i, k)) % mod;
        }
        return dp[ind][target] = (int)(ways % mod);
    }
}