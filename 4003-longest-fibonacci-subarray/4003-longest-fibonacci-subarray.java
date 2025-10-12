class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int maxi = 2, current = 2;
        int a = nums[0], b = nums[1];
        for (int i = 2; i < n; i++) {
            if (a + b != nums[i]) {
                a = b;
                b = nums[i];
                current = 2;
            } else {
                current++;
                maxi = Math.max(maxi, current);
                a = b;
                b = nums[i];
            }
        }
        return maxi;
    }
}