class Solution {
    private int pref[];
    private int mod = 1000000007;
    public int numWays(String s) {
        int n = s.length();
        pref = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1')
                pref[i] = 1;
            if (i - 1 >= 0)
                pref[i] += pref[i - 1];
        }

        long ways = 0;
        for (int i = 0; i < n - 2; i++) {
            int leftOnes = pref[i];

            int left = bsLeft(i + 1, n - 2, pref[i]);
            int right = bsRight(i + 1, n - 2, pref[i]);

            if (left == -1 || right == -1)
                continue;
            int rightOnes = pref[n - 1] - pref[right];

            if (rightOnes == leftOnes)
                ways = (ways + right - left + 1) % mod;
        }
        return (int)(ways);
    }

    private int bsLeft(int start, int end, int req) {
        int low = start, high = end;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int total = pref[mid];
            if (start - 1 >= 0)
                total -= pref[start - 1];
            if (total > req)
                high = mid - 1;
            else if (total == req) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    private int bsRight(int start, int end, int req) {
        int low = start, high = end, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int total = pref[mid];
            if (start - 1 >= 0)
                total -= pref[start - 1];
            if (total > req)
                high = mid - 1;
            else if (total == req) {
                ans = mid;
                low = mid + 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
}