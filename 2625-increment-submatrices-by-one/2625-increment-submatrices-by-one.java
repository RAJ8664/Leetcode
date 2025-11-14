class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] mat = new int[n][n];
        for (int[] q : queries) {
            int row1 = q[0], col1 = q[1], row2 = q[2], col2 = q[3];
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    mat[i][j] += 1;
                }
            }
        }
        return mat;
    }
}