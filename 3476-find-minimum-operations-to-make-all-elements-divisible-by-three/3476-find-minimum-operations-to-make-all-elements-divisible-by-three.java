class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 3 == 0) continue;
            if (nums[i] % 3 == 1 || nums[i] % 3 == 2) 
                sum += 1;
        }
        return sum;
    }
}