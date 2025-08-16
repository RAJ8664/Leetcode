class Solution {
    public long perfectPairs(int[] nums) {
        int n = nums.length;
        long count = 0;
        for (int i = 0; i < n; i++)
            nums[i] = Math.abs(nums[i]);
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n - 1, current = i;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] <= 2 * 1L * nums[i]) {
                    current = mid;
                    low = mid + 1;
                }
                else high = mid - 1;
            }
            count += current - i;
        }
        return count;
    }
}