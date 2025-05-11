class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int row_sum[] = new int[n];
        int col_sum[] = new int[m];
        int sum = 0;
        for (int i = 0;  i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += grid[i][j];
            }
            row_sum[i] = sum;
        }
        sum = 0;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                sum += grid[i][j];
            }
            col_sum[j] = sum;
        }
        for (int i = 0; i < n - 1; i++) {
            int up_sum = row_sum[i];
            int buttom_sum = row_sum[n - 1] - up_sum;
            if (up_sum == buttom_sum) return true;
        }
        for (int j = 0; j < m - 1; j++) {
            int left_sum = col_sum[j];
            int right_sum = col_sum[m - 1] - left_sum;
            if (left_sum == right_sum) return true;
        }    
        return false;
    }
}