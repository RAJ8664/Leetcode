class Solution {
    public long countCommas(long n) {
        if (n < 1000) return 0;
        long start = 1000L, res = 0;
        while (start <= n) {
            res += n - start * 1L+ 1;
            start = start * 1000L;
        }
        return res;
    }
}