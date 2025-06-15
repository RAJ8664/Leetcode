class Solution {
    public long maximumProduct(int[] nums, int m) {
        int n = nums.length;
        int max_pref[] = new int[n];
        int max_suff[] = new int[n];
        int min_pref[] = new int[n];
        int min_suff[] = new int[n];
        max_pref[0] = nums[0];
        min_pref[0] = nums[0];
        for (int i = 1; i < n; i++) {
            max_pref[i] = Math.max(nums[i], max_pref[i - 1]);
            min_pref[i] = Math.min(nums[i], min_pref[i - 1]);
        }
        max_suff[n - 1] = nums[n - 1];
        min_suff[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max_suff[i] = Math.max(nums[i], max_suff[i + 1]);
            min_suff[i] = Math.min(nums[i], min_suff[i + 1]);
        }
        long res = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int current = nums[i];
            if (current == 0 && (i - m - 1 >= 0 || i + m  - 1 < n))
                res = Math.max(res, 0);
            else if (current < 0) {
                if (i - m - 1 >= 0)
                    res = Math.max(res, current * 1L *  min_pref[i - m - 1]);
                if (i + m - 1 < n)
                    res = Math.max(res, current * 1L * min_suff[i + m - 1]);
            } else if (current > 0) {
                if (i - m - 1 >= 0)
                    res = Math.max(res, current * 1L * max_pref[i - m - 1]);
                if (i + m - 1 < n)
                    res = Math.max(res, current * 1L * max_suff[i + m - 1]);
            }
        }
        return res;
    }
}