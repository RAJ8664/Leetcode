class Solution {
    private int dp[][];
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1][3];
        for (int current[] : dp)
            Arrays.fill(current, -1);

        return Math.max(0, solve(0, 0, nums));
    }

    private int solve(int i, int rem, int[] nums) {
        if (i == nums.length)
            return rem == 0 ? 0 : Integer.MIN_VALUE / 2;

        if (dp[i][rem] != -1)
            return dp[i][rem];

        int newRem = (rem + nums[i] % 3) % 3;
        int pick = nums[i] + solve(i + 1, newRem, nums);
        int notPick = solve(i + 1, rem, nums);

        return dp[i][rem] = Math.max(pick, notPick);
    }
}