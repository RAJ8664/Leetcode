class Solution {
    public int subarraySum(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int start = Math.max(0, i - nums[i]);
            for (int j = start; j <= i; j++) res += nums[j];
        }
        return res;
    }
}