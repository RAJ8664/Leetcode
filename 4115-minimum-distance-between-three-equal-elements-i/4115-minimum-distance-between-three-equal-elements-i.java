class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int mini = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] == nums[j] && nums[j] == nums[k]) {
                        mini = Math.min(mini, Math.abs(i - j) + Math.abs(j - k) + Math.abs(k - i));
                    }
                }
            }
        }
        return mini == Integer.MAX_VALUE ? -1 : mini;
    }
}