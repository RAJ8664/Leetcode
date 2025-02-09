class Solution {
    public long maxScore(int[] points, int m) {
        int n = points.length;
        long low = 0, high = (long)(1e16 + 1);
        long ans = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (ok(mid, points, m)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }
    private boolean ok(long mid, int points[], int moves) {
        int n = points.length;
        long total = 0, sum = 0, skip = 0;
        for(int i = 0; i < n && total <= moves; i++) {
            long req = (mid + points[i] - 1) / points[i];
            if(sum >= req) {
                sum = 0;
                skip++;
            }
            else {
                long p = sum * points[i];
                long ops = (((mid - p) + points[i] - 1) / points[i]);
                total += 2 * ops - 1;
                total += skip;
                sum = Math.max(ops - 1, 0);
                skip = 0;
            }
        }
        return total <= moves;
    }
}