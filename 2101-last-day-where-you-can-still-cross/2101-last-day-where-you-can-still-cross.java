class Solution {
    public boolean isPossible(int m, int n, int t, int[][] cells) {
        int[][] grid = new int[m + 1][n + 1];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; 
        for (int i = 0; i < t; i++) {
            grid[cells[i][0]][cells[i][1]] = 1; 
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (grid[1][i] == 0) {
                queue.offer(new int[]{1, i}); 
                grid[1][i] = 1; 
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1]; 
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr > 0 && nc > 0 && nr <= m && nc <= n && grid[nr][nc] == 0) {
                    grid[nr][nc] = 1;
                    if (nr == m) 
                        return true; 
                    queue.offer(new int[]{nr, nc}); 
                }
            }
        }
        return false; 
    }
    public int latestDayToCross(int row, int col, int[][] cells) {
        int left = 0, right = row * col, latestPossibleDay = 0;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (isPossible(row, col, mid, cells)) {
                left = mid; 
                latestPossibleDay = mid;
            } else {
                right = mid; 
            }
        }
        return latestPossibleDay;
    }
}