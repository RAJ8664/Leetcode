class Solution {
    private int dp[][];
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length, m = multipliers.length;
        dp = new int[n + 1][m + 1];
        for (int current[] : dp)
            Arrays.fill(current, (int)(-1e9));
        int res = solve(nums, multipliers, 0, 0, 0);
        return res;
    }

    private int solve(int nums[], int multipliers[], int left, int right, int idx) {
        if (idx >= multipliers.length)
            return 0;
        if (dp[left][right] != (int)(-1e9))
            return dp[left][right];

        int op1 = nums[left] * multipliers[idx] + solve(nums, multipliers, left + 1, right, idx + 1);
        int op2 = nums[nums.length - 1 - right] * multipliers[idx] + solve(nums, multipliers, left, right + 1, idx + 1);

        return dp[left][right] = Math.max(op1, op2);
    }
}