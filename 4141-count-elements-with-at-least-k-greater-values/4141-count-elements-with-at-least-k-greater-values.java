class Solution {
    public int countElements(int[] nums, int k) {
        int n = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        int count = 0, total = n;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        for (int ele : nums)
            set.add(ele);
        while (set.size() > 0) {
            int current = set.pollFirst();
            total -= map.getOrDefault(current, 0);
            if (total >= k)
                count += map.getOrDefault(current, 0);
        }    
        return count;
    }
}