class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int idx = 1;
        while (idx < nums.length && nums[idx] > nums[idx - 1])
            idx++;
        if (idx == 1 || idx == nums.length) return false;
        while (idx < nums.length && nums[idx] < nums[idx - 1])
            idx++;
        if (idx == nums.length) return false;
        while (idx < nums.length && nums[idx] > nums[idx - 1])
            idx++;
        return idx == nums.length ? true : false;
    }
}