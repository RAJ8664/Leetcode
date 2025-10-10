class Solution {
    private int dp[][];
    public int maximumEnergy(int[] arr, int k) {
        int n = arr.length; 
        dp = new int[n + 1][2];
        for (int current[] : dp)
            Arrays.fill(current, (int)(-1e9));
        return solve(0, arr, 0, k);
    }
    private int solve(int idx, int arr[], int taken, int k) {
        if (idx >= arr.length)  {
            if (taken == 0) {
                return Integer.MIN_VALUE;
            }
            return 0;
        }
        if (dp[idx][taken] != (int)(-1e9))
            return dp[idx][taken];
        if (taken == 0) {
            int op1 = arr[idx] + solve(idx + k, arr, 1, k);
            int op2 = solve(idx + 1, arr, 0, k);
            return dp[idx][taken] = Math.max(op1, op2);
        } else {
            return dp[idx][taken] = arr[idx] + solve(idx + k, arr, 1, k);
        }
    }
}