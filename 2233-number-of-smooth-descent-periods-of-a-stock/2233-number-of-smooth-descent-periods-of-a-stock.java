class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long count = 0;
        int prev = -1, len = 1;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                prev = prices[i];
            } else {
                if (prices[i] == prev - 1) {
                    len++;
                } else {
                    count += (len * 1L * (len + 1) / 2);
                    len = 1;
                }
            }
            prev = prices[i];
        }
        count += (len * 1L * (len + 1) / 2);
        return count;
    }
}