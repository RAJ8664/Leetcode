class Solution {
    public int[] maxKDistinct(int[] nums, int k) {
        int n = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int ele : nums)
            set.add(ele);
        int res[] = new int[Math.min(k, set.size())];
        int idx = 0;
        while (k > 0 && set.size() > 0) {
            Integer curr = set.last();
            res[idx++] = curr;
            set.remove(curr);
            k--;
        }
        return res;
    }
}