class Solution {
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double maxi = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++)
                    maxi = Math.max(maxi, getArea(points[i], points[j], points[k]));
            }
        }
        return maxi;
    }
    private double getArea(int[] a, int[] b, int[] c) {
        double x1 = a[0], y1 = a[1], x2 = b[0], y2 = b[1], x3 = c[0], y3 = c[1];
        return 0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
    }
}