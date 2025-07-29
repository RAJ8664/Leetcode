import java.util.*;
class Solution {
    private int[][][] memo;
    private int[] prefix;
    private int k;
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        this.k = k;
        
        if ((n - 1) % (k - 1) != 0) return -1;

        prefix = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + stones[i];

        memo = new int[n][n][k + 1];
        for (int[][] layer : memo)
            for (int[] row : layer)
                Arrays.fill(row, -1);
        return dfs(0, n - 1, 1);
    }

    private int dfs(int i, int j, int piles) {
        if (memo[i][j][piles] != -1) return memo[i][j][piles];

        if (j - i + 1 < piles) return Integer.MAX_VALUE;

        if (i == j) {
            return piles == 1 ? 0 : Integer.MAX_VALUE;
        }

        int res = Integer.MAX_VALUE;
        if (piles > 1) {
            for (int m = i; m < j; m += (k - 1)) {
                int left = dfs(i, m, 1);
                int right = dfs(m + 1, j, piles - 1);
                if (left == Integer.MAX_VALUE || right == Integer.MAX_VALUE) continue;
                res = Math.min(res, left + right);
            }
        } else {
            int temp = dfs(i, j, k);
            if (temp != Integer.MAX_VALUE)
                res = temp + prefix[j + 1] - prefix[i];
        }
        return memo[i][j][piles] = res;
    }
}
