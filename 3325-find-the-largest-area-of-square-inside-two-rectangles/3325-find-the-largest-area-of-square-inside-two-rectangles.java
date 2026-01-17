class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long maxi = 0;
        for (int i = 0; i < bottomLeft.length; i++) {
            for (int j = i + 1; j < bottomLeft.length; j++) {
                long leftX = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                long rightX = Math.min(topRight[i][0], topRight[j][0]);
                long lowerY = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                long upperY = Math.min(topRight[i][1], topRight[j][1]);
                if (leftX < rightX && lowerY < upperY) {
                    long len = Math.min(rightX - leftX, upperY - lowerY);
                    long area = len * len;
                    maxi = Math.max(maxi, area);
                }
            }
        }
        return maxi;
    }
}