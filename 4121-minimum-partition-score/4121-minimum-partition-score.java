import java.util.*;

class Solution {
    private long prefixSum[];

    static class Pair {
        long minScore;
        int count;
        public Pair(long minScore, int count) {
            this.minScore = minScore;
            this.count = count;
        }
        @Override
        public String toString() {
            return "Pair{" +
                   "minScore=" + minScore +
                   ", count=" + count +
                   '}';
        }
    }

    public long minPartitionScore(int[] nums, int k) {
        int n = nums.length;
        prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++)
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        long low = 0, high = (long) 1e16, ans = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            Pair res = computeDP(prefixSum, mid);
            if (res.count >= k) {
                ans = res.minScore - (long) k * mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }

    private Pair computeDP(long[] prefixSum, long penalty) {
        int n = prefixSum.length - 1;
        long[] dp = new long[n + 1];
        int[] count = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Long.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                long sumArr = prefixSum[i] - prefixSum[j];
                long val = (sumArr * (sumArr + 1)) / 2 + penalty;
                if (dp[j] + val < dp[i]) {
                    dp[i] = dp[j] + val;
                    count[i] = count[j] + 1;
                } else if (dp[j] + val == dp[i])
                    count[i] = Math.max(count[i], count[j] + 1);
            }
        }
        return new Pair(dp[n], count[n]);
    }
}