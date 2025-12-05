class Solution {
    public int countPartitions(int[] nums) {
        int n = nums.length;
        int pref[] = new int[n];
        pref[0] = nums[0];
        for (int i = 1; i < n; i++) pref[i] = pref[i - 1] + nums[i];
        int total = pref[n - 1];
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            int first = pref[i];
            int second = total - first;
            if (Math.abs(first - second) % 2 == 0) count++;
        }
        return count;
    }
}