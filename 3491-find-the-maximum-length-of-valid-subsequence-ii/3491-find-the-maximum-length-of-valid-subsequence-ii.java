class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int dp[][] = new int[n + 1][k + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                int sum = (nums[i] + nums[j]) % k;
                dp[i][sum] = Math.max(dp[i][sum] , dp[j][sum] + 1);
            }
        }
        int maxi = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < k; j++) 
                maxi = Math.max(maxi, dp[i][j]);
        }
        return maxi + 1;
    }
}