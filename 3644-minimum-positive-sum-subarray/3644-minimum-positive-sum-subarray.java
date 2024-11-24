class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int mini = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            mini = Math.min(mini, solve(nums, i));
        }
        if (mini == Integer.MAX_VALUE) return -1;
        return mini;
    }
    private int solve(List<Integer> arr, int k) {
        int n = arr.size();
        int sum = 0, mini = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) sum += arr.get(i);
        if (sum > 0) mini = Math.min(mini, sum);
        int start = 0;
        for (int i = k; i < n; i++) {
            sum -= arr.get(start);
            sum += arr.get(i);
            if (sum > 0) mini = Math.min(mini, sum);
            start++;
        }
        return mini;
    }
}