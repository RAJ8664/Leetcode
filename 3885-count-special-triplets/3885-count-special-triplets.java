import java.util.HashMap;
class Solution {
    private int mod = (int)(1e9 + 7);
    public int specialTriplets(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        HashMap<Integer, Integer> pref_map = new HashMap<>();
        long count = 0;
        for (int i = 0; i < n; i++) {
            pref_map.put(nums[i], pref_map.getOrDefault(nums[i], 0) + 1);
            if (nums[i] == 0) {
                int left_count = pref_map.getOrDefault(0, 0) - 1;
                int right_count = map.getOrDefault(0, 0) - pref_map.getOrDefault(0, 0);
                count = (count + (left_count * 1L *  right_count) % mod) % mod;
                continue;
            }
            int left_count = pref_map.getOrDefault(nums[i] * 2, 0);
            int right_count = map.getOrDefault(nums[i] * 2, 0) - left_count;
            count = (count + (left_count * 1L * right_count) % mod) % mod;
        }
        return (int)(count);
    }
}