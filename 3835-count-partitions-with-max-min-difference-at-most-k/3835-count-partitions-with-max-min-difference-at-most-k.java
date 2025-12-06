class Solution {
    private int mod = (int)(1e9 + 7);
    private long dp[], prefix[];
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        dp = new long[n + 1];
        prefix = new long[n + 1];
        TreeMap<Integer, Integer> count = new TreeMap<>();

        dp[0] = prefix[0] = 1;
        int start = 0;
        for (int i = 0; i < n; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            while (count.lastKey() - count.firstKey() > k) {
                int currVal = nums[start];
                int x = count.get(currVal) - 1;
                if (x == 0)
                    count.remove(currVal);
                else 
                    count.put(currVal, x);
                start++;
            }
            long left = (start > 0 ? prefix[start - 1] : 0);
            dp[i + 1] = (prefix[i] - left + mod) % mod;
            prefix[i + 1] = (prefix[i] + dp[i + 1]) % mod;        
        } 
        return (int)(dp[n]);
    }
}