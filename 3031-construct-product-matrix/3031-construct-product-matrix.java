class Solution {
    static int mod = 12345;
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans[][] = new int[n][m];
        
        ArrayList<Integer> ele = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                grid[i][j] %= mod;
                ele.add(grid[i][j]);
            }
        }

        int pref[] = new int[n * m];
        int suff[] = new int[n * m];

        int prod = 1;
        for (int i = 0; i < ele.size(); i++) {
            prod *= ele.get(i);
            prod %= mod;
            pref[i] = prod;
        }

        prod = 1;
        for (int i = ele.size() - 1; i >= 0; i--) {
            prod *= ele.get(i);
            prod %= mod;
            suff[i] = prod;
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < n * m; i++) {
            int current = 1;
            if (i + 1 < m * n) 
                current = suff[i + 1];
            if (i - 1 >= 0) 
                current *= pref[i - 1];
            current %= mod;
            res.add(current);
        }

        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) 
                ans[i][j] = res.get(k++);
        }
        return ans;
    }
}