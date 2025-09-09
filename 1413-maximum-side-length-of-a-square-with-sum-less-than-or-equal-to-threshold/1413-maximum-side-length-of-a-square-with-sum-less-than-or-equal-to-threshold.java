class Solution {
    private int pref[][];
    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length, m = mat[0].length;

        buildPref(mat);
        
        int low = 0, high = Math.min(n, m), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(n, m, mid, threshold)) {
                ans = mid ;
                low = mid + 1;
            }
            else 
                high = mid - 1;
        } 
        return ans + 1;
    }
    private boolean ok(int n, int m, int target, int threshold) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + target < n && j + target < m) {
                    int r1 = i, c1 = j, r2 = i + target, c2 = j + target;
                    if (query(r1, c1, r2, c2) <= threshold) 
                        return true;
                }
            }
        }
        return false;
    }
    private void buildPref(int arr[][]) {
        int n = arr.length, m = arr[0].length;
        pref = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i - 1 >= 0) 
                    pref[i][j] += pref[i - 1][j];
                if (j - 1 >= 0) 
                    pref[i][j] += pref[i][j - 1];
                if (i - 1 >= 0 && j - 1 >= 0) 
                    pref[i][j] -= pref[i - 1][j - 1];
                pref[i][j] += arr[i][j];
            }
        }
    }
    private int query(int r1, int c1, int r2, int c2) {
        int total = pref[r2][c2];
        if (r1 - 1 >= 0)
            total -= pref[r1 - 1][c2];
        if (c1 - 1 >= 0)
            total -= pref[r2][c1 - 1];
        if (r1 - 1 >= 0 && c1 - 1 >= 0)
            total += pref[r1 - 1][c1 - 1];
        return total;
    }
}