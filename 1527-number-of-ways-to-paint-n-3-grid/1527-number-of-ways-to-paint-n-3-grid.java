class Solution {
    static int MOD = (int)(1e9 + 7);
    public int numOfWays(int n) {
        long first = 6, second = 6;
        for (int i = 2; i <= n; i++) {
            long new_first = (2 * first + 2 * second) % MOD;
            long new_second = (2 * first + 3 * second) % MOD;
            first = new_first;
            second = new_second;
        }
        return (int)((first + second) % MOD);
    }
}