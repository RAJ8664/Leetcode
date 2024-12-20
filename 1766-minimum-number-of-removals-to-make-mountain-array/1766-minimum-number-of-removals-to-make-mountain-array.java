class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        return n - solve(nums);
    }

    public static int solve(int arr[]){
        int n = arr.length;
        int dp1[] = new int[n + 1];
        int dp2[] = new int[n + 2];
        Arrays.fill(dp1,1); Arrays.fill(dp2, 1);
        for(int ind = 0; ind < n; ind ++ ){
            for(int prev = 0; prev < ind; prev ++){
                if(arr[ind] > arr[prev]) dp1[ind] = Math.max(dp1[ind] , 1 + dp1[prev]);
            }
        }
        for(int ind = n -  1; ind >=0 ;ind --){
            for(int prev = n - 1; prev > ind; prev--){
                if(arr[ind] > arr[prev]) dp2[ind] = Math.max(dp2[ind] , 1 + dp2[prev]);
            }
        }
        int maxi = -1;
        for(int i = 0; i < n; i++){
            if(dp1[i] >= 2 && dp2[i] >= 2) maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
        }
        return maxi;
    }
}