class Solution {
    public long maxScore(int[] a, int[] b) {
        long dp[][] = new long[5][b.length + 1];
        for (long current[] : dp) Arrays.fill(current, -1);
        long res = solve(0 , 0 , a, b, dp);
        return res;
    }

    private long solve(int i , int j , int arr[] , int brr[], long dp[][]) {
        if (i >= arr.length) return 0;
        if (j >= brr.length) return Integer.MIN_VALUE;
        
        if (dp[i][j] != -1) return dp[i][j];

        long not_consider = solve(i , j + 1, arr, brr, dp);
        long consider = arr[i] * 1L * brr[j] + solve(i + 1, j + 1, arr, brr, dp);
       
        return dp[i][j] = Math.max(not_consider, consider);
    }
}