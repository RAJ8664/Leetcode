class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n;
        int ans = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, nums)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }
    private boolean ok(int mid, int arr[]) {
        int n = arr.length;
        int freq[] = new int[33];
        HashSet<Integer> pos = new HashSet<>();
        for (int i = 0; i < mid; i++) {
            int current = arr[i];
            for (int j = 0; j < 32; j++) {
                int bit = ((current >> j) & 1);
                if (bit > 0) {
                    freq[j]++;
                }
            }
        }
        boolean flag = true;
        for (int i = 0; i <= 32; i++) {
            if (freq[i] > 1) flag = false;
        }
        if (flag == true) return true;
        int start = 0;
        for (int i = mid; i < n; i++) {
            int last = arr[start];
            for (int j = 0; j < 32; j++) {
                int bit = ((last >> j) & 1);
                if (bit > 0) {
                    freq[j]--;
                }
            }
            int current = arr[i];
            for (int j = 0; j < 32; j++) {
                int bit = ((current >> j) & 1);
                if (bit > 0) {
                    freq[j]++;
                }
            }
            flag = true;
            for (int j = 0; j <= 32; j++) {
                if (freq[j] > 1) flag = false;
            }
            if (flag == true) return true;
            start++;
        }
        return false;
    }
}