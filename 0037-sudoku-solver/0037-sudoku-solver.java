class Solution {
    public void solveSudoku(char[][] board) {
        int n = board.length, m = board[0].length;
        solve_sudoku(board);
    }
    private boolean solve_sudoku(char board[][]) {
        int n = board.length, m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.') {
                    for (char t = '1'; t <= '9'; t++) {
                        if (isPossible(t, i, j, board)) {
                            board[i][j] = t;
                            if (solve_sudoku(board) == true) 
                                return true;
                            else 
                                board[i][j] = '.'; 
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isPossible(char ch, int row, int col, char board[][]) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == ch) 
                return false;
            if (board[row][i] == ch) 
                return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == ch) 
                return false;
        }
        return true;
    }
}

