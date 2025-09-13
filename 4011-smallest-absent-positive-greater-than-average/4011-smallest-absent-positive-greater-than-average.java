class Solution {
    public int smallestAbsent(int[] nums) {
        int n = nums.length;
        int sum = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums) {
            set.add(ele);
            sum += ele;
        }
        int idx = 1;
        while (true) {
            if (idx * n > sum) {
                if (set.contains(idx)) {
                    idx++;
                    continue;
                }
                return idx;
            }
            idx++;
        }
    }
}