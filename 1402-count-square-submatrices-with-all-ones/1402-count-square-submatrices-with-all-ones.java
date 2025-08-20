class Solution {
    private int prefix[][];
    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        prefix = new int[n + 1][m + 1];
        build(matrix);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    int x1 = i, y1 = j, x2 = i, y2 = j;
                    while (x2 < n && y2 < m) {
                        int reqArea = (y2 - y1 + 1) * (x2 - x1 + 1);
                        if (query(x1 + 1, y1 + 1, x2 + 1, y2 + 1) == reqArea) 
                            count++;
                        x2++; y2++;
                    }
                }
            }
        }
        return count;
    }
    private int query(int x1, int y1, int x2, int y2) {
        return prefix[x2][y2] + prefix[x1 - 1][y1 - 1] - prefix[x1 - 1][y2] - prefix[x2][y1 - 1];
    }
    private void build(int matrix[][]) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] = matrix[i - 1][j - 1] + prefix[i][j - 1] + prefix[i - 1][j] - prefix[i - 1][j - 1];
            }
        }
    }
}