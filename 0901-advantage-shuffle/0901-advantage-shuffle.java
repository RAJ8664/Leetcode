class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        ArrayList<Integer> res = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums1) {
            set.add(ele);
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int current_ele = nums2[i];
            Integer next = set.higher(current_ele);
            if (next != null) {
                res.add(next);
                map.put(next, map.getOrDefault(next, 0) -1);
                if (map.getOrDefault(next, 0) == 0) set.remove(next);
            }
            else {
                res.add(set.first());
                map.put(set.first(), map.getOrDefault(set.first(), 0) -1);
                if (map.getOrDefault(set.first(), 0) == 0) set.remove(set.first());
            }
        }
        System.out.println(res);
        int ans[] = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}