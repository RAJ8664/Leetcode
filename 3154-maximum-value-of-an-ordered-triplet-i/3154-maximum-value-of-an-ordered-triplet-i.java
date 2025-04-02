class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long maxi = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < 0 && nums[j] < 0 && nums[k] < 0) maxi = Math.max(maxi, 0);
                    else maxi = Math.max(maxi, (nums[i] - nums[j]) * 1L * nums[k]);  
                }
            }
        }
        return maxi;
    }
}