import java.util.Arrays;

class Solution {
    private int[][] dp;

    public int longestPalindrome(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        String s = word1 + word2;
        dp = new int[n + m][n + m];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        fillDp(0, n + m - 1, s);

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (word1.charAt(i) == word2.charAt(j))
                    res = Math.max(res, dp[i][n + j]);
            }
        }
        return res;
    }

    private int fillDp(int i, int j, String s) {
        if (i > j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = (i == j ? 1 : 2) + fillDp(i + 1, j - 1, s);
        return dp[i][j] = Math.max(fillDp(i + 1, j, s), fillDp(i, j - 1, s));
    }
}