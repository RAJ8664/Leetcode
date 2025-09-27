class Solution {
    private int pref[][];
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        build(matrix);

        int count = 0;
        for (int x1 = 0; x1 < n; x1++) {
            for (int y1 = 0; y1 < m; y1++) {
                for (int x2 = x1; x2 < n; x2++) {
                    for (int y2 = y1; y2 < m; y2++) {
                        if (query(x1, y1, x2, y2) == target)
                            count++;
                    }
                }
            }
        }
        return count;

    }

    private void build(int arr[][]) {
        int n = arr.length, m = arr[0].length;
        pref = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pref[i][j] = arr[i][j];
                if (i - 1 >= 0)
                    pref[i][j] += pref[i - 1][j];
                if (j - 1 >= 0)
                    pref[i][j] += pref[i][j - 1];
                if (i - 1 >= 0 && j - 1 >= 0)
                    pref[i][j] -= pref[i - 1][j - 1];
            }
        }
    }

    private int query(int x1, int y1, int x2, int y2) {
        int total = pref[x2][y2];
        if (x1 - 1 >= 0)
            total -= pref[x1 - 1][y2];
        if (y1 - 1 >= 0)
            total -= pref[x2][y1 - 1];
        if (x1 - 1 >= 0 && y1 - 1 >= 0)
            total += pref[x1 - 1][y1 - 1];
        return total;
    }
}