class Solution {
    public int fun(int[] arr1,int[] arr2,int n,int m,int[][] dp,int i,int j){
        if(i==n || j==m)
            return 0;
        if(dp[i][j]!=-1)
            return dp[i][j];
        int skipI = fun(arr1,arr2,n,m,dp,i+1,j); 
        int skipJ = fun(arr1,arr2,n,m,dp,i,j+1);
        int pickIJ = (arr1[i]*arr2[j]) + fun(arr1,arr2,n,m,dp,i+1,j+1);
        return dp[i][j] = Math.max(pickIJ,Math.max(skipI,skipJ));
    }
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++)
                dp[i][j] = -1;
        }
        int ans = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++)
                ans = Math.max(ans,nums1[i]*nums2[j]);
        }
        int answer = fun(nums1,nums2,n,m,dp,0,0);
        if(answer==0)
            return ans;
        return answer;
    }
}