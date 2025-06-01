class Solution {
    public long distributeCandies(int n, int limit) {
        return combCount(n) - 3 * combCount(n - (limit + 1)) + 3 * combCount(n - 2 * (limit + 1))- combCount(n - 3 * (limit + 1));
    }
    private long combCount(long sum) {
        if (sum < 0) return 0;
        return (sum + 2) * (sum + 1) / 2;
    }
}