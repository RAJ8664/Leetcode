class Solution {
    public int countSubarrays(int[] nums) {
        int n = nums.length;
        int count = 0, i = 0, j = 2;
        while (j < n) {
            if (2 * (nums[i] + nums[j]) == nums[i + 1]) count++;
            i++; j++;
        }
        return count;
    }
}