class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int mini = Integer.MAX_VALUE / 10;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i + k - 1 < n) {
                mini = Math.min(mini, nums[i + k - 1] - nums[i]);
            }
        }
        return mini;
    }
}