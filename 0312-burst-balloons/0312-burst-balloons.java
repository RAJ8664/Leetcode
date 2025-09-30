class Solution {
    private int dp[][];
    public int maxCoins(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1][n + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        int arr[] = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 1; i <= n; i++)
            arr[i] = nums[i - 1];
        int res = solve(1, n, arr);
        return res;    
    }
    private int solve(int i, int j, int arr[]) {
        if (i > j) return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int ans = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            ans = Math.max(ans, arr[i - 1] * arr[k] * arr[j + 1] + solve(i, k - 1, arr) + solve(k + 1, j, arr));
        }
        return dp[i][j] = ans;
    }
}