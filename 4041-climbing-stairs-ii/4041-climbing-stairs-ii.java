class Solution {
    private int dp[];
    public int climbStairs(int n, int[] costs) {
        dp = new int[n + 1]; 
        Arrays.fill(dp, -1);
        return solve(0, costs);
    }
    private int solve(int idx, int cost[]) {
        if (idx == cost.length) 
            return 0;
        if (idx > cost.length)
            return Integer.MAX_VALUE;
        if (dp[idx] != -1)
            return dp[idx];
        int op1 = Integer.MAX_VALUE / 10, op2 = Integer.MAX_VALUE / 10, op3 = Integer.MAX_VALUE / 10;
        if (idx + 1 <= cost.length) {
           op1 = cost[idx] + 1 + solve(idx + 1, cost); 
        } 
        if (idx + 2 <= cost.length) {
            op2 = cost[idx + 1] + 4 + solve(idx + 2, cost);
        }
        if (idx + 3 <= cost.length) {
            op3 = cost[idx + 2] + 9 + solve(idx + 3, cost); 
        }
        return dp[idx] = Math.min(op1, Math.min(op2, op3));
    }
}