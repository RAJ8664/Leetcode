class Solution {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int low = 0, high = (int)(1e9), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, nums, k)) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        } 
        return ans;
    }
    private boolean ok(int mid, int arr[], int k) {
        int n = arr.length;
        long currSum = 0, count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > mid) {
                return false;
            }
            if (currSum + arr[i] > mid) {
                currSum = arr[i];
                count++;
            }
            else {
                currSum += arr[i];
            }
        }
        
        return count + 1 <= k;
    }
}