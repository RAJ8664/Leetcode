class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int low = 0, high = nums[n - 1] - nums[0], ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (count(mid, nums) < k) 
                low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
    private int count(int target, int arr[]) {
        int n = arr.length;
        int count = 0, j = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && arr[j] - arr[i] <= target) 
                j++;
            count += j - i - 1; 
        }
        return count;
    }
}