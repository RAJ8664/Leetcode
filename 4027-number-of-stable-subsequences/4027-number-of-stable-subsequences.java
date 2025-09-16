class Solution {
    private int dp[][][];
    private int mod = (int)(1e9 + 7);
    public int countStableSubsequences(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1][4][4];
        for (int current[][] : dp)
            for (int current1[] : current)
                Arrays.fill(current1, -1);
        int res = solve(0, -1, 0, nums);
        return res;
    }

    private int solve(int ind, int prevParity, int consParity, int arr[]) {
        if (ind >= arr.length) {
            return (prevParity != -1) ? 1 : 0;
        }
        if (dp[ind][prevParity + 2][consParity] != -1)
            return dp[ind][prevParity + 2][consParity];
        long ans = 0L;
        ans += solve(ind + 1, prevParity, consParity, arr);
        int curP = arr[ind] % 2;
        if (prevParity == -1) {
            ans += solve(ind + 1, curP, 1, arr);
        } else {
            if (curP == prevParity) {
                if (consParity + 1 < 3) {
                    ans += solve(ind + 1, curP, consParity + 1, arr);
                }
            } else {
                ans += solve(ind + 1, curP, 1, arr);
            }
        }
        ans %= mod;
        dp[ind][prevParity + 2][consParity] = (int) ans;
        return dp[ind][prevParity + 2][consParity];
    }
}
