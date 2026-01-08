class Solution {
    private int dp[][];
    public int solve(int arr[], int brr[], int i, int j) {
        if (i == arr.length || j == brr.length)
            return 0;
        if (dp[i][j] != -1) 
            return dp[i][j];
        int op1 = solve(arr, brr, i + 1, j);
        int op2 = solve(arr, brr, i, j + 1);
        int op3 = arr[i] * brr[j] + solve(arr, brr, i + 1, j + 1);
        return dp[i][j] = Math.max(op1, Math.max(op2, op3));
    }
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        dp = new int[n + 1][m + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        int res = Integer.MIN_VALUE;
        res = solve(nums1, nums2, 0, 0);
        if (res == 0) {
            int maxi = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) 
                    maxi = Math.max(maxi, nums1[i] * nums2[j]);
            }
            return maxi;
        }
        return res;
    }
}