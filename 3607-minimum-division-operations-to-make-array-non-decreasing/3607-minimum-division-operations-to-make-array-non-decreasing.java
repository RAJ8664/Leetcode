class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) continue;
            int find = solve(nums[i]);
            if (find <= 1) return -1;
            nums[i] = find;
            if (nums[i] > nums[i + 1]) return -1;
            res++;
        }
        return res;
    }

    private int solve(int current) {
        if (current <= 1) return 0;
        for (int i = 2; i * i <= current; i++) {
            if (current % i == 0) {
                if (i < current) return i;
                if (current / i < current) return current / i;
            }
        }
        return 0;
    }
}