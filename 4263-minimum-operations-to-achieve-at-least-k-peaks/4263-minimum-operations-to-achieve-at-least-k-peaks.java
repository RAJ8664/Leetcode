class Solution {
    private static int[][] dp;
    public static int minOperations(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) return 0;
        if (k == 1 && n == 2) {
            if (nums[0] == nums[1]) return 1;
            else return 0;
        }
        if (n < 3) 
            return -1;
        if (k > n / 2) return -1;
        return Math.min(solve(nums, k, 1, n - 1), solve(nums, k, 0, n - 2));
    }

    private static int solve(int[] arr, int k, int start, int end) {
        int len = end - start + 1;
        dp = new int[len + 2][k + 1];
        for (int[] row : dp) 
            Arrays.fill(row, -1);
        return dfs(0, k, arr, start, end);
    }

    private static int dfs(int idx, int k, int[] arr, int start, int end) {
        if (k == 0) return 0;
        if (start + idx > end) return Integer.MAX_VALUE / 2;

        if (dp[idx][k] != -1) return dp[idx][k];

        int i = start + idx;

        int op1 = dfs(idx + 1, k, arr, start, end);

        int left = arr[(i - 1 + arr.length) % arr.length];
        int right = arr[(i + 1) % arr.length];

        int need = Math.max(left, right) + 1;
        int cost = Math.max(0, need - arr[i]);

        int op2 = cost + dfs(idx + 2, k - 1, arr, start, end);

        return dp[idx][k] = Math.min(op1, op2);
    }
}