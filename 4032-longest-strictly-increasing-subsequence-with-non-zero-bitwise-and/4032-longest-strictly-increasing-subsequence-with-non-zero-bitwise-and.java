class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int maxi = 0;
        for (int i = 0; i < 31; i++)
            maxi = Math.max(maxi, LCS(nums, i));
        return maxi;
    }

    public static int LCS(int[] nums, int k) {
        int n = nums.length;
        int[] tail = new int[n];
        int len = 0;
        for (int x : nums) {
            // check if k-th bit is set
            if ((x & (1 << k)) == 0)
                continue;
            int pos = lowerBound(tail, len, x);
            tail[pos] = x;
            if (pos == len)
                len++;
        }
        return len;
    }

    private static int lowerBound(int[] arr, int len, int target) {
        int l = 0, r = len;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (arr[m] >= target)
                r = m;
            else
                l = m + 1;
        }
        return l;
    }
}