class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        int res[][] = new int[n - k + 1][m - k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                if (i + k - 1 >= n || j + k - 1 >= m) continue;
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        set.add(grid[x][y]);
                    }
                }
                if (set.size() <= 1) res[i][j] = 0;
                else {
                    ArrayList<Integer> temp = new ArrayList<>();
                    while (set.size() > 0) temp.add(set.pollFirst());
                    int mini = Integer.MAX_VALUE / 10;
                    for (int p = 0; p < temp.size() - 1; p++) mini = Math.min(mini, Math.abs(temp.get(p) - temp.get(p + 1)));
                    res[i][j] = mini;
                }
            }
        }
        return res;
    }
}