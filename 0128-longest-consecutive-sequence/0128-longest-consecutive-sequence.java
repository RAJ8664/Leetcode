class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        if (n == 0)
            return 0;
        for (int ele : nums)
            set.add(ele);
        int curr = 1, maxi = 1, prev = set.pollFirst();
        while (set.size() > 0) {
            int next = set.pollFirst();
            if (next == prev + 1) {
                curr++;
                maxi = Math.max(maxi, curr);
                prev = next;
            } else {
                curr = 1; prev = next;
            }
        }
        return maxi;
    }
}