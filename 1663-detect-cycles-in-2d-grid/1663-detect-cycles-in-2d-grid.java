class Solution {
    static int n, m;
    static char arr[][];
    
    static boolean dfs(int r, int c, int parI, int parJ, int visited[][]) {
        int dir[][] = {{0, 1},{0, -1},{-1, 0},{1, 0}};
        visited[r][c] = 1;
        boolean ans = false;
        for (int i = 0; i < 4; i++) {
            int nr = r + dir[i][0], nc = c + dir[i][1];
            if (nr >= 0 && nc >= 0 && nr < n && nc < m && arr[nr][nc] == arr[r][c]) {
                if (visited[nr][nc] == 1 && nr != parI && nc != parJ) return true;
                if (visited[nr][nc] != 1) ans = ans || dfs(nr, nc, r, c, visited);
            }
        }
        return ans;
    }
    
    public boolean containsCycle(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        arr = grid;

        int visited[][] = new int[n][m];
        for (var a : visited) Arrays.fill(a,-1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){
                char ch = grid[i][j];
                if (visited[i][j]!=1) {
                    boolean ans = dfs(i, j, -1, -1, visited);
                    if (ans == true) return true;
                }
            }
        }
        return false;
    }
}