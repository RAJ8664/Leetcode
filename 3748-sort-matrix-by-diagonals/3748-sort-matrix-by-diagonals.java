class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int cr = n - 1, cc = 0;
        while (cr >= 0) {
            int i = cr, j = 0;
            ArrayList<Integer> temp = new ArrayList<>();
            while (i < n && j < m) temp.add(grid[i++][j++]);
            Collections.sort(temp); Collections.reverse(temp);
            i = cr; j = 0;
            int idx = 0;
            while (i < n && j < m) grid[i++][j++] = temp.get(idx++); 
            cr--;
        }
        cr = 0; cc = m - 1;
        while (cc >= 1) {
            int i = cr, j = cc;
            ArrayList<Integer> temp = new ArrayList<>();
            while (i < n && j < m) temp.add(grid[i++][j++]);
            Collections.sort(temp);
            i = 0; j = cc;
            int idx = 0;
            while (i < n && j < m) grid[i++][j++] = temp.get(idx++);
            cc--;
        }
        return grid;
    }
}