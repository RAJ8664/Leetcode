class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int pref[] = new int[n];
        pref[0] = nums[0];
        for (int i = 1; i < n; i++) pref[i] = pref[i - 1] + nums[i];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int right_sum = 0, left_sum = 0;
                right_sum = pref[n - 1];
                right_sum -= pref[i];
                if (i - 1 >= 0) left_sum = pref[i - 1];
                if (left_sum == right_sum) res += 2;
                if (Math.abs(left_sum - right_sum) == 1) res++;
            }
        }
        return res;
    }
}