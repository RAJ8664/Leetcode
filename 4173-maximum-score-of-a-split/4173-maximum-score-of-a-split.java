class Solution {
    public long maximumScore(int[] nums) {
        int n = nums.length;
        long maxi = Long.MIN_VALUE;
        long pref[] = new long[n];
        pref[0] = nums[0];
        for (int i = 1; i < n; i++)
            pref[i] = pref[i - 1] + nums[i];
        long suffMin[] = new long[n];
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        for (int i = 0; i < n - 1; i++)
            maxi = Math.max(maxi, pref[i] - suffMin[i + 1]);
        return maxi;
    }
}