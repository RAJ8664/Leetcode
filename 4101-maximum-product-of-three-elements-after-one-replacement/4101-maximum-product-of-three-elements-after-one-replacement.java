class Solution {
    public long maxProduct(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) 
            nums[i] = Math.abs(nums[i]);
        Arrays.sort(nums);
        long maxi = nums[n - 1] * 1L * nums[n - 2];
        return maxi * 1L * (long)(1e5);
    }
}