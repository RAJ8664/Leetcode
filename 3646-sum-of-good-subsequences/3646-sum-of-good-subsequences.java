class Solution {
    private int mod = (int)(1e9 + 7);
    public int sumOfGoodSubsequences(int[] nums) {
        HashMap<Long, long[]> dp = new HashMap<>();
        long res = 0;
        for (long i : nums) {
            long sum = i, count = 1;
            if (dp.containsKey(i - 1)) {
                long[] temp = dp.get(i - 1);
                sum = (sum + temp[1] * i) % mod;
                sum = (sum + temp[0]) % mod;
                count = (count + temp[1]) % mod;
            }
            if (dp.containsKey(i + 1)) {
                long[] temp = dp.get(i + 1);
                sum = (sum + temp[1] * i) % mod;
                sum = (sum + temp[0]) % mod;
                count = (count + temp[1]) % mod;
            }
            res = (res + sum) % mod;
            long[] temp = dp.getOrDefault(i, new long[] {0, 0});
            temp[0] = (temp[0] + sum) % mod;
            temp[1] = (temp[1] + count) % mod;
            dp.put(i, temp);
        }
        return (int)res;
    }
}