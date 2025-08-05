class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int current = nums[i];
            if (set.contains(current)) continue;
            Integer next = set.higher(current);
            if (next != null) {
                set.remove(next);
                set.add(current);
            }
            else set.add(current);
        }
        return set.size();
    }
}