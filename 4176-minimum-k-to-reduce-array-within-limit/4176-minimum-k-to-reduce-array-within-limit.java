class Solution {
    public int minimumK(int[] nums) {
        int n = nums.length;
        long low = 1, high = (int)(10000), ans = -1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (ok(mid, nums)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return (int)(ans);
    }

    private boolean ok(long k, int nums[]) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0)
                continue;
            res += nums[i] / k * 1L + (nums[i] % k == 0 ? 0 : 1);
        }
        return res <= k * 1L * k;
    }
}