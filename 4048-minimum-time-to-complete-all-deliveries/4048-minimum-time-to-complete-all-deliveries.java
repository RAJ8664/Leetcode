class Solution {
    public long minimumTime(int[] d, int[] r) {
        long low = 0, high = (long)(1e18), ans = -1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (ok(mid, d, r)) {
                ans = mid;
                high = mid - 1;
            } else 
                low = mid + 1;
        } 
        return ans;
    }
    private boolean ok(long target, int d[], int r[]) {
        long c1 = target / r[0] * 1L, c2 = target / r[1] * 1L;
        long lcm = r[0] * 1L / gcd(r[0] * 1L, r[1] * 1L) * 1L * r[1];
        long both = target / lcm;
        long oc1 = c2 - both, oc2 = c1 - both;
        long wait = target - (c1 + c2 - both);
        long need1 = d[0] > oc1 ? d[0] - oc1 : 0;
        long need2 = d[1] > oc2 ? d[1] - oc2 : 0;
        return need1 + need2 <= wait; 
    }
    private long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}