class Solution {
    int dir[][] = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    private int dp[][][][];
    public int lenOfVDiagonal(int[][] grid) {
        int n = grid.length, m = grid[0].length; 
        int maxi = 0;

        dp = new int[n + 1][m + 1][dir.length + 1][2];
        for (int current[][][] : dp) {
            for (int current1[][] : current) {
                for (int current2[] : current1) 
                    Arrays.fill(current2, -1);
            }
        } 

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        maxi = Math.max(maxi, solve(i, j, k, 1, grid));
                    }
                }
            }
        }

        return maxi;
    }

    private int solve(int currRow, int currCol, int currDirIdx, int canRotate, int grid[][]) {
        if (currRow < 0 || currCol < 0 || currRow >= grid.length || currCol >= grid[0].length)
            return 0;

        if (dp[currRow][currCol][currDirIdx][canRotate] != -1)
            return dp[currRow][currCol][currDirIdx][canRotate];
    
        if (grid[currRow][currCol] == 1) {
            int nextRow = currRow + dir[currDirIdx][0], nextCol = currCol + dir[currDirIdx][1];
            int op1 = 1, op2 = 0;
            if (nextRow < grid.length && nextCol < grid[0].length && nextRow >= 0 && nextCol >= 0)
                if (grid[nextRow][nextCol] == 2)
                    op1 += 1 + solve(nextRow, nextCol, currDirIdx, canRotate, grid);
            
            return dp[currRow][currCol][currDirIdx][canRotate] = op1;
        }

        else if (grid[currRow][currCol] == 2) {
            int nextRow = currRow + dir[currDirIdx][0], nextCol = currCol + dir[currDirIdx][1];
            int op1 = 0, op2 = 0;
            if (nextRow < grid.length && nextCol < grid[0].length && nextRow >= 0 && nextCol >= 0) {
                if (grid[nextRow][nextCol] == 0) 
                    op1 = 1 + solve(nextRow, nextCol, currDirIdx, canRotate, grid);      
            }
            //or rotate it from here;
            if (canRotate == 1) {
                int nextDirIdx = (currDirIdx + 1) % 4;
                int newNextRow = currRow + dir[nextDirIdx][0], newNextCol = currCol + dir[nextDirIdx][1];
                if (newNextRow >= 0 && newNextRow < grid.length && newNextCol >= 0 && newNextCol < grid[0].length) {
                    if (grid[newNextRow][newNextCol] == 0) {
                        op2 = 1 + solve(newNextRow, newNextCol, nextDirIdx, 0, grid);
                    }
                }
            }
            return dp[currRow][currCol][currDirIdx][canRotate] = Math.max(op1, op2);
        }
        
        else if (grid[currRow][currCol] == 0) {
            int nextRow = currRow + dir[currDirIdx][0], nextCol = currCol + dir[currDirIdx][1];
            int op1 = 0, op2 = 0;
            if (nextRow < grid.length && nextCol < grid[0].length && nextRow >= 0 && nextCol >= 0) {
                if (grid[nextRow][nextCol] == 2)
                    op1 = 1 + solve(nextRow, nextCol, currDirIdx, canRotate, grid);      
            }
            //or rotate it from here;
            if (canRotate == 1) {
                int nextDirIdx = (currDirIdx + 1) % 4;
                int newNextRow = currRow + dir[nextDirIdx][0], newNextCol = currCol + dir[nextDirIdx][1];
                if (newNextRow >= 0 && newNextRow < grid.length && newNextCol >= 0 && newNextCol < grid[0].length) {
                    if (grid[newNextRow][newNextCol] == 2) {
                        op2 = 1 + solve(newNextRow, newNextCol, nextDirIdx, 0, grid);
                    }
                }
            }  
            return dp[currRow][currCol][currDirIdx][canRotate] = Math.max(op1, op2); 
        }
        return 0;
    }
}