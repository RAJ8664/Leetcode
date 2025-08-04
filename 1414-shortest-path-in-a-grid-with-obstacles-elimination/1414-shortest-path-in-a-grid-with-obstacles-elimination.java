import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    static class Tuple {
        int row, col, count;
        public Tuple(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
        @Override
        public String toString() {
            return "Tuple{" +
                   "row=" + row +
                   ", col=" + col +
                   ", count=" + count +
                   '}';
        }
    }
    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.count, second.count);
        }
    }
    public int shortestPath(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        int dist[][][] = new int[n + 1][m + 1][k + 1];
        for (int current[][] : dist)
            for (int current1[] : current)
                Arrays.fill(current1, (int)(1e9));

        int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new customSort());
        if (grid[0][0] == 1)
            dist[0][0][1] = 0;
        else
            dist[0][0][0] = 0;

        pq.offer(new Tuple(0, 0, grid[0][0]));
        while (pq.size() > 0) {
            int currRow = pq.peek().row, currCol = pq.peek().col, currCount = pq.peek().count;
            pq.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m) {
                    if (grid[newRow][newCol] == 1) {
                        if (currCount < k) {
                            if (dist[newRow][newCol][currCount + 1] > dist[currRow][currCol][currCount] + 1) {
                                dist[newRow][newCol][currCount + 1] = dist[currRow][currCol][currCount] + 1;
                                pq.offer(new Tuple(newRow, newCol, currCount + 1));
                            }
                        }
                    } else {
                        if (dist[newRow][newCol][currCount] > dist[currRow][currCol][currCount] + 1) {
                            dist[newRow][newCol][currCount] = dist[currRow][currCol][currCount] + 1;
                            pq.offer(new Tuple(newRow, newCol, currCount));
                        }
                    }
                }
            }
        }
        int mini = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++)
            mini = Math.min(mini, dist[n - 1][m - 1][i]);
        if (mini == (int)(1e9))
            return -1;
        return mini;
    }
}