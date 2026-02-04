class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long sum = 0, maxSum = Long.MIN_VALUE / 10, currSum = 0, turn = 0;
        for (int i = 1; i < n; i++) {
            if (turn == 0) {
                if (nums[i - 1] < nums[i]) {
                    sum += nums[i] + nums[i - 1];
                    turn = 1;
                }
            } else if (turn == 1) {
                if (nums[i - 1] < nums[i]) 
                    sum = Math.max(sum + nums[i], nums[i] + nums[i - 1]);
                else if (nums[i - 1] == nums[i]) {
                    sum = 0;
                    turn = 0;
                } else {
                    sum = sum + nums[i];
                    turn = 2;
                }
            } else if (turn == 2) {
                currSum = 0;
                if (nums[i - 1] > nums[i]) 
                    sum += nums[i];
                else if (nums[i - 1] == nums[i]) {
                    sum = 0;
                    turn = 0;
                } else {
                    sum = sum + nums[i];
                    maxSum = Math.max(maxSum, sum);
                    currSum = currSum + nums[i - 1] + nums[i];
                    turn = 3;
                }
            } else {
                if (nums[i - 1] < nums[i]) {
                    sum += nums[i];
                    currSum = Math.max(currSum + nums[i], nums[i] + nums[i - 1]);
                    maxSum = Math.max(maxSum, sum);
                } else if (nums[i - 1] == nums[i]) {
                    sum = 0;
                    turn = 0;
                } else {
                    sum = currSum + nums[i];
                    turn = 2;
                }
            }
        }
        return maxSum;
    }
}