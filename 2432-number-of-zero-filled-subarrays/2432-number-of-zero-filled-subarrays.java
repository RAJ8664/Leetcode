class Solution {
    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;
        int prev = -1, current = -1;
        long count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                if (prev == -1) continue;
                int len = current - prev + 1;
                current = -1; prev = -1;
                count += (len * 1L * (len + 1)) / 2;
            }
            if (nums[i] == 0) {
                if (prev == -1) {
                    prev = i;
                    current = i;
                }
                else 
                    current = i;
            }
        }      
        if (prev != -1) {
            int len = n - prev;
            count += (len * 1L * (len + 1)) / 2;
        }
        return count;
    }
}