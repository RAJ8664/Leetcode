class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int low = 0, high = m - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int maxEle = 0, maxR = -1;
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
            if (maxEle > left && maxEle > right)
                return new int[]{maxR, mid};
            if (left > maxEle)
                high = mid - 1;
            else 
                low = mid + 1;
        }
        return new int[]{-1, -1};
    }
}