import java.util.Arrays;
import java.util.List;

class Solution {
    private int dp[][];
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n = nums.size();
        dp = new int[n + 1][target + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        int res = solve(0, target, nums);
        System.out.println(Integer.MIN_VALUE / 10);
        if (res <= 0)
            return -1;
        return res;
    }
    private int solve(int ind, int sumLeft, List<Integer> arr) {
        if (ind >= arr.size()) {
            if (sumLeft == 0)
                return 0;
            return Integer.MIN_VALUE / 10;
        }

        if (dp[ind][sumLeft] != -1)
            return dp[ind][sumLeft];

        int op1 = solve(ind + 1, sumLeft, arr);
        int op2 = Integer.MIN_VALUE / 10;
        if (sumLeft >= arr.get(ind))
            op2 = 1 + solve(ind + 1, sumLeft - arr.get(ind), arr);

        return dp[ind][sumLeft] = Math.max(op1, op2);
    }
}