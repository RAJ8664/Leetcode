class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int count = 0, right = 0;
        for (int i = 0; i < n - 1; i++) {
            if ((nums[right] < nums[i] && nums[i] > nums[i + 1]) || (nums[right] > nums[i] && nums[i] < nums[i + 1])) {
                count++;
                right = i;
            } 
        }
        return count;
    }
}