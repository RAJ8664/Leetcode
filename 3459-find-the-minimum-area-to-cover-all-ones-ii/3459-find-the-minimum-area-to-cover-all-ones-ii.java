class Solution {
    public int minimumSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int res = Integer.MAX_VALUE;
        res = Math.min(res, getAns(grid));

        grid = rightRotate(grid);

        res = Math.min(res, getAns(grid));
        return res;
    }

    private int getAns(int grid[][]) {
        int n = grid.length, m = grid[0].length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j <= m - 2; j++) {
                int r1 = minSum1(0, i, 0, m - 1, grid);
                int r2 = minSum1(i + 1, n - 1, 0, j, grid);
                int r3 = minSum1(i + 1, n - 1, j + 1, m - 1, grid);
                res = Math.min(res, r1 + r2 + r3);
            }
        }

        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j <= m - 2; j++) {
                int r1 = minSum1(0, i, 0, j, grid);
                int r2 = minSum1(0, i, j + 1, m - 1, grid);
                int r3 = minSum1(i + 1, n - 1, 0, m - 1, grid);
                res = Math.min(res, r1 + r2 + r3);
            }
        }

        for (int i1 = 0; i1 <= n - 3; i1++) {
            for (int i2 = i1 + 1; i2 <= n - 2; i2++) {
                int r1 = minSum1(0, i1, 0, m - 1, grid);
                int r2 = minSum1(i1 + 1, i2, 0, m - 1, grid);
                int r3 = minSum1(i2 + 1, n - 1, 0, m - 1, grid);
                res = Math.min(res, r1 + r2 + r3);
            }
        }
        return res;
    }

    private int[][] rightRotate(int grid[][]) {
        int n = grid.length, m = grid[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                res[j][n - 1 - i] = grid[i][j];
        }

        grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                grid[i][j] = res[i][j];
        }
        return grid;
    }

    private int minSum1(int startRow, int endRow, int startCol, int endCol, int grid[][]) {
        int n = grid.length, m = grid[0].length;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (grid[i][j] == 1) {
                    minX = Math.min(minX, i);
                    maxX = Math.max(maxX, i);
                    minY = Math.min(minY, j);
                    maxY = Math.max(maxY, j);
                }
            }
        }
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
}