class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int n = workerTimes.length;
        long low = 1;
        long high = (long)(1e18);
        long ans = -1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (check(mid, mountainHeight, workerTimes)) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }

    static boolean check(long mid, int k , int arr[]) {
        int n = arr.length;
        long total_height = 0;
        for (int i = 0; i < n; i++) {
            long current = arr[i];
            /*
                sum += arr[i] * temp;
                (1 + 2 + 3 + .... + x) * arr[i] <= mid;
                maximum x such that (1 + 2 + 3 + .... + x) <= mid / arr[i];
                x * (x + 1) / 2 <= mid / arr[i];
                x * (x + 1) <= 2 * mid / arr[i];
                arr[i] * (x * (x + 1) / 2) <= mid;

            */
            long low = 1;
            long high = k + 1;
            long temp = -1;
            while (low <= high) {
                long x = low + (high - low) / 2;
                long compute = current * 1L * (x * (x + 1) / 2);
                if (compute <= mid) {
                    temp = x;
                    low = x + 1;
                }
                else high = x - 1;
            }
            total_height += high;
        }
        return total_height >= k;
    }
}