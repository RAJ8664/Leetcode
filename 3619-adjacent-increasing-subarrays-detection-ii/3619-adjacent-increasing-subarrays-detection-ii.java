class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int isincreasing[] = new int[n];
        Arrays.fill(isincreasing, 1);
        for (int i = n - 2; i >= 0; i--) {
            if (nums.get(i) < nums.get(i + 1)) isincreasing[i] = isincreasing[i + 1] + 1;
        }
        int low = 0;
        int high = n / 2 + 1;
        int ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean flag = false;
            for (int i = 0; i + 2 * mid <= n; i++) {
                if (isincreasing[i] >= mid && isincreasing[i + mid] >= mid) {
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }

    private boolean check(List<Integer> nums, int start, int end, int k) {
        int n = nums.size();
        int prev = -2000;        
        for (int i = start; i < start + k; i++) {
            if (prev == -2000) {
                prev = nums.get(i);
            }
            else {
                if (nums.get(i) <= prev) return false;
                prev = nums.get(i);
            }
        }
        prev = -2000;
        for (int i = start + k; i < end; i++) {
            if (prev == -2000) {
                prev = nums.get(i);
            }
            else {
                if (nums.get(i) <= prev) return false;
                prev = nums.get(i);
            }
        }
        return true;
    }
}