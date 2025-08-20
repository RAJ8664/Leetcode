class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int mini = Integer.MAX_VALUE;
        int i = 0, j = 0, currSum = 0;
        while (i < n) {
            while (j < n && currSum < target) {
                currSum += nums[j++]; 
            }
            if (currSum >= target) {
                mini = Math.min(mini, j - i);
            }
            currSum -= nums[i];
            i++;
        }
        if (mini == Integer.MAX_VALUE) 
            return 0;
        return mini;
    }
    private boolean ok(int mid, int arr[], int target) {
        int n = arr.length;
        int currSum = 0;
        for (int i = 0; i < mid; i++) {
            currSum += arr[i]; 
        }
        if (currSum >= target) 
            return true;
        int start = 0;
        for (int i = mid; i < n; i++) {
            currSum -= arr[start++];
            currSum += arr[i];
            if (currSum >= target) 
                return true;
        }
        return false;
    }
}