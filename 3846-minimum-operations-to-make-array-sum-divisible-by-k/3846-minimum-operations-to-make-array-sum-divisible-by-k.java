class Solution {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;
        for (int ele : nums) sum += ele;
        if (sum % k == 0) return 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int current = nums[i];
            if (sum % k == 0) return count;
            while (current > 0 && sum % k != 0) {
                current--;
                count++;
                sum--;
            }
            if (sum % k == 0) return count;
        }
        return -1;
    }
}