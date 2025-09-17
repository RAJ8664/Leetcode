class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int low = 0, high = m - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int currVal = 0, maxR = -1;
            for (int i = 0; i < n; i++) {
                if (mat[i][mid] > currVal) {
                    currVal = mat[i][mid];
                    maxR = i;
                }
            }
            int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;
            if (mid - 1 >= 0) 
                left = mat[maxR][mid - 1];
            if (mid + 1 < m) 
                right = mat[maxR][mid + 1];
            if (currVal > left && currVal > right) {
                return new int[]{maxR, mid};
            }
            if (right > mat[maxR][mid])
                low = mid + 1;
            else 
                high = mid - 1;
        }
        return new int[]{-1, -1};
    }
}