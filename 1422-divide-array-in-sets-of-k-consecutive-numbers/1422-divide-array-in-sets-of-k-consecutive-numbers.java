class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0)
            return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int ele : nums) {
            set.add(ele);
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        while (map.size() > 0) {
            int current = set.first();
            for (int i = 0; i < k; i++) {
                if (!map.containsKey(current))
                    return false;
                else {
                    map.put(current, map.getOrDefault(current, 0) -1);
                    if (map.getOrDefault(current, 0) == 0) {
                        map.remove(current);
                        set.remove(current);
                    }
                }
                current++;
            }
        }
        return true;
    }
}