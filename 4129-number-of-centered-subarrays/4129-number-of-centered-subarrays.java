class Solution {
    public int centeredSubarrays(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>();
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                set.add(nums[j]);
                if (set.contains(sum))
                    count++;
            }
        }
        return count;
    }
}