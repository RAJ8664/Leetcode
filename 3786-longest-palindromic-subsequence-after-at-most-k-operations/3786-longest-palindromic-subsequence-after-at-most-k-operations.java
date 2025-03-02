class Solution {
    static public int longestPalindromicSubsequence(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        Integer[][][] dp = new Integer[k + 1][n][n];
        return solve(0, n - 1, k, arr, dp);
    }
    static int solve(int i, int j, int k, char[] arr, Integer[][][] dp) {
        if (i > j) return 0;
        if (i == j) return 1;
        if(dp[k][i][j] != null) return dp[k][i][j];
        int a = -1000000, b = -1000000, c = -1000000, d = -1000000;
        if (arr[i] == arr[j]) a = 2 + solve(i + 1, j - 1, k, arr, dp);
        else {
            b = solve(i + 1, j, k, arr, dp);
            c = solve(i, j - 1, k, arr, dp);
            int x = (int) (arr[i] - 97);
            int y = (int) (arr[j] - 97);
            int first = Math.abs(x - y);
            x += 26;
            int second = Math.abs(x - y);
            x -= 26;
            y += 26;
            int third = Math.abs(x - y);
            int min = Math.min(first, Math.min(second, third));
            if(k >= min) d = 2 + solve(i + 1, j - 1, k - min, arr, dp);
        }
        return dp[k][i][j] =  Math.max(a, Math.max(b, Math.max(c, d)));
    }
}
