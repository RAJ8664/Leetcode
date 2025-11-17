class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                if (prev == -1)
                    prev = i;
                else if (i - prev <= k) {
                    return false;
                } else 
                    prev = i;
            }
            else 
                continue;
        }
        return true;
    }
}