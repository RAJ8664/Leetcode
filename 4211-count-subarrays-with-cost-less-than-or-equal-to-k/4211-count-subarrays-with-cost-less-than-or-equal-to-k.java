import java.util.*;

class Solution {
    int[][] maxST;
    int[][] minST;
    int[] logs;

    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        buildSparseTable(nums);
        long count = 0;
        for (int i = 0; i < n; i++) {
            int left = i, right = n - 1;
            int bestJ = i;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (getCost(i, mid, nums) <= k) {
                    bestJ = mid;
                    left = mid + 1;
                } else
                    right = mid - 1;
            }
            count += (bestJ - i + 1);
        }
        return count;
    }

    private void buildSparseTable(int[] nums) {
        int n = nums.length;
        int maxLog = (int)(Math.log(n) / Math.log(2)) + 1;
        maxST = new int[n][maxLog];
        minST = new int[n][maxLog];
        logs = new int[n + 1];
        for (int i = 2; i <= n; i++)
            logs[i] = logs[i / 2] + 1;
        for (int i = 0; i < n; i++) {
            maxST[i][0] = nums[i];
            minST[i][0] = nums[i];
        }
        for (int j = 1; j < maxLog; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                maxST[i][j] = Math.max(maxST[i][j - 1], maxST[i + (1 << (j - 1))][j - 1]);
                minST[i][j] = Math.min(minST[i][j - 1], minST[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    private long getCost(int l, int r, int[] nums) {
        int j = logs[r - l + 1];
        int maxVal = Math.max(maxST[l][j], maxST[r - (1 << j) + 1][j]);
        int minVal = Math.min(minST[l][j], minST[r - (1 << j) + 1][j]);
        return (long)(maxVal - minVal) * (r - l + 1);
    }
}