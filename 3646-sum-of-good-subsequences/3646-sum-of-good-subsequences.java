class Solution {
    private int mod = (int)(1e9 + 7);
    public int sumOfGoodSubsequences(int[] nums) {
        HashMap<Long, long[]> dp = new HashMap<>();
        long res = 0;
        for (long i : nums) {
            long sum = i, count = 1;
            if (dp.containsKey(i - 1)) {
                long[] v = dp.get(i - 1);
                sum = (sum + v[1] * i) % mod;
                sum = (sum + v[0]) % mod;
                count = (count + v[1]) % mod;
            }
            if (dp.containsKey(i + 1)) {
                long[] v = dp.get(i + 1);
                sum = (sum + v[1] * i) % mod;
                sum = (sum + v[0]) % mod;
                count = (count + v[1]) % mod;
            }
            res = (res + sum) % mod;
            long[] v = dp.getOrDefault(i, new long[] {0, 0});
            v[0] = (v[0] + sum) % mod;
            v[1] = (v[1] + count) % mod;
            dp.put(i, v);
        }
        return (int)res;
    }
}