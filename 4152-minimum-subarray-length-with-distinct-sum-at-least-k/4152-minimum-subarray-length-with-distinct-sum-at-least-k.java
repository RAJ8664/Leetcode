class Solution {
    public int minLength(int[] nums, int k) {
        int n = nums.length;
        int low = 1, high = n, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, nums, k)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
    private boolean ok(int len, int arr[], int k) {
        int n = arr.length;
        long currentSum = 0;
        int freq[] = new int[(int)(1e5 + 1)];
        for (int i = 0; i < len; i++) {
            if (freq[arr[i]] == 0)
                currentSum += arr[i] * 1L;
            freq[arr[i]]++;
        }
        if (currentSum >= k)
            return true;
        int start = 0;
        for (int i = len; i < n; i++) {
            int current = arr[i], prev = arr[start];
            freq[arr[start]]--;
            if (freq[arr[start]] == 0)
                currentSum -= arr[start] * 1L;
            if (freq[arr[i]] == 0)
                currentSum += arr[i] * 1L;
            freq[arr[i]]++;
            if (currentSum >= k)
                return true;
            start++;
        }
        return false;
    }
}