class Solution {
    public int largestPerimeter(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i], y = nums[i + 1], z = nums[i + 2];
            if (x + y > z && x + z > y && y + z > x)
                ans = Math.max(ans, x + y + z);
        }
        return ans;
    }
}