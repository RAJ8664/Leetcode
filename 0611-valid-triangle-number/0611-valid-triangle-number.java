class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int low = j + 1, high = n - 1, ansLeft = -1, ansRight = -1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (nums[i] + nums[mid] > nums[j]) {
                        ansLeft = mid;
                        high = mid - 1;
                    }
                    else 
                        low = mid + 1;
                }
                low = j + 1; high = n - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (nums[i] + nums[j] > nums[mid]) {
                        ansRight = mid;
                        low = mid + 1;
                    }
                    else high = mid - 1;
                }
                if (ansLeft == -1 || ansRight == -1) continue; 
                if (ansLeft <= ansRight) {
                    count += (ansRight - ansLeft + 1);
                }
            } 
        }
        return count;
    }
}