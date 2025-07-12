import java.util.Arrays;

class Solution {
    private int rowPref[][];
    private int colPref[][];
    private int arr[][];
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        rowPref = new int[n + 1][n + 1];
        colPref = new int[n + 1][n + 1];

        arr = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                arr[i][j] = 1;
        }
        for (int curr[] : mines)
            arr[curr[0]][curr[1]] = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += arr[i][j];
                rowPref[i][j] = sum;
            }
        }

        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += arr[i][j];
                colPref[j][i] = sum;
            }
        }

        int low = 1, high = n, ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, n)) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
    private boolean ok(int k, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    int rightSum = 0, leftSum = 0, upSum = 0, downSum = 0;

                    rightSum += rowPref[i][Math.min(j + k - 1, n - 1)];
                    if (j - 1 >= 0)
                        rightSum -= rowPref[i][j - 1];

                    leftSum += rowPref[i][j];
                    if (j - k >= 0)
                        leftSum -= rowPref[i][j - k];

                    upSum += colPref[j][i];
                    if (i - k >= 0)
                        upSum -= colPref[j][i - k];

                    downSum += colPref[j][Math.min(i + k - 1, n - 1)];
                    if (i - 1 >= 0)
                        downSum -= colPref[j][i - 1];
                    if (rightSum >= k && leftSum >= k && upSum >= k && downSum >= k)
                        return true;
                }
            }
        }
        return false;
    }
}