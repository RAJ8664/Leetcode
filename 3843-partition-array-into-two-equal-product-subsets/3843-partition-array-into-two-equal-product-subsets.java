class Solution {
    public boolean checkEqualPartitions(int[] nums, long target) {
        int n = nums.length;
        int total = 1 << n;
        for (int mask = 1; mask < total - 1; mask++) { 
            long prod1 = 1;
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    prod1 *= nums[i];
                    if (prod1 > target) {
                        valid = false;
                        break;
                    }
                }
            }
            if (!valid || prod1 != target) continue;
            long prod2 = 1;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    prod2 *= nums[i];
                    if (prod2 > target) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid && prod2 == target) return true;
        }
        return false;
    }
}