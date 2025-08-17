class Solution {
    public long minArraySum(int[] nums, int k) {
        int n = nums.length;
        long total = 0;
        for (int ele : nums) 
            total += ele;
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, 0L);
        long dp[] = new long[n + 1];
        long pref = 0;
        for (int i = 1; i <= n; i++) {
            pref += nums[i - 1];
            dp[i] = dp[i - 1];
            int rem = (int)(pref % k);
            if (map.containsKey(rem)) {
                dp[i] = Math.max(dp[i], map.get(rem) + pref);
            } 
            map.put(rem, Math.max(map.getOrDefault(rem, Long.MIN_VALUE), dp[i] - pref));
        } 
        return total - dp[n];
    }
}