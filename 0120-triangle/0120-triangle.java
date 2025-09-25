class Solution {
    private int dp[][];
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        dp = new int[n + 1][triangle.get(n - 1).size() + 1];
        for (int current[] : dp) 
            Arrays.fill(current, -(int)(1e9));
        return solve(0, 0, triangle);
    }
    private int solve(int row, int col, List<List<Integer>> triangle) {
        if (row >= triangle.size())
            return 0;

        if (col >= triangle.get(row).size()) {
            return Integer.MAX_VALUE / 10;
        }
        
        if (dp[row][col] != -(int)(1e9))
            return dp[row][col];
        
        int op1 = triangle.get(row).get(col) + solve(row + 1, col, triangle);
        int op2 = triangle.get(row).get(col) + solve(row + 1, col + 1, triangle);       

        return dp[row][col] = Math.min(op1, op2);
    }
}