class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int start = 0, ans = 0, zeroes = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) zeroes++;
            while (zeroes > 1) {
                if (nums[start] == 0) zeroes--;
                start++;
            }
            ans = Math.max(ans, i - start + 1 - zeroes);
        }
        return ans == n ? ans - 1 : ans;
    }
}