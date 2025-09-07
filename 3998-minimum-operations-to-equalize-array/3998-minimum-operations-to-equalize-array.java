class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != nums[0]) return 1;
        }
        return 0;
    }
}