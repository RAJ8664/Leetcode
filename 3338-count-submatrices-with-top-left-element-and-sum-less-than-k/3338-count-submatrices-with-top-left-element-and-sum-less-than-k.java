class Solution {
    private int pref[][]; 
    
    public int countSubmatrices(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        pref = new int[n][m];

        buildPref(grid); 

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    if (grid[0][0] <= k) count++;
                    continue;
                }
                int total = 0;
                total += query(0, 0, i, j);
                if (total <= k) count++;
            }
        } 

        return count;
    }

    private void buildPref(int matrix[][]) {
        int n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pref[i][j] = matrix[i][j];
                if (i > 0) pref[i][j] += pref[i - 1][j];
                if (j > 0) pref[i][j] += pref[i][j - 1];
                if (i > 0 && j > 0) pref[i][j] -= pref[i - 1][j - 1];
            }
        }
    }

    public int query(int x1, int y1, int x2, int y2) {
        int res = pref[x2][y2];
        if (x1 > 0) res -= pref[x1 - 1][y2];
        if (y1 > 0) res -= pref[x2][y1 - 1];
        if (x1 > 0 && y1 > 0) res += pref[x1 - 1][y1 - 1];
        return res;
    }
}