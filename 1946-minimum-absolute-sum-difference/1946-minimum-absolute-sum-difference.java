class Solution {
    private static int mod = (int)(1e9 + 7);
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) set.add(nums1[i]);
        long current_res = 0;
        long current_mini = 0;
        for (int i = 0; i < n; i++) {
            current_res += Math.abs(nums1[i] - nums2[i]);
        }
        current_mini = current_res;
        for (int i = 0; i < n; i++) {
            int second_element = nums2[i];
            if (set.ceiling(second_element) != null) {
                int replace = set.ceiling(second_element);
                long temp = current_res;
                temp -= Math.abs(second_element - nums1[i]);
                temp += Math.abs(second_element - set.ceiling(second_element));
                current_mini = Math.min(current_mini, temp);
            }
            if (set.floor(second_element) != null) {
                int replace = set.floor(second_element);
                long temp = current_res;
                temp -= Math.abs(second_element - nums1[i]);
                temp += Math.abs(second_element - set.floor(second_element));
                current_mini = Math.min(current_mini, temp);
            }
        }
        return (int)(current_mini % mod);
    }
}