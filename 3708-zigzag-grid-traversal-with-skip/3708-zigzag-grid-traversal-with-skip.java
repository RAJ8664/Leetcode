class Solution {
    public List<Integer> zigzagTraversal(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        List<Integer> res = new ArrayList<>();
        boolean skipped = true;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 1) {
                for (int j = m - 1; j >= 0; j--) {
                    if (skipped == true) res.add(grid[i][j]);
                    skipped = !skipped;
                }
            }
            else {
                for (int j = 0; j < m; j++) {
                    if (skipped == true) res.add(grid[i][j]);
                    skipped = !skipped;
                }    
            }
        }
        return res;
    }
}