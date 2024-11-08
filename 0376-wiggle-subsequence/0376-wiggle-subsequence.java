class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        int dp[][][] = new int[n + 1][n + 1][3];
        for (int current[][] : dp) for (int current1[] : current) Arrays.fill(current1, -1);
        int res = solve(0 , -1, -1, nums, dp);
        return res;
    }

    static int solve(int ind, int prev_diff, int prev_ind, int arr[], int dp[][][]) {
        if (ind >= arr.length) return 0;
        if (dp[ind][prev_ind + 1][prev_diff + 1] != -1) return 0;
        if (prev_ind == -1) {
            int op1 = 0, op2 = 0;
            op1 = 1 + solve(ind + 1, prev_diff, ind, arr, dp);
            op2 = solve(ind + 1, prev_diff, prev_ind, arr, dp);
            return dp[ind][prev_ind + 1][prev_diff + 1] = Math.max(op1, op2);
        } 
        else if (prev_diff == -1) {
            int op1 = 0, op2 = 0;
            if (arr[ind] - arr[prev_ind] > 0) op1 = 1 + solve(ind + 1, 1, ind, arr, dp);
            if (arr[ind] - arr[prev_ind] < 0) op1 = 1 + solve(ind + 1, 0, ind , arr, dp); 
            op2 = solve(ind + 1, prev_diff, prev_ind, arr, dp);
            return dp[ind][prev_ind + 1][prev_diff + 1] = Math.max(op1, op2);
        }
        int op1 = 0, op2 = 0;
        if (arr[ind] - arr[prev_ind] > 0) {
            if (prev_diff == 0) op1 = 1 + solve(ind + 1, 1, ind, arr, dp);
            else return 0;
        }
        if (arr[ind] - arr[prev_ind] < 0) {
            if (prev_diff == 1) op1 = 1 + solve(ind + 1, 0, ind, arr, dp);
            else return 0;
        }
        op2 = solve(ind + 1, prev_diff, prev_ind, arr, dp);
        return dp[ind][prev_ind + 1][prev_diff + 1] = Math.max(op1, op2);
    }
}