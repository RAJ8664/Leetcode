class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int res[] = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = 1;
        int last[] = new int[32];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 32; j++) {
                if (((nums[i] >> j) & 1) > 0)
                    last[j] = i;
                res[i] = Math.max(res[i], last[j] - i + 1);
            }
        }
        return res;
    }
}