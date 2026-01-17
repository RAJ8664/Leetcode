class Solution {
    public int minOperations(int[] nums, int[] target) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i])
                set.add(nums[i]);
        }
        return set.size();
    }
}