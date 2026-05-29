class Solution {
    public int minElement(int[] nums) {
        int n = nums.length;
        int mini = Integer.MAX_VALUE;
        for (int ele : nums) {
            int current_sum = find(ele);
            mini = Math.min(mini, current_sum);
        }
        return mini;
    }
    
    private static int find(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}