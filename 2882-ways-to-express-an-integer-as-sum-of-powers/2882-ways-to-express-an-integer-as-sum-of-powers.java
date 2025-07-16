import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    private int dp[][];
    private int mod = 1000000007;
    public int numberOfWays(int n, int x) {
        ArrayList<Integer> arr = new ArrayList<>();
        int idx = 1;
        while (true) {
            int current = (int)(Math.pow(idx++, x));
            if (current > n)
                break;
            arr.add(current);
        }
        dp = new int[arr.size() + 1][n + 1];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        int res = solve(0, n, arr);
        return res;
    }
    private int solve(int ind, int sumLeft, ArrayList<Integer> arr) {
        if (sumLeft == 0)
            return 1;
        if (ind >= arr.size()) {
            if (sumLeft == 0)
                return 1;
            return 0;
        }
        if (dp[ind][sumLeft] != -1)
            return dp[ind][sumLeft];

        int op1 = solve(ind + 1, sumLeft, arr);
        int op2 = 0;
        if (sumLeft >= arr.get(ind))
            op2 = solve(ind + 1, sumLeft - arr.get(ind), arr);

        return dp[ind][sumLeft] = (op1 + op2) % mod;
    }
}