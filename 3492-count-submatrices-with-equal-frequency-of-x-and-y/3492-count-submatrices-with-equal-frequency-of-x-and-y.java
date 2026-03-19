class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] cntX = new int[m][n];
        int[][] cntY = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'X') cntX[i][j]++;
                if (grid[i][j] == 'Y') cntY[i][j]++;
                if (i > 0) {
                    cntX[i][j] += cntX[i - 1][j];
                    cntY[i][j] += cntY[i - 1][j];
                }
                if (j > 0) {
                    cntX[i][j] += cntX[i][j - 1];
                    cntY[i][j] += cntY[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    cntX[i][j] -= cntX[i - 1][j - 1];
                    cntY[i][j] -= cntY[i - 1][j - 1];
                }
                if (cntX[i][j] == cntY[i][j] && cntX[i][j] > 0) res++;
            }
        }
        return res;
    }
}