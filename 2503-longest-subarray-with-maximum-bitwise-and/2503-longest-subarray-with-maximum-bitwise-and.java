class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int maxi = 0, prev = -1, count = 0;
        int maxiEle = 0;
        for (int ele : nums)
            maxiEle = Math.max(ele, maxiEle);

        for (int i = 0; i < n; i++) {
            int current = nums[i];
            if (current != maxiEle) {
                count = 0;
                continue;
            }
            if (current == maxiEle)
                count++;
            else
                count = 1;
            maxi = Math.max(maxi, count);
        }
        return maxi;
    }
}