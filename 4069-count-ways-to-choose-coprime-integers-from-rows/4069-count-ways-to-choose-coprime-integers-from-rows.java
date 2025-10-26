class Solution {
    private int mod = (int)(1e9 + 7);
    private int dp[][];
    public int countCoprime(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        dp = new int[n + 1][200];
        for (int current[] : dp)
            Arrays.fill(current, -1);
        return solve(0, 0, mat);
    }
    private int solve(int currRow, int gcd, int arr[][]) {
        if (currRow >= arr.length) {
            if (gcd == 1)
                return 1;
            return 0;
        }
        if (dp[currRow][gcd] != -1)
            return dp[currRow][gcd];
        int ans = 0;
        for (int j = 0; j < arr[0].length; j++) {
            int op1 = solve(currRow + 1, GCD(gcd, arr[currRow][j]), arr);
            ans = (ans + op1) % mod;
        }
        return dp[currRow][gcd] = ans;
    }
    private int GCD(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0) 
            return a;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}