class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        if (n == 1) return 0;
        int low = 0, high = (int)(n), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, nums, k)) {
                ans = mid;
                high = mid - 1;
            }
            else 
                low = mid + 1;
        }
        return ans;
    }
    private boolean ok(int target, int arr[], int k) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int totalDel = i;
            long req = k * 1L * arr[i];
            int get = findGreater(arr, i + 1, n - 1, req);
            if (get != -1) 
                totalDel += n - get;
            if (totalDel <= target) 
                return true;
        }
        return false;
    }
    private int findGreater(int arr[], int low, int high, long req) {
        int n = arr.length;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > req) {
                ans = mid;
                high = mid - 1;
            }
            else 
                low = mid + 1;
        }
        return ans;
    }
}