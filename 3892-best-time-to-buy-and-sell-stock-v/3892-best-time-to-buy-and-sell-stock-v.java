class Solution {
    private long dp[][][][];
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        dp = new long[n + 1][2][2][k + 1];
        for (long current[][][] : dp) for (long current1[][] : current) for (long current2[] : current1) Arrays.fill(current2, -1);
        long res = solve(0, 0, 0, k, prices);
        return res;
    }
    private long solve(int ind, int hasBought, int hasSold, int Rem, int prices[]) {
        if (ind == prices.length - 1) {
            if (hasBought == 1) return prices[ind];
            else if (hasSold == 1) return -prices[ind];
            return 0;
        }
        if (ind >= prices.length || Rem <= 0) return 0;
        if (dp[ind][hasBought][hasSold][Rem] != -1) return dp[ind][hasBought][hasSold][Rem];
        if (hasBought == 0 && hasSold == 0) {
            long op1 = 0, op2 = 0, op3 = 0;
            op1 = -prices[ind] + solve(ind + 1, 1, 0, Rem, prices);
            op2 = prices[ind] + solve(ind + 1, 0, 1, Rem, prices);
            op3 = solve(ind + 1, 0, 0, Rem, prices);
            return dp[ind][hasBought][hasSold][Rem] = Math.max(op1, Math.max(op2, op3));
        }
        else if (hasBought == 1 && hasSold == 0) {
            long op1 = 0, op2 = 0;
            op1 = prices[ind] + solve(ind + 1, 0, 0, Rem - 1, prices);
            op2 = solve(ind + 1, hasBought, hasSold, Rem, prices);
            return dp[ind][hasBought][hasSold][Rem] = Math.max(op1, op2);
        }
        else if (hasBought == 0 && hasSold == 1) {
            long op1 = 0, op2 = 0;
            op1 = -prices[ind] + solve(ind + 1, 0, 0, Rem - 1, prices);
            op2 = solve(ind + 1, hasBought, hasSold, Rem, prices);
            return dp[ind][hasBought][hasSold][Rem] = Math.max(op1, op2);
        }
        return 0;
    }
}