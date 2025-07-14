import java.util.Arrays;

class Solution {
    static class Pair {
        int maxLen, count;
        public Pair(int maxLen, int count) {
            this.maxLen = maxLen;
            this.count = count;
        }
        @Override
        public String toString() {
            return "Pair{" +
                   "maxLen=" + maxLen +
                   ", count=" + count +
                   '}';
        }
    }
    private Pair[][] dp;
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        dp = new Pair[n + 1][n + 1];
        for (Pair current[] : dp)
            Arrays.fill(current, null);
        return solve(0, -1, nums).count;
    }

    private Pair solve(int ind, int prev, int arr[]) {
        if (ind >= arr.length)
            return new Pair(0, 1);

        if (dp[ind][prev + 1] != null)
            return dp[ind][prev + 1];

        Pair op1 = solve(ind + 1, prev, arr);

        int op2maxLen = 0, op2count = 0;
        if (prev == -1 || arr[ind] > arr[prev]) {
            Pair op2 = solve(ind + 1, ind, arr);
            op2maxLen = 1 + op2.maxLen;
            op2count = op2.count;
        }

        Pair ans = new Pair(0, 0);
        if (op1.maxLen > op2maxLen) {
            ans.maxLen = op1.maxLen;
            ans.count = op1.count;
        } else if (op2maxLen > op1.maxLen) {
            ans.maxLen = op2maxLen;
            ans.count = op2count;
        } else if (op1.maxLen == op2maxLen) {
            ans.maxLen = op1.maxLen;
            ans.count = op1.count + op2count;
        }
        return dp[ind][prev + 1] = ans;
    }

}