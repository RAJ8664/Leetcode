class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int low = 0, high = m - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int maxR = -1, maxEle = -1;
            for (int i = 0; i < n; i++) {
                if (mat[i][mid] > maxEle) {
                    maxEle = mat[i][mid];
                    maxR = i;
                }
            }   

            int left = -1, right = -1;
            if (mid - 1 >= 0) 
                left = mat[maxR][mid - 1];
            if (mid + 1 < m) 
                right = mat[maxR][mid + 1];
            if (mat[maxR][mid] > left && mat[maxR][mid] > right) 
                return new int[]{maxR, mid};
            else {
                if (right > mat[maxR][mid])
                    low = mid + 1;
                else 
                    high = mid - 1;
            } 
        }
        return new int[]{-1, -1};
    }
}