class Solution {
    public int minimumPrefixLength(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) count++;
            else {
                if (nums[i] >= nums[i + 1]) break;
                else count++;
            }
        }
        return n - count;
    }
}