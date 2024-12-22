class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        TreeSet<Integer> set = new TreeSet<>();
        for (int ele : nums) {
            int mini = ele - k, maxi = ele + k;
            if (set.size() == 0 || !set.contains(mini)) set.add(mini);
            else {
                int last = set.getLast();
                if (last + 1 <= maxi) set.add(last + 1);
                else set.add(ele);
            }
        }
        return set.size();
    }
}