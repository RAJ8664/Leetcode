class Solution {
    public boolean isValidSudoku(char[][] board) {
        int row = board.length, col = board[0].length;
        
        for (int i = 0; i < row; i++)
            if (!validRow(board, i))
                return false;

        for (int j = 0; j < col; j++) {
            if (!validCol(board, j))
                return false;
        }

        for (int i = 0; i < row; i += 3) {
            for (int j = 0; j < col; j += 3) {
                if (!validSquare(board, i, j))
                    return false;
            }
        }
        return true;
    }

    public static boolean validRow(char[][] board, int row) {
        boolean vis[] = new boolean[10];
        for(int j  = 0; j < board[0].length; j++) {
            if (board[row][j] == '.') 
                continue;
            if (vis[board[row][j] - '1'] == true)
                return false; 
            vis[board[row][j] - '1'] = true;
        }
        return true;
    }
    
    public static boolean validCol(char board[][], int col) {
        boolean vis[] = new boolean[10];
        for(int i = 0; i < board.length; i++) {
            if (board[i][col] == '.') 
                continue;
            if (vis[board[i][col] - '1'] == true)
                return false;
            vis[board[i][col] - '1'] = true;
        }
        return true;
    }

    public static boolean validSquare(char board[][], int row, int col) {
        boolean vis[] = new boolean[10];
        for (int i = row; i < row + 3; i++) {
            for (int j =  col; j < col + 3; j++) {
                if (board[i][j] == '.') 
                    continue;
                if (vis[board[i][j] - '1'] == true) 
                    return false;
                vis[board[i][j] - '1'] = true; 
            }
        }
        return true;
    }
}