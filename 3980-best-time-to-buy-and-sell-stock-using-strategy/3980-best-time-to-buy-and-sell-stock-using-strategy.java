class Solution {
    private long pref[];
    private long pricePref[];
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        pref = new long[n];
        pricePref = new long[n];
        pref[0] = strategy[0] * 1L * prices[0];
        pricePref[0] = prices[0] * 1L;
        for (int i = 1; i < n; i++) { 
            pref[i] = pref[i - 1] + (prices[i] * 1L * strategy[i]);
            pricePref[i] = pricePref[i - 1] + prices[i] * 1L;
        } 
        long ans = 0;
        for (int i = 0; i < n; i++) 
            ans += prices[i] * 1L * strategy[i];
        for (int i = 0; i < n; i++) {
            long currentSum = 0;
            if (i + k - 1 < n) {
                if (i - 1 >= 0) 
                    currentSum += pref[i - 1];
                if (i + k < n) {
                    currentSum += pref[n - 1];
                    currentSum -= pref[i + k - 1];
                }
                currentSum += pricePref[i + k - 1];
                currentSum -= pricePref[i + (k / 2) - 1];
                ans = Math.max(ans, currentSum); 
            }
        }
        return ans;         
    }
}