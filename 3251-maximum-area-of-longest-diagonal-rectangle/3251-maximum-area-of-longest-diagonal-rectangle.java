class Solution {
    public int areaOfMaxDiagonal(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int maxi = Integer.MIN_VALUE;
        int currMaxDiagonal = Integer.MIN_VALUE / 10;
        for (int i = 0; i < n; i++) {
            int diagonal = (arr[i][0] * arr[i][0] + arr[i][1] * arr[i][1]);
            if (diagonal > currMaxDiagonal) {
                maxi = arr[i][1] * arr[i][0];
                currMaxDiagonal = diagonal;
            }
            else if (diagonal == currMaxDiagonal) {
                if (arr[i][0] * arr[i][1] > maxi) {
                    maxi = arr[i][0] * arr[i][1];
                }
            }
        }
        return maxi;
    }
}