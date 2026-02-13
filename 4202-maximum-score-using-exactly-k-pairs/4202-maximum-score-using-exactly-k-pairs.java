class Solution {
    private long dp[][][];
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        dp = new long[n + 1][m + 1][k + 1];

        for (long current[][] : dp)
            for (long current1[] : current)
                Arrays.fill(current1, -(int)(1e9));

        return solve(0, 0, k, nums1, nums2);
    }

    private long solve(int i, int j, int k, int arr[], int brr[]) {
        if (k == 0) return 0;
        if ((i == arr.length || j == brr.length) && k > 0) 
            return Long.MIN_VALUE / 10;
        
        if (dp[i][j][k] != -(int)(1e9))
            return dp[i][j][k];

        long op1 = arr[i] * 1L * brr[j] + solve(i + 1, j + 1, k - 1, arr, brr);
        long op2 = solve(i + 1, j, k, arr, brr);
        long op3 = solve(i, j + 1, k, arr, brr);

        return dp[i][j][k] = Math.max(op1, Math.max(op2, op3));
    }
}