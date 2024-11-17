class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int low = 0, high = queries.length - 1, ans = -1;
        boolean flag = true;
        for (int ele : nums) if (ele != 0) flag = false;
        if (flag == true) return 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, queries, nums)) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        if (ans == -1) return -1;
        return ans + 1;
    }

    private boolean ok(int mid, int[][] queries, int nums[]) {
        int n = nums.length;
        int arr[] = new int[n];
        for (int i = 0; i <= mid; i++) {
            int l = queries[i][0], r = queries[i][1], val = queries[i][2];
            arr[l] += val;
            if (r + 1 < n) arr[r + 1] -= val;
        }

        for (int i = 1; i < n; i++) arr[i] += arr[i - 1];
        for (int i = 0; i < n; i++) {
            if (nums[i] > arr[i]) return false;
        }
        return true;
    }
}