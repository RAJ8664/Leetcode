class Solution {
    public int absDifference(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int first = 0, last = 0, idx = n - 1;
        for (int i = 0; i < k; i++) {
            first += nums[i];
            last += nums[idx--];
        }
        return Math.abs(first - last);
    }
}