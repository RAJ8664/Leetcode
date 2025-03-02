class Solution {
static public int countArrays(int[] original, int[][] bounds) {
        int n = original.length, x = bounds[0][0], y = bounds[0][1];
        for (int i = 1; i < n; i++) {
            int a = bounds[i][0], b = bounds[i][1], diff = original[i] - original[i - 1];
            x += diff; 
            y += diff;
            x = Math.max(x, a);
            y = Math.min(y, b);
        }
        return Math.max(y - x + 1, 0);
    }
}