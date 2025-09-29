class Solution {
    private int dp[][];
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        dp = new int[n + 1][n + 1];
        for(int row[] : dp)
            Arrays.fill(row, -1);
        return solve(values, 1, n - 1);
    }
    private int solve(int arr[], int i, int j) {
        if(i == j)
             return 0;
        if(dp[i][j] != -1) 
            return dp[i][j];
        int min = Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
            int steps = arr[i - 1] * arr[j] * arr[k] + solve(arr, i, k) + solve(arr, k + 1, j);
            min = Math.min(min, steps);
        }
        return dp[i][j] = min;
    }
}