class Solution {
    public long minCuttingCost(int n, int m, int k) {
        if (n <= k && m <= k) return 0;
        if (n > k) return (n - k) * 1L * k;
        return (m - k) * 1L * k; 
    }
}