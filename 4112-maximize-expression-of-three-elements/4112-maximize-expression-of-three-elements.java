class Solution {
    public int maximizeExpressionOfThree(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - 1] + nums[n - 2] - nums[0];
    }
}