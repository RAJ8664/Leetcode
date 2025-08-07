import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
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
    public int maxCollectedFruits(int[][] arr) {
        int n = arr.length;
        int res = 0;
        for (int i = 0; i < n; i++)
            res += arr[i][i];
        res += solveTopRight(arr) + solveBottomLeft(arr) - 2 * arr[n - 1][n - 1];
        return res;
    }
    private int solveTopRight(int arr[][]) {
        int n = arr.length;
        int dp[][] = new int[n + 1][n + 1];
        for (int current[] : dp)
            Arrays.fill(current, (int)(-1e9));
        dp[0][n - 1] = arr[0][n - 1];
        int dir[][] = {{1, -1}, {1, 0}, {1, 1}};
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, n - 1));
        while (q.size() > 0) {
            int currRow = q.peek().row, currCol = q.peek().col;
            q.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                    if (newRow == newCol && newRow != n - 1 && newCol != n - 1)
                        continue;
                    if (dp[newRow][newCol] < dp[currRow][currCol] + arr[newRow][newCol]) {
                        dp[newRow][newCol] = dp[currRow][currCol] + arr[newRow][newCol];
                        q.offer(new Pair(newRow, newCol));
                    }
                }
            }
        }
        return dp[n - 1][n - 1];
    }
    private int solveBottomLeft(int arr[][]) {
        int n = arr.length;
        int dp[][] = new int[n + 1][n + 1];
        for (int current[] : dp)
            Arrays.fill(current, (int)(-1e9));
        dp[n - 1][0] = arr[n - 1][0];
        int dir[][] = {{-1, 1}, {0, 1}, {1, 1}};
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(n - 1, 0));
        while (q.size() > 0) {
            int currRow = q.peek().row, currCol = q.peek().col;
            q.poll();
            for (int dire[] : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                    if (newRow == newCol && newRow != n - 1 && newCol != n - 1)
                        continue;
                    if (dp[newRow][newCol] < dp[currRow][currCol] + arr[newRow][newCol]) {
                        dp[newRow][newCol] = dp[currRow][currCol] + arr[newRow][newCol];
                        q.offer(new Pair(newRow, newCol));
                    }
                }
            }
        }
        return dp[n - 1][n - 1];
    }
}