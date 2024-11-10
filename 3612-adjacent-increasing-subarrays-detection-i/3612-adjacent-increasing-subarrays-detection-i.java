class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            if ((i + 2 * k <= n) && check(nums, i , i + 2 * k, k)) return true;
        }
        return false;
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
            System.out.println(nums.get(i));
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