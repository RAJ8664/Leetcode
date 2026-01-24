class Solution {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < n / 2; i++) 
            res = Math.max(res, nums[i] + nums[n - 1 - i]);
        return res;
    }
}