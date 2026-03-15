class Solution {
    public int minCost(int[] nums1, int[] nums2) {
        int n = nums1.length;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums1) {
            map1.put(ele, map1.getOrDefault(ele, 0) + 1);
            set.add(ele); 
        }
        for (int ele : nums2) {
            map2.put(ele, map2.getOrDefault(ele, 0) + 1);
            set.add(ele); 
        }
        for (Map.Entry<Integer, Integer> curr : map1.entrySet()) {
            int key = curr.getKey(), val = curr.getValue();
            if ((val + map2.getOrDefault(key, 0)) % 2 == 1)
                return -1;
        }
        for (Map.Entry<Integer, Integer> curr : map2.entrySet()) {
            int key = curr.getKey(), val = curr.getValue();
            if ((val + map1.getOrDefault(key, 0)) % 2 == 1)
                return -1;
        }
        int diff = 0, res = 0;
        for (int ele : set) {
            int freq = map1.getOrDefault(ele, 0) + map2.getOrDefault(ele, 0);
            freq /= 2;
            if (map1.getOrDefault(ele, 0) == freq) 
                continue;
            diff += (map1.getOrDefault(ele, 0) - freq);
            res += Math.abs(map1.getOrDefault(ele, 0) - freq); 
        }
        if (diff == 0) return res / 2;
        return -1;
    }
}