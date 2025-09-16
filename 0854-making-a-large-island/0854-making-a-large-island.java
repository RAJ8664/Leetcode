class Solution {
    private int vis[][];
    private int id[][];
    private int size[];
    private int currId;
    static class Pair {
        int row, col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + ")";
        }
    }    
    public int largestIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        vis = new int[n][m];
        id = new int[n][m];
        size = new int[n * m + 1];
        currId = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && grid[i][j] == 1) {
                    BFS(i, j, grid);
                    currId++;
                }
            }
        }

        int maxi = 1;
        for (int x : size) 
            maxi = Math.max(maxi, x);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    HashSet<Integer> ids = new HashSet<>();
                   
                    if (i - 1 >= 0) ids.add(id[i - 1][j]);
                    if (j - 1 >= 0) ids.add(id[i][j - 1]);
                    if (i + 1 < n) ids.add(id[i + 1][j]);
                    if (j + 1 < m) ids.add(id[i][j + 1]);

                    int compSum = 1;
                    for (int curIds : ids) {
                        compSum += size[curIds]; 
                    }
                    maxi = Math.max(maxi, compSum);
                }
            }
        }
        return maxi;
    }

    private void BFS(int startRow, int startCol, int grid[][]) {
        int n = grid.length, m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        int compSize = 1;
    
        q.offer(new Pair(startRow, startCol));
        id[startRow][startCol] = currId;
        vis[startRow][startCol] = 1;
        int dir[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while (q.size() > 0) {
            int currRow = q.peek().row, currCol = q.peek().col;
            q.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow < n && newCol < m && newRow >= 0 && newCol >= 0 && vis[newRow][newCol] == 0 && grid[newRow][newCol] == 1) {
                    vis[newRow][newCol] = 1;
                    q.offer(new Pair(newRow, newCol));
                    id[newRow][newCol] = currId;
                    compSize++;
                }
            }
        }
        size[currId] = compSize;
    }
}