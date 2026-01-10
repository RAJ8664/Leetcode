class Solution {
    public int minCapability(int[] nums, int k) {
        int low = 1, high = (int)1e9 + 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPick(nums, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    private boolean canPick(int[] nums, int k, int target) {
        int count = 0, i = 0;
        while (i < nums.length) {
            if (nums[i] <= target) {
                count++;
                i += 2;
            } else
                i++;
        }
        return count >= k;
    }
}