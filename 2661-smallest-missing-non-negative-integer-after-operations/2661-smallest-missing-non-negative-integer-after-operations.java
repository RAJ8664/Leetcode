class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums) {
            int curr = ((ele % value) + value) % value;
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        int res = 0;
        while (map.getOrDefault(res % value, 0) > 0) {
            map.put(res % value, map.getOrDefault(res % value, 0) - 1);
            res++;
        } 
        return res;
    }
}