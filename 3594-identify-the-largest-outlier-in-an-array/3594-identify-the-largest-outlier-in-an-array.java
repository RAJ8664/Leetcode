class Solution {
    public int getLargestOutlier(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int ele : nums) sum += ele;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        int res = Integer.MIN_VALUE;
        for (int ele : nums) {
            map.put(ele, map.getOrDefault(ele, 0) -1);
            int new_sum = sum - ele;
            if (new_sum % 2 == 0 && map.getOrDefault(new_sum / 2, 0) > 0) res = Math.max(res, ele);
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        return res;
    }
}