class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            if (check(nums, i, i + k - 1)) res[i] = nums[i + k - 1];
            else res[i] = -1;
        }
        return res;
    }
    
   static boolean check(int[] nums, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] != nums[i - 1] + 1) return false;
        }
        return true;
    }
}