class Solution {
    public int maxSum(int[] nums) {
        int n = nums.length;
        int res = 0;
        boolean flag = false;
        for (int ele : nums) {
            if (ele >= 0) flag = true;
        }
        if (flag == false) {
            Arrays.sort(nums);
            return nums[n - 1];
        }
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums) {
            if (!set.contains(ele) && ele >= 0) {
                set.add(ele);
                res += ele;
            }
        }
        return res;
    }
}