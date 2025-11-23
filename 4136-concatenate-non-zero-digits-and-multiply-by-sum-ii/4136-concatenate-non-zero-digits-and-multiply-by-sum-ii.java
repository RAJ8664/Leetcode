class Solution {
    private final long MOD = (long)(1e9 + 7);
    private long prefHash[], prefSum[], pow10[];
    private int count[];
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        prefHash = new long[n + 1];
        prefSum = new long[n + 1];
        pow10 = new long[n + 1];
        count = new int[n + 1];

        pow10[0] = 1;
        for (int i = 1; i <= n; i++)
            pow10[i] = (pow10[i - 1] * 10) % MOD;

        for (int i = 1; i <= n; i++) {
            int d = s.charAt(i - 1) - '0';
            prefHash[i] = prefHash[i - 1];
            prefSum[i] = prefSum[i - 1];
            count[i] = count[i - 1];
            if (d != 0) {
                prefHash[i] = (prefHash[i] * 10 + d) % MOD;
                prefSum[i] += d;
                count[i]++;
            }
        }
        int q = queries.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int l = queries[i][0] + 1, r = queries[i][1] + 1;
            int k = count[r] - count[l - 1];
            long sum = prefSum[r] - prefSum[l - 1];
            if (k == 0 || sum == 0) {
                ans[i] = 0;
                continue;
            }
            long x = prefHash[r] - (prefHash[l - 1] * pow10[k] % MOD);
            if (x < 0) x += MOD;
            ans[i] = (int) ((x % MOD) * (sum % MOD) % MOD);
        }
        return ans;
    }
}
