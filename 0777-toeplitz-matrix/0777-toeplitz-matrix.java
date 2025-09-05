class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        for (int j = 0; j < m; j++) {
            int startRow = 0, startCol = j;
            while (startRow < n && startCol < m) {
                if (matrix[startRow][startCol] != matrix[0][j]) {
                    return false;
                }
                startRow++;
                startCol++;
            }
        }
        for (int i = 0; i < n; i++) {
            int startRow = i, startCol = 0;
            while (startRow < n && startCol < m) {
                if (matrix[startRow][startCol] != matrix[i][0]) {
                    return false;
                }
                startRow++;
                startCol++;
            }
        }
        return true;
    }
}