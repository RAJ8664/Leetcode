class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int low = 1, high = (int)(1e9), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, piles, h)) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        } 
        return ans;
    }
    private boolean ok(int mid, int arr[], int h) {
        int n = arr.length;
        long total = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] % mid == 0) total += arr[i] / mid;
            else total += (arr[i] / mid) + 1;
        }
        return total <= h;
    }
}