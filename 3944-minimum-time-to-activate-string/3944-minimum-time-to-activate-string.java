class Solution {
    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, order, k, s)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    private boolean ok(int target, int arr[], int k, String given) {
        int n = arr.length;
        int cost[] = new int[n];
        for (int i = 0; i <= target; i++)
            cost[arr[i]] = 1;

        if (solve(cost, 1) >= k)
            return true;
        return false;
    }

    private long solve(int arr[], int k) {
        int n = arr.length;
        int start = 0, end = 0, sum = arr[0];
        long count = 0;
        while (start < n && end < n) {
            if (sum < k) {
                end++;
                if (end >= start)
                    count += end * 1L  - start * 1L;
                if (end < n)
                    sum += arr[end];
            } else {
                sum -= arr[start];
                start++;
            }
        }
        long total = (n * 1L * (n + 1) / 2);
        return total - count;
    }
}